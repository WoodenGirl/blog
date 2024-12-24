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
 * @TableName comment
 */
@TableName(value ="comment")
@Data
public class Comment implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer commentId;

    /**
     * 父评论id
     */
    private Integer commentParent;

    /**
     * 
     */
    private String userId;

    /**
     * 评论内容
     */
    private String commentContent;

    /**
     * 回复的用户id
     */
    private String commentReply;

    /**
     * 
     */
    private String linkId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}