package co.synext.mybatis.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.google.common.base.Objects;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * <p>
 * 部门角色管理表
 * </p>
 *
 * @author xu.ran
 * @date 2019-09-17
 */
@ApiModel(value="BaseOrgRole对象", description="部门角色管理表")
@ToString
@Data
@TableName(value = "t_role_org")
public class TOrgRole implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "部门id")
    @TableField(value = "orgId")
    private String orgId;

    @ApiModelProperty(value = "角色id")
    @TableField(value = "roleId")
    private String roleId;

    @ApiModelProperty(value = "0不包含子部门,1包含.用于表示角色和部门的权限关系")
    private Integer children;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TOrgRole tOrgRole = (TOrgRole) o;
        return Objects.equal(orgId, tOrgRole.orgId) &&
                Objects.equal(roleId, tOrgRole.roleId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(orgId, roleId);
    }
}
