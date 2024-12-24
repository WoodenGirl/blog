package fun.aprilsxz.blog.domain.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.List;

@Data
@EqualsAndHashCode
public class CommentVO {
    /**
     * 主键
     */
    private Integer commentId;

    /**
     * 父评论id
     */
    private Integer commentParent;

    /**
     *
     */
    private String userId;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 用户名
     */
    private String nickName;

    /**
     * 评论内容
     */
    private String commentContent;

    /**
     * 回复的用户id
     */
    private String commentReply;

    /**
     *
     */
    private String linkId;

    private List<CommentVO> commentChildren;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;
}
