package fun.aprilsxz.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fun.aprilsxz.blog.domain.po.Category;
import fun.aprilsxz.blog.service.CategoryService;
import fun.aprilsxz.blog.mapper.CategoryMapper;
import org.springframework.stereotype.Service;

/**
* @author yang
* @description 针对表【category】的数据库操作Service实现
* @createDate 2024-12-11 14:25:50
*/
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category>
    implements CategoryService{

}




