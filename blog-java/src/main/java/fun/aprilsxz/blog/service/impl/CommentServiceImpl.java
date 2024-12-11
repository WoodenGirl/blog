package fun.aprilsxz.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fun.aprilsxz.blog.domain.po.Comment;
import fun.aprilsxz.blog.service.CommentService;
import fun.aprilsxz.blog.mapper.CommentMapper;
import org.springframework.stereotype.Service;

/**
* @author yang
* @description 针对表【comment】的数据库操作Service实现
* @createDate 2024-12-11 14:25:54
*/
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment>
    implements CommentService{

}




