package co.synext.module.Activiti.dto;

import java.time.LocalDate;
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
 * TApplyFlowUpdateDTO对象
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
@ApiModel(value="ApplyFlowUpdateDTO对象")
public class ApplyFlowUpdateDTO extends BaseDTO<ApplyFlowUpdateDTO, TApplyFlow>  implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    /**
    * 申报日期
    */
    @ApiModelProperty(value = "2-申报日期" )
    private LocalDate applyDate;
    /**
    * 流程ID
    */
    @ApiModelProperty(value = "3-流程ID" )
    private String procInstId;
    /**
    * 说明
    */
    @ApiModelProperty(value = "4-说明" )
    private String reason;
    /**
    * 审核状态，PENDING:审批中,WAITING_FOR_APPROVAL:待审批,APPROVAL_SUCCESS:审批成功,APPROVAL_FAILED:审批失败
    */
    @ApiModelProperty(value = "5-审核状态，PENDING:审批中,WAITING_FOR_APPROVAL:待审批,APPROVAL_SUCCESS:审批成功,APPROVAL_FAILED:审批失败" )
    private String status;
    /**
    * 申报人ID
    */
    @ApiModelProperty(value = "6-申报人ID" )
    private String userId;
    /**
    * 申报类型，BeforSubsidize:前资助，
    */
    @ApiModelProperty(value = "7-申报类型，BeforSubsidize:前资助，" )
    private String applyType;
    /**
    * 申报材料文件地址
    */
    @ApiModelProperty(value = "8-申报材料文件地址" )
    private String applyFilepath;

    public static Converter<ApplyFlowUpdateDTO, TApplyFlow> converter = new Converter<ApplyFlowUpdateDTO, TApplyFlow>() {
        @Override
        public TApplyFlow doForward(ApplyFlowUpdateDTO applyFlowUpdateDTO) {
            return WarpsUtils.copyTo(applyFlowUpdateDTO, TApplyFlow.class);
        }

        @Override
        public ApplyFlowUpdateDTO doBackward(TApplyFlow applyFlow) {
            return WarpsUtils.copyTo(applyFlow, ApplyFlowUpdateDTO.class);
        }
    };

    @Override
    public TApplyFlow convertToEntity() {
        return converter.doForward(this);
    }

    @Override
    public ApplyFlowUpdateDTO convertFor(TApplyFlow applyFlow) {
        return converter.doBackward(applyFlow);
    }
}
