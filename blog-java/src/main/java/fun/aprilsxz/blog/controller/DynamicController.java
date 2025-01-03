package fun.aprilsxz.blog.controller;

import fun.aprilsxz.blog.domain.common.PageResult;
import fun.aprilsxz.blog.domain.common.Result;
import fun.aprilsxz.blog.domain.dto.DynamicDto;
import fun.aprilsxz.blog.domain.dto.group.Update;
import fun.aprilsxz.blog.domain.vo.DynamicVO;
import fun.aprilsxz.blog.service.DynamicService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping("/dynamic")
@Api(tags = "动态接口")
@Validated
public class DynamicController {
    @Resource
    DynamicService dynamicService;

    @GetMapping()
    @ApiOperation("根据分类id分页查询动态")
    public Result<PageResult<DynamicVO>> queryByCategoryId(@RequestParam @Min(value = 1,message = "分类id最少为1") Integer categoryId, @RequestParam @Min(value = 1,message = "当前页码最小为1") Integer currentPage, @Min(value = 1,message = "每页大小最少为1") @RequestParam Integer pageSize){
        PageResult<DynamicVO> pageResult = dynamicService.queryByCategoryId(categoryId,currentPage,pageSize);
        return Result.ok(pageResult);
    }

    @GetMapping("/{dynamicId}")
    @ApiOperation("通过dynamicId查询详细动态")
    public Result<DynamicVO> queryDetailById(@PathVariable("dynamicId") @NotBlank(message = "动态id不能为空") String dynamicId){
        DynamicVO dynamicVO = dynamicService.queryDetailById(dynamicId);
        return Result.ok(dynamicVO);
    }

    @PostMapping()
    @ApiOperation("新增动态")
    public Result<Void> addDynamic(@RequestBody @Validated(Update.class) DynamicDto dynamicDto){
        dynamicService.addDynamic(dynamicDto);
        return Result.ok();
    }

    @DeleteMapping("/{dynamicId}")
    @ApiOperation("根据dynamicId删除动态")
    public Result<Void> deleteDynamicById(@PathVariable("dynamicId") @NotBlank(message = "动态id不能为空") String dynamicId){
        dynamicService.removeByDynamicId(dynamicId);
        return Result.ok();
    }
}
