package co.synext.module.system.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import co.synext.module.system.dto.MenuDTO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.google.common.collect.Lists;
import co.synext.common.base.service.BaseService;
import co.synext.common.enums.Enums.ActiveStateEnum;
import co.synext.common.exception.BizException;
import co.synext.common.base.resp.ReturnDatas;
import co.synext.mybatis.entity.TMenu;
import co.synext.mybatis.mapper.TMenuMapper;
import co.synext.module.system.service.IMenuService;
import co.synext.module.system.vo.MenuVo;
import co.synext.module.system.vo.RouteVo;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xu.ran
 * @date 2019-07-10
 */
@Service
public class MenuServiceImpl extends BaseService<TMenuMapper, TMenu> implements IMenuService {



    @Override
    public List<MenuVo> findMenuVoListByPid(String parentId) {
        List<MenuVo> menuList = Lists.newArrayList();
        List<TMenu> childMenuList = findByPid(parentId);
        if (CollectionUtil.isNotEmpty(childMenuList)) {
            TMenu parentMenu = getById(parentId);
            childMenuList.forEach(menu -> {
                MenuVo menuVo = copy2(menu, MenuVo.class);
                if (parentMenu != null) {
                    menuVo.setParentName(parentMenu.getName());
                } else {
                    menuVo.setParentName("顶级菜单");
                }
                menuList.add(menuVo);
            });
        }
        return menuList;
    }


    @Transactional
    @Override
    public ReturnDatas saveMenu(MenuDTO menuDto) {
        TMenu menu = copy2(menuDto, TMenu.class);
        checkAndSave(menu);
        return ReturnDatas.ok("菜单保存成功！");
    }


    @Transactional
    @Override
    public ReturnDatas updateMenu(MenuDTO menuDto) {
        TMenu menu = copy2(menuDto, TMenu.class);
        checkAndUpdate(menu);
        return ReturnDatas.ok("菜单更新成功！");
    }

    private void checkAndUpdate(TMenu menu) {

        TMenu currentMenu = getById(menu.getId());

        if (currentMenu == null) {
            throw new BizException("参数错误！");
        }

        if (menu.getPid().equals(currentMenu.getId())) {
            throw new BizException("上级菜单不能为当前菜单！");
        }

        List<TMenu> currentMenuChildList = findByPid(currentMenu.getId());

        if (StringUtils.compare("0", menu.getPid()) != 0 && !(menu.getPid().equals(currentMenu.getPid()))) {

            TMenu currentParentMenu = getById(menu.getPid());

            if (currentParentMenu == null) {
                throw new BizException("上级菜单不存在！");
            }

            if (CollectionUtil.isNotEmpty(currentMenuChildList)) {
                AtomicReference<Boolean> error = new AtomicReference<>(false);
                currentMenuChildList.parallelStream().forEach(e -> error.set(e.getId().equals(currentParentMenu.getId())));
                if (error.get()) {
                    throw new BizException("上级菜单选择有误！");
                }
            }

            currentParentMenu.setHasChildren(ActiveStateEnum.启用.getCode());
            updateById(currentParentMenu);

        }

        //xu.ran 判断当前菜单是否拥有下级菜单
        if (CollectionUtil.isNotEmpty(currentMenuChildList)) {
            menu.setHasChildren(ActiveStateEnum.启用.getCode());
        } else {
            menu.setHasChildren(ActiveStateEnum.禁用.getCode());
        }

        updateById(menu);

        TMenu quondamParentMenu = getById(currentMenu.getPid());
        if (quondamParentMenu != null) {
            List<TMenu> parentMenuChildList = findByPid(currentMenu.getPid());
            if (CollectionUtil.isEmpty(parentMenuChildList)) {
                quondamParentMenu.setHasChildren(ActiveStateEnum.禁用.getCode());
                updateById(quondamParentMenu);
            }
        }

    }

    @Override
    public MenuVo findMenuVoById(String id) {
        TMenu menu = getById(id);
        if (menu == null) {
            throw new BizException("菜单不存在！");
        }
        MenuVo menuVo = copy2(menu, MenuVo.class);
        if (StringUtils.compare("0", menu.getPid()) != 0) {
            TMenu parentMenu = getById(menu.getPid());
            menuVo.setParentName(parentMenu.getName());
        } else {
            menuVo.setParentName("顶级菜单");
        }
        return menuVo;
    }

    private void checkAndSave(TMenu sysMenu) {
        TMenu parentMenu = null;

        if (StringUtils.compare("0", sysMenu.getPid()) != 0) {
            parentMenu = getById(sysMenu.getPid());
            if (parentMenu == null) {
                throw new BizException("上级菜单选择有误！");
            }

        }

        //sysMenu.setId(getWorkId());
        save(sysMenu);

        if (parentMenu != null) {
            parentMenu.setHasChildren(ActiveStateEnum.启用.getCode());
            updateById(parentMenu);
        }

    }

    @Override
    public List<TMenu> findByPid(String parentId) {
        QueryWrapper queryWrapper = Wrappers.query().eq("pid", parentId);
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public ReturnDatas page(MenuDTO menuDTO) {
        TMenu tMenu = menuDTO.convertToEntity();
        return ReturnDatas.ok(baseMapper.selectPage(menuDTO.page(),getLambdaQueryWrapper().setEntity(tMenu)));
    }

    @Override
    public List<RouteVo> findAllRoutes() {
        return recursion("0");
    }

    @Override
    public List<TMenu> findByRoleIds(Set<String> roleIds) {
        return baseMapper.selectByRoleIds(roleIds);
    }

    @Override
    public List<MenuVo> findAllMenus() {
        // 原始的数据
        List<MenuVo> pMenus = getAllMenuVos();

        ArrayList<MenuVo> menuVoList = new ArrayList<>();
        pMenus.forEach(pmenu -> {
            menuVoList.add(pmenu);
        });
        // 最后的结果
        ArrayList<MenuVo> menuVos = new ArrayList<>();
        //先找到所有的一级菜单
        for(MenuVo menuVo : menuVoList) {
            if(menuVo.getPid().equals("0")){
                menuVos.add(menuVo);
            }
        }
        // 为一级菜单设置子菜单，getChild是递归调用的
        for(MenuVo menuVo : menuVos) {
            List<MenuVo> children = getChild(menuVo.getId(), menuVoList);
            menuVo.setChildren(children);
        }
        return menuVos;
    }

    @Override
    public List<MenuVo> findAllPermission() {
        // 原始的数据
        List<MenuVo> pMenus = getAllPermissions();

        ArrayList<MenuVo> menuVoList = new ArrayList<>();
        pMenus.forEach(pmenu -> {
            menuVoList.add(pmenu);
        });
        // 最后的结果
        ArrayList<MenuVo> menuVos = new ArrayList<>();
        //先找到所有的一级权限
        for(MenuVo menuVo : menuVoList) {
            if(menuVo.getPid().equals("0")){
                menuVos.add(menuVo);
            }
        }
        // 为一级菜单设置子菜单，getChild是递归调用的
        for(MenuVo menuVo : menuVos) {
            List<MenuVo> children = getChild(menuVo.getId(), menuVoList);
            menuVo.setChildren(children);
        }
        return menuVos;
    }

    //递归获取子菜单
    public List<MenuVo> getChild(String id,List<MenuVo> allMenus) {
        //子菜单
        List<MenuVo> childList = new ArrayList<>();
        for (MenuVo menuVo : allMenus) {
            if(!menuVo.getPid().equals("0")){
                if(menuVo.getPid().equals(id)) {
                    childList.add(menuVo);
                }
            }
        }
        //递归终止的条件,没有子菜单时
        if(childList.size() == 0){
            return null;
        }
        //如果子菜单还有子菜单,把子菜单的子菜单再循环一遍
        for(MenuVo menuVo:childList) {
            if(menuVo.getHasChildren() == 1){
                menuVo.setChildren(getChild(menuVo.getId(),allMenus));
            }
        }
        return childList;
    }

    //获取所有菜单
    public List<MenuVo> getAllMenuVos() {
        QueryWrapper queryWrapper = Wrappers.query().eq("menu_type",1).or().eq("menu_type",2);
        List<TMenu> menulist = baseMapper.selectList(queryWrapper);
        ArrayList<MenuVo> menuVoArrayList = new ArrayList<>();
        for (TMenu menu : menulist) {
            MenuVo menuVo = copy2(menu, MenuVo.class);
            menuVoArrayList.add(menuVo);
        }
        return menuVoArrayList;
    }

    //获取所有权限
    public List<MenuVo> getAllPermissions() {
        QueryWrapper queryWrapper = Wrappers.query();
        List<TMenu> menulist = baseMapper.selectList(queryWrapper);
        ArrayList<MenuVo> menuVoArrayList = new ArrayList<>();
        for (TMenu menu : menulist) {
            MenuVo menuVo = copy2(menu, MenuVo.class);
            menuVoArrayList.add(menuVo);
        }
        return menuVoArrayList;
    }

    @Transactional
    @Override
    public boolean deleteMenu(String menuId) {
        LambdaQueryWrapper<TMenu> queryWrapper = getLambdaQueryWrapper().eq(TMenu::getPid, menuId);
        Integer count = baseMapper.selectCount(queryWrapper);
        if (count > 0) {
            throw new BizException("请先删除子菜单后再操作！");
        }

        TMenu menu = getById(menuId);

        if (menu == null) {
            throw new BizException("菜单不存在！");
        }

        removeById(menuId);

        TMenu parentMenu = getById(menu.getPid());
        Integer childCount = baseMapper.selectCount(queryWrapper.eq(TMenu::getPid, parentMenu.getPid()));

        //更新上级菜单状态
        if (childCount <=0) {
            parentMenu.setHasChildren(ActiveStateEnum.禁用.getCode());
            updateById(parentMenu);
        }

        return true;
    }

    private List<RouteVo> recursion(String parentId) {
        List<RouteVo> routeVoList = new ArrayList<>();
        List<TMenu> currentMenuList = findByPid(parentId);
        if (CollectionUtil.isNotEmpty(currentMenuList)) {
            currentMenuList.forEach(menu -> {
                RouteVo routeVo = new RouteVo();
                routeVo.setName(menu.getName());
                routeVo.setHidden(false);
                routeVo.setAlwaysShow(false);
                routeVo.setPath(menu.getComcode());
                routeVo.setRedirect(menu.getHrefurl());
                RouteVo.Meta meta = new RouteVo.Meta();
                meta.setIcon(menu.getIcon());
                meta.setTitle(menu.getName());
                meta.setNoCache(true);
                routeVo.setMeta(meta);
                if (Integer.compare(1, menu.getHasChildren())==0) {
                    routeVo.setChildren(recursion(menu.getId()));
                }
                routeVoList.add(routeVo);
            });
        }
        return routeVoList;
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

}
