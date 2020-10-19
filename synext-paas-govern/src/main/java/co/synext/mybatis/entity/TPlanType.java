package co.synext.mybatis.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 计划类别,课题申报的是planType=0
 * </p>
 *
 * @author xu.ran
 * @since 2020-09-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_plan_Type")
public class TPlanType implements Serializable {

    private static final long serialVersionUID=1L;

    private String id;

    /**
     * URL路径中的部门类型,例如 URL路径中的 kjj 
     */
    @TableField("orgTypePathKey")
    private String orgTypePathKey;

    /**
     * 计划类别.0课题申报
     */
    @TableField("planType")
    private Integer planType;

    /**
     * 名称
     */
    private String name;

    /**
     * 创建时间
     */
    @TableField("createTime")
    private String createTime;

    /**
     * 创建人
     */
    @TableField("createUserId")
    private String createUserId;

    /**
     * 是否有效(0否,1是)
     */
    private Integer active;


}
