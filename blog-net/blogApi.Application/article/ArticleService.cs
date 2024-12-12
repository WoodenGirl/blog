using blogApi.Application.article.dots;
using blogApi.Application.comment.dots;
using blogApi.Core;
using blogApi.Core.entity;
using blogApi.Core.sqlsugar;


namespace blogApi.Application.article
{
    public class ArticleService : IDynamicApiController
    {
        public Repository<Article> _articleRepository => new Repository<Article>();
        public Repository<Category> _categoryRepository => new Repository<Category>();


        public ResultVo Query()
        {
            var db = _articleRepository.Context;
            List<ArticleView> articles = db.Queryable<ArticleView>().ToList();
            return ResultVo.success(articles);
        }

        [HttpGet("categoryId")]
        public ResultVo QueryByCategoryId(int categoryId)
        {
            var db = _articleRepository.Context;
            List<ArticleView> articles = db.Queryable<ArticleView>().Where(it => it.categoryId == categoryId).ToList();
            return ResultVo.success(articles);
        }
        [HttpPost]
        public ResultVo Add(Article article)
        {
            // id
            article.articleId = Tool.GenerateId("article");
            // 存储数据库
            _articleRepository.Insert(article);
            return ResultVo.success(article.articleId);
        }
        public ResultVo Delete(string articleId)
        {
            _articleRepository.DeleteById(articleId);
            return ResultVo.success();
        }
        public ResultVo Update(Article article)
        {         
            _articleRepository.Update(article);
            return ResultVo.success(article);
        }
    }
}
