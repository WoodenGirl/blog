package fun.aprilsxz.blog.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.UUID;
import cn.hutool.crypto.digest.MD5;
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

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

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
}




