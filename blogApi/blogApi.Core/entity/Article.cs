using System;
using System.Drawing;
using SqlSugar;

namespace entity
{
    ///<summary>
    ///
    ///</summary>
    [SugarTable("tb_article")]
    public partial class Article
    {
           public Article(){


           }
            
           [SugarColumn(IsPrimaryKey=true,IsIdentity=true)]
           public int articleId {get;set;}
               
           public string articleTitle {get;set;}
                
           public int userId {get;set;}
   
           public DateTime createdTime {get;set;}
 
           public DateTime updateTime {get;set;}

            public string articleTags {get;set;}

           public int categoryId {get;set;}
  
           public string articleContent {get;set;}

           public string articleCover { get; set; }

    }
}
