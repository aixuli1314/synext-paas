package co.synext.module.system.service;

import co.synext.common.base.resp.ReturnDatas;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import co.synext.mybatis.entity.TPlanType;
import co.synext.module.system.dto.PlanTypeInputDTO;
import co.synext.module.system.dto.PlanTypeUpdateDTO;
import co.synext.module.system.dto.PlanTypeQueryDTO;
import com.baomidou.mybatisplus.extension.service.IService;
import java.io.Serializable;
import java.util.Collection;

/**
 * <p>
 * 计划类别,课题申报的是planType=0 服务类
 * </p>
 *
 * @author xu.ran
 * @since 2020-09-10
 */
public interface IPlanTypeService extends IService<TPlanType> {

    /**
     * 分页方法
     * @param planTypeQueryDTO
     * @return
     */
    ReturnDatas page(PlanTypeQueryDTO planTypeQueryDTO);

    /**
     * 保存方法
     * @param planTypeInputDTO
     * @return
     */
    ReturnDatas save(PlanTypeInputDTO planTypeInputDTO);

    /**
     * 更新方法
     * @param planTypeUpdateDTO
     * @return
     */
    ReturnDatas update(PlanTypeUpdateDTO planTypeUpdateDTO);

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
}
