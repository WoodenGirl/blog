package fun.aprilsxz.blog.domain.dto;

import fun.aprilsxz.blog.domain.dto.group.Insert;
import fun.aprilsxz.blog.domain.dto.group.Update;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Data
@ApiModel("分类实体")
public class CategoryDto {
    /**
     * 类别id
     */
    @ApiModelProperty("分类id")
    @NotNull(groups = Update.class)
    @Null(groups = Insert.class)
    private Integer categoryId;

    /**
     * 类别名称
     */
    @ApiModelProperty("类别姓名")
    @NotBlank
    private String categoryName;

    /**
     * 父目录
     */
    @ApiModelProperty("父目录id")
    @NotNull @Min(0)
    @Null(groups = Update.class)
    private Integer categoryParent;

    /**
     *  是否不向下展开
     */
    @ApiModelProperty("是否不向下展开")
    @NotNull @Range(min = 0, max = 1)
    private Integer isInterrupt;
}
