package fun.aprilsxz.blog.domain.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ArticleBrief {
    /**
     * 主键
     */
    private String articleId;

    /**
     * 文章标题
     */
    private String articleTitle;

    /**
     * nickname
     */
    private String nickname;

    private String avatar;

    /**
     * 文字标签
     */
    private String[] articleTags;

    private String[] tags;
    /**
     * 分类名
     */
    private String categoryName;

    /**
     * 封面图片
     */
    private String articleCover;

    /**
     * 发布状态：0表示草稿，1表示已发布
     */
    private Integer articleStatus;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}
