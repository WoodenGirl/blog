package fun.aprilsxz.blog.mapper;

import fun.aprilsxz.blog.domain.po.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author yang
* @description 针对表【user】的数据库操作Mapper
* @createDate 2024-12-14 16:18:02
* @Entity fun.aprilsxz.blog.domain.po.User
*/
public interface UserMapper extends BaseMapper<User> {

    void save(User user);
}



