package co.synext.mybatis.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.google.common.base.Objects;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * <p>
 * 角色菜单表
 * </p>
 *
 * @author xu.ran
 * @date 2019-07-21
 */
@ApiModel(value="SysRoleMenu对象", description="角色菜单表")
@Getter
@Setter
@ToString
public class TRoleMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id")
    private String id;
    
    private String roleId;

    private String menuId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TRoleMenu that = (TRoleMenu) o;
        return Objects.equal(roleId, that.roleId) &&
                Objects.equal(menuId, that.menuId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(roleId, menuId);
    }
}
