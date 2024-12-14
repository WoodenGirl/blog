package fun.aprilsxz.blog.domain.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import fun.aprilsxz.blog.enums.UserStatus;
import lombok.Builder;
import lombok.Data;

/**
 * 
 * @TableName user
 */
@TableName(value ="user")
@Data
@Builder
public class User implements Serializable {
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
     * 用户密码
     */
    private String password;

    /**
     * 随机盐值加密
     */
    private String salt;

    /**
     * 个性签名
     */
    private String signature;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 用户角色：0管理员，1普通用户
     */
    private Integer role;

    /**
     * 账号状态：0冻结，1正常
     */
    private UserStatus status;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}