package co.synext.module.system.service;

import co.synext.common.base.resp.ReturnDatas;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import co.synext.mybatis.entity.TRegisteredFlow;
import co.synext.module.system.dto.RegisteredFlowInputDTO;
import co.synext.module.system.dto.RegisteredFlowUpdateDTO;
import co.synext.module.system.dto.RegisteredFlowQueryDTO;
import com.baomidou.mybatisplus.extension.service.IService;
import java.io.Serializable;
import java.util.Collection;

/**
 * <p>
 * 注册流程,关联t_user和t_org,有专家注册和企业注册,企业注册.注册成功之后,短信通知 服务类
 * </p>
 *
 * @author xu.ran
 * @since 2020-09-10
 */
public interface IRegisteredFlowService extends IService<TRegisteredFlow> {

    /**
     * 分页方法
     * @param registeredFlowQueryDTO
     * @return
     */
    ReturnDatas page(RegisteredFlowQueryDTO registeredFlowQueryDTO);

    /**
     * 保存方法
     * @param registeredFlowInputDTO
     * @return
     */
    ReturnDatas save(RegisteredFlowInputDTO registeredFlowInputDTO);

    /**
     * 更新方法
     * @param registeredFlowUpdateDTO
     * @return
     */
    ReturnDatas update(RegisteredFlowUpdateDTO registeredFlowUpdateDTO);

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
