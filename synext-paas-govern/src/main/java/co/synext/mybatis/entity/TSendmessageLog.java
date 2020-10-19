package co.synext.mybatis.entity;

import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 消息发送记录表
 * </p>
 *
 * @author xu.ran
 * @since 2020-09-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TSendmessageLog implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * id主键
     */
    private String id;

    /**
     * URL路径中的部门类型,例如 URL路径中的 kjj 
     */
    @TableField("orgTypePathKey")
    private String orgTypePathKey;

    /**
     * 模板Id
     */
    @TableField("templateId")
    private String templateId;

    /**
     * 发送消息的业务模块,例如 单位注册模块
     */
    @TableField("businessModule")
    private String businessModule;

    /**
     * 发送模板消息的业务记录Id,比如t_user_20200808 这样的记录ID
     */
    @TableField("businessId")
    private String businessId;

    /**
     * 发送方式,一个类型一条记录,0站内信,1邮件,2短信,3微信
     */
    @TableField("sendType")
    private Integer sendType;

    /**
     * 模板Id
     */
    @TableField("sendTime")
    private String sendTime;

    /**
     * 发送成功时间
     */
    @TableField("successTime")
    private String successTime;

    /**
     * 发送错误时间
     */
    @TableField("errorTime")
    private String errorTime;

    /**
     * 读取时间
     */
    @TableField("readTime")
    private String readTime;

    /**
     * 发送人
     */
    @TableField("fromUserId")
    private String fromUserId;

    /**
     * 接收人Id
     */
    @TableField("toUserId")
    private String toUserId;

    /**
     * 内容
     */
    private String content;

    /**
     * 如果发送失败,记录错误原因
     */
    @TableField("errorMessage")
    private String errorMessage;

    /**
     * 消息状态.0已创建,1发送成功,2.发送失败,3已读取
     */
    private Integer status;

    /**
     * 排序,查询时倒叙排列
     */
    private Integer sortno;

    /**
     * 是否有效(0否,1是)
     */
    private Integer active;


}
