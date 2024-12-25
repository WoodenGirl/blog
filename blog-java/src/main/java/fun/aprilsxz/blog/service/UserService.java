package fun.aprilsxz.blog.service;

import fun.aprilsxz.blog.domain.common.Result;
import fun.aprilsxz.blog.domain.dto.LoginFormDTO;
import fun.aprilsxz.blog.domain.po.User;
import com.baomidou.mybatisplus.extension.service.IService;
import fun.aprilsxz.blog.domain.vo.UserLoginVO;

/**
* @author yang
* @description 针对表【user】的数据库操作Service
* @createDate 2024-12-14 16:18:02
*/
public interface UserService extends IService<User> {

    UserLoginVO login(LoginFormDTO loginFormDTO);

    UserLoginVO register(LoginFormDTO loginFormDTO);

    UserLoginVO qqLogin(String code);
}
