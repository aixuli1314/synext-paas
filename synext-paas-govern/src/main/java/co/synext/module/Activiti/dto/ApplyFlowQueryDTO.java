package co.synext.module.Activiti.dto;

import java.time.LocalDate;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

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
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * ApplyFlowQueryDTO对象
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
@ApiModel(value="ApplyFlowQueryDTO对象")
public class ApplyFlowQueryDTO extends BaseDTO<ApplyFlowQueryDTO, TApplyFlow>  implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "1-当前页码")
    public Integer current = 1;

    @ApiModelProperty(value = "2-分页数量")
    public Integer size = 20;

    /**
    * 申报日期
    */
    @ApiModelProperty(value = "3-申报日期" )
    private LocalDate applyDate;
    /**
    * 流程ID
    */
    @ApiModelProperty(value = "4-流程ID" )
    private String procInstId;
    /**
    * 说明
    */
    @ApiModelProperty(value = "5-说明" )
    private String reason;
    /**
    * 审核状态，PENDING:审批中,WAITING_FOR_APPROVAL:待审批,APPROVAL_SUCCESS:审批成功,APPROVAL_FAILED:审批失败
    */
    @ApiModelProperty(value = "6-审核状态，PENDING:审批中,WAITING_FOR_APPROVAL:待审批,APPROVAL_SUCCESS:审批成功,APPROVAL_FAILED:审批失败" )
    private String status;
    /**
    * 申报人ID
    */
    @ApiModelProperty(value = "7-申报人ID" )
    private String userId;
    /**
    * 申报类型，BeforSubsidize:前资助，
    */
    @ApiModelProperty(value = "8-申报类型，BeforSubsidize:前资助，" )
    private String applyType;
    /**
    * 申报材料文件地址
    */
    @ApiModelProperty(value = "9-申报材料文件地址" )
    private String applyFilepath;

    public Page<TApplyFlow> page() {
        return new Page<>(current, size);
    }
    public Page<TApplyFlow> page(int totle) {
    	Page<TApplyFlow> page = page();
    	page.setTotal(totle);
    	return page;
    }

    public static Converter<ApplyFlowQueryDTO, TApplyFlow> converter = new Converter<ApplyFlowQueryDTO, TApplyFlow>() {
        @Override
        public TApplyFlow doForward(ApplyFlowQueryDTO applyFlowQueryDTO) {
            return WarpsUtils.copyTo(applyFlowQueryDTO, TApplyFlow.class);
        }

        @Override
        public ApplyFlowQueryDTO doBackward(TApplyFlow applyFlow) {
            return WarpsUtils.copyTo(applyFlow, ApplyFlowQueryDTO.class);
        }
    };

    @Override
    public TApplyFlow convertToEntity() {
        return converter.doForward(this);
    }

    @Override
    public ApplyFlowQueryDTO convertFor(TApplyFlow applyFlow) {
        return converter.doBackward(applyFlow);
    }
}
