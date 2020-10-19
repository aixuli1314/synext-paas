package co.synext.module.system.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import co.synext.common.base.service.BaseService;
import co.synext.module.system.service.IOrgRoleService;
import co.synext.module.system.service.IRoleService;
import co.synext.mybatis.entity.TOrgRole;
import co.synext.mybatis.entity.TRole;
import co.synext.mybatis.mapper.TOrgRoleMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrgRoleServiceImpl extends BaseService<TOrgRoleMapper, TOrgRole> implements IOrgRoleService {
    private final IRoleService roleService;
    @Override
    public void updateOrgRole(String oid, List<Long> roleIds) {
        if(CollectionUtil.isNotEmpty(roleIds)){
            LambdaQueryWrapper lambdaQueryWrapper = getLambdaQueryWrapper().eq(TOrgRole::getOrgId, oid);
            Integer count = baseMapper.selectCount(lambdaQueryWrapper);

            if(count>0){
                baseMapper.delete(lambdaQueryWrapper);
            }
            roleIds.forEach(roleId->{
                TRole role =  roleService.getById(roleId);
                System.out.println(role.toString());
                if(role!=null){
                    TOrgRole tOrgRole = new TOrgRole();
                    tOrgRole.setRoleId(role.getId());
                    tOrgRole.setOrgId(oid);
                    baseMapper.insert(tOrgRole);
                }
            });
        }
    }
}
