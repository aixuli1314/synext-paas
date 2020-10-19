package co.synext.module.Activiti.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;

import co.synext.common.base.resp.ReturnDatas;
import co.synext.common.base.service.BaseService;
import co.synext.module.Activiti.dto.UserTaskInputDTO;
import co.synext.module.Activiti.dto.UserTaskQueryDTO;
import co.synext.module.Activiti.dto.UserTaskUpdateDTO;
import co.synext.module.Activiti.service.IUserTaskService;
import co.synext.mybatis.entity.TUserTask;
import co.synext.mybatis.mapper.TUserTaskMapper;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xu.ran
 * @since 2020-09-16
 */
@Service("UserTaskServiceImpl")
public class UserTaskServiceImpl extends BaseService<TUserTaskMapper, TUserTask> implements IUserTaskService {

    @Override
    public ReturnDatas page(UserTaskQueryDTO userTaskQueryDTO) {
        TUserTask userTask = userTaskQueryDTO.convertToEntity();
        return ReturnDatas.ok(baseMapper.selectPage(userTaskQueryDTO.page(), getLambdaQueryWrapper().setEntity(userTask)));
    }

    @Override
    public ReturnDatas save(UserTaskInputDTO userTaskInputDTO) {
        TUserTask userTask = userTaskInputDTO.convertToEntity();
        baseMapper.insert(userTask);
        return ReturnDatas.ok(true);
    }

    @Override
    public ReturnDatas update(List<UserTaskUpdateDTO> updateDTOS) {
        for (UserTaskUpdateDTO updateDTO : updateDTOS) {
            TUserTask userTask = updateDTO.convertToEntity();
            baseMapper.updateById(userTask);
        }
        return ReturnDatas.ok(true);
    }

    @Override
    public ReturnDatas findById(Serializable id) {
        TUserTask userTask = baseMapper.selectById(id);
        return ReturnDatas.ok(userTask);
    }

    @Override
    public ReturnDatas batchDelete(Collection<String> ids) {
        int i = baseMapper.deleteBatchIds(ids);
        if(i == 0){
            return ReturnDatas.error(false);
        }else {
            return ReturnDatas.ok(true);
        }
    }

    @Override
    public ReturnDatas deleteById(Serializable id) {
        baseMapper.deleteById(id);
        return ReturnDatas.ok(true);
    }

}
