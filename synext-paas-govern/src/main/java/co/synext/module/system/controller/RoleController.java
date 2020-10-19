package co.synext.module.system.controller;

import co.synext.module.system.dto.RoleDTO;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import co.synext.common.base.resp.ReturnDatas;
import co.synext.module.system.service.IRoleService;
import co.synext.mybatis.entity.TRole;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;


@Api(tags = "4-系统角色接口")
@RestController
@AllArgsConstructor
@RequestMapping("/system/api/kjj/role")
public class RoleController {
    private final IRoleService roleService;

/*  @GetMapping("/routes")
    public ReturnDatas getRoutes(Page page, TRole queryBean) {
        return ReturnDatas.ok(roleService.page(page, Wrappers.query(queryBean)));
    }*/

    @PostMapping("/list")
    public ReturnDatas getRoleList(@RequestBody RoleDTO roleDTO) {
        return roleService.page(roleDTO);
    }

    @PostMapping("/save")
    public ReturnDatas save(@Valid @RequestBody RoleDTO roleDto) {
        return roleService.saveRole(roleDto);
    }

    @PostMapping("/update")
    public ReturnDatas update(@Valid @RequestBody RoleDTO roleDto) {
        return roleService.updateRole(roleDto);
    }

    @PostMapping("/look/{id}")
    public ReturnDatas getById(@PathVariable String id) {
        return ReturnDatas.ok(roleService.getById(id));
    }

    @PostMapping("/delete")
    public ReturnDatas<Boolean> remove(@RequestBody List<String> ids) {
        return roleService.batchDelete(ids);
    }

}
