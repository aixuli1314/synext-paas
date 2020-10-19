package co.synext.module.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import co.synext.mybatis.entity.TRoleMenu;


import java.util.List;

/**
 * <p>
 * 角色菜单表 服务类
 * </p>
 *
 * @author xu.ran
 * @date 2019-07-21
 */
public interface IRoleMenuService extends IService<TRoleMenu> {

    void updateRoleMenu(String id, List<String> permissionIds);

}
