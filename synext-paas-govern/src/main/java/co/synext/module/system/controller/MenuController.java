package co.synext.module.system.controller;

import co.synext.common.base.resp.ReturnDatas;
import co.synext.module.system.dto.MenuDTO;
import co.synext.module.system.service.IMenuService;

import co.synext.mybatis.entity.TUser;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;


@Api(tags = "4-系统菜单接口")
@RestController
@RequestMapping("/system/api/kjj/menu")
@AllArgsConstructor
public class MenuController {
    private final IMenuService menuService;

    @PostMapping("/child")
    public ReturnDatas getSysMenuPage(@RequestParam(value = "parentId",required = false,defaultValue = "0") String parentId) {
        return ReturnDatas.ok(menuService.findMenuVoListByPid(parentId));
    }

    @PostMapping("/list")
    public ReturnDatas<Page<TUser>> getMenuPage(@RequestBody MenuDTO menuDTO) {
        return menuService.page(menuDTO);
    }

    @PostMapping("/permission")
    public ReturnDatas getSysPermission(){
        return ReturnDatas.ok(menuService.findAllPermission());
    }
    @PostMapping()
    public ReturnDatas getSysMenus(){
        return ReturnDatas.ok(menuService.findAllMenus());
    }

    @PostMapping("/routes")
    public ReturnDatas getRoutes() {
        return ReturnDatas.ok(menuService.findAllRoutes());
    }

    @PostMapping("/save")
    public ReturnDatas save(@Valid @RequestBody MenuDTO menuDto) {
        return menuService.saveMenu(menuDto);
    }

    @PostMapping("/update")
    public ReturnDatas updateById(@Valid @RequestBody MenuDTO menuDto) {
        return menuService.updateMenu(menuDto);
    }

    @PostMapping("/look/{id}")
    public ReturnDatas getById(@PathVariable String id) {
        return ReturnDatas.ok(menuService.findMenuVoById(id));
    }

    @ApiOperation(value = "菜单删除接口")
    @PostMapping("/delete")
    public ReturnDatas<Boolean> remove(@RequestBody List<String> ids) {
        return menuService.batchDelete(ids);
    }

/*  @DeleteMapping("/{id}")
    public ReturnDatas removeById(@PathVariable String id) {
        return ReturnDatas.ok(menuService.deleteMenu(id));
    }*/


}
