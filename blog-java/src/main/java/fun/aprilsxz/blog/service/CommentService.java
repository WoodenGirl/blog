package fun.aprilsxz.blog.service;

import fun.aprilsxz.blog.domain.common.PageResult;
import fun.aprilsxz.blog.domain.po.Comment;
import com.baomidou.mybatisplus.extension.service.IService;
import fun.aprilsxz.blog.domain.vo.CommentVO;

/**
* @author yang
* @description 针对表【comment】的数据库操作Service
* @createDate 2024-12-11 14:25:54
*/
public interface CommentService extends IService<Comment> {

    PageResult<CommentVO> queryComment(String linkId, Integer currentPage, Integer pageSize);

    void deleteComment(Integer commentId);
}
