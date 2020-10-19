package co.synext.module.system.vo;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * TRoleVO对象
 * </p>
 *
 * @author xu.ran
 * @since 2020-08-27
 */
@Data
@Accessors(chain = true)
@ApiModel(value="TRoleVO对象", description="角色")
public class RoleVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 角色ID
    */
    @ApiModelProperty(value = "1-角色ID" )
    private String id;
    /**
    * 角色名称
    */
    @ApiModelProperty(value = "2-角色名称" )
    private String name;
    /**
    * 权限编码
    */
    @ApiModelProperty(value = "3-权限编码" )
    private String code;
    /**
    * 角色的部门是否私有,0否,1是,默认0.当角色私有时,菜单只使用此角色的部门权限,不再扩散到全局角色权限,用于设置特殊的菜单权限.公共权限时部门主管有所管理部门的数据全权限,无论角色是否分配. 私有部门权限时,严格按照配置的数据执行,部门主管可能没有部门权限.
    */
    @ApiModelProperty(value = "4-角色的部门是否私有,0否,1是,默认0.当角色私有时,菜单只使用此角色的部门权限,不再扩散到全局角色权限,用于设置特殊的菜单权限.公共权限时部门主管有所管理部门的数据全权限,无论角色是否分配. 私有部门权限时,严格按照配置的数据执行,部门主管可能没有部门权限." )
    private Integer privateOrg;
    /**
    * 0自己的数据,1所在部门,2所在部门及子部门数据,3.自定义部门数据,4全部权限
    */
    @ApiModelProperty(value = "5-0自己的数据,1所在部门,2所在部门及子部门数据,3.自定义部门数据,4全部权限" )
    private Integer roleOrgType;
    /**
    * 角色的归属部门,只有归属部门的主管和上级主管才可以管理角色,其他人员只能增加归属到角色的人员.不能选择部门或则其他操作,只能添加人员,不然存在提权风险,例如 员工角色下有1000人, 如果给 角色 设置了部门,那这1000人都起效了.
    */
    @ApiModelProperty(value = "6-角色的归属部门,只有归属部门的主管和上级主管才可以管理角色,其他人员只能增加归属到角色的人员.不能选择部门或则其他操作,只能添加人员,不然存在提权风险,例如 员工角色下有1000人, 如果给 角色 设置了部门,那这1000人都起效了." )
    private Long orgid;
    /**
    * 角色是否共享,0否 1是,默认0,共享的角色可以被下级部门直接使用,但是下级只能添加人员,不能设置其他属性.共享的角色一般只设置roleOrgType,并不设定部门.
    */
    @ApiModelProperty(value = "7-角色是否共享,0否 1是,默认0,共享的角色可以被下级部门直接使用,但是下级只能添加人员,不能设置其他属性.共享的角色一般只设置roleOrgType,并不设定部门." )
    private Integer shareRole;
    /**
    * 排序,查询时倒叙排列
    */
    @ApiModelProperty(value = "8-排序,查询时倒叙排列" )
    private Integer sortno;
    /**
    * 备注
    */
    @ApiModelProperty(value = "9-备注" )
    private String remark;
    /**
    * 是否有效(0否,1是)
    */
    @ApiModelProperty(value = "10-是否有效(0否,1是)" )
    private Integer active;
    
    @ApiModelProperty(value = "是否有子角色" )
    private boolean hasChildren;
    @ApiModelProperty(value = "权限IDs" )
    private List<Long> permissionIds;
    @ApiModelProperty(value = "权限名称" )
    private List<String> permissionNames;


}
