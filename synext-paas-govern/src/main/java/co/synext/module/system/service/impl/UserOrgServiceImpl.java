package co.synext.module.system.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import co.synext.common.base.service.BaseService;
import co.synext.module.system.service.IUserOrgService;
import co.synext.mybatis.entity.TUserOrg;
import co.synext.mybatis.mapper.TUserOrgMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserOrgServiceImpl extends BaseService<TUserOrgMapper, TUserOrg> implements IUserOrgService {

    @Override
    public List<TUserOrg> findByOrgId(String orgId) {
        LambdaQueryWrapper<TUserOrg> wrapper = getLambdaQueryWrapper().eq(TUserOrg::getOrgId, orgId);
        return baseMapper.selectList(wrapper);
    }

    @Override
    public void updateUserRole(String uid, List<String> orgIds,Integer managerType) {
        if(CollectionUtil.isNotEmpty(orgIds)){

            LambdaQueryWrapper lambdaQueryWrapper = getLambdaQueryWrapper().eq(TUserOrg::getUserId, uid);
            Integer count = baseMapper.selectCount(lambdaQueryWrapper);

            if(count>0){
                baseMapper.delete(lambdaQueryWrapper);
            }

            orgIds.forEach(orgId->{
                TUserOrg tUserOrg = new TUserOrg();
                tUserOrg.setOrgId(orgId);
                tUserOrg.setUserId(uid);
                tUserOrg.setManagerType(managerType);
                baseMapper.insert(tUserOrg);
            });
        }
    }
}
