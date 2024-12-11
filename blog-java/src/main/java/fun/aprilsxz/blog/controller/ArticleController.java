package fun.aprilsxz.blog.controller;

import fun.aprilsxz.blog.domain.common.PageResult;
import fun.aprilsxz.blog.domain.common.Result;
import fun.aprilsxz.blog.domain.dto.ArticleDto;
import fun.aprilsxz.blog.domain.dto.group.Update;
import fun.aprilsxz.blog.domain.po.Article;
import fun.aprilsxz.blog.domain.vo.ArticleVO;
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

    @GetMapping("/{categoryId}/{pageNum}/{pageSize}")
    @ApiOperation("通过categoryId分页查询文章")
    @ApiImplicitParams({@ApiImplicitParam(name = "categoryId",value = "分类id"),
                        @ApiImplicitParam(name = "pageNum",value = "当前页码"),
                        @ApiImplicitParam(name = "pageSize",value = "每页条数")})
    public Result<PageResult<Article>> pageQueryByCategoryId(@PathVariable("categoryId") Integer categoryId,
                                                             @PathVariable("pageNum") Integer pageNum,
                                                             @PathVariable("pageSize") Integer pageSize){
        return null;
    }


    @GetMapping()
    @ApiOperation("获取所有文章")
    public Result<List<Article>> queryAll(){
        return Result.ok(articleService.list());
    }

    @GetMapping("/categoryId/{categoryId}")
    @ApiOperation("通过分类id查询文章")
    public Result<List<ArticleVO>> queryByCategoryId(@PathVariable("categoryId") Integer categoryId){
        List<ArticleVO> articleVOList = articleService.queryByCategoryId(categoryId);
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
