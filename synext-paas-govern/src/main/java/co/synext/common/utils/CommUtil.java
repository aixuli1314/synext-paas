package co.synext.common.utils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.impl.bpmn.data.IOSpecification;
import org.activiti.engine.impl.persistence.entity.IdentityLinkEntity;
import org.activiti.engine.impl.persistence.entity.SuspensionState;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.BeanUtils;

public class CommUtil {
	
	/**
	 * 因为ProcessDefinition有懒加载成员属性，无法给前端转Json,因此把ProcessDefinition转为Map
	 * @param source 目标对象
	 * @return
	 */
	public static Map<String, Object> processDefinition2map(ProcessDefinition processDefinition) {
		String[] ps = {"name","description","key","version","category","deploymentId","resourceName",
				"tenantId","historyLevel","diagramResourceName","isGraphicalNotationDefined","variables",
				"hasStartFormKey","suspensionState","isIdentityLinksInitialized","ioSpecification",
				"engineVersion"};
		return obj2map(processDefinition,ps );
	}
	/**
	 * 因为Task有懒加载成员属性，无法给前端转Json,因此把Task转为Map
	 * @param source 目标对象
	 * @return
	 */
	public static Map<String, Object> task2map(Task source) {
		String[] ps = {"id","owner","assigneeUpdatedCount","originalAssignee","assignee","delegationState","parentTaskId","name",
				"localizedName","description","localizedDescription","priority","createTime","dueDate","suspensionState",
				"category","isIdentityLinksInitialized","executionId","processInstanceId",
				"processDefinitionId","taskDefinitionKey","formKey","isDeleted","isCanceled","eventName",
				"tenantId","forcedUpdate","claimTime"};
		return obj2map(source,ps );
	}
	/**
	 * 因为ProcessInstance有懒加载成员属性，无法给前端转Json,因此把ProcessInstance转为Map
	 * @param source 目标对象
	 * @return
	 */
	public static Map<String, Object> processInstance2map(ProcessInstance source) {
		String[] ps = {"id","tenantId","name","description","localizedName","localizedDescription",
				"lockTime","isActive","isScope","isConcurrent","isEnded","isEventScope","isMultiInstanceRoot",
				"isCountEnabled","eventName",
				"deleteReason","suspensionState","startUserId","startTime",
				"eventSubscriptionCount","taskCount","jobCount","timerJobCount","suspendedJobCount",
				"deadLetterJobCount","variableCount","identityLinkCount","processDefinitionId",
				"processDefinitionKey","processDefinitionName","processDefinitionVersion","deploymentId",
				"activityId","activityName","processInstanceId","businessKey","parentId","superExecutionId",
				"rootProcessInstanceId","forcedUpdate","isDeleted"};
		return obj2map(source,ps );
	}
	
		
	/**
	 * 把指定的复杂对象属性，按照指定的内容，封装到新的map中
	 * @param source 目标对象
	 * @param ps     需要封装到map中的属性
	 * @return
	 */
	public static Map<String, Object> obj2map(Object source,String[] ps ) {
		
		Map<String, Object> map = new HashMap<>();
		if (source == null)
			return null;
		if (ps == null || ps.length < 1) {
			return null;
		}
		for (String p : ps) {
			PropertyDescriptor sourcePd = BeanUtils.getPropertyDescriptor(
					source.getClass(), p);
			if (sourcePd != null && sourcePd.getReadMethod() != null) {
				try {
					Method readMethod = sourcePd.getReadMethod();
					if (!Modifier.isPublic(readMethod.getDeclaringClass()
							.getModifiers())) {
						readMethod.setAccessible(true);
					}
					Object value = readMethod.invoke(source, new Object[0]);
					map.put(p, value);
				} catch (Exception ex) {
					ex.printStackTrace();
					throw new RuntimeException(
							"Could not copy properties from source to target",
							ex);
				}
			}
		}
		return map;
	}
}