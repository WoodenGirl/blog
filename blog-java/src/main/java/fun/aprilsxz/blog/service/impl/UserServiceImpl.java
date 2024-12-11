package fun.aprilsxz.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fun.aprilsxz.blog.domain.po.User;
import fun.aprilsxz.blog.service.UserService;
import fun.aprilsxz.blog.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author yang
* @description 针对表【user】的数据库操作Service实现
* @createDate 2024-12-11 14:26:00
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




