package co.synext.module.system.dto;

import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import co.synext.mybatis.entity.TRegisteredFlow;
import co.synext.common.base.convert.Converter;
import co.synext.common.base.dto.BaseDTO;
import co.synext.common.utils.WarpsUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * RegisteredFlowQueryDTO对象
 * 注册流程,关联t_user和t_org,有专家注册和企业注册,企业注册.注册成功之后,短信通知
 * </p>
 *
 * @author xu.ran
 * @since 2020-09-10
 */
@Data
@Accessors(chain = true)
@ToString
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="RegisteredFlowQueryDTO对象")
public class RegisteredFlowQueryDTO extends BaseDTO<RegisteredFlowQueryDTO, TRegisteredFlow>  implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "1-当前页码")
    public Integer current = 1;

    @ApiModelProperty(value = "2-分页数量")
    public Integer size = 20;

    private String id;
    /**
    * URL路径中的部门类型,例如 URL路径中的 kjj 
    */
    @ApiModelProperty(value = "3-URL路径中的部门类型,例如 URL路径中的 kjj " )
    private String orgTypePathKey;
    /**
    * 用户ID
    */
    @ApiModelProperty(value = "4-用户ID" )
    private String userId;
    /**
    * 用户类型:1注册企业,2企业个人,3专家
    */
    @ApiModelProperty(value = "5-用户类型:1注册企业,2企业个人,3专家" )
    private String userType;
    /**
    * 业务模块名称,例如专家注册
    */
    @ApiModelProperty(value = "6-业务模块名称,例如专家注册" )
    private String businessModule;
    /**
    * 创建时间
    */
    @ApiModelProperty(value = "7-创建时间" )
    private LocalDateTime createTime;
    /**
    * 提交时间
    */
    @ApiModelProperty(value = "8-提交时间" )
    private LocalDateTime submitTime;
    /**
    * 审批时间
    */
    @ApiModelProperty(value = "9-审批时间" )
    private LocalDateTime approvalDate;
    /**
    * 审批人员ID
    */
    @ApiModelProperty(value = "10-审批人员ID" )
    private String approvalUserId;
    /**
    * 驳回时间
    */
    @ApiModelProperty(value = "11-驳回时间" )
    private LocalDateTime rejectionDate;
    /**
    * 驳回说明
    */
    @ApiModelProperty(value = "12-驳回说明" )
    private String rejectionDescription;
    /**
    * 上报状态:0已填写未提交,1已提交,2,已审核,3已驳回,6已超期
    */
    @ApiModelProperty(value = "13-上报状态:0已填写未提交,1已提交,2,已审核,3已驳回,6已超期" )
    private Integer status;
    /**
    * 排序,查询时倒叙排列
    */
    @ApiModelProperty(value = "14-排序,查询时倒叙排列" )
    private Integer sortno;
    /**
    * 是否有效(0否,1是)
    */
    @ApiModelProperty(value = "15-是否有效(0否,1是)" )
    private Integer active;

    public Page<TRegisteredFlow> page() {
        return new Page<>(current, size);
    }

    public static Converter<RegisteredFlowQueryDTO, TRegisteredFlow> converter = new Converter<RegisteredFlowQueryDTO, TRegisteredFlow>() {
        @Override
        public TRegisteredFlow doForward(RegisteredFlowQueryDTO registeredFlowQueryDTO) {
            return WarpsUtils.copyTo(registeredFlowQueryDTO, TRegisteredFlow.class);
        }

        @Override
        public RegisteredFlowQueryDTO doBackward(TRegisteredFlow registeredFlow) {
            return WarpsUtils.copyTo(registeredFlow, RegisteredFlowQueryDTO.class);
        }
    };

    @Override
    public TRegisteredFlow convertToEntity() {
        return converter.doForward(this);
    }

    @Override
    public RegisteredFlowQueryDTO convertFor(TRegisteredFlow registeredFlow) {
        return converter.doBackward(registeredFlow);
    }
}
