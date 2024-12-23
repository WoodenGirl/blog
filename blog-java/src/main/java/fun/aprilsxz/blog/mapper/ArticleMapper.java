package fun.aprilsxz.blog.mapper;

import fun.aprilsxz.blog.domain.po.Article;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import fun.aprilsxz.blog.domain.vo.ArticleBrief;
import fun.aprilsxz.blog.domain.vo.ArticleDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author yang
* @description 针对表【article】的数据库操作Mapper
* @createDate 2024-12-11 14:25:37
* @Entity fun.aprilsxz.blog.domain.po.Article
*/
@Mapper
public interface ArticleMapper extends BaseMapper<Article> {

    List<ArticleBrief> queryByCategoryId(Integer categoryId);

    ArticleDetail queryDetailById(String articleId);
}




