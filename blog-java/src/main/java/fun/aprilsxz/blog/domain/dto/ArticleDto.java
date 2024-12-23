package fun.aprilsxz.blog.domain.dto;

import fun.aprilsxz.blog.domain.dto.group.Update;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@ApiModel
public class ArticleDto {
    @NotBlank(groups = Update.class)
    @ApiModelProperty("文章id")
    private String articleId;

    @NotBlank
    @ApiModelProperty("文章标题")
    private String articleTitle;

    @NotBlank
    @ApiModelProperty("用户id")
    private String userId;

    @NotBlank
    @ApiModelProperty("文章标签")
    private String[] articleTags;

    @NotNull
    @Min(1)
    @ApiModelProperty("分类id")
    private Integer categoryId;

    @NotBlank
    @ApiModelProperty("文章内容")
    private String articleContent;

    @NotBlank
    @ApiModelProperty("文章封面")
    private String articleCover;

    @NotNull
    @ApiModelProperty("发布状态：0表示草稿，1表示已发布")
    private Integer articleStatus;
}
