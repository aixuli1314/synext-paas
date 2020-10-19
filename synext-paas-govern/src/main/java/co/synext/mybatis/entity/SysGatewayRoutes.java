package co.synext.mybatis.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 网关路由
 * </p>
 *
 * @author xu.ran
 * @date 2019-07-18
 */
@ApiModel(value="SysGatewayRoutes对象", description="网关路由")
@Getter
@Setter
@ToString
public class SysGatewayRoutes implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    @NotNull
    @ApiModelProperty(value = "路由名称")
    private String routeName;

    @NotNull
    @ApiModelProperty(value = "路由ID")
    private String routeId;

    @NotNull
    @ApiModelProperty(value = "断言")
    private String predicates;

    @NotNull
    @ApiModelProperty(value = "过滤器")
    private String filters;

    @NotNull
    private String routeUri;

    @ApiModelProperty(value = "排序")
    private Integer routeOrder;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

}
