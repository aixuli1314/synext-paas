package co.synext.module.system.service;

import co.synext.common.base.resp.ReturnDatas;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import co.synext.mybatis.entity.TAttachment;
import co.synext.module.system.dto.AttachmentInputDTO;
import co.synext.module.system.dto.AttachmentUpdateDTO;
import co.synext.module.system.dto.AttachmentQueryDTO;
import com.baomidou.mybatisplus.extension.service.IService;
import java.io.Serializable;

/**
 * <p>
 * 专家专业 信息表 服务类
 * </p>
 *
 * @author xu.ran
 * @since 2020-09-11
 */
public interface IAttachmentService extends IService<TAttachment> {

    /**
     * 分页方法
     * @param attachmentQueryDTO
     * @return
     */
    ReturnDatas page(AttachmentQueryDTO attachmentQueryDTO);

    /**
     * 保存方法
     * @param attachmentInputDTO
     * @return
     */
    ReturnDatas save(AttachmentInputDTO attachmentInputDTO);

    /**
     * 更新方法
     * @param attachmentUpdateDTO
     * @return
     */
    ReturnDatas update(AttachmentUpdateDTO attachmentUpdateDTO);

    /**
     * 查询方法
     * @param id
     * @return
     */
    ReturnDatas findById(Serializable id);

    /**
     * 删除方法
     * @param id
     * @return
     */
    ReturnDatas deleteById(Serializable id);
}
