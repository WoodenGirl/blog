package fun.aprilsxz.blog.domain.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ArticleDetail {
    /**
     * 主键
     */
    private String articleId;

    /**
     * 文章标题
     */
    private String articleTitle;

    /**
     * 用户id
     */
    private String userId;

    /**
     * nickname
     */
    private String nickname;

    /**
     * 文字标签
     */
    private String articleTags;

    /**
     * 分类id
     */
    private Integer categoryId;

    /**
     * 分类名
     */
    private String categoryName;

    /**
     * 文字内容
     */
    private String articleContent;

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
