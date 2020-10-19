package co.synext.module.Activiti.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import co.synext.module.system.vo.DicDataVO;
import co.synext.mybatis.entity.TDicData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * TDicDataVO对象
 * </p>
 *
 * @author xu.ran
 * @since 2020-08-27
 */
@Data
@Accessors(chain = true)
@ApiModel(value="DeploymentVO对象", description="流程部署实例，页面展示数据")
public class DeploymentVO implements Serializable{
		private static final long serialVersionUID = 1L;
		/**
	    * 流程部署ID
	    */
	    @ApiModelProperty(value = "1-流程部署ID" )
		private String id;
	    /**
	    * 流程部署名称
	    */
	    @ApiModelProperty(value = "2-流程部署名称" )
	    private String name;
	    /**
	    * 类型
	    */
	    @ApiModelProperty(value = "3-类型" )
	    private String category ;
	    /**
	    * 部署时间
	    */
	    @ApiModelProperty(value = "4-部署时间" )
	    private Date deploymentTime ;
	    /**
	    * 主键key
	    */
	    @ApiModelProperty(value = "5-主键key" )
	    private String key ;
		/**
	    * 租户ID
	    */
	    @ApiModelProperty(value = "6-租户ID" )
	    private String tenantId;
}
