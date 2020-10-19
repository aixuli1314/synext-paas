package co.synext.module.Activiti.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import co.synext.mybatis.entity.TApplyFlow;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * <p>
 * DicDataQueryDTO对象
 * 公共的字典,通过pathkey区分不通的字典类型,例如 民族字典 pathkey 的值都是minzu, controller通过 /api/dicdata/{pathkey}/list 进行查看
 * </p>
 *
 * @author xu.ran
 * @since 2020-08-27
 */
@Data
@Accessors(chain = true)
@ToString
@ApiModel(value="ActivitQueryDTO对象")
public class DeploymentQueryDTO  implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "1-当前页码")
    public Integer current = 1;

    @ApiModelProperty(value = "2-分页数量")
    public Integer size = 20;

    /**
    * 租户ID
    */
    @ApiModelProperty(value = "租户ID" ,required = true)
    @NotNull
    private String tenantId;
    /**
     * 流程名称
     */
    @ApiModelProperty(value = "流程名称")
    private String deploymentName;
    /**
     * 流程文件路径
     */
    @ApiModelProperty(value = "流程文件路径")
    private String deploymentPath;
   

    public Page page() {
        return new Page(current, size);
    }
    public Page<TApplyFlow> page(int totle) {
    	Page<TApplyFlow> page = page();
    	page.setTotal(totle);
    	return page;
    }
}
