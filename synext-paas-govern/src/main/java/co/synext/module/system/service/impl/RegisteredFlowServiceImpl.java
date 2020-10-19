package co.synext.module.system.service.impl;

import co.synext.common.base.resp.ReturnDatas;
import co.synext.mybatis.entity.TRegisteredFlow;
import co.synext.mybatis.mapper.TRegisteredFlowMapper;
import co.synext.module.system.service.IRegisteredFlowService;
import co.synext.module.system.dto.RegisteredFlowInputDTO;
import co.synext.module.system.dto.RegisteredFlowUpdateDTO;
import co.synext.module.system.dto.RegisteredFlowQueryDTO;
import co.synext.common.base.service.BaseService;
import org.springframework.stereotype.Service;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

/**
 * <p>
 * 注册流程,关联t_user和t_org,有专家注册和企业注册,企业注册.注册成功之后,短信通知 服务实现类
 * </p>
 *
 * @author xu.ran
 * @since 2020-09-10
 */
@Service("RegisteredFlowServiceImpl")
public class RegisteredFlowServiceImpl extends BaseService<TRegisteredFlowMapper, TRegisteredFlow> implements IRegisteredFlowService {

    @Override
    public ReturnDatas page(RegisteredFlowQueryDTO registeredFlowQueryDTO) {
        TRegisteredFlow registeredFlow = registeredFlowQueryDTO.convertToEntity();
        return ReturnDatas.ok(baseMapper.selectPage(registeredFlowQueryDTO.page(), getLambdaQueryWrapper().setEntity(registeredFlow)));
    }

    @Override
    public ReturnDatas save(RegisteredFlowInputDTO registeredFlowInputDTO) {
        TRegisteredFlow registeredFlow = registeredFlowInputDTO.convertToEntity();
/*        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        registeredFlow.setCreateTime(dateFormat.format(date));*/
        baseMapper.insert(registeredFlow);
        return ReturnDatas.ok(true);
    }

    @Override
    public ReturnDatas update(RegisteredFlowUpdateDTO registeredFlowUpdateDTO) {
        System.out.println(registeredFlowUpdateDTO.toString());
        TRegisteredFlow registeredFlow = registeredFlowUpdateDTO.convertToEntity();
        baseMapper.updateById(registeredFlow);
        return ReturnDatas.ok(true);
    }

    @Override
    public ReturnDatas findById(Serializable id) {
        TRegisteredFlow registeredFlow = baseMapper.selectById(id);
        return ReturnDatas.ok(registeredFlow);
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
