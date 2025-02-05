package fun.aprilsxz.blog.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fun.aprilsxz.blog.domain.dto.CategoryDto;
import fun.aprilsxz.blog.domain.po.Article;
import fun.aprilsxz.blog.domain.po.Category;
import fun.aprilsxz.blog.domain.po.Dynamic;
import fun.aprilsxz.blog.domain.vo.CategoryVO;
import fun.aprilsxz.blog.mapper.ArticleMapper;
import fun.aprilsxz.blog.mapper.CategoryMapper;
import fun.aprilsxz.blog.mapper.CommentMapper;
import fun.aprilsxz.blog.mapper.DynamicMapper;
import fun.aprilsxz.blog.service.CategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
* @author yang
* @description 针对表【category】的数据库操作Service实现
* @createDate 2024-12-11 14:25:50
*/
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category>
    implements CategoryService{
    @Resource
    CategoryMapper categoryMapper;
    @Resource
    ArticleMapper articleMapper;
    @Resource
    DynamicMapper dynamicMapper;
    @Resource
    CommentMapper commentMapper;

    @Override
    public List<CategoryVO> queryById(Integer categoryId) {
        List<Category> categoryList = categoryMapper.queryById(categoryId);
        List<CategoryVO> categoryVOList = categoryList.stream().map(category -> BeanUtil.copyProperties(category, CategoryVO.class)).collect(Collectors.toList());

        return buildTree(categoryVOList).getCategoryChildren();
    }

    @Override
    public void addCategory(CategoryDto categoryDto) {
        Category category = BeanUtil.copyProperties(categoryDto, Category.class);
        categoryMapper.insert(category);
    }

    @Override
    public void updateCategory(CategoryDto categoryDto) {
        Category category = BeanUtil.copyProperties(categoryDto, Category.class);
        categoryMapper.updateById(category);
    }

    /**
     * 删除目录下的所有子目录，以及目录下的所有内容
     * @param categoryId 分类id
     */
    @Override
    @Transactional
    // TODO 其他插入删除等操作关联category_to_link表
    public void deleteCategory(Integer categoryId) {
        List<Category> categoryList = categoryMapper.queryById(categoryId);
        //.1.查询子目录id
        List<Integer> ids = categoryList.stream().map(Category::getCategoryId).collect(Collectors.toList());
        // 2.查询
        // 1. 删除对应的评论
//        commentMapper.delete(new QueryWrapper<Comment>().in("link_id",))
        // 2. 删除所有的文章和动态
        articleMapper.delete(new QueryWrapper<Article>().in("category_id",ids));
        dynamicMapper.delete(new QueryWrapper<Dynamic>().in("category_id",ids));

    }

    // 构建目录树的方法
    public static CategoryVO buildTree(List<CategoryVO> nodes) {
        if (nodes == null || nodes.isEmpty()) {
            return null;
        }
        // 用Map来方便根据id查找节点，便于后续构建父子关系
        Map<Integer, CategoryVO> nodeMap = new HashMap<>();
        for (CategoryVO node : nodes) {
            nodeMap.put(node.getCategoryId(), node);
        }
        CategoryVO root = nodes.get(0);
        for (int i = 1; i < nodes.size(); i++) {
            CategoryVO node = nodes.get(i);

            int parentId = node.getCategoryParent();

            CategoryVO parentNode = nodeMap.get(parentId);
            if(parentNode.getCategoryChildren() == null){
                parentNode.setCategoryChildren(new ArrayList<>());
            }
            parentNode.getCategoryChildren().add(node);
        }

        return root;
    }
}




