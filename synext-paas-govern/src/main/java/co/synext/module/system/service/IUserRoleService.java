package co.synext.module.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import co.synext.mybatis.entity.TUserRole;

import java.util.List;

/**
 * <p>
 * 用户角色管理表 服务类
 * </p>
 *
 * @author xu.ran
 * @date 2019-07-21
 */
public interface IUserRoleService extends IService<TUserRole> {

    List<TUserRole> findByUserId(Long uid);

    void updateUserRole(String uid, List<Long> roleIds);

}
