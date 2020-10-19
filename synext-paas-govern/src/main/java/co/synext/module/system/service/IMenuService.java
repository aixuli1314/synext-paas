package co.synext.module.system.service;

import co.synext.module.system.dto.MenuDTO;
import com.baomidou.mybatisplus.extension.service.IService;
import co.synext.common.base.resp.ReturnDatas;
import co.synext.mybatis.entity.TMenu;
import co.synext.module.system.vo.MenuVo;
import co.synext.module.system.vo.RouteVo;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xu.ran
 * @date 2019-07-10
 */
public interface IMenuService extends IService<TMenu> {

    List<MenuVo> findMenuVoListByPid(String parentId);

    ReturnDatas saveMenu(MenuDTO menuDto);

    ReturnDatas updateMenu(MenuDTO menuDto);

    MenuVo findMenuVoById(String id);

    List<TMenu> findByPid(String id);

    List<RouteVo> findAllRoutes();

    List<TMenu> findByRoleIds(Set<String> RoleIds);

    List<MenuVo> findAllMenus();

    List<MenuVo> findAllPermission();

    ReturnDatas page(MenuDTO menuDTO);

    /**
     * 删除多个方法
     * @param ids
     * @return
     */
    ReturnDatas batchDelete(Collection<String> ids);

    boolean deleteMenu(String menuId);

}
