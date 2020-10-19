package co.synext.module.Activiti.service;

import co.synext.common.base.resp.ReturnDatas;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import co.synext.mybatis.entity.TUserTask;
import co.synext.module.Activiti.dto.UserTaskInputDTO;
import co.synext.module.Activiti.dto.UserTaskUpdateDTO;
import co.synext.module.Activiti.dto.UserTaskQueryDTO;
import com.baomidou.mybatisplus.extension.service.IService;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xu.ran
 * @since 2020-09-16
 */
public interface IUserTaskService extends IService<TUserTask> {

    /**
     * 分页方法
     * @param userTaskQueryDTO
     * @return
     */
    ReturnDatas page(UserTaskQueryDTO userTaskQueryDTO);

    /**
     * 保存方法
     * @param userTaskInputDTO
     * @return
     */
    ReturnDatas save(UserTaskInputDTO userTaskInputDTO);

    /**
     * 更新方法
     * @param updateDTOS
     * @return
     */
    ReturnDatas update(List<UserTaskUpdateDTO> updateDTOS);

    /**
     * 查询方法
     * @param id
     * @return
     */
    ReturnDatas findById(Serializable id);

    /**
     * 删除方法
     * @param ids
     * @return
     */
    ReturnDatas batchDelete(Collection<String> ids);
    /**
     * 删除方法
     * @param id
     * @return
     */
    ReturnDatas deleteById(Serializable id);
}
