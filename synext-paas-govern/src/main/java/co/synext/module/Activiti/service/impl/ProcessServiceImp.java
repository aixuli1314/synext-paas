package co.synext.module.Activiti.service.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.bpmn.model.ActivitiListener;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.EndEvent;
import org.activiti.bpmn.model.ExclusiveGateway;
import org.activiti.bpmn.model.SequenceFlow;
import org.activiti.bpmn.model.StartEvent;
import org.activiti.bpmn.model.UserTask;
import org.activiti.engine.ActivitiObjectNotFoundException;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricProcessInstanceQuery;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricTaskInstanceQuery;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.impl.ProcessEngineImpl;
import org.activiti.engine.impl.context.Context;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceQuery;
import org.activiti.engine.task.DelegationState;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.activiti.image.ProcessDiagramGenerator;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import co.synext.common.base.resp.ReturnDatas;
import co.synext.common.base.service.ActivitiBaseService;
import co.synext.common.enums.Enums.AuditStatus;
import co.synext.common.utils.CommUtil;
import co.synext.config.ProcessTask.TaskCommand.RevokeTaskCmd;
import co.synext.config.security.details.LoginUser;
import co.synext.module.Activiti.dto.ApplyFlowQueryDTO;
import co.synext.module.Activiti.service.IApplyFlowService;

/*import com.zml.oa.ProcessTask.TaskCommand.DeleteActiveTaskCmd;
import com.zml.oa.ProcessTask.TaskCommand.RevokeTaskCmd;
import com.zml.oa.ProcessTask.TaskCommand.StartActivityCmd;
import com.zml.oa.entity.BaseVO;
import com.zml.oa.entity.CommentVO;
import com.zml.oa.entity.ExpenseAccount;
import com.zml.oa.entity.Salary;
import com.zml.oa.entity.SalaryAdjust;
import com.zml.oa.entity.User;
import com.zml.oa.entity.TApplyFlow;
import com.zml.oa.pagination.Page;
import com.zml.oa.service.IExpenseService;
import com.zml.oa.service.ISalaryAdjustService;
import com.zml.oa.service.ISalaryService;
import com.zml.oa.service.IUserService;
import com.zml.oa.service.IApplyFlowService;
import com.zml.oa.util.BeanUtils;*/

import co.synext.module.Activiti.service.IProcessService;
import co.synext.module.Activiti.service.activiti.WorkflowService;
import co.synext.module.Activiti.util.BeanUtils;
import co.synext.module.Activiti.vo.ApplyFlowVO;
import co.synext.module.Activiti.vo.BaseVO;
import co.synext.module.system.vo.ProcessInstanceEntityVO;
import co.synext.mybatis.entity.TApplyFlow;

/**
 * 流程相关Service
 * @author zml
 *
 */
@Service
public class ProcessServiceImp extends ActivitiBaseService implements IProcessService{

	private static final Logger logger = Logger.getLogger(ProcessServiceImp.class);
	
	@Autowired
	protected RuntimeService runtimeService;
	
    @Autowired
    protected IdentityService identityService;
    
    @Autowired
    protected TaskService taskService;
    
    @Autowired
    protected RepositoryService repositoryService;
    
    @Autowired
    protected HistoryService historyService;
    
	/*
	 * @Autowired protected IUserService userService;
	 */
	
    @Autowired
    ProcessEngineFactoryBean processEngineFactory;

    @Autowired
    ProcessEngineConfiguration processEngineConfiguration;
    
    @Autowired
    protected WorkflowService workflowService;
    @Autowired
	private ProcessEngine processEngine;
	
    @Autowired private IApplyFlowService applyFlowService;
	 /* 
	 * @Autowired private IExpenseService expenseService;
	 * 
	 * @Autowired private ISalaryAdjustService saService;
	 * 
	 * @Autowired private ISalaryService salaryService;
	 * 
	 * @Autowired private ProcessEngine processEngine;
	 */
    
	
	
    /**
     * 查询代办任务
     * @param user
     * @param model
     * @return
     */
	@Override
    public ReturnDatas findTodoTask(LoginUser user, BaseVO baseVO){
		//taskCandidateOrAssigned查询某个人的待办任务，包含已签收、候选任务<候选人范围和候选组范围>
		TaskQuery taskQuery = this.taskService.createTaskQuery().taskCandidateOrAssigned(user.getId().toString());
		Integer totalSum = taskQuery.list().size();
		List<Task> tasks = taskQuery.orderByTaskCreateTime().desc().listPage(baseVO.getCurrent()-1, baseVO.getSize());
		List<BaseVO> taskList = getBaseVOList(tasks);
		return ReturnDatas.ok(taskList, null, baseVO.page(totalSum));
    } 

    /**
     * 读取已结束中的流程(admin查看)
     *
     * @return
     */
    @Override
    public List<BaseVO> findFinishedProcessInstances(BaseVO baseVO) {
        HistoricProcessInstanceQuery historQuery = historyService.createHistoricProcessInstanceQuery().finished();
    	
        Integer totalSum = historQuery.list().size();
		List<HistoricProcessInstance> list = historQuery.orderByProcessInstanceEndTime().desc().listPage(baseVO.getCurrent()-1, baseVO.getSize());
		List<BaseVO> processList = new ArrayList<BaseVO>();
		
		for (HistoricProcessInstance historicProcessInstance : list) {
			String processInstanceId = historicProcessInstance.getId();
			List<HistoricVariableInstance> listVar = this.historyService.createHistoricVariableInstanceQuery().processInstanceId(processInstanceId).list();
			for(HistoricVariableInstance var : listVar){
				if("serializable".equals(var.getVariableTypeName()) && "entity".equals(var.getVariableName())){
					BaseVO base = (BaseVO) var.getValue();
					base.setHistoricProcessInstance(historicProcessInstance);
					base.setProcessDefinition(getProcessDefinition(historicProcessInstance.getProcessDefinitionId()));
					processList.add(base);
					break;
				}
			}
		}
		
        return processList;
    }
	
    /**
     * 各个审批人员查看自己完成的任务
     * @param model
     * @return
     * @throws Exception
     */
	@Override
	public List<BaseVO> findFinishedTaskInstances(LoginUser user, BaseVO baseVO) throws Exception {
		HistoricTaskInstanceQuery historQuery = historyService.createHistoricTaskInstanceQuery().taskAssignee(user.getId().toString()).finished();
		Integer totalSum = historQuery.list().size();
    	List<HistoricTaskInstance> list = historQuery.orderByHistoricTaskInstanceEndTime().desc().listPage(baseVO.getCurrent()-1, baseVO.getSize());
    	List<BaseVO> taskList = new ArrayList<BaseVO>();
    	
    	for(HistoricTaskInstance historicTaskInstance : list){
    		String processInstanceId = historicTaskInstance.getProcessInstanceId();
    		List<HistoricVariableInstance> listVar = this.historyService.createHistoricVariableInstanceQuery().processInstanceId(processInstanceId).list();
			for(HistoricVariableInstance var : listVar){
				if("serializable".equals(var.getVariableTypeName()) && "entity".equals(var.getVariableName())){
					BaseVO base = (BaseVO) var.getValue();
					base.setHistoricTaskInstance(historicTaskInstance);
					base.setProcessDefinition(getProcessDefinition(historicTaskInstance.getProcessDefinitionId()));
					taskList.add(base);
					break;
				}
			}
    	}
		return taskList;
	}
    
    /**
     * 将Task集合转为BaseVO集合
     * @param tasks
     * @return
     */
    protected List<BaseVO> getBaseVOList(List<Task> tasks) {
    	List<BaseVO> taskList = new ArrayList<BaseVO>();
        for (Task task : tasks) {
        	String processInstanceId = task.getProcessInstanceId();
            ProcessInstance processInstance = this.runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).active().singleResult();
            if(BeanUtils.isBlank(processInstance)){
            	//如果有挂起的流程则continue
            	continue;
            }
            //获取当前流程下的key为entity的variable
            BaseVO base = (BaseVO) this.runtimeService.getVariable(processInstance.getId(), "entity");
            base.setTask(task);
            base.setTaskInfo(CommUtil.task2map(task));
            base.setProcessInstanceInfo(CommUtil.processInstance2map(processInstance));
            ProcessDefinition processDefinition = getProcessDefinition(processInstance.getProcessDefinitionId());
            base.setProcessDefinitionInfo(CommUtil.processDefinition2map(processDefinition));
            taskList.add(base);
        }
    	return taskList;
    }
    
    /**
     * 查询流程定义对象
     *
     * @param processDefinitionId 流程定义ID
     * @return
     */
    protected ProcessDefinition getProcessDefinition(String processDefinitionId) {
        ProcessDefinition processDefinition = this.repositoryService.createProcessDefinitionQuery().processDefinitionId(processDefinitionId).singleResult();
        logger.info(processDefinition.getVersion());
        return processDefinition;
    }
    
    /**
     * 签收任务
     * @param user
     * @param taskId
     */
	@Override
    public void doClaim(LoginUser user, String taskId){
    	this.identityService.setAuthenticatedUserId(user.getId().toString());
        this.taskService.claim(taskId, user.getId().toString());
    }
	
    /**
     * 委派任务
     */
	@Override
	public void doDelegateTask(String userId, String taskId) throws Exception {
		//API: If no owner is set on the task, the owner is set to the current assignee of the task.
		//OWNER_（委托人）：受理人委托其他人操作该TASK的时候，受理人就成了委托人OWNER_，其他人就成了受理人ASSIGNEE_
		//assignee容易理解，主要是owner字段容易误解，owner字段就是用于受理人委托别人操作的时候运用的字段
		this.taskService.delegateTask(taskId, userId);
	}
	
	/**
	 * 转办任务
	 */
	@Override
	public void doTransferTask(String userId, String taskId) throws Exception {
		Task task = this.taskService.createTaskQuery().taskId(taskId).singleResult();
		if(task != null){
			String assign = task.getAssignee();
			this.taskService.setAssignee(taskId, userId);
			this.taskService.setOwner(taskId, assign);
		}else{
			throw new ActivitiObjectNotFoundException("任务不存在！", this.getClass());
		}
	}
	
	/**
	 * 获取评论
	 * @param processInstanceId
	 * @param model
	 * @return
	 * @throws Exception
	 */
	/*
	 * @Override public List<CommentVO> getComments(String processInstanceId) throws
	 * Exception{ // 查询一个任务所在流程的全部评论 List<Comment> comments =
	 * this.taskService.getProcessInstanceComments(processInstanceId);
	 * List<CommentVO> commnetList = new ArrayList<CommentVO>(); for(Comment comment
	 * : comments){ User user = this.userService.getUserById(new
	 * Integer(comment.getUserId())); CommentVO vo = new CommentVO();
	 * vo.setContent(comment.getFullMessage()); vo.setTime(comment.getTime());
	 * vo.setUserName(user.getName()); commnetList.add(vo); } return commnetList; }
	 */
    
    
    /**
     * 显示流程图,带流程跟踪
     * @param processInstanceId
     * @return
     */
    @Override
    public InputStream getDiagram(String processInstanceId){
    	ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
        BpmnModel bpmnModel = repositoryService.getBpmnModel(processInstance.getProcessDefinitionId());
        List<String> activeActivityIds = runtimeService.getActiveActivityIds(processInstanceId);
        // 不使用spring请使用下面的两行代码
    	ProcessEngineImpl defaultProcessEngine = (ProcessEngineImpl) ProcessEngines.getDefaultProcessEngine();
    	Context.setProcessEngineConfiguration(defaultProcessEngine.getProcessEngineConfiguration());

        // 使用spring注入引擎请使用下面的这行代码
        //processEngineConfiguration = processEngineFactory.getProcessEngineConfiguration();
        //Context.setProcessEngineConfiguration((ProcessEngineConfigurationImpl) processEngineConfiguration);

        //通过引擎生成png图片，并标记当前节点,并把当前节点用红色边框标记出来，弊端和直接部署流程文件生成的图片问题一样-乱码！。
        ProcessDiagramGenerator diagramGenerator = processEngineConfiguration.getProcessDiagramGenerator();
        InputStream imageStream = diagramGenerator.generateDiagram(bpmnModel, "png", activeActivityIds);
    	return imageStream;
    }
    
    /**
     * 显示图片-通过流程ID，，不带流程跟踪(没有乱码问题)
     * @param resourceType
     * @param processInstanceId
     * @return
     */
    @Override
    public InputStream getDiagramByProInstanceId_noTrace(String resourceType, String processInstanceId){
    	
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(processInstance.getProcessDefinitionId())
                .singleResult();

        String resourceName = "";
        if (resourceType.equals("png") || resourceType.equals("image")) {
            resourceName = processDefinition.getDiagramResourceName();
        } else if (resourceType.equals("xml")) {
            resourceName = processDefinition.getResourceName();
        }
        InputStream resourceAsStream = repositoryService.getResourceAsStream(processDefinition.getDeploymentId(), resourceName);
        return resourceAsStream;
    }
    
    /**
     * 显示图片-通过部署ID，不带流程跟踪(没有乱码啊问题)
     * @param resourceType
     * @param processInstanceId
     * @return
     * @throws Exception
     */
	@Override
	public InputStream getDiagramByProDefinitionId_noTrace(String resourceType,
			String processDefinitionId) throws Exception {
		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(processDefinitionId).singleResult();
        String resourceName = "";
        if (resourceType.equals("png") || resourceType.equals("image")) {
            resourceName = processDefinition.getDiagramResourceName();
        } else if (resourceType.equals("xml")) {
            resourceName = processDefinition.getResourceName();
        }
        InputStream resourceAsStream = repositoryService.getResourceAsStream(processDefinition.getDeploymentId(), resourceName);
        return resourceAsStream;
	}

    /**
     * 查看正在运行的申报流程
     * @param user
     * @return
     * @throws Exception
     */
    @Override
    public ReturnDatas listRuningTApplyFlow(LoginUser user, ApplyFlowQueryDTO flowDTO) throws Exception{
    	flowDTO.setUserId(user.getId());
    	
    	Page<TApplyFlow> listPage = applyFlowService.listPage(flowDTO);
    	List<TApplyFlow> listApplyFlow = listPage.getRecords();
		List<BaseVO> result = new ArrayList<BaseVO>();
		if(listApplyFlow != null ){
			for (TApplyFlow flow : listApplyFlow) {
				if(flow.getProcInstId() == null){
					continue;
				}
				// 查询流程实例
				ProcessInstance pi = this.runtimeService
						.createProcessInstanceQuery()
						.processInstanceId(flow.getProcInstId())
						.singleResult();
				Task task = this.taskService.createTaskQuery().processInstanceId(flow.getProcInstId()).singleResult();
				if (pi != null) {
					// 查询流程参数
					ApplyFlowVO base = (ApplyFlowVO) this.runtimeService.getVariable(pi.getId(), "entity");
					base.setStatusName(AuditStatus.getApplyFlowName(flow.getStatus()));
					base.setTaskInfo(CommUtil.task2map(task));
		            base.setProcessInstanceInfo(CommUtil.processInstance2map(pi));
		            ProcessDefinition processDefinition = getProcessDefinition(pi.getProcessDefinitionId());
		            base.setProcessDefinitionInfo(CommUtil.processDefinition2map(processDefinition));
		            
					base.setTask(null);
		            base.setProcessInstance(null);
		            base.setProcessDefinition(null);
					
					result.add(base);
				}
			}
		}
		return ReturnDatas.ok(result,null,flowDTO.page((int)listPage.getTotal()));
    }    

	@Override
	public String startApplyFlow(ApplyFlowVO applyFlowVO) throws Exception {
		// 用来设置启动流程的人员ID，引擎会自动把用户ID保存到activiti:initiator中
        identityService.setAuthenticatedUserId(applyFlowVO.getUserId().toString());
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("entity", applyFlowVO);
        //由userTask自动分配审批权限
//        if(applyFlow.getDays() <= 3){
//        	variables.put("auditGroup", "manager");
//        }else{
//        	variables.put("auditGroup", "director");
//        }
        TApplyFlow applyFlow = applyFlowService.copy2(applyFlowVO, TApplyFlow.class);
        String businessKey = applyFlowVO.getBusinessKey();
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("com.zml.oa.applyFlow", businessKey, variables);
        String processInstanceId = processInstance.getId();
        applyFlow.setProcInstId(processInstanceId);
        this.applyFlowService.updateById(applyFlow);

        logger.info("processInstanceId: "+processInstanceId);
        //最后要设置null，就是这么做，还没研究为什么
        this.identityService.setAuthenticatedUserId(null);
        return processInstanceId;
	}


	/**
	 * 完成任务
	 */
	@Override
	public void complete(String taskId, String content, String userid, Map<String, Object> variables) throws Exception{
		Task task = this.taskService.createTaskQuery().taskId(taskId).singleResult();
		// 根据任务查询流程实例
    	String processInstanceId = task.getProcessInstanceId();
    	ProcessInstance pi = this.runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
		//评论人的id  一定要写，不然查看的时候会报错，没有用户
    	this.identityService.setAuthenticatedUserId(userid);
		// 添加评论--意见为空时，默认“同意”。
    	
    	if(content != null){
    		if(content == ""){
        		content = "同意";
        	}
    		this.taskService.addComment(taskId, pi.getId(), content);
    	}
    	
		// 完成委派任务
    	if(DelegationState.PENDING == task.getDelegationState()){
    		this.taskService.resolveTask(taskId, variables);
    		return;
    	}
    	//正常完成任务
		this.taskService.complete(taskId, variables);
	}
	
	@Override
	public List<ProcessInstanceEntityVO> listRuningProcess(BaseVO page) throws Exception {
		
		ProcessInstanceQuery processInstanceQuery = runtimeService.createProcessInstanceQuery();
		List<ProcessInstance> list = processInstanceQuery.orderByProcessInstanceId().desc().listPage(page.getCurrent()-1, page.getSize());
		List<ProcessInstanceEntityVO> pieList = new ArrayList<ProcessInstanceEntityVO>();
    	for(ProcessInstance processInstance : list){
    		ProcessInstanceEntityVO processInstanceEntityVO = copy2(processInstance, ProcessInstanceEntityVO.class);
    		//查询节点名称未完待续。。。
    		processInstanceEntityVO.setTaskName("");
    		pieList.add(processInstanceEntityVO);
    	}
		return pieList;
	}

	@Override
	public void activateProcessInstance(String processInstanceId)
			throws Exception {
		runtimeService.activateProcessInstanceById(processInstanceId);
	}

	@Override
	public void suspendProcessInstance(String processInstanceId)
			throws Exception {
		runtimeService.suspendProcessInstanceById(processInstanceId);
	}

	/**
	 * 撤回任务
	 */
	@Override
	public Integer revoke(String historyTaskId, String processInstanceId) throws Exception {
		Command<Integer> cmd = new RevokeTaskCmd(historyTaskId, processInstanceId, this.runtimeService, this.workflowService, this.historyService );
		Integer revokeFlag = this.processEngine.getManagementService().executeCommand(cmd);
		return revokeFlag;
	}

	

	/**
	 * 创建开始节点
	 * @return
	 */
	protected static StartEvent createStartEvent() {
		StartEvent startEvent = new StartEvent();
		startEvent.setId("startEvent");
		startEvent.setName("start");
		startEvent.setInitiator("startUserId");
		return startEvent;
	}
	
	/**
	 * 创建结束节点
	 * @return
	 */
	protected static EndEvent createEndEvent() {
		EndEvent endEvent = new EndEvent();
		endEvent.setId("endEvent");
		endEvent.setName("end");
		return endEvent;
	}
	
	/**
	 * 创建用户任务节点
	 * @param id
	 * @param name
	 * @return
	 */
	protected static UserTask createUserTask(String id, String name) {
		List<ActivitiListener> taskListeners = new ArrayList<ActivitiListener>();
		ActivitiListener listener = new ActivitiListener();
		listener.setId("");
		listener.setEvent("create");
		listener.setImplementationType("delegateExpression");
		listener.setImplementation("${userTaskListener}");
		taskListeners.add(listener);
		
		UserTask userTask = new UserTask();
		userTask.setId(id);
		userTask.setName(name);
		userTask.setTaskListeners(taskListeners);
		userTask.setDocumentation("");		// 说明
		return userTask;
	}
	
	/**
	 * 创建节点间的连线
	 * @param from
	 * @param to
	 * @param id
	 * @param name
	 * @param conditionExpression
	 * @return
	 */
	protected static SequenceFlow createSequenceFlow(String from, String to,String id,String name,String conditionExpression) {
		SequenceFlow flow = new SequenceFlow();
		flow.setId(id);
		flow.setName(name);
		flow.setSourceRef(from);
		flow.setTargetRef(to);
		if(StringUtils.isNotBlank(conditionExpression)) {
			flow.setConditionExpression(conditionExpression);
		}
		return flow;
	}
	
	/**
	 * 创建排他网关
	 * @param id
	 * @return
	 */
	protected static ExclusiveGateway createExclusiveGateway(String id) {
		ExclusiveGateway gateway = new ExclusiveGateway();
		gateway.setId(id);
		return gateway;
	}
}
