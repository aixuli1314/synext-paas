package co.synext.module.system.service.impl;

import co.synext.common.base.resp.ReturnDatas;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import co.synext.mybatis.entity.TOrgEnterprise;
import co.synext.mybatis.mapper.TOrgEnterpriseMapper;
import co.synext.module.system.service.IOrgEnterpriseService;
import co.synext.module.system.dto.OrgEnterpriseInputDTO;
import co.synext.module.system.dto.OrgEnterpriseUpdateDTO;
import co.synext.module.system.dto.OrgEnterpriseQueryDTO;
import co.synext.common.base.service.BaseService;
import org.springframework.stereotype.Service;
import java.io.Serializable;
import java.util.Collection;

/**
 * <p>
 *  企业信息表,企业实际是t_org部门,orgType=1.给部门创建一个账号,作为主管,用于登录.附件关联附件表t_attachment 服务实现类
 * </p>
 *
 * @author xu.ran
 * @since 2020-09-10
 */
@Service("OrgEnterpriseServiceImpl")
public class OrgEnterpriseServiceImpl extends BaseService<TOrgEnterpriseMapper, TOrgEnterprise> implements IOrgEnterpriseService {

    @Override
    public ReturnDatas page(OrgEnterpriseQueryDTO orgEnterpriseQueryDTO) {
        System.out.println(orgEnterpriseQueryDTO.toString());
        TOrgEnterprise orgEnterprise = orgEnterpriseQueryDTO.convertToEntity();
        return ReturnDatas.ok(baseMapper.selectPage(orgEnterpriseQueryDTO.page(), getLambdaQueryWrapper().setEntity(orgEnterprise)));
    }

    @Override
    public ReturnDatas save(OrgEnterpriseInputDTO orgEnterpriseInputDTO) {
        TOrgEnterprise orgEnterprise = orgEnterpriseInputDTO.convertToEntity();
        System.out.println(orgEnterpriseInputDTO.toString());
        baseMapper.insert(orgEnterprise);
        return ReturnDatas.ok(true);
    }

    @Override
    public ReturnDatas update(OrgEnterpriseUpdateDTO orgEnterpriseUpdateDTO) {
        TOrgEnterprise orgEnterprise = orgEnterpriseUpdateDTO.convertToEntity();
        baseMapper.updateById(orgEnterprise);
        return ReturnDatas.ok(true);
    }

    @Override
    public ReturnDatas findById(Serializable id) {
        TOrgEnterprise orgEnterprise = baseMapper.selectById(id);
        return ReturnDatas.ok(orgEnterprise);
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

/*    @Override
    public ReturnDatas deleteById(Serializable id) {
        baseMapper.deleteById(id);
        return ReturnDatas.ok(true);
    }*/

}
