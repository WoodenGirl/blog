package fun.aprilsxz.blog.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import fun.aprilsxz.blog.domain.common.PageResult;
import fun.aprilsxz.blog.domain.po.Comment;
import fun.aprilsxz.blog.domain.vo.CommentVO;
import fun.aprilsxz.blog.mapper.CommentMapper;
import fun.aprilsxz.blog.service.CommentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
* @author yang
* @description 针对表【comment】的数据库操作Service实现
* @createDate 2024-12-11 14:25:54
*/
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment>
    implements CommentService{
    @Resource
    CommentMapper commentMapper;

    @Override
    public PageResult<CommentVO> queryComment(String linkId, Integer currentPage, Integer pageSize) {
        PageHelper.startPage(currentPage,pageSize);
        //1.查询顶级评论
        Page<CommentVO> commentPage = (Page<CommentVO>)commentMapper.queryComment(linkId);
        List<CommentVO> commentVOS = commentPage.getResult();
        if(CollUtil.isEmpty(commentVOS)){
            return null;
        }

        //2.查询对应的子评论
        List<Integer> ids = commentVOS.stream().map(CommentVO::getCommentId).collect(Collectors.toList());
        List<CommentVO> commentChildren = commentMapper.selectBatchParentIds(ids);

        // 3. 构建评论树
        Map<Integer, CommentVO> commentMap = new HashMap<>();
        for (CommentVO comment : commentVOS) {
            commentMap.put(comment.getCommentId(), comment);
        }
        for (CommentVO commentChild : commentChildren) {
            CommentVO commentVO = commentMap.get(commentChild.getCommentParent());
            if(commentVO.getCommentChildren() == null){
                commentVO.setCommentChildren(new ArrayList<>());
            }
            commentVO.getCommentChildren().add(commentChild);
        }

        PageResult<CommentVO> pageResult = new PageResult<>();
        pageResult.setList(commentVOS);
        pageResult.setTotal(commentPage.getTotal());
        pageResult.setPages(commentPage.getPages());

        return pageResult;
    }

    /**
     * 删除评论及子评论
     * @param commentId
     */
    @Override
    @Transactional
    public void deleteComment(Integer commentId) {
        //1.查询评论
        Comment comment = commentMapper.selectById(commentId);
        //2.不存在，直接返回
        if(comment == null){
            return;
        }
        //3.删除评论
        commentMapper.deleteById(commentId);
        //4.删除子评论
        commentMapper.delete(new QueryWrapper<Comment>().eq("comment_parent",commentId));
    }
}




