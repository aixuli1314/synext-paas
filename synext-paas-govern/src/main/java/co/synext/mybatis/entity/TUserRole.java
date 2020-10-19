package co.synext.mybatis.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.google.common.base.Objects;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * <p>
 * 用户角色管理表
 * </p>
 *
 * @author xu.ran
 * @date 2019-07-21
 */
@ApiModel(value="BaseUserRole对象", description="用户角色管理表")
@ToString
@Data
public class TUserRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @ApiModelProperty(value = "用户id")
    private String userId;

    @ApiModelProperty(value = "角色id")
    private String roleId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TUserRole that = (TUserRole) o;
        return Objects.equal(userId, that.userId) &&
                Objects.equal(roleId, that.roleId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(userId, roleId);
    }
}
