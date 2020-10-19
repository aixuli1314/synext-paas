package co.synext.mybatis.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 注册流程,关联t_user和t_org,有专家注册和企业注册,企业注册.注册成功之后,短信通知
 * </p>
 *
 * @author xu.ran
 * @since 2020-09-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TRegisteredFlow implements Serializable {

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
     * 用户ID
     */
    @TableField("userId")
    private String userId;

    /**
     * 用户类型:1注册企业,2企业个人,3专家
     */
    @TableField("userType")
    private String userType;

    /**
     * 业务模块名称,例如专家注册
     */
    @TableField("businessModule")
    private String businessModule;

    /**
     * 创建时间
     */
    @TableField("createTime")
    private String createTime;

    /**
     * 提交时间
     */
    @TableField("submitTime")
    private String submitTime;

    /**
     * 审批时间
     */
    @TableField("approvalDate")
    private String approvalDate;

    /**
     * 审批人员ID
     */
    @TableField("approvalUserId")
    private String approvalUserId;

    /**
     * 驳回时间
     */
    @TableField("rejectionDate")
    private String rejectionDate;

    /**
     * 驳回说明
     */
    @TableField("rejectionDescription")
    private String rejectionDescription;

    /**
     * 上报状态:0已填写未提交,1已提交,2,已审核,3已驳回,6已超期
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
