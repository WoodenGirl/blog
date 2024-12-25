package fun.aprilsxz.blog.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.UUID;
import cn.hutool.crypto.digest.MD5;
import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fun.aprilsxz.blog.config.JwtProperties;
import fun.aprilsxz.blog.domain.common.Result;
import fun.aprilsxz.blog.domain.dto.LoginFormDTO;
import fun.aprilsxz.blog.domain.po.User;
import fun.aprilsxz.blog.domain.vo.UserLoginVO;
import fun.aprilsxz.blog.enums.UserStatus;
import fun.aprilsxz.blog.exception.BadRequestException;
import fun.aprilsxz.blog.exception.CommonException;
import fun.aprilsxz.blog.exception.ForbiddenException;
import fun.aprilsxz.blog.service.UserService;
import fun.aprilsxz.blog.mapper.UserMapper;
import fun.aprilsxz.blog.utils.JwtTool;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;

/**
* @author yang
* @description 针对表【user】的数据库操作Service实现
* @createDate 2024-12-14 16:18:02
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    @Resource
    private PasswordEncoder passwordEncoder;
    @Resource
    private JwtTool jwtTool;
    @Resource
    private JwtProperties jwtProperties;
    @Resource
    private UserMapper userMapper;

    @Override
    public UserLoginVO login(LoginFormDTO loginDTO) {
        // 1.数据校验
        String username = loginDTO.getUsername();
        String password = loginDTO.getPassword();
        // 2.根据用户名或手机号查询
        User user = lambdaQuery().eq(User::getNickname, username).one();
        Assert.notNull(user, "用户名错误");
        // 3.校验是否禁用
        if (user.getStatus() == UserStatus.FROZEN) {
            throw new ForbiddenException("用户被冻结");
        }
        // 4.校验密码
        String s = MD5.create().setSalt(user.getSalt().getBytes(StandardCharsets.UTF_8)).digestHex(password);
        if (!Objects.equals(s, user.getPassword())) {
            throw new BadRequestException("用户名或密码错误");
        }
        // 5.生成TOKEN
        String token = jwtTool.createToken(user.getUserId(), jwtProperties.getTokenTTL());
        // 6.封装VO返回
        UserLoginVO userLoginVO = BeanUtil.copyProperties(user, UserLoginVO.class);
        userLoginVO.setToken(token);
        return userLoginVO;
    }

    @Override
    public UserLoginVO register(LoginFormDTO loginDTO) {
        String username = loginDTO.getUsername();
        String password = loginDTO.getPassword();

        User user = lambdaQuery().eq(User::getNickname, username).one();
        if(user != null){
            throw new CommonException("用户名重复", 400);
        }

        String salt = UUID.randomUUID().toString();
        String password1 = MD5.create().setSalt(salt.getBytes(StandardCharsets.UTF_8)).digestHex(password);

        user = User.builder().userId(UUID.randomUUID().toString())
                .salt(salt)
                .password(password1)
                .build();
        userMapper.save(user);

        return UserLoginVO.builder().userId(user.getUserId())
                .nickname(username)
                .token(jwtTool.createToken(user.getUserId(), jwtProperties.getTokenTTL()))
                .build();
    }

    /**
     * qq登录，如果不存在用户，默认创建用户
     * @param code
     * @return
     */
    @Override
    public UserLoginVO qqLogin(String code) {
        //1.获取access_token
        HashMap<String, String> map = new HashMap<>();
        map.put("grant_type","authorization_code");
        map.put("client_id","102569233");
        map.put("client_secret","a2FCzXqthGdonY5P");
        //TODO 回调地址
        map.put("redirect_uri","");
        map.put("fmt","json");
        map.put("code",code);
        String body = HttpRequest.get(mapToParam("https://graph.qq.com/oauth2.0/token",map))
                .execute().body();
        JSONObject jsonObject = JSON.parseObject(body);
        String access_token = (String)jsonObject.get("access_token");
        //2.通过access_token获取用户OpenId
        map.clear();
        map.put("fmt","json");
        map.put("access_token",access_token);
        String body1 = HttpRequest.get(mapToParam("https://graph.qq.com/oauth2.0/me",map))
                .execute().body();
        JSONObject jsonObject1 = JSON.parseObject(body1);
        String openid = (String)jsonObject1.get("openid");
        //3.根据openid查询用户是否存在
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("openid", openid));
        //4.存在直接返回
        if(user != null){
            return UserLoginVO.builder().userId(user.getUserId())
                    .nickname(user.getNickname())
                    .token(jwtTool.createToken(user.getUserId(), jwtProperties.getTokenTTL()))
                    .build();
        }
        //5.不存在，通过openId获取用户信息（头像和用户名），注册为新用户
        map.clear();
        map.put("access_token",access_token);
        map.put("oauth_consumer_key","102569233");
        map.put("openid",openid);
        String body2 = HttpRequest.get(mapToParam("https://graph.qq.com/user/get_user_info", map))
                .execute().body();
        JSONObject jsonObject2 = JSON.parseObject(body2);
        String nickname = (String) jsonObject2.get("nickname");
        String figureurl_qq_1 = (String) jsonObject2.get("figureurl_qq_1");//40x40像素的头像
        //6.创建用户
        user = User.builder()
                .userId(UUID.randomUUID().toString())
                .nickname(nickname)
                .avatar(figureurl_qq_1)
                .build();
        userMapper.save(user);

        return UserLoginVO.builder().userId(user.getUserId())
                .nickname(user.getNickname())
                .token(jwtTool.createToken(user.getUserId(), jwtProperties.getTokenTTL()))
                .build();
    }

    private String mapToParam(String url, Map<String,String> map){
        if(map == null || map.size() == 0){
            return url;
        }
        StringBuilder urlBuilder = new StringBuilder(url + "?");
        map.forEach(new BiConsumer<String, String>() {
            @Override
            public void accept(String s, String s2) {
                urlBuilder.append(s).append("=").append(s2).append("&");
            }
        });

        urlBuilder.deleteCharAt(urlBuilder.length()-1);
        url = urlBuilder.toString();
        return url;
    }
}




