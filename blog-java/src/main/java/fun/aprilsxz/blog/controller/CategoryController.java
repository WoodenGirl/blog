package fun.aprilsxz.blog.controller;

import fun.aprilsxz.blog.domain.common.Result;
import fun.aprilsxz.blog.domain.dto.CategoryDto;
import fun.aprilsxz.blog.domain.dto.group.Insert;
import fun.aprilsxz.blog.domain.dto.group.Update;
import fun.aprilsxz.blog.domain.vo.CategoryVO;
import fun.aprilsxz.blog.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.Min;
import java.util.List;

@Api(tags = "分类接口")
@RestController
@RequestMapping("/category")
@Validated
public class CategoryController {
    @Resource
    CategoryService categoryService;

    @GetMapping("/{categoryId}")
    @ApiOperation("查询子目录，考虑isInterrupt")
    public Result<List<CategoryVO>> queryById(@PathVariable("categoryId") @Min(value = 0,message = "分类id不能小于0") Integer categoryId){
        List<CategoryVO> categoryVOList = categoryService.queryById(categoryId);
        return Result.ok(categoryVOList);
    }

    @PostMapping()
    @ApiOperation("添加目录")
    public Result<Void> addCategory(@RequestBody @Validated(Insert.class) CategoryDto categoryDto){
        categoryService.addCategory(categoryDto);
        return Result.ok();
    }

    @PutMapping()
    @ApiOperation("修改目录 参数category只能修改两项{categoryName,isInterrupt}")
    public Result<Void> updateCategory(@RequestBody @Validated(Update.class) CategoryDto categoryDto){
        categoryService.updateCategory(categoryDto);
        return Result.ok();
    }

    @DeleteMapping("/categoryId/{categoryId}")
    @ApiOperation(("删除目录下的所有子目录，以及目录下的所有内容"))
    public Result<Void> deleteCategory(@PathVariable("categoryId") Integer categoryId){
        categoryService.deleteCategory(categoryId);
        return Result.ok();
    }
}
