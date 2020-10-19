package co.synext.module.Activiti.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import java.io.Serializable;
import co.synext.mybatis.entity.TApplyFlow;
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
 * ApplyFlowInputDTO对象
 * 
 * </p>
 *
 * @author xu.ran
 * @since 2020-09-16
 */
@Data
@Accessors(chain = true)
@ToString
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="InputApplyFlowDTO对象")
public class ApplyFlowInputDTO extends BaseDTO<ApplyFlowInputDTO, TApplyFlow>  implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 流程业务信息id
     */
    @ApiModelProperty(value = "1-流程业务信息id" )
    private String id;
    /**
    * 申报日期
    */
    @ApiModelProperty(value = "1-申报日期" )
    private LocalDate applyDate =  LocalDate.now();;
    /**
    * 流程ID
    */
    @ApiModelProperty(value = "2-流程ID" )
    private String procInstId;
    /**
    * 说明
    */
    @ApiModelProperty(value = "3-说明" )
    private String reason;
    /**
    * 审核状态，PENDING:审批中,WAITING_FOR_APPROVAL:待审批,APPROVAL_SUCCESS:审批成功,APPROVAL_FAILED:审批失败
    */
    @ApiModelProperty(value = "4-审核状态，PENDING:审批中,WAITING_FOR_APPROVAL:待审批,APPROVAL_SUCCESS:审批成功,APPROVAL_FAILED:审批失败" )
    private String status="WAITING_FOR_APPROVAL";
    /**
    * 申报人ID
    */
    @ApiModelProperty(value = "5-申报人ID" )
    private String userId;
    /**
    * 申报类型，BeforSubsidize:前资助，
    */
    @ApiModelProperty(value = "6-申报类型，BeforSubsidize:前资助，" )
    private String applyType;
    /**
    * 申报材料文件地址
    */
    @ApiModelProperty(value = "7-申报材料文件地址" )
    @NotNull(message = "申报材料文件地址不能为空!")
    private String applyFilepath;

    public static Converter<ApplyFlowInputDTO, TApplyFlow> converter = new Converter<ApplyFlowInputDTO, TApplyFlow>() {
        @Override
        public TApplyFlow doForward(ApplyFlowInputDTO applyFlowInputDTO) {
            return WarpsUtils.copyTo(applyFlowInputDTO, TApplyFlow.class);
        }

        @Override
        public ApplyFlowInputDTO doBackward(TApplyFlow applyFlow) {
            return WarpsUtils.copyTo(applyFlow, ApplyFlowInputDTO.class);
        }
    };

    @Override
    public TApplyFlow convertToEntity() {
        return converter.doForward(this);
    }

    @Override
    public ApplyFlowInputDTO convertFor(TApplyFlow applyFlow) {
        return converter.doBackward(applyFlow);
    }
}
