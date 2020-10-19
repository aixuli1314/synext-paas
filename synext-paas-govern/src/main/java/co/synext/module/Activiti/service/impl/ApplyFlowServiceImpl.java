package co.synext.module.Activiti.service.impl;

import co.synext.common.base.resp.ReturnDatas;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import co.synext.mybatis.entity.TApplyFlow;
import co.synext.mybatis.entity.TUser;
import co.synext.mybatis.mapper.TApplyFlowMapper;
import co.synext.module.Activiti.service.IApplyFlowService;
import co.synext.module.Activiti.dto.ApplyFlowInputDTO;
import co.synext.module.Activiti.dto.ApplyFlowUpdateDTO;
import co.synext.module.Activiti.dto.ApplyFlowQueryDTO;
import co.synext.common.base.service.BaseService;
import co.synext.common.utils.WarpsUtils;

import org.springframework.stereotype.Service;
import java.io.Serializable;
import java.util.Collection;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xu.ran
 * @since 2020-09-16
 */
@Service("ApplyFlowServiceImpl")
public class ApplyFlowServiceImpl extends BaseService<TApplyFlowMapper, TApplyFlow> implements IApplyFlowService {
	@Override
	public <T> T copy2(Object object, Class<T> clazz) {
        return WarpsUtils.copyTo(object, clazz);
    }
    @Override
    public ReturnDatas page(ApplyFlowQueryDTO applyFlowQueryDTO) {
        TApplyFlow applyFlow = applyFlowQueryDTO.convertToEntity();
        return ReturnDatas.ok(baseMapper.selectPage(applyFlowQueryDTO.page(), getLambdaQueryWrapper().setEntity(applyFlow)));
    }
    @Override
    public Page<TApplyFlow> listPage(ApplyFlowQueryDTO applyFlowQueryDTO) {
    	TApplyFlow applyFlow = applyFlowQueryDTO.convertToEntity();
    	LambdaQueryWrapper<TApplyFlow> setEntity = getLambdaQueryWrapper().setEntity(applyFlow);
    	Page<TApplyFlow> selectPage = baseMapper.selectPage(applyFlowQueryDTO.page(), setEntity);
    	return selectPage;
    }

    @Override
    public ReturnDatas save(ApplyFlowInputDTO applyFlowInputDTO) {
        TApplyFlow applyFlow = applyFlowInputDTO.convertToEntity();
        baseMapper.insert(applyFlow);
        return ReturnDatas.ok(true);
    }

    @Override
    public ReturnDatas update(ApplyFlowUpdateDTO applyFlowUpdateDTO) {
        TApplyFlow applyFlow = applyFlowUpdateDTO.convertToEntity();
        baseMapper.updateById(applyFlow);
        return ReturnDatas.ok(true);
    }

    @Override
    public ReturnDatas findById(Serializable id) {
        TApplyFlow applyFlow = baseMapper.selectById(id);
        return ReturnDatas.ok(applyFlow);
    }

    @Override
    public ReturnDatas deleteById(Serializable id) {
        baseMapper.deleteById(id);
        return ReturnDatas.ok(true);
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
