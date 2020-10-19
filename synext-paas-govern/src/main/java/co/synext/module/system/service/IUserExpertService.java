package co.synext.module.system.service;

import co.synext.common.base.resp.ReturnDatas;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import co.synext.mybatis.entity.TUserExpert;
import co.synext.module.system.dto.UserExpertInputDTO;
import co.synext.module.system.dto.UserExpertUpdateDTO;
import co.synext.module.system.dto.UserExpertQueryDTO;
import com.baomidou.mybatisplus.extension.service.IService;
import java.io.Serializable;
import java.util.Collection;

/**
 * <p>
 * 专家信息表,是t_user表的扩展表,userType=3 服务类
 * </p>
 *
 * @author xu.ran
 * @since 2020-09-08
 */
public interface IUserExpertService extends IService<TUserExpert> {

    /**
     * 分页方法
     * @param userExpertQueryDTO
     * @return
     */
    ReturnDatas page(UserExpertQueryDTO userExpertQueryDTO);

    /**
     * 保存方法
     * @param userExpertInputDTO
     * @return
     */
    ReturnDatas save(UserExpertInputDTO userExpertInputDTO);

    /**
     * 更新方法
     * @param userExpertUpdateDTO
     * @return
     */
    ReturnDatas update(UserExpertUpdateDTO userExpertUpdateDTO);

    /**
     * 查询方法
     * @param id
     * @return
     */
    ReturnDatas findById(Serializable id);

    TUserExpert getById(String id);

    /**
     * 批次删除
     * @param ids
     * @return
     */
    ReturnDatas batchDelete(Collection<String> ids);;
}
