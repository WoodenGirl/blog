package fun.aprilsxz.blog.service;

import fun.aprilsxz.blog.domain.dto.DynamicDto;
import fun.aprilsxz.blog.domain.po.Dynamic;
import com.baomidou.mybatisplus.extension.service.IService;
import fun.aprilsxz.blog.domain.vo.DynamicVO;

import java.util.List;

/**
* @author yang
* @description 针对表【dynamic】的数据库操作Service
* @createDate 2024-12-11 14:25:57
*/
public interface DynamicService extends IService<Dynamic> {

    List<DynamicVO> queryByCategoryId(Integer categoryId);

    void addDynamic(DynamicDto dynamicDto);
}
