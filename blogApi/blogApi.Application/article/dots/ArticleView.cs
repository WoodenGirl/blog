﻿
namespace blogApi.Application.article.dots
{
    public class ArticleView
    {
        public string articleId { get; set; }
        public int categoryId { get; set; }
        public string userId { get; set; }

        public string articleTitle { get; set; }

        public string nickname { get; set; }

        public DateTime createdTime { get; set; }

        public DateTime updateTime { get; set; }

        public string articleTags { get; set; }

        public string categoryDetail { get; set; }

        public string articleContent { get; set; }

        public string articleCover { get; set; }

        
    }
}