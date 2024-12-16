package fun.aprilsxz.blog.controller;

import fun.aprilsxz.blog.domain.common.Result;
import fun.aprilsxz.blog.domain.po.Category;
import fun.aprilsxz.blog.domain.vo.CategoryVO;
import fun.aprilsxz.blog.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@Api(tags = "分类接口")
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Resource
    CategoryService categoryService;

    @GetMapping("/{categoryId}")
    @ApiOperation("查询子目录，考虑isInterrupt")
    Result<List<CategoryVO>> queryById(@PathVariable("categoryId") Integer categoryId){
        List<CategoryVO> categoryVOList = categoryService.queryById(categoryId);
        return Result.ok(categoryVOList);
    }
}
