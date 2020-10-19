package co.synext.module.system.service.impl;

import co.synext.common.base.resp.ReturnDatas;
import co.synext.mybatis.entity.TSendmessageLog;
import co.synext.mybatis.mapper.TSendmessageLogMapper;
import co.synext.module.system.service.ISendmessageLogService;
import co.synext.module.system.dto.SendmessageLogInputDTO;
import co.synext.module.system.dto.SendmessageLogUpdateDTO;
import co.synext.module.system.dto.SendmessageLogQueryDTO;
import co.synext.common.base.service.BaseService;
import org.springframework.stereotype.Service;
import java.io.Serializable;
import java.util.Collection;

/**
 * <p>
 * 消息发送记录表 服务实现类
 * </p>
 *
 * @author xu.ran
 * @since 2020-09-10
 */
@Service("SendmessageLogServiceImpl")
public class SendmessageLogServiceImpl extends BaseService<TSendmessageLogMapper, TSendmessageLog> implements ISendmessageLogService {

    @Override
    public ReturnDatas page(SendmessageLogQueryDTO sendmessageLogQueryDTO) {
        TSendmessageLog sendmessageLog = sendmessageLogQueryDTO.convertToEntity();
        return ReturnDatas.ok(baseMapper.selectPage(sendmessageLogQueryDTO.page(), getLambdaQueryWrapper().setEntity(sendmessageLog)));
    }

    @Override
    public ReturnDatas save(SendmessageLogInputDTO sendmessageLogInputDTO) {
        TSendmessageLog sendmessageLog = sendmessageLogInputDTO.convertToEntity();
        if("".equals(sendmessageLog.getErrorTime())){
            sendmessageLog.setErrorTime(null);
        }
        if("".equals(sendmessageLog.getReadTime())){
            sendmessageLog.setReadTime(null);
        }
        if("".equals(sendmessageLog.getSendTime())){
            sendmessageLog.setSendTime(null);
        }
        if("".equals(sendmessageLog.getSuccessTime())){
            sendmessageLog.setSuccessTime(null);
        }
        baseMapper.insert(sendmessageLog);
        return ReturnDatas.ok(true);
    }

    @Override
    public ReturnDatas update(SendmessageLogUpdateDTO sendmessageLogUpdateDTO) {
        TSendmessageLog sendmessageLog = sendmessageLogUpdateDTO.convertToEntity();
        if("".equals(sendmessageLog.getErrorTime())){
            sendmessageLog.setErrorTime(null);
        }
        if("".equals(sendmessageLog.getReadTime())){
            sendmessageLog.setReadTime(null);
        }
        if("".equals(sendmessageLog.getSendTime())){
            sendmessageLog.setSendTime(null);
        }
        if("".equals(sendmessageLog.getSuccessTime())){
            sendmessageLog.setSuccessTime(null);
        }
        baseMapper.updateById(sendmessageLog);
        return ReturnDatas.ok(true);
    }

    @Override
    public ReturnDatas findById(Serializable id) {
        TSendmessageLog sendmessageLog = baseMapper.selectById(id);
        return ReturnDatas.ok(sendmessageLog);
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
