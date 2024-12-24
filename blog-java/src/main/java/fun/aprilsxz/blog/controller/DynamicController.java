package fun.aprilsxz.blog.controller;

import fun.aprilsxz.blog.domain.common.PageResult;
import fun.aprilsxz.blog.domain.common.Result;
import fun.aprilsxz.blog.domain.dto.DynamicDto;
import fun.aprilsxz.blog.domain.dto.group.Update;
import fun.aprilsxz.blog.domain.vo.ArticleDetail;
import fun.aprilsxz.blog.domain.vo.DynamicVO;
import fun.aprilsxz.blog.service.DynamicService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.checkerframework.checker.units.qual.min;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RestController
@RequestMapping("/dynamic")
@Api(tags = "动态接口")
@Validated
public class DynamicController {
    @Resource
    DynamicService dynamicService;

    @GetMapping()
    @ApiOperation("根据分类id分页查询动态")
    public Result<PageResult<DynamicVO>> queryByCategoryId(@RequestParam @Min(1) Integer categoryId, @RequestParam Integer currentPage, @Min(1) @RequestParam Integer pageSize){
        PageResult<DynamicVO> pageResult = dynamicService.queryByCategoryId(categoryId,currentPage,pageSize);
        return Result.ok(pageResult);
    }

    @GetMapping("/{dynamicId}")
    @ApiOperation("通过dynamicId查询详细动态")
    public Result<DynamicVO> queryDetailById(@PathVariable("dynamicId") String dynamicId){
        DynamicVO dynamicVO = dynamicService.queryDetailById(dynamicId);
        return Result.ok(dynamicVO);
    }

    @PostMapping()
    @ApiOperation("新增动态")
    @Validated(Update.class)
    public Result<Void> addDynamic( @RequestBody DynamicDto dynamicDto){
        dynamicService.addDynamic(dynamicDto);
        return Result.ok();
    }

    @DeleteMapping("/{dynamicId}")
    @ApiOperation("根据dynamicId删除动态")
    public Result<Void> deleteDynamicById(@PathVariable("dynamicId") @NotBlank String dynamicId){
        dynamicService.removeByDynamicId(dynamicId);
        return Result.ok();
    }
}
