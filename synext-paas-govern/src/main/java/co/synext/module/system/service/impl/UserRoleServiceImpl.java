package co.synext.module.system.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import co.synext.common.base.service.BaseService;
import co.synext.mybatis.entity.TRole;
import co.synext.mybatis.entity.TUserRole;
import co.synext.mybatis.mapper.TUserRoleMapper;
import co.synext.module.system.service.IRoleService;
import co.synext.module.system.service.IUserRoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户角色管理表 服务实现类
 * </p>
 *
 * @author xu.ran
 * @date 2019-07-21
 */
@Service
@AllArgsConstructor
public class UserRoleServiceImpl extends BaseService<TUserRoleMapper, TUserRole> implements IUserRoleService {
    private final IRoleService roleService;

    @Override
    public List<TUserRole> findByUserId(Long uid) {
        return baseMapper.selectList(getLambdaQueryWrapper().eq(TUserRole::getUserId,uid));
    }

    @Override
    public void updateUserRole(String uid, List<Long> roleIds) {
        if(CollectionUtil.isNotEmpty(roleIds)){

            LambdaQueryWrapper lambdaQueryWrapper = getLambdaQueryWrapper().eq(TUserRole::getUserId, uid);
            Integer count = baseMapper.selectCount(lambdaQueryWrapper);

            if(count>0){
                baseMapper.delete(lambdaQueryWrapper);
            }

            roleIds.forEach(roleId->{
                TRole role =  roleService.getById(roleId);
                if(role!=null){
                    TUserRole sysUserRole = new TUserRole();
                    sysUserRole.setRoleId(role.getId());
                    sysUserRole.setUserId(uid);
                    baseMapper.insert(sysUserRole);
                }
            });
        }
    }
}