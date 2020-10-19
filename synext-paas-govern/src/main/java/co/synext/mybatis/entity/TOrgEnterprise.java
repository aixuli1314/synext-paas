package co.synext.mybatis.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *  企业信息表,企业实际是t_org部门,orgType=1.给部门创建一个账号,作为主管,用于登录.附件关联附件表t_attachment
 * </p>
 *
 * @author xu.ran
 * @since 2020-09-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TOrgEnterprise implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * id主键,和orgId保持一致
     */
    private String id;

    /**
     * URL路径中的部门类型,例如 URL路径中的 kjj 
     */
    @TableField("orgTypePathKey")
    private String orgTypePathKey;

    /**
     * 企业名称,和从t_org的name字段同步过来,和营业执照保持一致
     */
    private String name;

    /**
     * 联系地址
     */
    @TableField("contactAddress")
    private String contactAddress;

    /**
     * 联系人姓名
     */
    @TableField("contactPersonName")
    private String contactPersonName;

    /**
     * 联系电话
     */
    @TableField("contactPhone")
    private String contactPhone;

    /**
     * 联系邮箱
     */
    @TableField("contactEmail")
    private String contactEmail;

    /**
     * 营业执照类型,例如:有限责任
     */
    @TableField("bType")
    @JsonProperty(value = "bType")
    private String bType;

    /**
     * 营业执照注册地
     */
    @TableField("bAddress")
    @JsonProperty(value = "bAddress")
    private String bAddress;

    /**
     * 注册法人
     */
    @TableField("bLegalPerson")
    @JsonProperty(value = "bLegalPerson")
    private String bLegalPerson;

    /**
     * 注册资本
     */
    @TableField("bCapital")
    @JsonProperty(value = "bCapital")
    private String bCapital;

    /**
     * 成立日期
     */
    @TableField("bCreateTime")
    @JsonProperty(value = "bCreateTime")
    private String bCreateTime;

    /**
     * 营业期限开始时间
     */
    @TableField("bTermStart")
    @JsonProperty(value = "bTermStart")
    private String bTermStart;

    /**
     * 营业期限结束时间
     */
    @TableField("bTermEnd")
    @JsonProperty(value = "bTermEnd")
    private String bTermEnd;

    /**
     * 经营范围
     */
    @TableField("bScope")
    @JsonProperty(value = "bScope")
    private String bScope;

    /**
     * 排序,查询时倒叙排列
     */
    private Integer sortno;

    /**
     * 是否有效(0否,1是)
     */
    private Integer active;


}
