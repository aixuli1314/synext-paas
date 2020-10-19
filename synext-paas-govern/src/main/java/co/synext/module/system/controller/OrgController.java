package co.synext.module.system.controller;

import co.synext.common.base.resp.ReturnDatas;
import co.synext.mybatis.entity.TOrg;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import co.synext.module.system.dto.OrgInputDTO;
import co.synext.module.system.dto.OrgUpdateDTO;
import co.synext.module.system.dto.OrgQueryDTO;
import co.synext.module.system.service.IOrgService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;


/**
 * <p>
 * 部门 控制器
 * </p>
 *
 * @author xu.ran
 * @since 2020-08-27
 */
@Api(tags = "部门接口")
@RestController
@RequestMapping("/system/api/kjj/org")
@AllArgsConstructor
public class OrgController {
    private final IOrgService orgService;

    @ApiOperation(value = "部门列表接口")
    @PostMapping("/list")
    public ReturnDatas<Page<TOrg>> getPage(@RequestBody OrgQueryDTO orgQueryDTO) {
        return orgService.page(orgQueryDTO);
    }

    @ApiOperation(value = "所有部门接口")
    @PostMapping("/getAll")
    public ReturnDatas<TOrg> getAllOrg() {
        return orgService.getAllOrg();
    }

    @ApiOperation(value = "部门保存接口")
    @PostMapping("/save")
    public ReturnDatas<Boolean> save(@Valid @RequestBody OrgInputDTO orgInputDTO) {
        return orgService.save(orgInputDTO);
    }
    @ApiOperation(value = "获取机构树")
    @PostMapping("/orgTree")
    public ReturnDatas getOrgTree(){
        return orgService.getOrgTree();
    }

    @ApiOperation(value = "部门更新接口")
    @PostMapping("/update")
    public ReturnDatas<Boolean> updateById(@Valid @RequestBody OrgUpdateDTO orgUpdateDTO) {
        return orgService.update(orgUpdateDTO);
    }

    @ApiOperation(value = "部门详情接口")
    @PostMapping("/look/{id}")
    public ReturnDatas<TOrg> getById(@PathVariable String id) {
        return orgService.findById(id);
    }

    @ApiOperation(value = "部门删除接口")
    @PostMapping("/delete")
    public ReturnDatas<Boolean> remove(@RequestBody List<String> ids) {
        return orgService.batchDelete(ids);
    }

/*    @ApiOperation(value = "部门删除接口")
    @PostMapping("/delete/{id}")
    public ReturnDatas<Boolean> removeById(@PathVariable Long id) {
        return orgService.deleteById(id);
    }*/


}