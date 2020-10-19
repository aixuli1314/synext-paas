package co.synext.module.system.dto;


import co.synext.common.base.convert.Converter;
import co.synext.common.base.dto.BaseDTO;
import co.synext.common.utils.WarpsUtils;
import co.synext.mybatis.entity.TMenu;
import co.synext.mybatis.entity.TRole;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import javax.validation.constraints.NotNull;
import java.util.List;


@Data
@ToString
public class RoleDTO extends BaseDTO<RoleDTO, TRole> {


    @ApiModelProperty(value = "1-当前页码")
    public Integer current = 1;

    @ApiModelProperty(value = "2-分页数量")
    public Integer size = 20;

    private String id;

    @ApiModelProperty(value = "租户id" )
    private Long tenantId;

    @NotNull
    private String name;

    private String code;

    @ApiModelProperty(value = "角色的部门是否私有,0否,1是,默认0.当角色私有时,菜单只使用此角色的部门权限,"
            +"不再扩散到全局角色权限,用于设置特殊的菜单权限.公共权限时部门主管有所管理部门的数据全权限,"
            +"无论角色是否分配. 私有部门权限时,严格按照配置的数据执行,部门主管可能没有部门权限." )
    private int privateOrg;

    @ApiModelProperty(value = "0自己的数据,1所在部门,2所在部门及子部门数据,3.自定义部门数据,4全部权限")
    private int roleOrgType;

    @ApiModelProperty(value = "角色的归属部门,只有归属部门的主管和上级主管才可以管理角色,"
            + "其他人员只能增加归属到角色的人员.不能选择部门或则其他操作,只能添加人员,不然存在提权风险,"
            + "例如 员工角色下有1000人, 如果给 角色 设置了部门,那这1000人都起效了.")
    private String orgId;

    @ApiModelProperty(value = "角色是否共享,0否 1是,默认0,共享的角色可以被下级部门直接使用,但是下级只能添加人员,"
            + "不能设置其他属性.共享的角色一般只设置roleOrgType,并不设定部门.")
    private Integer shareRole;

    private String roleKey;

    @ApiModelProperty(value = "菜单类型(1，系统角色，2，租户角色)" )
    private Integer roleType;

    private String remark;

    private int active = 1;

    private boolean hasChildren;

    public Page<TRole> page() {
        return new Page<>(current, size);
    }

    @NotNull
    private List<String> permissionIds;


    public static Converter<RoleDTO, TRole> converter = new Converter<RoleDTO, TRole>() {
        @Override
        public TRole doForward(RoleDTO roleDTO) {
            return WarpsUtils.copyTo(roleDTO,TRole.class);
        }

        @Override
        public RoleDTO doBackward(TRole tRole) {
            return WarpsUtils.copyTo(tRole,RoleDTO.class);
        }
    };

    @Override
    public TRole convertToEntity() {
        return converter.doForward(this);
    }

    @Override
    public RoleDTO convertFor(TRole tRole) {
        return converter.doBackward(tRole);
    }
}
