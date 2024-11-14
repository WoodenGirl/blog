using blogApi.Application.article.dots;
using blogApi.Core.entity;
using blogApi.Core.sqlsugar;
using blogApi.Core.Tool;
using entity;
using Newtonsoft.Json;


namespace blogApi.Application.article
{
    public class ArticleService : IDynamicApiController
    {
        public Repository<Article> _articleRepository => new Repository<Article>();
        public Repository<Category> _categoryRepository => new Repository<Category>();     


        public ResultVo Query()
        {
            var db = _articleRepository.Context;
            string viewSql = "SELECT * FROM view_article"; 
            List<ArticleView> articles = db.Ado.SqlQuery<ArticleView>(viewSql);        
            return ResultVo.success(articles);
        }

        [HttpGet("categoryId")]
        public ResultVo QueryByCategoryId(int categoryId)
        {
            var db = _articleRepository.Context;
            string viewSql = "SELECT * FROM view_article where categoryId =" + categoryId;
            List<ArticleView> articles = db.Ado.SqlQuery<ArticleView>(viewSql);                    
            return ResultVo.success(articles);
        }
        [HttpPost]
        public ResultVo Add([FromForm] ArticleFormData formData)
        {
            Article article = JsonConvert.DeserializeObject<Article>(formData.articleJson);
            article.articleCover = ImgTool.UploadImage(formData.articleCover);
            if (article.articleCover == null) return ResultVo.error(201, "图片上传失败");
            article.createdTime = DateTime.Now;
            article.updateTime = DateTime.Now;
            // 存储数据库
            _articleRepository.Insert(article);
            return ResultVo.success();
        }
        public ResultVo Delete(Article article)
        {
            _articleRepository.DeleteById(article.articleId);
            return ResultVo.success();
        }
        public ResultVo Update(Article article)
        {
            _articleRepository.Update(article);
            return ResultVo.success();
        }
    }
}
