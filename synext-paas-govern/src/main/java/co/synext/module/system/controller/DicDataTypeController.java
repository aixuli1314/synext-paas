package co.synext.module.system.controller;

import co.synext.common.base.resp.ReturnDatas;
import co.synext.module.system.dto.DicDataTypeDTO;
import co.synext.module.system.service.IDicDataTypeService;
import co.synext.mybatis.entity.TOrg;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/system/api/kjj/dicdatatype")
@AllArgsConstructor
public class DicDataTypeController {
    private final IDicDataTypeService dataTypeService;

    @ApiOperation(value = "字典类型列表接口")
    @PostMapping("/list")
    public ReturnDatas<Page<TOrg>> getPage(@RequestBody DicDataTypeDTO dicDataTypeDTO) {
        return dataTypeService.page(dicDataTypeDTO);
    }

    @ApiOperation(value = "字典类型保存接口")
    @PostMapping("/save")
    public ReturnDatas<Boolean> save(@Valid @RequestBody DicDataTypeDTO dicDataTypeDTO) {
        return dataTypeService.save(dicDataTypeDTO);
    }

    @ApiOperation(value = "字典类型更新接口")
    @PostMapping("/update")
    public ReturnDatas<Boolean> updateById(@Valid @RequestBody DicDataTypeDTO dicDataTypeDTO) {
        return dataTypeService.update(dicDataTypeDTO);
    }

    @ApiOperation(value = "字典类型详情接口")
    @PostMapping("/look/{id}")
    public ReturnDatas<TOrg> getById(@PathVariable String id) {
        return dataTypeService.findById(id);
    }

    @ApiOperation(value = "字典类型删除接口")
    @PostMapping("/delete")
    public ReturnDatas<Boolean> remove(@RequestBody List<String> ids) {
        return dataTypeService.batchDelete(ids);
    }
}
