package co.synext.module.system.service.impl;

import co.synext.common.base.resp.ReturnDatas;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import co.synext.mybatis.entity.TSendmessageTemplate;
import co.synext.mybatis.mapper.TSendmessageTemplateMapper;
import co.synext.module.system.service.ISendmessageTemplateService;
import co.synext.module.system.dto.SendmessageTemplateInputDTO;
import co.synext.module.system.dto.SendmessageTemplateUpdateDTO;
import co.synext.module.system.dto.SendmessageTemplateQueryDTO;
import co.synext.common.base.service.BaseService;
import org.springframework.stereotype.Service;
import java.io.Serializable;

/**
 * <p>
 * 消息模板表 服务实现类
 * </p>
 *
 * @author xu.ran
 * @since 2020-09-11
 */
@Service("SendmessageTemplateServiceImpl")
public class SendmessageTemplateServiceImpl extends BaseService<TSendmessageTemplateMapper, TSendmessageTemplate> implements ISendmessageTemplateService {

    @Override
    public ReturnDatas page(SendmessageTemplateQueryDTO sendmessageTemplateQueryDTO) {
        TSendmessageTemplate sendmessageTemplate = sendmessageTemplateQueryDTO.convertToEntity();
        return ReturnDatas.ok(baseMapper.selectPage(sendmessageTemplateQueryDTO.page(), getLambdaQueryWrapper().setEntity(sendmessageTemplate)));
    }

    @Override
    public ReturnDatas save(SendmessageTemplateInputDTO sendmessageTemplateInputDTO) {
        TSendmessageTemplate sendmessageTemplate = sendmessageTemplateInputDTO.convertToEntity();
        baseMapper.insert(sendmessageTemplate);
        return ReturnDatas.ok(true);
    }

    @Override
    public ReturnDatas update(SendmessageTemplateUpdateDTO sendmessageTemplateUpdateDTO) {
        TSendmessageTemplate sendmessageTemplate = sendmessageTemplateUpdateDTO.convertToEntity();
        baseMapper.updateById(sendmessageTemplate);
        return ReturnDatas.ok(true);
    }

    @Override
    public ReturnDatas findById(Serializable id) {
        TSendmessageTemplate sendmessageTemplate = baseMapper.selectById(id);
        return ReturnDatas.ok(sendmessageTemplate);
    }

    @Override
    public ReturnDatas deleteById(Serializable id) {
        baseMapper.deleteById(id);
        return ReturnDatas.ok(true);
    }

}
