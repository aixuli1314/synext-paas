package co.synext.module.Activiti.service;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import co.synext.common.base.resp.ReturnDatas;
import co.synext.config.security.details.LoginUser;
import co.synext.module.Activiti.dto.ApplyFlowQueryDTO;
import co.synext.module.Activiti.vo.ApplyFlowVO;

/*import com.zml.oa.entity.ExpenseAccount;
import com.zml.oa.entity.SalaryAdjust;
import com.zml.oa.entity.User;
import com.zml.oa.entity.TApplyFlow;
import com.zml.oa.pagination.Page;*/

import co.synext.module.Activiti.vo.BaseVO;
import co.synext.module.system.vo.ProcessInstanceEntityVO;
import co.synext.mybatis.entity.TApplyFlow;

public interface IProcessService {
	
	/**
	 * 启动薪资调整
	 * @param user
	 * @param salary
	 * @param variables
	 * @return
	 * @throws Exception
	 */
	//public String startSalaryAdjust(SalaryAdjust salary) throws Exception;
	
	/**
	 * 启动审批流程
	 * @param applyFlow
	 * @return
	 * @throws Exception
	 */
	public String startApplyFlow(ApplyFlowVO applyFlowVO) throws Exception;
	
	/**
	 * 启动报销流程
	 * @param expense
	 * @return
	 * @throws Exception
	 */
	//public String startExpense(ExpenseAccount expense) throws Exception;
	
	/**
	 * 查询代办任务
	 * @param user
	 * @param model
	 * @return
	 */
	public ReturnDatas findTodoTask(LoginUser user, BaseVO page) throws Exception;
	
    /**
     * 签收任务
     * @param user
     * @param taskId
     */
    public void doClaim(LoginUser user, String taskId) throws Exception;
    
    /**
     * 委派任务
     * @param userId
     * @throws Exception
     */
    public void doDelegateTask(String userId, String taskId) throws Exception;
    
    /**
     * 转办任务
     * @param userId
     * @param taskId
     * @throws Exception
     */
    public void doTransferTask(String userId, String taskId) throws Exception;
    
    /**
     * 完成任务
     * @param taskId
     * @param content
     * @param userid
     * @param completeFlag
     */
    public void complete(String taskId, String content, String userid, Map<String, Object> variables) throws Exception;
    
    /**
     * 撤销任务
     * @param historyTaskId
     * @throws Exception
     */
    public Integer revoke(String historyTaskId, String processInstanceId) throws Exception;
    
    /**
     * 获取评论
     * @param processInstanceId
     * @return
     * @throws Exception
     */
   // public List<CommentVO> getComments(String processInstanceId) throws Exception;
    
    /**
     * 显示流程图,带流程跟踪
     * @param processInstanceId
     * @return
     */
    public InputStream getDiagram(String processInstanceId) throws Exception;
    
    /**
     * 显示图片-通过流程ID，不带流程跟踪(没有乱码问题)
     * @param resourceType
     * @param processInstanceId
     * @return
     */
    public InputStream getDiagramByProInstanceId_noTrace(String resourceType, String processInstanceId) throws Exception;
    
    /**
     * 显示图片-通过部署ID，不带流程跟踪(没有乱码啊问题)
     * @param resourceType
     * @param processInstanceId
     * @return
     * @throws Exception
     */
    public InputStream getDiagramByProDefinitionId_noTrace(String resourceType, String processDefinitionId) throws Exception;

    /**
     * 读取已结束中的流程-admin查看
     *
     * @return
     */
    public List<BaseVO> findFinishedProcessInstances(BaseVO page) throws Exception;
    
    /**
     * 各个审批人员查看自己完成的任务
     * @param model
     * @return
     * @throws Exception
     */
    public List<BaseVO> findFinishedTaskInstances(LoginUser user, BaseVO page) throws Exception;
    
    /**
     * 查看正在运行的申报流程
     * @param user
     * @return
     * @throws Exception
     */
    public ReturnDatas listRuningTApplyFlow(LoginUser user, ApplyFlowQueryDTO flowDTO) throws Exception;
    
    /**
     * 查看正在运行的报销流程
     * @param user
     * @return
     * @throws Exception
     */
    //public List<BaseVO> listRuningExpense(User user, Page<ExpenseAccount> page) throws Exception;
    
    /**
     * 查看正在运行的薪资跳转流程
     * @param user
     * @return
     * @throws Exception
     */
    //public List<BaseVO> listRuningSalaryAdjust(User user, Page<SalaryAdjust> page) throws Exception;
    
    /**
     * 管理运行中流程
     * @return
     * @throws Exception
     */
    public List<ProcessInstanceEntityVO> listRuningProcess(BaseVO page) throws Exception;

    /**
     * 激活流程实例
     * @param processInstanceId
     * @throws Exception
     */
    public void activateProcessInstance(String processInstanceId) throws Exception;
    
    /**
     * 挂起流程实例
     * @param processInstanceId
     * @throws Exception
     */
    public void suspendProcessInstance(String processInstanceId) throws Exception;
    
    
}
