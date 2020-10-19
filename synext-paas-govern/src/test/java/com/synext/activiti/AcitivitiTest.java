package com.synext.activiti;

import java.util.List;

import javax.annotation.Resource;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.GraphicInfo;
import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.util.ProcessDefinitionUtil;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import co.synext.GovernApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = GovernApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AcitivitiTest {
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
	
	/**
	 * 获取所有流程节点
	 */
	
	@Test
	public void getTasks() {
		// 查询当前的流程实例
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("BeforSubsidize");
		//ProcessInstance processInstance = runtimeService.startProcessInstanceById("BeforSubsidize:1:47504");
		//ProcessInstance processInstance = runtimeService.startProcessInstanceById("BeforSubsidize:1:47504", "BeforSubsidize");
		System.out.println(processInstance.getId());
		
	}
	/**
	 * 部署一个流程
	 */

	@Test
	public void deployProcess() {
		repositoryService.createDeployment()
				.addClasspathResource("processes/MyProcess.bpmn")
				//.addClasspathResource("processes/test.svg")
				.name("申报流程")
				.deploy();
	}
}
