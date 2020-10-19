package co.synext.module.Activiti.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.ActivitiException;
import org.activiti.engine.ActivitiObjectNotFoundException;
import org.activiti.engine.ActivitiTaskAlreadyClaimedException;
import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSON;

import co.synext.common.base.resp.ReturnDatas;
import co.synext.common.constant.Constant;
import co.synext.common.enums.Enums.TaskDefKey;
import co.synext.common.enums.Enums.YesOrNo;
import co.synext.common.utils.HttpClientUtils;
import co.synext.config.security.details.LoginUser;
import co.synext.config.security.helper.LoginUserHelper;
import co.synext.module.Activiti.dto.ApplyFlowQueryDTO;
import co.synext.module.Activiti.service.IActivitiService;
import co.synext.module.Activiti.service.IApplyFlowService;
import co.synext.module.Activiti.service.IProcessService;
import co.synext.module.Activiti.service.activiti.WorkflowDeployService;
import co.synext.module.Activiti.service.activiti.WorkflowService;
import co.synext.module.Activiti.util.BeanUtils;
import co.synext.module.Activiti.util.WorkflowUtils;
import co.synext.module.Activiti.vo.ApplyFlowVO;
import co.synext.module.Activiti.vo.BaseVO;
import co.synext.module.Activiti.vo.ProcessDefinitionEntityVO;
import co.synext.module.system.service.IOrgService;
import co.synext.module.system.service.IUserService;
import co.synext.module.system.vo.ProcessInstanceEntityVO;
import co.synext.mybatis.entity.TApplyFlow;
import co.synext.mybatis.entity.TOrg;
import co.synext.mybatis.entity.TUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

/**
 * 全用户通用流程控制类
 * @author ZML
 *
 */
@Api(tags = "★全用户通用流程接口")
@RestController
@AllArgsConstructor
@RequestMapping("/open/api/kjj/process")
public class ProcessController {
	private static final Logger logger = Logger.getLogger(ProcessController.class);

	private final IActivitiService activitiService;
	@Resource
	private ProcessEngine processEngine;
	@Resource
	private HistoryService historyService;
	@Autowired
	protected IUserService userService;

	@Autowired
	private TaskService taskService;
	@Autowired
	protected WorkflowService traceService;

	@Autowired
	private IProcessService processService;

	@Autowired
	private RepositoryService repositoryService;

	@Autowired
	private WorkflowDeployService workflowProcessDefinitionService;
	
	private final IApplyFlowService applyFlowService;
	@Autowired
	private RuntimeService runtimeService;
	@Autowired
	private final IOrgService orgService;
	/**
	 * 流程定义的加载
	 * @param current
	 * @param size
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "可申请流程列表接口")
	@GetMapping("/listProcess")
    public ReturnDatas listProcess(ApplyFlowQueryDTO applyFlowQueryDTO) throws Exception{
    	ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery().orderByDeploymentId().desc();
    	List<ProcessDefinition> processDefinitionList = processDefinitionQuery.listPage(applyFlowQueryDTO.getCurrent()-1, applyFlowQueryDTO.getSize());
    	
    	List<ProcessDefinitionEntityVO> pdList = new  ArrayList<>();
    	for (ProcessDefinition processDefinition : processDefinitionList) {
    		ProcessDefinitionEntityVO pd = new ProcessDefinitionEntityVO();
            
            //封装到ProcessDefinitionEntity中
            pd.setId(processDefinition.getId());
            pd.setName(processDefinition.getName());
            pd.setKey(processDefinition.getKey());
            pd.setDeploymentId(processDefinition.getDeploymentId());
            pd.setVersion(processDefinition.getVersion());
            pd.setResourceName(processDefinition.getResourceName());
            pd.setDiagramResourceName(processDefinition.getDiagramResourceName());
            pd.setSuspended(processDefinition.isSuspended());
            
            String deploymentId = processDefinition.getDeploymentId();
            Deployment deployment = repositoryService.createDeploymentQuery().deploymentId(deploymentId).singleResult();
            pd.setDeploymentTime(deployment.getDeploymentTime());
            
            pdList.add(pd);
        }
    	return ReturnDatas.ok(pdList,null,applyFlowQueryDTO.page(processDefinitionQuery.list().size()));
    }

	@ApiOperation(value = "所有组织接口")
	@GetMapping("/getAllOrg")
	public ReturnDatas<TOrg> getAllOrg() {
		return orgService.getAllOrg();
	}
    
    /**
   	 * 查询待办任务
   	 * @param session
   	 * @param redirectAttributes
   	 * @param model
   	 * @return
   	 * @throws Exception
   	 */
    @ApiOperation(value = "查询待办任务")
   	@PostMapping(value = "/todoTask")
   	public ReturnDatas todoTask(BaseVO baseVO) throws Exception{
   		LoginUser user = LoginUserHelper.getCurrentLoginUser();
   		ReturnDatas returnDatas = this.processService.findTodoTask(user, baseVO);
   		List<BaseVO> taskList = (List<BaseVO>)returnDatas.getData();
   		for(BaseVO base : taskList){
   			String assign = base.getTask().getAssignee();
   			if(assign != null){
   				TUser u = userService.getById(assign);
   				base.setAssign(u.getUserName());
   			}
   			String owner = base.getTask().getOwner();
   			if(owner != null){
   				TUser u = userService.getById(owner);
   				base.setOwner(u.getUserName());
   			}
   			String taskDefinitionKey = base.getTask().getTaskDefinitionKey();
   			if(StringUtils.compare(TaskDefKey.县区级科技局受理.code, taskDefinitionKey)==0) {
   				base.setIsReceive(YesOrNo.是.code);
   			}else if(StringUtils.compare(TaskDefKey.市级科技局审核.code, taskDefinitionKey)==0) {
   				base.setIsNeedExpert(YesOrNo.是.code);
   				base.setIsMetaphase(YesOrNo.是.code);
   			}else if(StringUtils.compare(TaskDefKey.市级科技局中期评估.code, taskDefinitionKey)==0 
   					|| StringUtils.compare(TaskDefKey.科技局结题审核.code, taskDefinitionKey)==0) {
   				base.setIsNeedExpert(YesOrNo.是.code);
   			}
   			//Task必须清空，使用TaskInfo,因为Task有懒加载成员属性，无法给前端转Json
   			base.setTask(null);
   		}
   		return returnDatas;
   	}
    /**
     * 完成任务
     * @param content
     * @param completeFlag
     * @param taskId
     * @param redirectAttributes
     * @param session
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "任务办理/审核")
    @PostMapping(value ="/complate/{taskId}")
    public ReturnDatas complate(
    		@RequestParam("applyflowId") String applyflowId,
    		@RequestParam("content") String content,
    		Boolean isPass,
    		Boolean isReceive, 
    		Boolean isNeedExpert, 
    		Boolean isMetaphase, 
    		@PathVariable("taskId") String taskId, 
    		RedirectAttributes redirectAttributes) throws Exception{
    	
    	if(isPass==null&&isReceive==null&&isNeedExpert==null) {
    		return ReturnDatas.error(null,"请执行操作!");
    	}
    	
    	LoginUser user = LoginUserHelper.getCurrentLoginUser();
    	try {
    		TApplyFlow applyFlow = applyFlowService.getById(applyflowId);
    		ApplyFlowVO applyFlowVO = (ApplyFlowVO) this.runtimeService.getVariable(applyFlow.getProcInstId(), "entity");
    		Map<String, Object> variables =  new HashMap<>();
    		if(isPass!=null) {
	    		if(!isPass){
	    			applyFlowVO.setTitle(applyFlowVO.getUser_name()+" 的申请失败！");
	    			applyFlowVO.setStatus(BaseVO.APPROVAL_FAILED);
	    			variables.put("entity", applyFlowVO);
	    		}else {
	    			applyFlowVO.setStatus(BaseVO.PENDING);
	    		}
	    		variables.put("isPass", isPass);
    		}
    		if(isReceive!=null) {
    			if(!isReceive){
	    			applyFlowVO.setTitle(applyFlowVO.getUser_name()+" 申请未被受理！");
	    			applyFlowVO.setStatus(BaseVO.APPROVAL_FAILED);
	    			variables.put("entity", applyFlowVO);
	    		}
    			variables.put("isReceive", isReceive);
    		}
    		if(isNeedExpert!=null) {
    			variables.put("isNeedExpert", isNeedExpert);
    		}
    		if(isMetaphase!=null) {
    			variables.put("isMetaphase", isMetaphase);
    		}
    		if(isPass!=null && isPass){
    			//此处需要修改，不能根据人来判断审批是否结束。应该根据流程实例id(processInstanceId)来判定。
    			//判断指定ID的实例是否存在，如果结果为空，则代表流程结束，实例已被删除(移到历史库中)
    			ProcessInstance pi = this.runtimeService.createProcessInstanceQuery().processInstanceId(applyFlow.getProcInstId()).singleResult();
    			if(BeanUtils.isBlank(pi)){
    				applyFlowVO.setStatus(BaseVO.APPROVAL_SUCCESS);
    			}
    		}
    		// 完成任务
    		this.processService.complete(taskId, content, user.getId().toString(), variables);
    		HistoricTaskInstance task = historyService.createHistoricTaskInstanceQuery().taskId(taskId).singleResult();
    		String s = JSON.toJSONString(task);
            Map<String, String> map = new HashMap<>();
            map.put("projectId",task.getId());
            map.put("projectInfo",s);
            map.put("submitResult","1");
            map.put("examineResult",isPass?"1":"0");
            HttpClientUtils.doPost(Constant.FISCO_WEBSDK_URL+"/beforeDeclare/submitBlock",map);
    		applyFlow.setStatus(applyFlowVO.getStatus());
    		this.applyFlowService.updateById(applyFlow);
    		
		} catch (ActivitiObjectNotFoundException e) {
			return ReturnDatas.error(e.getMessage(), "此任务不存在，请联系管理员！");
		} catch (ActivitiException e) {
			return ReturnDatas.error(e.getMessage(), "此任务正在协办，您不能办理此任务！");
		} catch (Exception e) {
			return ReturnDatas.error(e.getMessage(), "任务办理失败，请联系管理员！");
		}
		return ReturnDatas.ok(null, "任务办理完成！");
    }
    
    /**
     * 显示流程图,带流程跟踪
     * @param processInstanceId
     * @param response
     * @throws Exception 
     */
    @GetMapping(value = "/showDiagram/{processInstanceId}")
	public void showDiagram(@PathVariable("processInstanceId") String processInstanceId, HttpServletResponse response) throws Exception {
	        InputStream imageStream = this.processService.getDiagram(processInstanceId);
	        // 输出资源内容到相应对象
	        byte[] b = new byte[1024];
	        int len;
	        while ((len = imageStream.read(b, 0, 1024)) != -1) {
	            response.getOutputStream().write(b, 0, len);
	        }
	}
    
    /**
     * 显示图片通过部署id，不带流程跟踪(没有乱码问题)
     * @param processDefinitionId
     * @param resourceType	资源类型(xml|image)
     * @param response
     * @throws Exception
     */
    @GetMapping(value = "/process-definition")
    public void loadByDeployment(@RequestParam("processDefinitionId") String processDefinitionId, @RequestParam("resourceType") String resourceType,
                                 HttpServletResponse response) throws Exception {
    	InputStream resourceAsStream = this.processService.getDiagramByProDefinitionId_noTrace(resourceType, processDefinitionId);
        byte[] b = new byte[1024];
        int len = -1;
        while ((len = resourceAsStream.read(b, 0, 1024)) != -1) {
            response.getOutputStream().write(b, 0, len);
        }
    }
    
    
    /**
     * 显示图片通过流程id，不带流程跟踪(没有乱码问题)
     *
     * @param resourceType      资源类型(xml|image)
     * @param processInstanceId 流程实例ID
     * @param response
     * @throws Exception
     */
    @GetMapping(value = "/process-instance")
    public void loadByProcessInstance(@RequestParam("type") String resourceType, @RequestParam("pid") String processInstanceId, HttpServletResponse response)
            throws Exception {
        InputStream resourceAsStream = this.processService.getDiagramByProInstanceId_noTrace(resourceType, processInstanceId);
        byte[] b = new byte[1024];
        int len = -1;
        while ((len = resourceAsStream.read(b, 0, 1024)) != -1) {
            response.getOutputStream().write(b, 0, len);
        }
    }
    
    

   
    
	
    /**
     * 查看已完成任务列表
     *
     * @return
     * @throws Exception 
     */
    @GetMapping(value = "/endTask")
    @ResponseBody
    public ReturnDatas findFinishedTaskInstances(BaseVO baseVO) throws Exception {
    	LoginUser user = LoginUserHelper.getCurrentLoginUser();
    	List<BaseVO> taskList = this.processService.findFinishedTaskInstances(user, baseVO);
    	List<Object> jsonList=new ArrayList<Object>(); 
    	return ReturnDatas.ok(taskList);
    }
    
	/**
	 * 签收任务
	 * @return
	 */
    @GetMapping("/claim/{taskId}")
	@ResponseBody
	public ReturnDatas claim(@PathVariable("taskId") String taskId) {
		try {
			LoginUser user = LoginUserHelper.getCurrentLoginUser();
			this.processService.doClaim(user, taskId);
		}catch (ActivitiObjectNotFoundException e){
			 return ReturnDatas.error(null,"此任务不存在！任务签收失败！");
		}catch (ActivitiTaskAlreadyClaimedException e) {
			 return ReturnDatas.error(null,"此任务已被其他组成员签收！请刷新页面重新查看！");
		}catch (Exception e) {
			return ReturnDatas.error(null,"任务签收失败！请联系管理员！");
		} 
        return ReturnDatas.ok(null,"任务签收成功！");
	}
    
	/**
	 * 委派任务
	 * 委派也是代办、协办，你领导接到一个任务，让你代办，你办理完成后任务还是回归到你的领导，事情是你做的，功劳是你领导的，这就是代办。
	 * 所以代办人完成任务后，任务还会回到原执行人，流程不会发生变化。
	 * @param taskId	代办人
	 * @param userId
	 * @return
	 */
    @GetMapping(value = "/delegateTask/{taskId}")
	@ResponseBody
	public ReturnDatas delegateTask(@PathVariable("taskId") String taskId, @RequestParam("userId") String userId){
		try {
			this.processService.doDelegateTask(userId, taskId);
		} catch (ActivitiObjectNotFoundException e){
			return ReturnDatas.error(null,"此任务不存在！委派任务失败！");
		} catch (Exception e) {
			return ReturnDatas.error(null,"委派任务失败，系统错误！");
		}
		return ReturnDatas.ok(null,"委派任务成功！");
	}
	
	/**
	 * 转办任务，办理完成后，流程会继续向下走。
	 * @param taskId
	 * @param userId
	 * @return
	 */
    @GetMapping(value = "/transferTask/{taskId}")
	@ResponseBody
	public ReturnDatas transferTask(@PathVariable("taskId") String taskId, @RequestParam("userId") String userId){
		try {
			this.processService.doTransferTask(userId, taskId);
		} catch (ActivitiObjectNotFoundException e){
			return ReturnDatas.error(null,"此任务不存在！委派任务失败！");
		} catch (Exception e) {
			return ReturnDatas.error(null,"转办任务失败，系统错误！");
		}
		return ReturnDatas.ok(null,"转办任务成功！");
	}
	
	/**
	 * 撤销任务
	 * @return
	 * @throws Exception 
	 */
    @GetMapping("/revoke/{taskId}/{processInstanceId}")
	@ResponseBody
	public ReturnDatas revoke(@PathVariable("taskId") String taskId, @PathVariable("processInstanceId") String processInstanceId) throws Exception{
		
		try {
			Integer revokeFlag = this.processService.revoke(taskId, processInstanceId);
			
			if(revokeFlag == 1){
				return ReturnDatas.error(null,"撤销任务失败 - [ 此审批流程已结束! ]");
			}else if(revokeFlag == 2){
				return ReturnDatas.error(null,"撤销任务失败 - [ 下一结点已经通过,不能撤销! ]");
			}
		} catch (Exception e) {
			return ReturnDatas.error(null,"撤销任务失败 - [ 内部错误！]");
		}
		return ReturnDatas.ok(null,"撤销任务成功！");
	}
    
    /**
     * 管理运行中的流程
     * @param baseVO
     * @return
     * @throws Exception
     */
    @GetMapping(value="/runningProcess")
    @ResponseBody
    public ReturnDatas listRuningProcess(BaseVO baseVO) throws Exception{
    	List<ProcessInstanceEntityVO> list = this.processService.listRuningProcess(baseVO);
    	
    	return ReturnDatas.ok(list);
    }
    
    /**
     * 管理已结束的流程
     *
     * @return
     * @throws Exception 
     */
    @GetMapping(value = "/finishedProcess")
    @ResponseBody
    public ReturnDatas findFinishedProcessInstances(BaseVO baseVO) throws Exception {
    	List<BaseVO> processList = this.processService.findFinishedProcessInstances(baseVO);
    	return ReturnDatas.ok(processList);
    }
    
    
	
	
    /**
     * 激活、挂起流程定义-根据processDefinitionId
     * @param status
     * @param processInstanceId
     * @param redirectAttributes
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/updateProcessStatusByProDefinitionId")
    @ResponseBody
    public ReturnDatas updateProcessStatusByProDefinitionId(
    		@RequestParam("status") String status,
    		@RequestParam("processDefinitionId") String processDefinitionId) throws Exception{
    	//如果用/{status}/{processDefinitionId} rest风格，@PathVariable获取的processDefinitionId 为com.zml.oa,实际是com.zml.oa.applyFlow:1:32529.难道是BUG?
    	if (status.equals("active")) {
            repositoryService.activateProcessDefinitionById(processDefinitionId, true, null);
            ReturnDatas.ok(null, "已激活ID为[" + processDefinitionId + "]的流程定义。");
        } else if (status.equals("suspend")) {
            repositoryService.suspendProcessDefinitionById(processDefinitionId, true, null);
           return ReturnDatas.ok(null, "已挂起ID为[" + processDefinitionId + "]的流程定义。");
        }
    	return ReturnDatas.ok(null, "已激活ID为[" + processDefinitionId + "]的流程定义。");
    }
    
    /**
     * 激活、挂起流程实例-根据processInstanceId
     * @param status
     * @param processInstanceId
     * @param redirectAttributes
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/updateProcessStatusByProInstanceId/{status}/{processInstanceId}")
    @ResponseBody
    public ReturnDatas updateProcessStatusByProInstanceId(
    		@PathVariable("status") String status, 
    		@PathVariable("processInstanceId") String processInstanceId,
            RedirectAttributes redirectAttributes) throws Exception{
    	if (status.equals("active")) {
    		this.processService.activateProcessInstance(processInstanceId);
//          redirectAttributes.addFlashAttribute("message", "已激活ID为[ " + processInstanceId + " ]的流程实例。");
    	} else if (status.equals("suspend")) {
        	this.processService.suspendProcessInstance(processInstanceId);
//          redirectAttributes.addFlashAttribute("message", "已挂起ID为[ " + processInstanceId + " ]的流程实例。");
        	return ReturnDatas.ok(null, "已挂起ID为[" + processInstanceId + "]的流程实例。");
    	}
    	return ReturnDatas.ok(null, "已激活ID为[" + processInstanceId + "]的流程实例。");
    }
    
    
    
    /**
     * 部署单个流程
     *
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/redeploy/single")
    @ResponseBody
    public ReturnDatas redeploySingle(@Value("#{APP_PROPERTIES['export.diagram.path']}") String exportDir,
    							@RequestParam("resourceName") String resourceName,
    							@RequestParam(value = "diagramResourceName", required = false) String diagramResourceName,
    							@RequestParam("deploymentId") String deploymentId) throws Exception {
        try {
        	this.repositoryService.deleteDeployment(deploymentId, true);
        	//方法一：通过classpath/deploy目录下的.zip或.bar文件部署
        	String processKey = resourceName.substring(0, resourceName.indexOf('.'))+".zip";
        	this.workflowProcessDefinitionService.redeploySingleFrom(exportDir, processKey);
        	//方法二：通过classpath/bpmn下的流程描述文件部署--流程图错乱，一直提倡用打包部署没有任何问题。
//        	workflowProcessDefinitionService.redeployBpmn(exportDir, resourceName,diagramResourceName);
		} catch (Exception e) {
			return ReturnDatas.error(null,"部署选定流程失败！");
		}
        return ReturnDatas.ok(null,"已重新部署选定流程！");
    }
    
    /**
     * 导入部署
     * --@Value用于将一个SpEL表达式结果映射到到功能处理方法的参数上。
     * @RequestParam(value = "file", required = false) required = false时可以不用传递这个参数，默认为true
     * @param exportDir
     * @param file
     * @return
     */
    @PostMapping(value = "/deploy")
    @ResponseBody
    public ReturnDatas deploy(@Value("#{APP_PROPERTIES['export.diagram.path']}") String exportDir, 
    					  @RequestParam(value = "file", required = false) MultipartFile file) {
        String fileName = file.getOriginalFilename();
        try {
            InputStream fileInputStream = file.getInputStream();
            Deployment deployment = null;

            String extension = FilenameUtils.getExtension(fileName);
            if (extension.equals("zip") || extension.equals("bar")) {
                ZipInputStream zip = new ZipInputStream(fileInputStream);
                deployment = this.repositoryService.createDeployment().addZipInputStream(zip).deploy();
            } else {
                deployment = this.repositoryService.createDeployment().addInputStream(fileName, fileInputStream).deploy();
            }

            List<ProcessDefinition> list = this.repositoryService.createProcessDefinitionQuery().deploymentId(deployment.getId()).list();

            for (ProcessDefinition processDefinition : list) {
                WorkflowUtils.exportDiagramToFile(this.repositoryService, processDefinition, exportDir);
            }
        } catch (Exception e) {
        	logger.error("error on deploy process, because of file input stream", e);
        	return ReturnDatas.error(null,"流程部署失败！");
        }

        return ReturnDatas.ok(null,"流程部署成功！");
    }
    
    
    
   
    
    /**
     * 用户查看申报申请 - easyui
     * @param page
     * @param rows
     * @param businessType
     * @param session
     * @return
     * @throws Exception
     */
    @GetMapping(value="/runingProcessInstance/{businessType}/list")
    @ResponseBody
    public ReturnDatas<Object> getRuningProcessInstance(ApplyFlowQueryDTO applyFlowQueryDTO,
    		@PathVariable("businessType") String businessType) throws Exception{
    	LoginUser user = LoginUserHelper.getCurrentLoginUser();
		//申报
    	ReturnDatas result = this.processService.listRuningTApplyFlow(user, applyFlowQueryDTO);
    	return result;
    }
    
    /**
     * 任务跳转（包括回退和向前）至指定活动节点
     * @param currentTaskId
     * @param targetTaskDefinitionKey
     * @return
     * @throws Exception
     */
	/*
	 * @RequestMapping(value="/jumpTask")
	 * 
	 * @ResponseBody public ReturnDatas jumpTargetTask(@RequestParam("taskId")
	 * String currentTaskId, @RequestParam("taskDefinitionKey") String
	 * targetTaskDefinitionKey) throws Exception{ try {
	 * this.processService.moveTo(currentTaskId, targetTaskDefinitionKey);
	 * 
	 * } catch (Exception e) { return ReturnDatas.error(); } return
	 * ReturnDatas.ok(); }
	 */
    
  //查看流程图
	@RequestMapping(value = "/image", method = RequestMethod.GET)
    public ReturnDatas image(HttpServletResponse response,
     @RequestParam String processInstanceId) {
 		// String imagePath = File.separator+ "processes/tempImage"+ File.separator+processInstanceId+".png";
 		 String imagePath = "/processes/tempImage/"+processInstanceId+".png";
        try {
            InputStream is = getDiagram(processInstanceId);
            if (is == null)
            	return ReturnDatas.error(null,"查看流程图失败");
            
            File directory = new File(Constant.MAIN_RESOURCES_PATH);
    		String saveResourceDir = directory.getCanonicalPath();
    		//File img = new File(saveResourceDir + imagePath) ;
    		//img.createNewFile();
    		OutputStream dos = new FileOutputStream(saveResourceDir + imagePath);
    		IOUtils.copy(is,dos);
    		
			/*
			 * response.setContentType("image/png");
			 * 
			 * BufferedImage image = ImageIO.read(is); OutputStream out =
			 * response.getOutputStream(); ImageIO.write(image, "png", out);
			 */

            is.close();
            dos.close();
            
            
        } catch (Exception ex) {
        	ex.printStackTrace();
        	return ReturnDatas.error(null,"查看流程图失败");
        }
        return ReturnDatas.ok(imagePath);
    }
 	 
   
	public InputStream getDiagram(String processInstanceId) {
	    //获得流程实例
	    ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
	            .processInstanceId(processInstanceId).singleResult();
	    String processDefinitionId = StringUtils.EMPTY;
	    if (processInstance == null) {
	        //查询已经结束的流程实例
	        HistoricProcessInstance processInstanceHistory =
	                historyService.createHistoricProcessInstanceQuery()
	                        .processInstanceId(processInstanceId).singleResult();
	        if (processInstanceHistory == null)
	            return null;
	        else
	            processDefinitionId = processInstanceHistory.getProcessDefinitionId();
	    } else {
	        processDefinitionId = processInstance.getProcessDefinitionId();
	    }

	    //使用宋体
	    String fontName = "宋体";
	    //获取BPMN模型对象
	    BpmnModel model = repositoryService.getBpmnModel(processDefinitionId);
	    //获取流程实例当前的节点，需要高亮显示
	    List<String> currentActs = Collections.EMPTY_LIST;
	    if (processInstance != null)
	        currentActs = runtimeService.getActiveActivityIds(processInstance.getId());

	    return processEngine.getProcessEngineConfiguration()
	            .getProcessDiagramGenerator()
	            .generateDiagram(model, "png", currentActs, new ArrayList<String>(),
	                    fontName, fontName, fontName, null, 1.0);
	}
	
	
}
