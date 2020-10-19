package co.synext.mybatis.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.google.common.base.Objects;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * <p>
 * 用户组织管理表
 * </p>
 *
 * @author xu.ran
 * @date 2019-09-18
 */
@ApiModel(value="BaseUserOrg对象", description="用户组织管理表")
@ToString
@Data
public class TUserOrg {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "用户id")
    private String userId;
    @ApiModelProperty(value = "组织id")
    private String orgId;
    @ApiModelProperty(value = "管理类型,0会员,1员工,2主管")
    private Integer managerType;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TUserOrg tUserOrg = (TUserOrg) o;
        return Objects.equal(userId, tUserOrg.userId) &&
                Objects.equal(orgId, tUserOrg.orgId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(userId, orgId);
    }
}
