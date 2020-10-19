package co.synext.config.ProcessTask.TaskListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.activiti.engine.repository.ProcessDefinition;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.synext.common.enums.Enums.TaskDefKey;
import co.synext.common.utils.SpringContextHolder;
import co.synext.config.security.details.LoginUser;
import co.synext.config.security.helper.LoginUserHelper;
import co.synext.module.Activiti.service.IUserTaskService;
import co.synext.module.system.service.IUserService;
import co.synext.mybatis.entity.TUser;
import co.synext.mybatis.entity.TUserTask;

/**
 * 动态用户任务分配
 * @author ZML
 *
 */
@Component("userTaskListener")
public class UserTaskListener implements TaskListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2190559253653576032L;
	
	private static final Logger logger = Logger.getLogger(UserTaskListener.class);
    @Autowired
    protected RepositoryService repositoryService;

	@Autowired
	private IUserTaskService userTaskService;
    
	@Override
	public void notify(DelegateTask delegateTask) {
		String processDefinitionId = delegateTask.getProcessDefinitionId();	//com.zml.oa.applyFlow:8:30012
		ProcessDefinition processDefinition = this.repositoryService.createProcessDefinitionQuery().processDefinitionId(processDefinitionId).singleResult();
//		String processDefinitionName = processDefinition.getName();			//申报流程
		String processDefinitionKey = processDefinition.getKey();			//com.zml.oa.applyFlow
		String taskDefinitionKey = delegateTask.getTaskDefinitionKey();		//directorAudit
		try {
			if(StringUtils.compare(taskDefinitionKey, TaskDefKey.企业管理员审核.getCode())==0) {
				LoginUser user = LoginUserHelper.getCurrentLoginUser();
				List<TUser> admins = SpringContextHolder.getBean(IUserService.class).findEnterpriseAdmin(user.getEnterpriseId());
				List<String> users = new ArrayList<String>();
				for(TUser tuser : admins){
					users.add(tuser.getId());
				}
				delegateTask.addCandidateUsers(users);
			}
			Map<String,Object> param = new HashMap<>();
			param.put("PROC_DEF_KEY", processDefinitionKey);
			List<TUserTask> taskList = this.userTaskService.listByMap(param);
			for(TUserTask userTask : taskList){
				String taskKey = userTask.getTaskDefKey();
				String taskType = userTask.getTaskType();
				String ids = userTask.getCandidateIds();
				if(taskDefinitionKey.equals(taskKey)){
					switch (taskType){
						case "assignee" : {
							delegateTask.setAssignee(ids);
							logger.info("assignee id: "+ids);
							break;
						}
						case "candidateUser" : {
							String[] userIds = ids.split(",");
							List<String> users = new ArrayList<String>();
							for(int i=0; i<userIds.length;i++){
								users.add(userIds[i]);
							}
							delegateTask.addCandidateUsers(users);
							logger.info("候选人审批 ids: "+ids);
							break;
						}
						case "candidateGroup" : {
							String[] groupIds = ids.split(",");
							List<String> groups = new ArrayList<String>();
							for(int i=0; i<groupIds.length;i++){
								groups.add(groupIds[i]);
							}
							delegateTask.addCandidateGroups(groups);
							logger.info("候选组审批 ids: "+ids);
							break;
						}
					}
				}
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
