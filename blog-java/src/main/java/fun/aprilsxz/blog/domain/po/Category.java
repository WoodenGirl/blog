package fun.aprilsxz.blog.domain.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import io.swagger.models.auth.In;
import lombok.Data;

/**
 * 
 * @TableName category
 */
@TableName(value ="category")
@Data
public class Category implements Serializable {
    /**
     * 类别id
     */
    @TableId(type = IdType.AUTO)
    private Integer categoryId;

    /**
     * 类别名称
     */
    private String categoryName;

    /**
     * 父目录
     */
    private Integer categoryParent;

    /**
     *  是否不向下展开
     */
    private Integer isInterrupt;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}