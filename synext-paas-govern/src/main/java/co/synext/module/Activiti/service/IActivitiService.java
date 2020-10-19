package co.synext.module.Activiti.service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import co.synext.common.base.resp.ReturnDatas;
import co.synext.module.Activiti.dto.ApplyFlowInputDTO;
import co.synext.module.Activiti.dto.DeploymentQueryDTO;

/**
 * <p>
 * 流程引擎 服务类
 * </p>
 *
 * @author xu.ran
 * @since 2020-04-07
 */
public interface IActivitiService {
	/**
	 * 通过路径部署流程
	 * @author xu.ran
	 * @since 2020-09-10
	 */
	public ReturnDatas deployProcessByName(String deploymentPath,String deploymentName,String isdeleteHis );
	/**
	 * 通过文本部署流程
	 * @author xu.ran
	 * @since 2020-09-10
	 */
	public ReturnDatas deployProcessByText(String deploymentPath,String deploymentName );
	/**
	 *执行流程节点
	 * @author xu.ran
	 * @since 2020-09-10
	 */
	public ReturnDatas completeTask(String taskId);
	/**
	 *获取流程实例列表(分页)
	 * @author xu.ran
	 * @since 2020-09-13
	 */
	public ReturnDatas getDeploymentPage(DeploymentQueryDTO deploymentQueryDTO);
	/**
	 *流程审批列表(分页)
	 * @author xu.ran
	 * @since 2020-09-13
	 */
	public ReturnDatas getTaskListPage();
	/**
	 *删除流程部署
	 * @author xu.ran
	 * @since 2020-09-13
	 */
	public ReturnDatas<Boolean> removeDeploymentById(String deploymentId,String isdeleteHis);
	/**
	 *启动流程
	 * @author xu.ran
	 * @since 2020-09-13
	 */
	public ReturnDatas startProcessInstance(String processDefinitionId,ApplyFlowInputDTO applyFlowInputDTO);
	/**
	 * 部署全部流程
	 *
	 * @return
	 * @throws Exception
	 */
	public ReturnDatas redeployAllProcess(String isdeleteHis);

}
