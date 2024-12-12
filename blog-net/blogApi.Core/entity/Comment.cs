using System;
using SqlSugar;

namespace blogApi.Core.entity
{
    ///<summary>
    ///
    ///</summary>
    [SugarTable("tb_comment")]
    public partial class Comment
    {
           public Comment(){


           }
           /// <summary>
           /// Desc:
           /// Default:
           /// Nullable:False
           /// </summary>           
           [SugarColumn(IsPrimaryKey=true,IsIdentity=true)]
           public int commentId {get;set;}

           /// <summary>
           /// Desc:
           /// Default:0
           /// Nullable:False
           /// </summary>           
           public int commentParent {get;set;}

           /// <summary>
           /// Desc:
           /// Default:
           /// Nullable:False
           /// </summary>           
           public string userId {get;set;}

           /// <summary>
           /// Desc:
           /// Default:
           /// Nullable:False
           /// </summary>           
           public string commentContent {get;set;}

           /// <summary>
           /// Desc:
           /// Default:
           /// Nullable:False
           /// </summary>           
           public DateTime createdTime {get;set;}

           /// <summary>
           /// Desc:
           /// Default:
           /// Nullable:False
           /// </summary>           
           public string linkedId {get;set;}

           /// <summary>
           /// Desc:
           /// Default:
           /// Nullable:True
           /// </summary>           
           public string? commentReply {get;set;}
    }
}
