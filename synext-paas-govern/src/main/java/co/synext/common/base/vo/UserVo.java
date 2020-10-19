package co.synext.common.base.vo;

import java.io.Serializable;
import java.util.Set;

import co.synext.mybatis.entity.TRole;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserVo implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @ApiModelProperty(value = "账号id")
    private String id;
    
    @ApiModelProperty(value = "账号")
    private String account;
    
    @ApiModelProperty(value = "性别")
    private String sex;
    
    @ApiModelProperty(value = "openid")
    private String openid;
    
    @ApiModelProperty(value = "unionid")
    private String unionid;
    
    @ApiModelProperty(value = "头像")
    private String avatar;
    
    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ApiModelProperty(value = "用户姓名")
    private String userName;

    @ApiModelProperty(value = "密码")
    private String password;
    @ApiModelProperty(value = "租户ID")
    private String tenantId;

    @ApiModelProperty(value = "用户类型（0访客,1注册企业,2科技局后台,9系统管理员）")
    private Integer userType;

    @ApiModelProperty(value = "是否有效(0否,1是)")
    private Integer active;
    
    @ApiModelProperty(value = "用户拥有角色")
    private Set<TRole> roleNames;
    @ApiModelProperty(value = "用户企业ID")
    private String enterpriseId;
}
