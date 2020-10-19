package co.synext.module.system.service;

import co.synext.common.base.resp.ReturnDatas;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import co.synext.mybatis.entity.TSendmessageLog;
import co.synext.module.system.dto.SendmessageLogInputDTO;
import co.synext.module.system.dto.SendmessageLogUpdateDTO;
import co.synext.module.system.dto.SendmessageLogQueryDTO;
import com.baomidou.mybatisplus.extension.service.IService;
import java.io.Serializable;
import java.util.Collection;

/**
 * <p>
 * 消息发送记录表 服务类
 * </p>
 *
 * @author xu.ran
 * @since 2020-09-10
 */
public interface ISendmessageLogService extends IService<TSendmessageLog> {

    /**
     * 分页方法
     * @param sendmessageLogQueryDTO
     * @return
     */
    ReturnDatas page(SendmessageLogQueryDTO sendmessageLogQueryDTO);

    /**
     * 保存方法
     * @param sendmessageLogInputDTO
     * @return
     */
    ReturnDatas save(SendmessageLogInputDTO sendmessageLogInputDTO);

    /**
     * 更新方法
     * @param sendmessageLogUpdateDTO
     * @return
     */
    ReturnDatas update(SendmessageLogUpdateDTO sendmessageLogUpdateDTO);

    /**
     * 查询方法
     * @param id
     * @return
     */
    ReturnDatas findById(Serializable id);

    /**
     * 删除多个方法
     * @param ids
     * @return
     */
    ReturnDatas batchDelete(Collection<String> ids);

/*    *//**
     * 删除方法
     * @param id
     * @return
     *//*
    ReturnDatas deleteById(Serializable id);*/
}
