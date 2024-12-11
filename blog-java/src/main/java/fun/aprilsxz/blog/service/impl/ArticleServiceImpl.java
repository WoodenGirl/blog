package fun.aprilsxz.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fun.aprilsxz.blog.domain.dto.ArticleDto;
import fun.aprilsxz.blog.domain.po.Article;
import fun.aprilsxz.blog.domain.vo.ArticleVO;
import fun.aprilsxz.blog.service.ArticleService;
import fun.aprilsxz.blog.mapper.ArticleMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
* @author yang
* @description 针对表【article】的数据库操作Service实现
* @createDate 2024-12-11 14:25:37
*/
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article>
    implements ArticleService{
    @Resource
    ArticleMapper articleMapper;

    @Override
    public List<ArticleVO> queryByCategoryId(Integer categoryId) {
        return articleMapper.queryByCategoryId(categoryId);
    }

    @Override
    public void addArticle(ArticleDto articleDto) {
        Article article = new Article();
        BeanUtils.copyProperties(articleDto,article);
        article.setArticleId(UUID.randomUUID().toString());
        article.setCreateTime(LocalDateTime.now());

        articleMapper.insert(article);
    }

    @Override
    public void updateArticle(ArticleDto articleDto) {
        Article article = new Article();
        BeanUtils.copyProperties(articleDto,article);
        article.setUpdateTime(LocalDateTime.now());

        articleMapper.updateById(article);
    }
}




