package fun.aprilsxz.blog.service.impl;

import cn.hutool.core.collection.ListUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import fun.aprilsxz.blog.domain.common.PageResult;
import fun.aprilsxz.blog.domain.po.Comment;
import fun.aprilsxz.blog.domain.po.User;
import fun.aprilsxz.blog.domain.vo.CommentVO;
import fun.aprilsxz.blog.service.CommentService;
import fun.aprilsxz.blog.mapper.CommentMapper;
import lombok.Builder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.ZoneOffset;
import java.util.*;

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
    public PageResult<CommentVO> queryComment(String linkedId, Integer currentPage, Integer pageSize) {
        PageHelper.startPage(currentPage,pageSize);
        Page<CommentVO> commentPage = (Page<CommentVO>)commentMapper.queryComment(linkedId);

        //构建父子关系
        List<CommentVO> commentVOS = commentPage.getResult();
        List<CommentVO> commentVOList = processComments(commentVOS);

        PageResult<CommentVO> pageResult = new PageResult<>();
        pageResult.setList(commentVOList);
        pageResult.setTotal(commentPage.getTotal());
        pageResult.setPages(commentPage.getPages());

        return pageResult;
    }

    /**
     * 构建评论父子关系
     * @param comments
     * @return
     */
    public static List<CommentVO> processComments(List<CommentVO> comments) {
        Map<Integer, CommentVO> commentMap = new HashMap<>();

        // 将所有评论放入Map中，便于通过ID查找
        for (CommentVO comment : comments) {
            commentMap.put(comment.getCommentId(), comment);
        }

        // 构建评论的父子关系
        for (CommentVO comment : comments) {
            if (comment.getCommentParent() != null) {
                CommentVO parent = commentMap.get(comment.getCommentParent());
                if (parent != null) {
                    parent.getCommentChildren().add(comment);
                }
            }
        }

        // 提取顶级评论（即parentId为null的评论）
        List<CommentVO> topLevelComments = new ArrayList<>();
        for (CommentVO comment : commentMap.values()) {
            if (comment.getCommentParent() == null) {
                topLevelComments.add(comment);
            }
        }

        topLevelComments.sort((o1, o2) -> (int) (o1.getCreateTime().toEpochSecond(ZoneOffset.UTC) - o2.getCreateTime().toEpochSecond(ZoneOffset.UTC)));

        return topLevelComments;
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
        commentMapper.delete(new QueryWrapper<Comment>().eq("link_id",commentId));
    }
}




