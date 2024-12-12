using blogApi.Core.entity;

namespace blogApi.Application.category.dots
{
    public partial class CategoryTree
    {
        public CategoryTree(){

        }
        public CategoryTree(int id, string label, List<CategoryTree> children)
        {
            this.id = id;
            this.label = label;
            this.children = children;
        }   
        public int id {get;set;}
 
        public string label {get;set;}

        public List<CategoryTree> children {get;set;}

        public static CategoryTree convert(Category category)
        {
            return new CategoryTree(category.categoryId, category.categoryName, new List<CategoryTree>());
        }
    }
}
