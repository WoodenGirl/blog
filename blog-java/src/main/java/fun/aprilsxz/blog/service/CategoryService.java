package fun.aprilsxz.blog.service;

import fun.aprilsxz.blog.domain.dto.CategoryDto;
import fun.aprilsxz.blog.domain.po.Category;
import com.baomidou.mybatisplus.extension.service.IService;
import fun.aprilsxz.blog.domain.vo.CategoryVO;

import java.util.List;

/**
* @author yang
* @description 针对表【category】的数据库操作Service
* @createDate 2024-12-11 14:25:50
*/
public interface CategoryService extends IService<Category> {

    List<CategoryVO> queryById(Integer categoryId);

    void addCategory(CategoryDto categoryDto);

    void updateCategory(CategoryDto categoryDto);

    void deleteCategory(Integer categoryId);
}
