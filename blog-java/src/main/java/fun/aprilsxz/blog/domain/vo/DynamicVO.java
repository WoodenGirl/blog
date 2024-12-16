package fun.aprilsxz.blog.domain.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DynamicVO {
    private String dynamicId;

    private String userId;

    private String nickname;

    private String avatar;

    private Integer categoryId;

    private String categoryName;

    private String dynamicContent;

    private String dynamicImages;

    private Integer status;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
