package fun.aprilsxz.blog.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LinkedType {
    ARTICLE(0, "文章"),
    DYNAMIC(1, "动态"),
    ;
    @EnumValue
    final int value;
    final String desc;

}
