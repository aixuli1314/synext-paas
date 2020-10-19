package co.synext.module.system.controller;

import co.synext.common.base.resp.ReturnDatas;
import co.synext.mybatis.entity.TRegisteredFlow;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import co.synext.module.system.dto.RegisteredFlowInputDTO;
import co.synext.module.system.dto.RegisteredFlowUpdateDTO;
import co.synext.module.system.dto.RegisteredFlowQueryDTO;
import co.synext.module.system.vo.RegisteredFlowVO;
import co.synext.module.system.service.IRegisteredFlowService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;


/**
 * <p>
 * 注册流程,关联t_user和t_org,有专家注册和企业注册,企业注册.注册成功之后,短信通知 控制器
 * </p>
 *
 * @author xu.ran
 * @since 2020-09-10
 */
@Api(tags = "注册流程,关联t_user和t_org,有专家注册和企业注册,企业注册.注册成功之后,短信通知接口")
@RestController
@RequestMapping("/system/api/kjj/registeredflow")
@AllArgsConstructor
public class RegisteredFlowController {
    private final IRegisteredFlowService registeredFlowService;

    @ApiOperation(value = "列表接口")
    @PostMapping("/list")
    public ReturnDatas<Page<TRegisteredFlow>> getPage(@RequestBody RegisteredFlowQueryDTO registeredFlowQueryDTO) {
        return registeredFlowService.page(registeredFlowQueryDTO);
    }

    @ApiOperation(value = "保存接口")
    @PostMapping("/save")
    public ReturnDatas<Boolean> save(@Valid @RequestBody RegisteredFlowInputDTO registeredFlowInputDTO) {
        return registeredFlowService.save(registeredFlowInputDTO);
    }

    @ApiOperation(value = "更新接口")
    @PostMapping("/update")
    public ReturnDatas<Boolean> updateById(@Valid @RequestBody RegisteredFlowUpdateDTO registeredFlowUpdateDTO) {
        return registeredFlowService.update(registeredFlowUpdateDTO);
    }

    @ApiOperation(value = "详情接口")
    @PostMapping("/look/{id}")
    public ReturnDatas<TRegisteredFlow> getById(@PathVariable String id) {
        return registeredFlowService.findById(id);
    }

    @ApiOperation(value = "删除接口")
    @PostMapping("/delete")
    public ReturnDatas<Boolean> removeById(@RequestBody List<String> ids) {
        return registeredFlowService.batchDelete(ids);
    }


}