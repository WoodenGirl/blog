package fun.aprilsxz.blog.mapper;

import fun.aprilsxz.blog.domain.po.Dynamic;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import fun.aprilsxz.blog.domain.vo.DynamicVO;

import java.util.List;

/**
* @author yang
* @description 针对表【dynamic】的数据库操作Mapper
* @createDate 2024-12-11 14:25:57
* @Entity fun.aprilsxz.blog.domain.po.Dynamic
*/
public interface DynamicMapper extends BaseMapper<Dynamic> {

    List<DynamicVO> queryByCategoryId(Integer categoryId);

     DynamicVO queryDetailById(String dynamicId);
}




