package fun.aprilsxz.blog.domain.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 
 * @TableName article
 */
@TableName(value ="article")
@Data
public class Article implements Serializable {
    /**
     * 主键
     */
    @TableId
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
     * 文字标签
     */
    private String articleTags;

    /**
     * 分类id
     */
    private Integer categoryId;

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
    private Integer status;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}