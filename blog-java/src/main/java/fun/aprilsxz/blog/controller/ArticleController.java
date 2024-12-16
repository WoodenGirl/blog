package fun.aprilsxz.blog.controller;

import fun.aprilsxz.blog.domain.common.PageResult;
import fun.aprilsxz.blog.domain.common.Result;
import fun.aprilsxz.blog.domain.dto.ArticleDto;
import fun.aprilsxz.blog.domain.dto.group.Update;
import fun.aprilsxz.blog.domain.vo.ArticleBrief;
import fun.aprilsxz.blog.domain.vo.ArticleDetail;
import fun.aprilsxz.blog.service.ArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@Api(tags = "文章接口")
@RequestMapping("/article")
@Validated
public class ArticleController {
    @Resource
    ArticleService articleService;

    @GetMapping("/{categoryId}/{currentPage}/{pageSize}")
    @ApiOperation("通过categoryId分页查询文章")
    @ApiImplicitParams({@ApiImplicitParam(name = "categoryId",value = "分类id"),
                        @ApiImplicitParam(name = "currentPage",value = "当前页码"),
                        @ApiImplicitParam(name = "pageSize",value = "每页条数")})
    public Result<PageResult<ArticleBrief>> pageQueryByCategoryId(@PathVariable("categoryId") Integer categoryId,
                                                                  @PathVariable("currentPage") Integer currentPage,
                                                                  @PathVariable("pageSize") Integer pageSize){
        PageResult<ArticleBrief> pageResult = articleService.queryByCategoryId(categoryId, currentPage, pageSize);
        return Result.ok(pageResult);
    }

    @GetMapping("/articleId/{articleId}")
    @ApiOperation("通过articleId查询详细文章")
    public Result<ArticleDetail> queryDetailById(@PathVariable("articleId") String articleId){
        ArticleDetail articleVOList = articleService.queryDetailById(articleId);
        return Result.ok(articleVOList);
    }

    @PostMapping()
    @ApiOperation("新增文章")
    public Result<Void> addArticle(@Valid @RequestBody ArticleDto articleDto){
        articleService.addArticle(articleDto);
        return Result.ok();
    }

    @PutMapping()
    @ApiOperation("更新文章")
    @Validated(Update.class)
    public Result<Void> updateArticle(@Valid @RequestBody ArticleDto articleDto){
        articleService.updateArticle(articleDto);
        return Result.ok();
    }

    @DeleteMapping("/{articleId}")
    @ApiOperation("根据articleId删除文章")
    public Result<Void> deleteByArticleId(@PathVariable("articleId") String articleId){
        articleService.removeById(articleId);
        return Result.ok();
    }
}
