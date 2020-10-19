package co.synext.mybatis.entity;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户基础信息
 * </p>
 *
 * @author xu.ran
 * @since 2020-08-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TUser implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * ID
     */
    private String id;

    /**
     * 姓名
     */
    private String userName;

    /**
     * 账号
     */
    private String account;

    /**
     * 密码
     */
    private String password;

    /**
     * 性别
     */
    private String sex;

    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 微信openId
     */
    private String openid;

    /**
     * 微信UnionID
     */
    private String unionid;

    /**
     * 头像地址
     */
    private String avatar;

    /**
     * 0访客,1注册企业管理员,2企业个人,3专家,4科技局后台,9系统管理员'
     */
    private Integer userType;

    /**
     * 如果属于企业用户，填写所属企业id
     */
    private String enterpriseId;

    /**
     * 是否有效(0否,1是)
     */
    private Integer active;
    /**
     * URL路径中的部门类型,例如 URL路径中的 kjj
     */
    private String orgTypePathKey;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 更新时间
     */
    private String updateTime;
    /**
     * 创建者编号
     */
    private String createUserId;
    /**
     * 更新者编号
     */
    private String updateUserId;
    /**
     * 租户ID
     */
    private String tenantId;
}
