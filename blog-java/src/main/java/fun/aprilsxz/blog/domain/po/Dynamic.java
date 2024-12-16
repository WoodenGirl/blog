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
 * @TableName dynamic
 */
@TableName(value ="dynamic")
@Data
public class Dynamic implements Serializable {
    /**
     * 主键
     */
    @TableId
    private String dynamicId;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 分类id
     */
    private Integer categoryId;

    /**
     * 动态内容
     */
    private String dynamicContent;

    /**
     * 动态图片
     */
    private String dynamicImages;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;


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
        Dynamic other = (Dynamic) that;
        return (this.getDynamicId() == null ? other.getDynamicId() == null : this.getDynamicId().equals(other.getDynamicId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getCategoryId() == null ? other.getCategoryId() == null : this.getCategoryId().equals(other.getCategoryId()))
            && (this.getDynamicContent() == null ? other.getDynamicContent() == null : this.getDynamicContent().equals(other.getDynamicContent()))
            && (this.getDynamicImages() == null ? other.getDynamicImages() == null : this.getDynamicImages().equals(other.getDynamicImages()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getDynamicId() == null) ? 0 : getDynamicId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getCategoryId() == null) ? 0 : getCategoryId().hashCode());
        result = prime * result + ((getDynamicContent() == null) ? 0 : getDynamicContent().hashCode());
        result = prime * result + ((getDynamicImages() == null) ? 0 : getDynamicImages().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", dynamicId=").append(dynamicId);
        sb.append(", userId=").append(userId);
        sb.append(", categoryId=").append(categoryId);
        sb.append(", dynamicContent=").append(dynamicContent);
        sb.append(", dynamicImages=").append(dynamicImages);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}