package co.synext.module.system.service.impl;

import co.synext.common.base.resp.ReturnDatas;
import co.synext.mybatis.entity.TAttachment;
import co.synext.mybatis.mapper.TAttachmentMapper;
import co.synext.module.system.service.IAttachmentService;
import co.synext.module.system.dto.AttachmentInputDTO;
import co.synext.module.system.dto.AttachmentUpdateDTO;
import co.synext.module.system.dto.AttachmentQueryDTO;
import co.synext.common.base.service.BaseService;
import org.springframework.stereotype.Service;
import java.io.Serializable;

/**
 * <p>
 * 专家专业 信息表 服务实现类
 * </p>
 *
 * @author xu.ran
 * @since 2020-09-11
 */
@Service("AttachmentServiceImpl")
public class AttachmentServiceImpl extends BaseService<TAttachmentMapper, TAttachment> implements IAttachmentService {

    @Override
    public ReturnDatas page(AttachmentQueryDTO attachmentQueryDTO) {
        TAttachment attachment = attachmentQueryDTO.convertToEntity();
        return ReturnDatas.ok(baseMapper.selectPage(attachmentQueryDTO.page(), getLambdaQueryWrapper().setEntity(attachment)));
    }

    @Override
    public ReturnDatas save(AttachmentInputDTO attachmentInputDTO) {
        TAttachment attachment = attachmentInputDTO.convertToEntity();
        baseMapper.insert(attachment);
        return ReturnDatas.ok(true);
    }

    @Override
    public ReturnDatas update(AttachmentUpdateDTO attachmentUpdateDTO) {
        TAttachment attachment = attachmentUpdateDTO.convertToEntity();
        baseMapper.updateById(attachment);
        return ReturnDatas.ok(true);
    }

    @Override
    public ReturnDatas findById(Serializable id) {
        TAttachment attachment = baseMapper.selectById(id);
        return ReturnDatas.ok(attachment);
    }

    @Override
    public ReturnDatas deleteById(Serializable id) {
        baseMapper.deleteById(id);
        return ReturnDatas.ok(true);
    }

}
