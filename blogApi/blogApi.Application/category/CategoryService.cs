using blogApi.Application.category.dots;
using blogApi.Core.entity;
using blogApi.Core.sqlsugar;

namespace blogApi.Application.category;

public class CategoryService : IDynamicApiController
{
    Repository<Category> _categoryRepository => new Repository<Category>();

    protected void buildTree(CategoryTree parent, List<Category> categories)
    {
        foreach (var category in categories)
        {
            if (category.categoryParent == parent.id)
            {
                CategoryTree categoryTree = CategoryTree.convert(category);
                parent.children.Add(categoryTree);
                buildTree(categoryTree, categories);
            }
        }
    }
    public ResultVo Query(int categoryParent)
    {
        List<Category> categories = _categoryRepository.GetList();
        // 构建树结构
        List<CategoryTree> categoriesTree = new List<CategoryTree>();
        // 获取所有根节点
        foreach (var category in categories)
        {
            if (category.categoryParent == categoryParent)
            {
                categoriesTree.Add(CategoryTree.convert(category));
            }
        }
        // 对根节点进行递归
        foreach (var categoryTree in categoriesTree) { 
            buildTree(categoryTree, categories);
        }

        return ResultVo.success(categoriesTree);
    }
    public ResultVo Add(Category category)
    {  
        _categoryRepository.Insert(category);
        return ResultVo.success();
    }
    
    [HttpPost]
    public ResultVo DeleteCategories(Object[] categoryIds)
    {
        try
        {
            _categoryRepository.DeleteByIds(categoryIds);
            return ResultVo.success();
        }
        catch (Exception e)
        {
            return ResultVo.error(301, "该目录或子目录下存在文章，需不存在文章时删除目录！");
        }
    }
    public ResultVo Update(Category category)
    {
        _categoryRepository.Update(it => new Category { categoryName = category.categoryName}, it => it.categoryId == category.categoryId); 
        return ResultVo.success();
    }
}
