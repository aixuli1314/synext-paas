package co.synext.module.system.service;

import co.synext.common.base.resp.ReturnDatas;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import co.synext.mybatis.entity.TSendmessageTemplate;
import co.synext.module.system.dto.SendmessageTemplateInputDTO;
import co.synext.module.system.dto.SendmessageTemplateUpdateDTO;
import co.synext.module.system.dto.SendmessageTemplateQueryDTO;
import com.baomidou.mybatisplus.extension.service.IService;
import java.io.Serializable;

/**
 * <p>
 * 消息模板表 服务类
 * </p>
 *
 * @author xu.ran
 * @since 2020-09-11
 */
public interface ISendmessageTemplateService extends IService<TSendmessageTemplate> {

    /**
     * 分页方法
     * @param sendmessageTemplateQueryDTO
     * @return
     */
    ReturnDatas page(SendmessageTemplateQueryDTO sendmessageTemplateQueryDTO);

    /**
     * 保存方法
     * @param sendmessageTemplateInputDTO
     * @return
     */
    ReturnDatas save(SendmessageTemplateInputDTO sendmessageTemplateInputDTO);

    /**
     * 更新方法
     * @param sendmessageTemplateUpdateDTO
     * @return
     */
    ReturnDatas update(SendmessageTemplateUpdateDTO sendmessageTemplateUpdateDTO);

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
