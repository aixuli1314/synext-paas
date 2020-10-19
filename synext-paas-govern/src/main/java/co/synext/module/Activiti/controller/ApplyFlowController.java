package co.synext.module.Activiti.controller;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.*;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import co.synext.common.base.resp.ReturnDatas;
import co.synext.module.Activiti.dto.ApplyFlowInputDTO;
import co.synext.module.Activiti.dto.ApplyFlowQueryDTO;
import co.synext.module.Activiti.dto.ApplyFlowUpdateDTO;
import co.synext.module.Activiti.service.IApplyFlowService;
import co.synext.mybatis.entity.TApplyFlow;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

import java.util.List;


/**
 * <p>
 *  业务流程信息控制器
 * </p>
 *
 * @author xu.ran
 * @since 2020-09-16
 */
@Api(tags = "业务流程信息接口")
@RestController
@RequestMapping("/open/api/kjj/applyFlow")
@AllArgsConstructor
public class ApplyFlowController {
    private final IApplyFlowService applyFlowService;

    @ApiOperation(value = "列表接口")
    @GetMapping("/list")
    public ReturnDatas<Page<TApplyFlow>> getPage(@RequestBody ApplyFlowQueryDTO applyFlowQueryDTO) {
        return applyFlowService.page(applyFlowQueryDTO);
    }

    @ApiOperation(value = "保存接口")
    @PostMapping
    public ReturnDatas<Boolean> save(@Valid @RequestBody ApplyFlowInputDTO applyFlowInputDTO) {
        return applyFlowService.save(applyFlowInputDTO);
    }

    @ApiOperation(value = "更新接口")
    @PostMapping("/update")
    public ReturnDatas<Boolean> updateById(@Valid @RequestBody ApplyFlowUpdateDTO applyFlowUpdateDTO) {
        return applyFlowService.update(applyFlowUpdateDTO);
    }

    @ApiOperation(value = "详情接口")
    @GetMapping("/{id}")
    public ReturnDatas<TApplyFlow> getById(@PathVariable @RequestParam String id) {
        return applyFlowService.findById(id);
    }

    @ApiOperation(value = "删除接口")
    @DeleteMapping("/{id}")
    public ReturnDatas<Boolean> removeById(@PathVariable @RequestParam String id) {
        return applyFlowService.deleteById(id);
    }

    @ApiOperation(value = "删除多个接口")
    @DeleteMapping("/delete")
    public ReturnDatas<Boolean> remove(@RequestBody List<String> ids) {
        return applyFlowService.batchDelete(ids);
    }

}