package co.synext.module.system.controller;

import co.synext.common.base.resp.ReturnDatas;
import co.synext.mybatis.entity.TDicData;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import co.synext.module.system.dto.DicDataInputDTO;
import co.synext.module.system.dto.DicDataUpdateDTO;
import co.synext.module.system.dto.DicDataQueryDTO;
import co.synext.module.system.service.IDicDataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;


/**
 * <p>
 * 公共的字典,通过pathkey区分不通的字典类型,例如 民族字典 pathkey 的值都是minzu, controller通过 /api/dicdata/{pathkey}/list 进行查看 控制器
 * </p>
 *
 * @author xu.ran
 * @since 2020-08-27
 */
@Api(tags = "公共的字典,通过pathkey区分不同的字典类型,例如 民族字典 pathkey 的值都是minzu, controller通过 /api/dicdata/{pathkey}/list 进行查看接口")
@RestController
@RequestMapping("/system/api/kjj/dicdata")
@AllArgsConstructor
public class DicDataController {
    private final IDicDataService dicDataService;

    @ApiOperation(value = "列表接口")
    @PostMapping("/{pathkey}/list")
    public ReturnDatas<Page<TDicData>> getPage(@RequestBody DicDataQueryDTO dicDataQueryDTO) {
        return dicDataService.page(dicDataQueryDTO);
    }
    @ApiOperation(value = "获取字典树")
    @GetMapping("/dicDataTree")
    public ReturnDatas getOrgTree(){
        return dicDataService.getDicDataTree();
    }

    @ApiOperation(value = "保存接口")
    @PostMapping("/{pathkey}/save")
    public ReturnDatas<Boolean> save(@Valid @RequestBody DicDataInputDTO dicDataInputDTO) {
        return dicDataService.save(dicDataInputDTO);
    }

    @ApiOperation(value = "更新接口")
    @PostMapping("/{pathkey}/update")
    public ReturnDatas<Boolean> updateById(@Valid @RequestBody DicDataUpdateDTO dicDataUpdateDTO) {
        return dicDataService.update(dicDataUpdateDTO);
    }

    @ApiOperation(value = "详情接口")
    @PostMapping("/{pathkey}/look/{id}")
    public ReturnDatas<TDicData> getById(@PathVariable String id) {
        return dicDataService.findById(id);
    }

    @ApiOperation(value = "公共的字典,通过pathkey区分不同的字典类型,例如 民族字典 pathkey 的值都是minzu, controller通过 /api/dicdata/{pathkey}/list 进行查看删除接口")
    @PostMapping("/{pathkey}/{id}")
    public ReturnDatas<Boolean> removeById(@PathVariable Long id) {
        return dicDataService.deleteById(id);
    }


}