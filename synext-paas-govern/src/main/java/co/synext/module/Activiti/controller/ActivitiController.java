package co.synext.module.Activiti.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.web.bind.annotation.*;

import co.synext.common.base.resp.ReturnDatas;
import co.synext.module.Activiti.dto.DeploymentQueryDTO;
import co.synext.module.Activiti.service.IActivitiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;


/**
 * <p>
 * 审批流程业务接口 控制器
 * </p>
 *
 * @author xu.ran
 * @since 2020-08-27
 */
@Api(tags = "★管理员审批流程业务接口")
@RestController
@RequestMapping("/system/api/kjj/activiti")
@AllArgsConstructor
public class ActivitiController {
    private final IActivitiService activitiService;

    @ApiOperation(value = "查看流程部署列表接口")
    @PostMapping("/deployment/list")
    public ReturnDatas getPage(@Valid @RequestBody DeploymentQueryDTO deploymentQueryDTO) {
        return activitiService.getDeploymentPage(deploymentQueryDTO);
    }

    @ApiOperation(value = "通过路径流程保存/部署接口")
    @PostMapping("/deployment/deployProcessByName")
    public ReturnDatas deployProcessByName(@Valid @NotNull @RequestParam String deploymentPath, @Valid @NotNull @RequestParam String deploymentName,
                                           @RequestParam String isdeleteHis) {
        return activitiService.deployProcessByName(deploymentPath, deploymentName,isdeleteHis);
    }

    @ApiOperation(value = "通过文本流程保存/部署接口")
    @PostMapping("/deployment/deployProcessByText")
    public ReturnDatas deployProcessByText(@Valid @NotNull @RequestParam String text, @Valid @NotNull @RequestParam String deploymentName,@Valid @NotNull String isdeleteHis) {
        return activitiService.deployProcessByText(text, deploymentName);
    }
    /**
     * 部署全部流程
     *
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "重新部署全部流程")
    @GetMapping(value = "/deployment/redeployall")
    public ReturnDatas redeployAll(@Valid @NotNull @RequestParam String isdeleteHis) throws Exception {

        return activitiService.redeployAllProcess(isdeleteHis);
    }

    @ApiOperation(value = "流程删除接口")
    @GetMapping("/deployment/delete")
    public ReturnDatas<Boolean> removeDeploymentById(@Valid @NotNull @RequestParam String deploymentId,@RequestParam String isdeleteHis) {
        return activitiService.removeDeploymentById(deploymentId,isdeleteHis);
    }



}