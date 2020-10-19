package co.synext.module.system.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * TUserExpertVO对象
 * </p>
 *
 * @author xu.ran
 * @since 2020-09-08
 */
@Data
@Accessors(chain = true)
@ApiModel(value="TUserExpertVO对象", description="专家信息表,是t_user表的扩展表,userType=3")
public class UserExpertVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * id主键,和t_user的ID保持一致
    */
    @ApiModelProperty(value = "1-id主键,和t_user的ID保持一致" )
    private String id;
    /**
    * URL路径中的部门类型,例如 URL路径中的 kjj 
    */
    @ApiModelProperty(value = "2-URL路径中的部门类型,例如 URL路径中的 kjj " )
    private String orgTypePathKey;
    /**
    * 专家名称,和从t_user的name字段同步过来
    */
    @ApiModelProperty(value = "3-专家名称,和从t_user的name字段同步过来" )
    private String name;
    /**
    * 单位名称
    */
    @ApiModelProperty(value = "4-单位名称" )
    private String unitName;
    /**
    * 注册状态,0资料保存未提交,1资料已提交,2内部审核中,3驳回,4申请成功
    */
    @ApiModelProperty(value = "5-注册状态,0资料保存未提交,1资料已提交,2内部审核中,3驳回,4申请成功" )
    private Integer status;
    /**
    * 排序,查询时倒叙排列
    */
    @ApiModelProperty(value = "6-排序,查询时倒叙排列" )
    private Integer sortno;
    /**
    * 是否有效(0否,1是)
    */
    @ApiModelProperty(value = "7-是否有效(0否,1是)" )
    private Integer active;

}
