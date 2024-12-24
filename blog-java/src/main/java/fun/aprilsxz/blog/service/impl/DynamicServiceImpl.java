package fun.aprilsxz.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import fun.aprilsxz.blog.domain.common.PageResult;
import fun.aprilsxz.blog.domain.dto.DynamicDto;
import fun.aprilsxz.blog.domain.po.Comment;
import fun.aprilsxz.blog.domain.po.Dynamic;
import fun.aprilsxz.blog.domain.vo.DynamicVO;
//import fun.aprilsxz.blog.enums.LinkedType;
import fun.aprilsxz.blog.service.CommentService;
import fun.aprilsxz.blog.service.DynamicService;
import fun.aprilsxz.blog.mapper.DynamicMapper;
import fun.aprilsxz.blog.service.ObsService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.UUID;

/**
* @author yang
* @description 针对表【dynamic】的数据库操作Service实现
* @createDate 2024-12-11 14:25:57
*/
@Service
public class DynamicServiceImpl extends ServiceImpl<DynamicMapper, Dynamic>
    implements DynamicService{

    @Resource
    DynamicMapper dynamicMapper;
    @Resource
    CommentService commentService;
    @Resource
    ObsService obsService;

    @Override
    public PageResult<DynamicVO> queryByCategoryId(Integer categoryId, Integer currentPage, Integer pageSize) {
        PageHelper.startPage(currentPage,pageSize);
        Page<DynamicVO> dynamicVOPage = (Page<DynamicVO>) dynamicMapper.queryByCategoryId(categoryId);

        PageResult<DynamicVO> pageResult = new PageResult<>();
        pageResult.setPages(dynamicVOPage.getPages());
        pageResult.setTotal(dynamicVOPage.getTotal());
        pageResult.setList(dynamicVOPage.getResult());

        System.out.println(pageResult);

        return pageResult;
    }

    @Override
    public DynamicVO queryDetailById(String dynamicId) {

        return dynamicMapper.queryDetailById(dynamicId);
    }

    @Override
    public void addDynamic(DynamicDto dynamicDto) {
        Dynamic dynamic = new Dynamic();
        BeanUtils.copyProperties(dynamicDto,dynamic);
        dynamic.setDynamicId(UUID.randomUUID().toString());
        dynamic.setCreateTime(LocalDateTime.now());

        dynamicMapper.insert(dynamic);
    }

    @Override
    @Transactional
    public void removeByDynamicId(String dynamicId) {
        //1.查询出动态并将其删除
        Dynamic dynamic = getById(dynamicId);
        //如果不存在dynamic，直接返回
        if(dynamic == null){
            return;
        }
        //删除动态
        removeById(dynamicId);
        //2.删除相关评论
        commentService.remove(new QueryWrapper<Comment>()
                .eq("link_id",dynamicId)
//                .eq("link_type", LinkType.DYNAMIC));
        );
        //3.删除obs上的图片(图片存储格式)
        String dynamicImages = dynamic.getDynamicImages();
        obsService.deleteObject(dynamicImages);

    }
}




