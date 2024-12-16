package fun.aprilsxz.blog.domain.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

import fun.aprilsxz.blog.enums.LinkedType;
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
    private String linkedId;

    /**
     * 关联的文章类型
     */
    private LinkedType linkedType;

    /**
     * 创建时间
     */
    private LocalDateTime createdTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Comment other = (Comment) that;
        return (this.getCommentId() == null ? other.getCommentId() == null : this.getCommentId().equals(other.getCommentId()))
            && (this.getCommentParent() == null ? other.getCommentParent() == null : this.getCommentParent().equals(other.getCommentParent()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getCommentContent() == null ? other.getCommentContent() == null : this.getCommentContent().equals(other.getCommentContent()))
            && (this.getCommentReply() == null ? other.getCommentReply() == null : this.getCommentReply().equals(other.getCommentReply()))
            && (this.getLinkedId() == null ? other.getLinkedId() == null : this.getLinkedId().equals(other.getLinkedId()))
            && (this.getCreatedTime() == null ? other.getCreatedTime() == null : this.getCreatedTime().equals(other.getCreatedTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCommentId() == null) ? 0 : getCommentId().hashCode());
        result = prime * result + ((getCommentParent() == null) ? 0 : getCommentParent().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getCommentContent() == null) ? 0 : getCommentContent().hashCode());
        result = prime * result + ((getCommentReply() == null) ? 0 : getCommentReply().hashCode());
        result = prime * result + ((getLinkedId() == null) ? 0 : getLinkedId().hashCode());
        result = prime * result + ((getCreatedTime() == null) ? 0 : getCreatedTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", commentId=").append(commentId);
        sb.append(", commentParent=").append(commentParent);
        sb.append(", userId=").append(userId);
        sb.append(", commentContent=").append(commentContent);
        sb.append(", commentReply=").append(commentReply);
        sb.append(", linkedId=").append(linkedId);
        sb.append(", createdTime=").append(createdTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}