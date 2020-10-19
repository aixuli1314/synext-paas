package co.synext.mybatis.entity;

import com.baomidou.mybatisplus.annotation.TableField;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 菜单,根目录id是root
 * </p>
 *
 * @author xu.ran
 * @since 2020-08-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TMenu implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "id")
    private String id;

    /**
     * 菜单名称
     */
    @ApiModelProperty(value = "菜单名称")
    private String name;

    /**
     * 路径代码,用于标识菜单路径,例如 pid是根目录root,此菜单的comcode是  ,root,mymenuid,
     */
    @ApiModelProperty(value = "路径代码,用于标识菜单路径,例如 pid是根目录root,此菜单的comcode是  ,root,mymenuid,")
    private String comcode;

    /**
     * 父级ID
     */
    @ApiModelProperty(value = "父级ID")
    private String pid;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * 跳转地址
     */
    @ApiModelProperty(value = "跳转地址")
    private String hrefurl;

    /**
     * 0.功能按钮,1.导航菜单,2.导航目录
     */
    @ApiModelProperty(value = "0.功能按钮,1.导航菜单,2.导航目录")
    @TableField("menu_type")
    private Integer menuType;

    /**
     * 图标样式
     */
    @ApiModelProperty(value = "是否有效(0否,1是)")
    private String icon;

    /**
     * 排序,查询时倒叙排列
     */
    @ApiModelProperty(value = "是否有效(0否,1是)")
    private Integer sortno;

    /**
     * 是否有效(0否,1是)
     */
    @ApiModelProperty(value = "是否有效(0否,1是)")
    private Integer active;

    @ApiModelProperty(value = "是否有子菜单")
    @TableField("has_child")
    private Integer hasChildren;
    
    @ApiModelProperty(value = "权限标识")
    private String permissionKey;


}
