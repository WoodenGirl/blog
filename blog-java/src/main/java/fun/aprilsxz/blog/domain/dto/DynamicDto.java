package fun.aprilsxz.blog.domain.dto;

import fun.aprilsxz.blog.domain.dto.group.Update;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

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
    private String dynamicImages;
    @Range(min = 0,max = 1)
    @ApiModelProperty("状态：0表示草稿，1表示发布")
    private Integer status;
}
