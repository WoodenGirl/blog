package fun.aprilsxz.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import fun.aprilsxz.blog.domain.common.PageResult;
import fun.aprilsxz.blog.domain.dto.ArticleDto;
import fun.aprilsxz.blog.domain.po.Article;
import fun.aprilsxz.blog.domain.vo.ArticleBrief;
import fun.aprilsxz.blog.domain.vo.ArticleDetail;
import fun.aprilsxz.blog.domain.vo.DynamicVO;
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
    public PageResult<ArticleBrief> queryByCategoryId(Integer categoryId, Integer currentPage, Integer pageSize) {
        PageHelper.startPage(currentPage,pageSize);
        Page<ArticleBrief> articleBriefPage = (Page<ArticleBrief>) articleMapper.queryByCategoryId(categoryId);

        PageResult<ArticleBrief> pageResult = new PageResult<>();
        pageResult.setPages(articleBriefPage.getPages());
        pageResult.setTotal(articleBriefPage.getTotal());
        pageResult.setList(articleBriefPage.getResult());

        System.out.println(pageResult);

        return pageResult;
    }

    @Override
    public ArticleDetail queryDetailById(String articleId) {
        return articleMapper.queryDetailById(articleId);
    }

    @Override
    public void addArticle(ArticleDto articleDto) {
        Article article = new Article();
        BeanUtils.copyProperties(articleDto,article);
        article.setArticleId(UUID.randomUUID().toString());
        article.setCreateTime(LocalDateTime.now());
        article.setUpdateTime(LocalDateTime.now());
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




