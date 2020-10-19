package co.synext.module.system.service.impl;

import co.synext.common.base.resp.ReturnDatas;
import co.synext.mybatis.entity.TPlanType;
import co.synext.mybatis.mapper.TPlanTypeMapper;
import co.synext.module.system.service.IPlanTypeService;
import co.synext.module.system.dto.PlanTypeInputDTO;
import co.synext.module.system.dto.PlanTypeUpdateDTO;
import co.synext.module.system.dto.PlanTypeQueryDTO;
import co.synext.common.base.service.BaseService;
import org.springframework.stereotype.Service;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

/**
 * <p>
 * 计划类别,课题申报的是planType=0 服务实现类
 * </p>
 *
 * @author xu.ran
 * @since 2020-09-10
 */
@Service("PlanTypeServiceImpl")
public class PlanTypeServiceImpl extends BaseService<TPlanTypeMapper, TPlanType> implements IPlanTypeService {

    @Override
    public ReturnDatas page(PlanTypeQueryDTO planTypeQueryDTO) {
        TPlanType planType = planTypeQueryDTO.convertToEntity();
        return ReturnDatas.ok(baseMapper.selectPage(planTypeQueryDTO.page(), getLambdaQueryWrapper().setEntity(planType)));
    }

    @Override
    public ReturnDatas save(PlanTypeInputDTO planTypeInputDTO) {
        TPlanType planType = planTypeInputDTO.convertToEntity();
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        planType.setCreateTime(simpleDateFormat.format(date));
        baseMapper.insert(planType);
        return ReturnDatas.ok(true);
    }

    @Override
    public ReturnDatas update(PlanTypeUpdateDTO planTypeUpdateDTO) {
        TPlanType planType = planTypeUpdateDTO.convertToEntity();
        baseMapper.updateById(planType);
        return ReturnDatas.ok(true);
    }

    @Override
    public ReturnDatas findById(Serializable id) {
        TPlanType planType = baseMapper.selectById(id);
        return ReturnDatas.ok(planType);
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
