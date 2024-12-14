package fun.aprilsxz.blog.domain.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserLoginVO {
    /**
     * 用户id
     */
    @TableId
    private String userId;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 个性签名
     */
    private String signature;

    /**
     * 用户头像
     */
    private String avatar;

    private String token;
}
