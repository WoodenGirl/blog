using SqlSugar;

namespace blogApi.Core.entity
{
    ///<summary>
    ///
    ///</summary>
    [SugarTable("tb_category")]
    public partial class Category
    {
           public Category(){


           }
           /// <summary>
           /// Desc:类别id
           /// Default:
           /// Nullable:False
           /// </summary>           
           [SugarColumn(IsPrimaryKey=true,IsIdentity=true)]
           public int categoryId {get;set;}

           /// <summary>
           /// Desc:类别名称
           /// Default:
           /// Nullable:False
           /// </summary>           
           public string categoryName {get;set;}

           /// <summary>
           /// Desc:父目录
           /// Default:
           /// Nullable:False
           /// </summary>           
           public int categoryParent {get;set;}


            /// <summary>
            /// Desc:详细名称
            /// </summary>  
            public string categoryDetail { get; set; }

    }
}
