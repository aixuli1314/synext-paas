package co.synext.module.Activiti.service;

import co.synext.common.base.resp.ReturnDatas;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import co.synext.mybatis.entity.TApplyFlow;
import co.synext.module.Activiti.dto.ApplyFlowInputDTO;
import co.synext.module.Activiti.dto.ApplyFlowUpdateDTO;
import co.synext.module.Activiti.dto.ApplyFlowQueryDTO;
import com.baomidou.mybatisplus.extension.service.IService;
import java.io.Serializable;
import java.util.Collection;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xu.ran
 * @since 2020-09-16
 */
public interface IApplyFlowService extends IService<TApplyFlow> {
	public <T> T copy2(Object object, Class<T> clazz) ;
    /**
     * 分页方法
     * @param applyFlowQueryDTO
     * @return
     */
    ReturnDatas page(ApplyFlowQueryDTO applyFlowQueryDTO);

    /**
     * 保存方法
     * @param applyFlowInputDTO
     * @return
     */
    ReturnDatas save(ApplyFlowInputDTO applyFlowInputDTO);

    /**
     * 更新方法
     * @param applyFlowUpdateDTO
     * @return
     */
    ReturnDatas update(ApplyFlowUpdateDTO applyFlowUpdateDTO);

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
    /**
     * 删除多个方法
     * @param ids
     * @return
     */
    ReturnDatas batchDelete(Collection<String> ids);
    /**
     * 获取流程列表数据(分页)
     * @param applyFlowQueryDTO
     * @return
     */
    public Page<TApplyFlow> listPage(ApplyFlowQueryDTO applyFlowQueryDTO);
}
