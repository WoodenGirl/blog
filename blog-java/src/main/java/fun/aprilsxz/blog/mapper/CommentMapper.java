package fun.aprilsxz.blog.mapper;

import fun.aprilsxz.blog.domain.po.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import fun.aprilsxz.blog.domain.vo.CommentVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author yang
* @description 针对表【comment】的数据库操作Mapper
* @createDate 2024-12-11 14:25:54
* @Entity fun.aprilsxz.blog.domain.po.Comment
*/
public interface CommentMapper extends BaseMapper<Comment> {

    List<CommentVO> queryComment(String linkedId);

    List<CommentVO> selectBatchParentIds(@Param("ids") List<Integer> ids);
}




