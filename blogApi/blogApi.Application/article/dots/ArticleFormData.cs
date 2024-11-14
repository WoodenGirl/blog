using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace blogApi.Application.article.dots
{
    public class ArticleFormData
    {
        public IFormFile articleCover { get; set; }
        public string articleJson { get; set; }
    }
}
