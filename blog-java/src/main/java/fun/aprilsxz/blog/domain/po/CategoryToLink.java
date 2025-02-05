package fun.aprilsxz.blog.domain.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName category_to_link
 */
@TableName(value ="category_to_link")
@Data
public class CategoryToLink implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 关联文章id
     */
    private String linkId;

    /**
     * 分类id
     */
    private Integer categoryId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}