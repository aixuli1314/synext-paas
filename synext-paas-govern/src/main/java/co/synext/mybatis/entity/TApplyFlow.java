package co.synext.mybatis.entity;

import java.time.LocalDate;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import co.synext.module.Activiti.vo.BaseVO;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author xu.ran
 * @since 2020-09-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TApplyFlow implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.INPUT)
    private String id;

    /**
     * 申报日期
     */
    private LocalDate applyDate;

    /**
     * 流程ID
     */
    private String procInstId;

    /**
     * 说明
     */
    private String reason;

    /**
     * 审核状态，PENDING:审批中,WAITING_FOR_APPROVAL:待审批,APPROVAL_SUCCESS:审批成功,APPROVAL_FAILED:审批失败
     */
    private String status;

    /**
     * 申报人ID
     */
    private String userId;

    /**
     * 申报类型，BeforSubsidize:前资助，
     */
    private String applyType;

    /**
     * 申报材料文件地址
     */
    private String applyFilepath;


}
