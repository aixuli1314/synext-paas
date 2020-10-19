package co.synext.module.system.dto;

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

/**
 * <p>
 * RegisteredFlowInputDTO对象
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
@ApiModel(value="InputRegisteredFlowDTO对象")
public class RegisteredFlowInputDTO extends BaseDTO<RegisteredFlowInputDTO, TRegisteredFlow>  implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * URL路径中的部门类型,例如 URL路径中的 kjj 
    */
    @ApiModelProperty(value = "1-URL路径中的部门类型,例如 URL路径中的 kjj " )
    private String orgTypePathKey;
    /**
    * 用户ID
    */
    @ApiModelProperty(value = "2-用户ID" )
    private String userId;
    /**
    * 用户类型:1注册企业,2企业个人,3专家
    */
    @ApiModelProperty(value = "3-用户类型:1注册企业,2企业个人,3专家" )
    private String userType;
    /**
    * 业务模块名称,例如专家注册
    */
    @ApiModelProperty(value = "4-业务模块名称,例如专家注册" )
    private String businessModule;
    /**
    * 创建时间
    */
    @ApiModelProperty(value = "5-创建时间" )
    private String createTime;
    /**
    * 提交时间
    */
    @ApiModelProperty(value = "6-提交时间" )
    private String submitTime;
    /**
    * 审批时间
    */
    @ApiModelProperty(value = "7-审批时间" )
    private String approvalDate;
    /**
    * 审批人员ID
    */
    @ApiModelProperty(value = "8-审批人员ID" )
    private String approvalUserId;
    /**
    * 驳回时间
    */
    @ApiModelProperty(value = "9-驳回时间" )
    private String rejectionDate;
    /**
    * 驳回说明
    */
    @ApiModelProperty(value = "10-驳回说明" )
    private String rejectionDescription;
    /**
    * 上报状态:0已填写未提交,1已提交,2,已审核,3已驳回,6已超期
    */
    @ApiModelProperty(value = "11-上报状态:0已填写未提交,1已提交,2,已审核,3已驳回,6已超期" )
    private Integer status;
    /**
    * 排序,查询时倒叙排列
    */
    @ApiModelProperty(value = "12-排序,查询时倒叙排列" )
    private Integer sortno;
    /**
    * 是否有效(0否,1是)
    */
    @ApiModelProperty(value = "13-是否有效(0否,1是)" )
    private Integer active;

    public static Converter<RegisteredFlowInputDTO, TRegisteredFlow> converter = new Converter<RegisteredFlowInputDTO, TRegisteredFlow>() {
        @Override
        public TRegisteredFlow doForward(RegisteredFlowInputDTO registeredFlowInputDTO) {
            return WarpsUtils.copyTo(registeredFlowInputDTO, TRegisteredFlow.class);
        }

        @Override
        public RegisteredFlowInputDTO doBackward(TRegisteredFlow registeredFlow) {
            return WarpsUtils.copyTo(registeredFlow, RegisteredFlowInputDTO.class);
        }
    };

    @Override
    public TRegisteredFlow convertToEntity() {
        return converter.doForward(this);
    }

    @Override
    public RegisteredFlowInputDTO convertFor(TRegisteredFlow registeredFlow) {
        return converter.doBackward(registeredFlow);
    }
}
