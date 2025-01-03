package fun.aprilsxz.blog.controller;

import fun.aprilsxz.blog.domain.common.PageResult;
import fun.aprilsxz.blog.domain.common.Result;
import fun.aprilsxz.blog.domain.po.Comment;
import fun.aprilsxz.blog.domain.vo.CommentVO;
import fun.aprilsxz.blog.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;

@RequestMapping("/comment")
@RestController
@Api(tags = "评论相关接口")
public class CommentController {
    @Resource
    CommentService commentService;

    @GetMapping()
    @ApiOperation("分页查询评论")
    public Result<PageResult<CommentVO>> queryComment(@RequestParam("linkId") String linkId,
                                               @RequestParam("currentPage") Integer currentPage,
                                               @RequestParam("pageSize") Integer pageSize){
        PageResult<CommentVO> commentPageResult = commentService.queryComment(linkId,currentPage,pageSize);
        return Result.ok(commentPageResult);
    }

    @PostMapping()
    @ApiOperation("添加评论")
    public Result<Void> addComment(@RequestBody Comment comment){
        comment.setCreateTime(LocalDateTime.now());
        commentService.save(comment);
        return Result.ok();
    }

    @DeleteMapping("/{commentId}")
    @ApiOperation("删除评论及子评论")
    public Result<Void> deleteComment(@PathVariable("commentId") Integer commentId){
        commentService.deleteComment(commentId);
        return Result.ok();
    }

}
