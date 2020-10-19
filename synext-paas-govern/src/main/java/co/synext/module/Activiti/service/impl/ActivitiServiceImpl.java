package co.synext.module.Activiti.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import javax.annotation.Resource;

import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.impl.context.Context;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.synext.common.base.resp.ReturnDatas;
import co.synext.common.base.service.ActivitiBaseService;
import co.synext.common.constant.Constant;
import co.synext.common.enums.Enums.ApplyFlowName;
import co.synext.common.enums.Enums.YesOrNo;
import co.synext.config.security.helper.LoginUserHelper;
import co.synext.module.Activiti.dto.ApplyFlowInputDTO;
import co.synext.module.Activiti.dto.DeploymentQueryDTO;
import co.synext.module.Activiti.service.IActivitiService;
import co.synext.module.Activiti.service.IApplyFlowService;
import co.synext.module.Activiti.service.activiti.WorkflowDeployService;
import co.synext.module.Activiti.vo.ApplyFlowVO;
import co.synext.module.Activiti.vo.DeploymentVO;
import co.synext.mybatis.entity.TApplyFlow;

/**
 * <p>
 * 流程引擎 服务实现类
 * </p>
 *
 * @author xu.ran
 * @since 2020-04-07
 */
@Service
public class ActivitiServiceImpl extends ActivitiBaseService implements IActivitiService {
	@Resource
	private RuntimeService runtimeService;
	@Resource
	private TaskService taskService;
	@Resource
	private ProcessEngine processEngine;
	@Resource
	private RepositoryService repositoryService;
	@Resource
	private HistoryService historyService;
	@Autowired
	private WorkflowDeployService workflowProcessDefinitionService;
	@Autowired
	private IApplyFlowService applyFlowService;

	/**
	 * 通过路径部署流程
	 * @author xu.ran
	 * @since 2020-09-10
	 */
	@Override
	public ReturnDatas deployProcessByName(String deploymentPath, String deploymentName,String isdeleteHis) {
		if(StringUtils.isBlank(deploymentPath)) {
			return ReturnDatas.error("流程实例文件不能为空!");
		}
		if(StringUtils.isBlank(deploymentName)) {
			return ReturnDatas.error("新流程名称不能为空!");
		}
		String fileName = deploymentPath.substring(deploymentPath.lastIndexOf("/")+1);

		if(StringUtils.isNoneBlank(fileName)) {
			fileName = fileName.substring(0,fileName.lastIndexOf("."));
		}
		List<Deployment> deploymentList = this.repositoryService.createDeploymentQuery().deploymentKey(fileName).list();
		//删除现有所有流程实例
		for(Deployment deployment : deploymentList){
			String deploymentId = deployment.getId();
			if(StringUtils.compare(YesOrNo.是.getCode(), isdeleteHis)==0) {
				this.repositoryService.deleteDeployment(deploymentId, true);
			}else {
				this.repositoryService.deleteDeployment(deploymentId);
			}
		}

		repositoryService.createDeployment()
				.addClasspathResource(deploymentPath)
				.addClasspathResource(deploymentPath.substring(0, deploymentPath.lastIndexOf(".bpmn"))+".png")
				.name(ApplyFlowName.getApplyFlowName(fileName)).tenantId(LoginUserHelper.getCurrentLoginUser().getTenantId())
				.key(fileName)
				.deploy();
		return ReturnDatas.ok();
	}
	/**
	 * 通过文本内容部署流程
	 * @author xu.ran
	 * @since 2020-09-10
	 */
	@Override
	public ReturnDatas deployProcessByText(String text, String deploymentName) {
		if(StringUtils.isBlank(text)) {
			return ReturnDatas.error("流程实例内容不能为空!");
		}
		if(StringUtils.isBlank(deploymentName)) {
			return ReturnDatas.error("新流程名称不能为空!");
		}
		repositoryService.createDeployment()
				.addString(deploymentName,text)
				.tenantId(LoginUserHelper.getCurrentLoginUser().getTenantId())
				.deploy();
		return ReturnDatas.ok();
	}

	/**
	 *执行流程节点
	 * @author xu.ran
	 * @since 2020-09-10
	 */
	@Override
	public ReturnDatas completeTask(String taskId) {
		taskService.complete(taskId);
		return ReturnDatas.ok();
	}
	/**
	 *获取可申请流程列表(分页)
	 * @author xu.ran
	 * @since 2020-09-13
	 */
	@Override
	public ReturnDatas getDeploymentPage(DeploymentQueryDTO deploymentQueryDTO) {
		List<Deployment> listPage = repositoryService.createDeploymentQuery()
				.deploymentTenantId(deploymentQueryDTO.getTenantId())
				.orderByDeploymentId().desc().listPage(deploymentQueryDTO.getCurrent()-1, deploymentQueryDTO.getSize());
		List<DeploymentVO> resultList = new ArrayList<>();
		if(CollectionUtils.isNotEmpty(listPage)) {
			for(Deployment deployment : listPage) {
				DeploymentVO deploymentVO = copy2(deployment, DeploymentVO.class);
				resultList.add(deploymentVO);
			}
		}
		ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery().orderByDeploymentId().desc();
		return ReturnDatas.ok(resultList,null,deploymentQueryDTO.page(processDefinitionQuery.list().size()));
	}
	/**
	 *流程审批列表(分页)
	 * @author xu.ran
	 * @since 2020-09-13
	 */
	@Override
	public ReturnDatas getTaskListPage() {

		return ReturnDatas.ok();
	}

	/**
	 *删除流程部署
	 * @author xu.ran
	 * @since 2020-09-13
	 */
	@Override
	public ReturnDatas<Boolean> removeDeploymentById(String deploymentId, String isdeleteHis) {
		if(StringUtils.compare(YesOrNo.是.getCode(), isdeleteHis)==0) {
			repositoryService.deleteDeployment(deploymentId, true);
		}else {
			repositoryService.deleteDeployment(deploymentId);
		}
		return ReturnDatas.ok();
	}
	/**
	 *启动流程
	 * @author xu.ran
	 * @since 2020-09-13
	 */
	@Override
	public ReturnDatas startProcessInstance(String processDefinitionId,ApplyFlowInputDTO applyFlowInputDTO) {
		String userId = LoginUserHelper.getCurrentLoginUser().getId();
		ApplyFlowVO applyFlowVO = applyFlowService.copy2(applyFlowInputDTO, ApplyFlowVO.class);
		applyFlowVO.setId(getWorkId());
		applyFlowVO.setUserId(userId);
		LocalDate now = LocalDate.now();
		applyFlowVO.setApplyDate(now);
		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(processDefinitionId).singleResult();
		applyFlowVO.setApplyType(processDefinition.getKey());
		
		Map<String,Object> variables = new HashMap<>();
		variables.put("isPass", true);
		variables.put("applyUserId", userId);
		variables.put("entity", applyFlowVO);
		
		ProcessInstance processInstance = runtimeService.startProcessInstanceById(processDefinitionId,applyFlowVO.getId(),variables);
		applyFlowVO.setProcInstId(processInstance.getId());
		
		
		//流程部署成功后，整合业务数据并报存
		TApplyFlow applyFlow = applyFlowService.copy2(applyFlowVO, TApplyFlow.class);
		applyFlowService.save(applyFlow);
		return ReturnDatas.ok();
	}

	/**
	 * 部署全部流程
	 *
	 * @return
	 * @throws Exception
	 */
	@Override
	public ReturnDatas redeployAllProcess(String isdeleteHis) {
		try {
			List<Deployment> deploymentList = this.repositoryService.createDeploymentQuery().list();
			//删除现有所有流程实例
			for(Deployment deployment : deploymentList){
				String deploymentId = deployment.getId();
				if(StringUtils.compare(YesOrNo.是.getCode(), isdeleteHis)==0) {
					this.repositoryService.deleteDeployment(deploymentId, true);
				}else {
					this.repositoryService.deleteDeployment(deploymentId);
				}
			}
			//重新部署全部流程实例
			//方法一：通过classpath/deploy目录下的.zip或.bar文件部署
			//workflowProcessDefinitionService.deployAllFromClasspath(Constant.MAIN_RESOURCES_BPMNPATH);

			//方法二：通过classpath/bpmn下的流程描述文件部署-流程图错乱，一直提倡用打包部署没有任何问题。
			workflowProcessDefinitionService.redeployBpmn(Constant.MAIN_RESOURCES_BPMNPATH);

			return ReturnDatas.ok(null, "已重新部署全部流程！");
		} catch (Exception e) {
			return ReturnDatas.error(null,"重新部署流程失败！");
		}
	}

}
