package fun.aprilsxz.blog.service;

import fun.aprilsxz.blog.domain.dto.ArticleDto;
import fun.aprilsxz.blog.domain.po.Article;
import com.baomidou.mybatisplus.extension.service.IService;
import fun.aprilsxz.blog.domain.vo.ArticleVO;

import java.util.List;

/**
* @author yang
* @description 针对表【article】的数据库操作Service
* @createDate 2024-12-11 14:25:37
*/
public interface ArticleService extends IService<Article> {

    /**
     * 通过categoryId查询文章
     * @param categoryId
     * @return
     */
    List<ArticleVO> queryByCategoryId(Integer categoryId);

    void addArticle(ArticleDto articleDto);

    void updateArticle(ArticleDto articleDto);
}
