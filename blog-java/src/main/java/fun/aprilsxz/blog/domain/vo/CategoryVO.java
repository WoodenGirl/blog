package fun.aprilsxz.blog.domain.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.List;

@Data
public class CategoryVO {
    /**
     * 类别id
     */
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

    private List<CategoryVO> categoryChildren;
}
