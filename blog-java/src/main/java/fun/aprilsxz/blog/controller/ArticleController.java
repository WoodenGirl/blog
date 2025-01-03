package fun.aprilsxz.blog.controller;

import fun.aprilsxz.blog.domain.common.PageResult;
import fun.aprilsxz.blog.domain.common.Result;
import fun.aprilsxz.blog.domain.dto.ArticleDto;
import fun.aprilsxz.blog.domain.dto.group.Update;
import fun.aprilsxz.blog.domain.vo.ArticleBrief;
import fun.aprilsxz.blog.domain.vo.ArticleDetail;
import fun.aprilsxz.blog.service.ArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@RestController
@Api(tags = "文章接口")
@RequestMapping("/article")
@Validated
public class ArticleController {
    @Resource
    ArticleService articleService;

    @GetMapping()
    @ApiOperation("通过categoryId分页查询文章")
    public Result<PageResult<ArticleBrief>> pageQueryByCategoryId(@RequestParam @Min(value = 1,message = "分类id最少为1") Integer categoryId, @RequestParam Integer currentPage, @Min(1) @RequestParam Integer pageSize){
        PageResult<ArticleBrief> pageResult = articleService.queryByCategoryId(categoryId, currentPage, pageSize);
        return Result.ok(pageResult);
    }

    @GetMapping("/{articleId}")
    @ApiOperation("通过articleId查询详细文章")
    public Result<ArticleDetail> queryDetailById(@PathVariable("articleId") @NotBlank(message = "分类id不能为空") String articleId){
        ArticleDetail articleVO = articleService.queryDetailById(articleId);
        return Result.ok(articleVO);
    }

    @PostMapping()
    @ApiOperation("新增文章")
    public Result<Void> addArticle(@Valid @RequestBody ArticleDto articleDto){
        articleService.addArticle(articleDto);
        return Result.ok();
    }

    @PutMapping()
    @ApiOperation("更新文章")
    public Result<Void> updateArticle(@RequestBody @Validated(Update.class) ArticleDto articleDto){
        articleService.updateArticle(articleDto);
        return Result.ok();
    }

    @DeleteMapping("/{articleId}")
    @ApiOperation("根据articleId删除文章")
    public Result<Void> deleteByArticleId(@PathVariable("articleId") @NotBlank(message = "文章id不能为空") String articleId){
        articleService.removeByArticleId(articleId);
        return Result.ok();
    }
}
