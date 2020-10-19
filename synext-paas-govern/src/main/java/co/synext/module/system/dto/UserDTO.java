package co.synext.module.system.dto;

import co.synext.common.base.convert.Converter;
import co.synext.common.base.dto.BaseDTO;
import co.synext.common.utils.WarpsUtils;
import co.synext.mybatis.entity.TRole;
import co.synext.mybatis.entity.TUser;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.List;


@Data
@ToString
public class UserDTO extends BaseDTO<UserDTO, TUser> {

    @ApiModelProperty(value = "1-当前页码")
    public Integer current = 1;

    @ApiModelProperty(value = "2-分页数量")
    public Integer size = 20;

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
    @NotNull
    private String account;

    /**
     * 密码
     */
    @NotNull
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
     * 0访客,1注册企业,2企业个人,3专家,4科技局后台,9系统管理员',
     */

    private Integer userType = 0;

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

    private List<Long> roleIds;

    private List<String> deptIds;
    /**
     * 用户组织机构管理类型,0会员,1员工,2主管
     */
    private Integer userOrgManagerType;
    /**
     * 租户ID
     */
    private String tenantId;

    public Page<TUser> page() {
        return new Page<>(current, size);
    }

    public static Converter<UserDTO, TUser> converter = new Converter<UserDTO, TUser>() {
        @Override
        public TUser doForward(UserDTO userDTO) {
            return WarpsUtils.copyTo(userDTO,TUser.class);
        }

        @Override
        public UserDTO doBackward(TUser tUser) {
            return WarpsUtils.copyTo(tUser,UserDTO.class);
        }
    };
    @Override
    public TUser convertToEntity() {
        return converter.doForward(this);
    }

    @Override
    public UserDTO convertFor(TUser tUser) {
        return converter.doBackward(tUser);
    }
}
