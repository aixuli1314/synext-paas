package co.synext.module.Activiti.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.synext.common.base.resp.ReturnDatas;
import co.synext.module.Activiti.dto.ApplyFlowInputDTO;
import co.synext.module.Activiti.service.IActivitiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

/**
 * 流程控制类
 * @author ZML
 *
 */
@Api(tags = "★企业跟人流程任务接口")
@RestController
@AllArgsConstructor
@RequestMapping("/member/process")
public class MemberProcessController {
	private static final Logger logger = Logger.getLogger(MemberProcessController.class);
	
	private final IActivitiService activitiService;
	
	@ApiOperation(value = "启动流程接口")

    @PostMapping("/startProcessInstance")
    public ReturnDatas startProcessInstance(@Valid @NotNull String processDefinitionId,@Valid ApplyFlowInputDTO applyFlowInputDTO) {
    	return activitiService.startProcessInstance(processDefinitionId,applyFlowInputDTO);
    }
   
}
