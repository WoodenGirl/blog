package fun.aprilsxz.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fun.aprilsxz.blog.domain.dto.DynamicDto;
import fun.aprilsxz.blog.domain.po.Dynamic;
import fun.aprilsxz.blog.domain.vo.DynamicVO;
import fun.aprilsxz.blog.service.DynamicService;
import fun.aprilsxz.blog.mapper.DynamicMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
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

    @Override
    public List<DynamicVO> queryByCategoryId(Integer categoryId) {
        return dynamicMapper.queryByCategoryId(categoryId);
    }

    @Override
    public void addDynamic(DynamicDto dynamicDto) {
        Dynamic dynamic = new Dynamic();
        BeanUtils.copyProperties(dynamicDto,dynamic);
        dynamic.setDynamicId(UUID.randomUUID().toString());
        dynamic.setCreateTime(LocalDateTime.now());

        dynamicMapper.insert(dynamic);
    }
}




