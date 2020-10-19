package co.synext.module.system.service.impl;

import co.synext.module.system.dto.RoleDTO;
import co.synext.mybatis.entity.TMenu;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import co.synext.common.base.service.BaseService;
import co.synext.common.base.resp.ReturnDatas;
import co.synext.mybatis.entity.TRole;
import co.synext.mybatis.mapper.TRoleMapper;
import co.synext.module.system.service.IMenuService;
import co.synext.module.system.service.IRoleMenuService;
import co.synext.module.system.service.IRoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 角色 服务实现类
 * </p>
 *
 * @author xu.ran
 * @date 2019-07-10
 */
@Service
@AllArgsConstructor
public class RoleServiceImpl extends BaseService<TRoleMapper, TRole> implements IRoleService {
    private final IRoleMenuService roleMenuService;
    private final IMenuService menuService;

    @Override
    public List<TRole> findByUserId(String userId) {
        return baseMapper.selectByUserId(userId);
    }

    @Override
    public ReturnDatas saveRole(RoleDTO roleDto) {
        TRole sysRole = copy2(roleDto,TRole.class);
        sysRole.setId(getWorkId());
        System.out.println(sysRole.toString());
        save(sysRole);
        roleMenuService.updateRoleMenu(sysRole.getId(),roleDto.getPermissionIds());
        return ReturnDatas.ok();
    }

    @Override
    public ReturnDatas updateRole(RoleDTO roleDto) {
        TRole sysRole = copy2(roleDto,TRole.class);
        updateById(sysRole);
        roleMenuService.updateRoleMenu(sysRole.getId(),roleDto.getPermissionIds());
        return ReturnDatas.ok();
    }

    @Override
    public ReturnDatas page(RoleDTO roleDTO) {
        TRole tRole = roleDTO.convertToEntity();
        return ReturnDatas.ok(baseMapper.selectPage(roleDTO.page(),getLambdaQueryWrapper().setEntity(tRole)));
    }

    @Override
    public ReturnDatas batchDelete(Collection<String> ids) {
        int i = baseMapper.deleteBatchIds(ids);
        if(i == 0){
            return ReturnDatas.error(false);
        }else {
            return ReturnDatas.ok(true);
        }
    }


}
