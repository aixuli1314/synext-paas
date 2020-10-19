package co.synext.module.system.service;

import co.synext.module.system.dto.RoleDTO;
import com.baomidou.mybatisplus.extension.service.IService;
import co.synext.common.base.resp.ReturnDatas;
import co.synext.mybatis.entity.TRole;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 角色 服务类
 * </p>
 *
 * @author xu.ran
 * @date 2019-07-10
 */
public interface IRoleService extends IService<TRole> {

    List<TRole> findByUserId(String userId);

    ReturnDatas saveRole(RoleDTO roleDto);

    ReturnDatas updateRole(RoleDTO roleDto);

    ReturnDatas page(RoleDTO roleDTO);

    ReturnDatas batchDelete(Collection<String> ids);

}
