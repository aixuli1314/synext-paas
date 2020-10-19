package co.synext.mybatis.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 消息模板表
 * </p>
 *
 * @author xu.ran
 * @since 2020-09-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TSendmessageTemplate implements Serializable {

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
     * 名称
     */
    private String name;

    /**
     * 归属的部门,哪些部门有权限管理使用模板
     */
    @TableField("orgId")
    private String orgId;

    /**
     * 发送方式使用,号隔开.0站内信,1邮件,2短信,3微信
     */
    @TableField("sendType")
    private String sendType;

    /**
     * 内容
     */
    private String content;

    /**
     * 排序,查询时倒叙排列
     */
    private Integer sortno;

    /**
     * 是否有效(0否,1是)
     */
    private Integer active;


}
