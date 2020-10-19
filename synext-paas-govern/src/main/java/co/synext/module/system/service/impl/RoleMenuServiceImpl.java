package co.synext.module.system.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import co.synext.common.base.service.BaseService;
import co.synext.common.exception.BizException;
import co.synext.mybatis.entity.TRoleMenu;
import co.synext.mybatis.entity.TMenu;
import co.synext.mybatis.mapper.TRoleMenuMapper;
import co.synext.module.system.service.IMenuService;
import co.synext.module.system.service.IRoleMenuService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 角色菜单表 服务实现类
 * </p>
 *
 * @author xu.ran
 * @date 2019-07-21
 */
@Service
@AllArgsConstructor
public class RoleMenuServiceImpl extends BaseService<TRoleMenuMapper, TRoleMenu> implements IRoleMenuService {
    private final IMenuService menuService;

    @Override
    public void updateRoleMenu(String roleId, List<String> permissionIds) {
        if (CollectionUtil.isNotEmpty(permissionIds)) {

            LambdaQueryWrapper lambdaQueryWrapper = getLambdaQueryWrapper().eq(TRoleMenu::getRoleId, roleId);
            Integer counter = baseMapper.selectCount(lambdaQueryWrapper);

            if (counter > 0) {
                baseMapper.delete(lambdaQueryWrapper);
            }

            permissionIds.forEach(id -> {
                TMenu sysMenu = menuService.getById(id);
                if (sysMenu == null) {
                    throw new BizException("资源不存在! id-" + id);
                }
                TRoleMenu sysRoleMenu = new TRoleMenu();
                sysRoleMenu.setMenuId(sysMenu.getId());
                sysRoleMenu.setRoleId(roleId);
                save(sysRoleMenu);
            });

        }
    }


}
