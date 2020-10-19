package co.synext.module.system.service;

import co.synext.mybatis.entity.TOrgRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 部门角色管理表 服务类
 * </p>
 *
 * @author xu.ran
 * @date 2019-09-17
 */
public interface IOrgRoleService extends IService<TOrgRole> {

    void updateOrgRole(String oid, List<Long> roleIds);
}
