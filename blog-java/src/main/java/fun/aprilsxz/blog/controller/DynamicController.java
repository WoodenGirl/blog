package fun.aprilsxz.blog.controller;

import fun.aprilsxz.blog.domain.common.Result;
import fun.aprilsxz.blog.domain.dto.DynamicDto;
import fun.aprilsxz.blog.domain.vo.DynamicVO;
import fun.aprilsxz.blog.service.DynamicService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

    @GetMapping("/{categoryId}")
    @ApiOperation("根据分类id查询动态")
    public Result<List<DynamicVO>> queryByCategoryId(@PathVariable("categoryId") @Min(1) Integer categoryId){
        List<DynamicVO> dynamicVOList = dynamicService.queryByCategoryId(categoryId);
        return Result.ok(dynamicVOList);
    }

    @PostMapping()
    @ApiOperation("新增动态")
    public Result<Void> addDynamic( @RequestBody @Valid DynamicDto dynamicDto){
        dynamicService.addDynamic(dynamicDto);
        return Result.ok();
    }

    @DeleteMapping("/{dynamicId}")
    @ApiOperation("根据dynamicId删除动态")
    public Result<Void> deleteDynamicById(@PathVariable("dynamicId") @NotBlank String dynamicId){
        dynamicService.removeById(dynamicId);
        return Result.ok();
    }
}
