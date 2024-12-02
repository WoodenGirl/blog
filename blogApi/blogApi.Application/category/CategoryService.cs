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
    public ResultVo Query()
    {
        List<Category> categories = _categoryRepository.GetList();
        // 构建树结构
        List<CategoryTree> categoriesTree = new List<CategoryTree>();
        // 获取所有根节点
        foreach (var category in categories)
        {
            if (category.categoryParent == 0)
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
        List<string> categaries = new List<string>();
        categaries.Add(category.categoryName);
        // 寻找父节点
        if (category.categoryParent != 0)
        {
            Category temp = new Category();
            temp.categoryParent = category.categoryParent;
            while (temp.categoryParent != 0)
            {
                temp = _categoryRepository.GetById(temp.categoryParent);
                categaries.Add(temp.categoryName);
            }
            // 获取categoryDetail完整类别名称
            for (int i = categaries.Count - 1; i >= 1; i--)
            {
                category.categoryDetail += categaries[i] + '-';
            }
        }
        category.categoryDetail += categaries[0];
        // 插入
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
