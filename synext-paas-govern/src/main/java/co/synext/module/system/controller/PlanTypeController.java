package co.synext.module.system.controller;

import co.synext.common.base.resp.ReturnDatas;
import co.synext.mybatis.entity.TPlanType;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import co.synext.module.system.dto.PlanTypeInputDTO;
import co.synext.module.system.dto.PlanTypeUpdateDTO;
import co.synext.module.system.dto.PlanTypeQueryDTO;
import co.synext.module.system.vo.PlanTypeVO;
import co.synext.module.system.service.IPlanTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;


/**
 * <p>
 * 计划类别,课题申报的是planType=0 控制器
 * </p>
 *
 * @author xu.ran
 * @since 2020-09-10
 */
@Api(tags = "计划类别,课题申报的是planType=0接口")
@RestController
@RequestMapping("/system/api/kjj/plantype")
@AllArgsConstructor
public class PlanTypeController {
    private final IPlanTypeService planTypeService;

    @ApiOperation(value = "列表接口")
    @PostMapping("/list")
    public ReturnDatas<Page<TPlanType>> getPage(@RequestBody PlanTypeQueryDTO planTypeQueryDTO) {
        return planTypeService.page(planTypeQueryDTO);
    }

    @ApiOperation(value = "保存接口")
    @PostMapping("/save")
    public ReturnDatas<Boolean> save(@Valid @RequestBody PlanTypeInputDTO planTypeInputDTO) {
        return planTypeService.save(planTypeInputDTO);
    }

    @ApiOperation(value = "更新接口")
    @PostMapping("/update")
    public ReturnDatas<Boolean> updateById(@Valid @RequestBody PlanTypeUpdateDTO planTypeUpdateDTO) {
        return planTypeService.update(planTypeUpdateDTO);
    }

    @ApiOperation(value = "详情接口")
    @PostMapping("/look/{id}")
    public ReturnDatas<TPlanType> getById(@PathVariable String id) {
        return planTypeService.findById(id);
    }

    @ApiOperation(value = "删除接口")
    @PostMapping("/delete")
    public ReturnDatas<Boolean> remove(@RequestBody List<String> ids) {
        return planTypeService.batchDelete(ids);
    }


}