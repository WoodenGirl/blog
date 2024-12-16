package fun.aprilsxz.blog.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fun.aprilsxz.blog.domain.po.Category;
import fun.aprilsxz.blog.domain.vo.CategoryVO;
import fun.aprilsxz.blog.service.CategoryService;
import fun.aprilsxz.blog.mapper.CategoryMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
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

    @Override
    public List<CategoryVO> queryById(Integer categoryId) {
        List<Category> categoryList = categoryMapper.queryById(categoryId);
        List<CategoryVO> categoryVOList = categoryList.stream().map(new Function<Category, CategoryVO>() {
            @Override
            public CategoryVO apply(Category category) {
                return BeanUtil.copyProperties(category, CategoryVO.class);
            }
        }).collect(Collectors.toList());

        return buildTree(categoryVOList).getCategoryChildren();
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




