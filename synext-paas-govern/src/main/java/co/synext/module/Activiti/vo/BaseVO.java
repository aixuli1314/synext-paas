package co.synext.module.Activiti.vo;

import java.io.Serializable;
import java.util.Map;

import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ApiModel(value="BaseVO对象", description="流程部署实例相关父类，页面展示数据")
public class BaseVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6165121688276341503L;
	@ApiModelProperty(value = "1-当前页码")
    public Integer current = 1;

    @ApiModelProperty(value = "2-分页数量")
    public Integer size = 20;

	// 业务类型
	public final static String APPLYFLOW = "applyFlow";	
	public final static String SALARY = "salary";	
	public final static String EXPENSE = "expense";
	public final static String BEFORSUBSIDIZE = "beforsubsidize";
	public final static String AFTERSUBSIDIZE = "aftersubsidize";
	
	// 待办任务标识
	public final static String CANDIDATE = "candidate";
	
	// 受理任务标识
	public final static String ASSIGNEE = "assignee";
	
	// 运行中的流程表示
	public final static String RUNNING = "running";
	
	// 已结束任务标识
	public final static String FINISHED = "finished";
	
	//审批中
	public static final String PENDING = "PENDING";
	//待审批
	public static final String WAITING_FOR_APPROVAL = "WAITING_FOR_APPROVAL";
	//审批成功
	public static final String APPROVAL_SUCCESS = "APPROVAL_SUCCESS";
	//审批失败
	public static final String APPROVAL_FAILED = "APPROVAL_FAILED";
	
	
	// 申请人id
	private String user_id;
	
	// 申请的标题
	private String title;
	
	// 申请人名称
	private String user_name;
	
	// 业务类型
	private String businessType;
	
	//对应业务的id
	private String businessKey;
	
    // 流程任务
    private Task task;
    // 流程任务
    //private Task variableInstances;
    // 流程任务
    private Map<String,Object> taskInfo;

    // 运行中的流程实例
    private ProcessInstance processInstance;
    // 运行中的流程实例
    private Map<String,Object> processInstanceInfo;

    // 历史的流程实例
    private HistoricProcessInstance historicProcessInstance;

    // 历史任务
    private HistoricTaskInstance historicTaskInstance;
    
    // 流程定义
    private ProcessDefinition processDefinition;
    // 流程定义
    private Map<String,Object> processDefinitionInfo;

    public Page page(int total) {
        return new Page(current, size);
    }
    //任务执行人
    private String assign;
    //任务所属人
    private String owner;
    //是否受理标识
    private String isReceive;
    //是否需要专家标识
    private String isNeedExpert;
    //是否中期评估标识
    private String isMetaphase;
}
