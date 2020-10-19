package co.synext.module.system.vo;

import co.synext.common.base.vo.UserVo;
import co.synext.mybatis.entity.TUser;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * TOrgVO对象
 * </p>
 *
 * @author xu.ran
 * @since 2020-08-27
 */
@Data
@Accessors(chain = true)
@ApiModel(value="TOrgVO对象", description="部门")
public class OrgVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 编号
    */
    @ApiModelProperty(value = "1-编号" )
    private String id;
    /**
    * 名称
    */
    @ApiModelProperty(value = "2-名称" )
    private String name;
    /**
    * 路径代码,用于标识部门路径,例如 pid是root,此部门的comcode是  ,root,myorgid,
    */
    @ApiModelProperty(value = "3-路径代码,用于标识部门路径,例如 pid是root,此部门的comcode是  ,root,myorgid," )
    private String comcode;
    /**
    * 上级部门ID
    */
    @ApiModelProperty(value = "4-上级部门ID" )
    private String pid;
    /**
    * 0科技局
    */
    @ApiModelProperty(value = "5-0科技局" )
    private Integer orgType;
    /**
    * 排序,查询时倒叙排列
    */
    @ApiModelProperty(value = "6-排序,查询时倒叙排列" )
    private Integer sortno;
    /**
    * 备注
    */
    @ApiModelProperty(value = "7-备注" )
    private String remark;
    /**
    * 是否有效(0否,1是)
    */
    @ApiModelProperty(value = "8-是否有效(0否,1是)" )
    private Integer active;

    @ApiModelProperty(value = "9-组织下用户")
    private List<UserVo> users;

    List<OrgVO> children;

}
