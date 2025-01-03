package fun.aprilsxz.blog.domain.dto;

import fun.aprilsxz.blog.domain.dto.group.Update;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@ApiModel("动态实体")
public class DynamicDto {
    @NotBlank(groups = Update.class)
    @ApiModelProperty("动态id")
    private String dynamicId;

    @NotBlank
    @ApiModelProperty("创建者id")
    private String userId;

    @NotNull
    @ApiModelProperty("分类id")
    private Integer categoryId;

    @NotBlank
    @ApiModelProperty("动态内容")
    private String dynamicContent;

    @ApiModelProperty("动态图片url")
    private String[] dynamicImages;
}
