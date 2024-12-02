using blogApi.Application.article.dots;
using blogApi.Core;
using blogApi.Core.entity;
using blogApi.Core.obs;
using blogApi.Core.sqlsugar;
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
            // id
            article.articleId = Tool.GenerateId("article");
            // cover
            article.articleCover = "test.jpg";
            article.articleCover = ObsOperate.UploadImage(formData.articleCover);
            if (article.articleCover == "101") return ResultVo.error(101, "图片不存在");
            else if (article.articleCover == "201") return ResultVo.error(201, "图片上传失败");
            // 存储数据库
            _articleRepository.Insert(article);
            return ResultVo.success(article.articleId);
        }
        public ResultVo Delete(int articleId)
        {
            _articleRepository.DeleteById(articleId);
            return ResultVo.success();
        }
        public ResultVo Update([FromForm] ArticleFormData formData)
        {
            Article article = JsonConvert.DeserializeObject<Article>(formData.articleJson);
            // 图像处理
            article.articleCover = "test";
            _articleRepository.Update(article);
            return ResultVo.success(article);
        }
    }
}
