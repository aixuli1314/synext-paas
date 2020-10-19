package co.synext.module.Activiti.vo;

import java.time.LocalDate;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * TApplyFlowVO对象
 * </p>
 *
 * @author xu.ran
 * @since 2020-09-16
 */
@Data
@Accessors(chain = true)
@ApiModel(value="TApplyFlowVO对象", description="")
public class ApplyFlowVO extends BaseVO implements Serializable {

    private static final long serialVersionUID = -1495795296316800235L;

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
    /**
     * 审核状态名称
     */
    @ApiModelProperty(value = "9-审核状态名称" )
    private String statusName;

}
