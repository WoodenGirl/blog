package fun.aprilsxz.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import fun.aprilsxz.blog.domain.common.PageResult;
import fun.aprilsxz.blog.domain.dto.ArticleDto;
import fun.aprilsxz.blog.domain.po.Article;
import fun.aprilsxz.blog.domain.po.Comment;
import fun.aprilsxz.blog.domain.vo.ArticleBrief;
import fun.aprilsxz.blog.domain.vo.ArticleDetail;
import fun.aprilsxz.blog.exception.CommonException;
import fun.aprilsxz.blog.mapper.ArticleMapper;
import fun.aprilsxz.blog.mapper.CommentMapper;
import fun.aprilsxz.blog.service.ArticleService;
import fun.aprilsxz.blog.service.ObsService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Resource
    ObsService obsService;
    @Resource
    CommentMapper commentMapper;

    @Override
    public PageResult<ArticleBrief> queryByCategoryId(Integer categoryId, Integer currentPage, Integer pageSize) {
        PageHelper.startPage(currentPage,pageSize);
        Page<ArticleBrief> articleBriefPage = (Page<ArticleBrief>) articleMapper.queryByCategoryId(categoryId);

        List<ArticleBrief> result = articleBriefPage.getResult();
        /*result = result.stream().peek(new Consumer<ArticleBrief>() {
            @Override
            public void accept(ArticleBrief articleBrief) {
                if (articleBrief != null && StringUtils.hasLength(articleBrief.getArticleTags())) {
                    articleBrief.setTags(articleBrief.getArticleTags().split(","));
                }
            }
        }).collect(Collectors.toList());*/

        PageResult<ArticleBrief> pageResult = new PageResult<>();
        pageResult.setPages(articleBriefPage.getPages());
        pageResult.setTotal(articleBriefPage.getTotal());
        pageResult.setList(articleBriefPage.getResult());

        System.out.println(pageResult);

        return pageResult;
    }

    @Override
    public ArticleDetail queryDetailById(String articleId) {
        ArticleDetail articleDetail = articleMapper.queryDetailById(articleId);
        /*if(articleDetail != null && StringUtils.hasLength(articleDetail.getArticleTags())){
            articleDetail.setTags(articleDetail.getArticleTags().split(","));
        }*/
        return articleDetail;
    }

    @Override
    public void addArticle(ArticleDto articleDto) {
        Article article = new Article();
        BeanUtils.copyProperties(articleDto,article);
        article.setArticleId(UUID.randomUUID().toString());
        article.setCreateTime(LocalDateTime.now());

        //处理tags
        String[] articleTags = articleDto.getArticleTags();
        if(articleTags != null){
            article.setArticleTags(String.join(",",articleTags));
        }

        //将封面图片转移至articleCover
        String articleCover = articleDto.getArticleCover();
        String des = articleCover.replaceFirst("temp", "articleCover");
        boolean b = obsService.moveObject(articleCover, des);
        if(!b){
            throw new CommonException("封面上传失败",501);
        }
        article.setArticleCover(des);
        articleMapper.insert(article);
    }

    @Override
    public void updateArticle(ArticleDto articleDto) {
        Article article = new Article();
        BeanUtils.copyProperties(articleDto,article);
        article.setUpdateTime(LocalDateTime.now());
        //处理封面图片
        String articleCover = articleDto.getArticleCover();
        Article article1 = articleMapper.selectById(article.getArticleId());
        if(!article1.getArticleCover().equals(articleCover)){
            //删除原封面
            obsService.deleteObject(article1.getArticleCover());
            //更新新封面
            while(!obsService.copyObject(articleCover, articleCover.replaceFirst("temp", "articleCover"))){}
            article.setArticleCover(articleCover.replaceFirst("temp","articleCover"));
        }
        articleMapper.updateById(article);
    }

    @Override
    @Transactional
    public void removeByArticleId(String articleId) {
        Article article = articleMapper.selectById(articleId);
        if(article == null){
            return;
        }
        //1.删除封面图片
        obsService.deleteObject(article.getArticleCover());
        //2.删除相关评论
        commentMapper.delete(new QueryWrapper<Comment>().eq("link_id",articleId));
        //3.删除文章
        articleMapper.deleteById(articleId);
    }
}




