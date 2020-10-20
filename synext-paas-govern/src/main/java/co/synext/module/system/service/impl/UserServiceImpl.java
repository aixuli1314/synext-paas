package co.synext.module.system.service.impl;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


import co.synext.module.system.dto.RegisteredFlowInputDTO;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.google.common.collect.Sets;

import cn.hutool.core.collection.CollectionUtil;
import co.synext.common.base.resp.ReturnDatas;
import co.synext.common.base.service.BaseService;
import co.synext.common.base.vo.UserVo;
import co.synext.common.constant.Constant;
import co.synext.common.exception.BizException;
import co.synext.common.utils.HttpClientUtils;
import co.synext.common.utils.SpringContextHolder;
import co.synext.config.security.details.LoginUser;
import co.synext.config.security.helper.LoginUserHelper;
import co.synext.module.system.dto.UserDTO;
import co.synext.module.system.service.IMenuService;
import co.synext.module.system.service.IRegisteredFlowService;
import co.synext.module.system.service.IRoleService;
import co.synext.module.system.service.IUserOrgService;
import co.synext.module.system.service.IUserRoleService;
import co.synext.module.system.service.IUserService;
import co.synext.mybatis.entity.TMenu;
import co.synext.mybatis.entity.TRole;
import co.synext.mybatis.entity.TUser;
import co.synext.mybatis.mapper.TUserMapper;
import jodd.util.StringUtil;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl extends BaseService<TUserMapper, TUser> implements IUserService {
    //private final PasswordEncoder passwordEncoder;
    private final IRoleService roleService;
    private final IMenuService menuService;
    private final IUserRoleService userRoleService;
    private final IUserOrgService userOrgService;
    private final IRegisteredFlowService registeredFlowService;

    public LoginUser login(String username) {

        TUser user = getOne(getLambdaQueryWrapper().eq(TUser::getAccount, username)
                .or().eq(TUser::getMobile, username)
        );
        if (user == null) {
            throw new BizException("用户不存在！");
        }

        LoginUser loginUser = copy2(user, LoginUser.class);

        //设置所属角色信息
        List<TRole> roleList = roleService.findByUserId(user.getId());
        if (CollectionUtil.isNotEmpty(roleList)) {
            Set<String> roleIds = Sets.newHashSet();
            Set<String> roleKeys = Sets.newHashSet();
            roleList.stream().parallel().forEach(role -> {
                roleIds.add(role.getId());
                roleKeys.add(role.getId().toString());
            });
            loginUser.setRoles(roleKeys);
            List<TMenu> menuList = menuService.findByRoleIds(roleIds);
            if (CollectionUtil.isNotEmpty(menuList)) {
                Set<String> permissions = Sets.newHashSet();
                
                menuList.stream().parallel().forEach(menu -> {
	                permissions.add(menu.getPermissionKey());
                }
                );
                loginUser.setPermissions(permissions);
            }
        }
        return loginUser;
    }

    @Override
    public UserVo findByUid(String uid) {
        TUser sysUser = getById(uid);

        if(sysUser != null){
            HashMap<String, String> map = new HashMap<>();
            map.put("userId",uid);
            String s = HttpClientUtils.doGet(Constant.FISCO_WEBSDK_URL+"/user/getBlock", map);
            Map mapTypes = JSON.parseObject(s);
            JSONObject object = (JSONObject) mapTypes.get("data");
            JSONObject userInfo = object.getJSONObject("userInfo");
            if(userInfo != null) {
                TUser tUser = userInfo.toJavaObject(TUser.class);
                System.out.println(tUser.toString());
            }
        }
        if (sysUser == null) {
            throw new BizException("用户不存在！");
        }
        UserVo userVo = copy2(sysUser, UserVo.class);
       // settingUserRoleAndUserDept(userVo);
        return userVo;
    }

    @Override
    public UserVo findByNameAndMobile(String name,String mobile) {
        LambdaQueryWrapper<TUser> lambdaQueryWrapper = getLambdaQueryWrapper();
        lambdaQueryWrapper.eq(TUser::getUserName, name);
        lambdaQueryWrapper.eq(TUser::getMobile,mobile);
        TUser user = getOne(lambdaQueryWrapper);
        if (user == null) {
            //throw new BizException("用户不存在！");
            return null;
        }
        UserVo userVo = copy2(user, UserVo.class);
        return userVo;
    }

    @Transactional
    @Override
    public ReturnDatas saveUser(UserDTO userDto) {
        TUser user = copy2(userDto, TUser.class);
        String id = getWorkId();
        user.setId(id);
        PasswordEncoder passwordEncoder = SpringContextHolder.getBean(PasswordEncoder.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String sdate = dateFormat.format(date);
        user.setCreateTime(sdate);
        user.setCreateUserId(LoginUserHelper.getCurrentLoginUser().getId());
        //提交新用户（游客类型）注册流
        RegisteredFlowInputDTO registeredFlowInputDTO = new RegisteredFlowInputDTO();
        registeredFlowInputDTO.setOrgTypePathKey(user.getOrgTypePathKey());
        registeredFlowInputDTO.setUserId(id);
        registeredFlowInputDTO.setUserType("0");
        registeredFlowInputDTO.setBusinessModule("新用户注册");
        registeredFlowInputDTO.setCreateTime(sdate);
        registeredFlowInputDTO.setSubmitTime(sdate);
        registeredFlowInputDTO.setStatus(1);
        registeredFlowService.save(registeredFlowInputDTO);
        
        save(user);
        //保存到链上
        String s = JSON.toJSONString(user);
        HashMap<String, String> map = new HashMap<>();
        map.put("userInfo",s);
        map.put("userType",user.getUserType().toString());
        map.put("id",user.getId());
        HttpClientUtils.doPost(Constant.FISCO_WEBSDK_URL+"/user/submitBlock",map);
        //设置用户角色
        userRoleService.updateUserRole(user.getId(), userDto.getRoleIds());
        //设置用户所属组织
        userOrgService.updateUserRole(user.getId(),userDto.getDeptIds(),userDto.getUserOrgManagerType());
        return ReturnDatas.ok();
    }

    @Transactional
    @Override
    public ReturnDatas updateUser(UserDTO userDto) {
        TUser user = copy2(userDto, TUser.class);
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        user.setUpdateTime(dateFormat.format(date));
        if (StringUtil.isNotEmpty(user.getPassword())) {
        	PasswordEncoder passwordEncoder = SpringContextHolder.getBean(PasswordEncoder.class);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        updateById(user);
        //更新到链上
        String s = JSON.toJSONString(user);
        HashMap<String, String> map = new HashMap<>();
        map.put("userInfo",s);
        map.put("userType",user.getUserType().toString());
        map.put("id",user.getId());
        HttpClientUtils.doPost(Constant.FISCO_WEBSDK_URL+"/user/updateBlock",map);
        userRoleService.updateUserRole(user.getId(), userDto.getRoleIds());
        userOrgService.updateUserRole(user.getId(),userDto.getDeptIds(),userDto.getUserOrgManagerType());
        return ReturnDatas.ok();
    }


    @Override
    public ReturnDatas page(UserDTO userDTO) {
/*        LinkedHashMap<String,Integer> pageDate = (LinkedHashMap<String, Integer>) map.get("page");
        Page page = new Page();
        page.setCurrent(pageDate.get("current"));
        page.setSize(pageDate.get("size"));
        LinkedHashMap<String,String> userDate = (LinkedHashMap<String, String>) map.get("userDto");
        UserDTO userDto = new UserDTO();
        userDto.setId(userDate.get("id"));
        LambdaQueryWrapper<TUser> queryWrapper = getLambdaQueryWrapper();
        if (StringUtil.isNotEmpty(userDto.getId())) {
            queryWrapper.eq(TUser::getId,userDto.getId());
        }
        if (StringUtil.isNotEmpty(userDto.getAccount())) {
            queryWrapper.like(TUser::getAccount, userDto.getAccount());
        }
        if (StringUtil.isNotEmpty(userDto.getMobile())) {
            queryWrapper.like(TUser::getMobile, userDto.getMobile());
        }
        if (userDto.getActive() != null) {
            queryWrapper.like(TUser::getActive, userDto.getActive());
        }
        IPage userPage = baseMapper.selectPage(page, queryWrapper);
        if (CollectionUtil.isNotEmpty(userPage.getRecords())) {
            List<UserVo> userVoList = Lists.newArrayList();
            userPage.getRecords().forEach(user -> {
                UserVo userVo = copy2(user, UserVo.class);
                settingUserRoleAndUserDept(userVo);
                userVoList.add(userVo);
            });
            userPage.setRecords(userVoList);
        }*/
        TUser tUser = userDTO.convertToEntity();
        return ReturnDatas.ok(baseMapper.selectPage(userDTO.page(),getLambdaQueryWrapper().setEntity(tUser)));
    }

    @Override
    public ReturnDatas batchDelete(Collection<String> ids) {
        int i = baseMapper.deleteBatchIds(ids);
        for (String id : ids) {
            HashMap<String, String> map = new HashMap<>();
            map.put("userId",id);
            HttpClientUtils.doGet(Constant.FISCO_WEBSDK_URL+"/user/removeBlock", map);
        }
        if(i == 0){
            return ReturnDatas.error(false);
        }else {
            return ReturnDatas.ok(true);
        }
    }

    private void settingUserRoleAndUserDept(UserVo userVo) {
        //用户所属角色
        List<TRole> roleList = roleService.findByUserId(userVo.getId());
        if (CollectionUtil.isNotEmpty(roleList)) {
            Set<TRole> roleSet = Sets.newHashSet();
            roleList.parallelStream().forEach(e -> roleSet.add(e));
            userVo.setRoleNames(roleSet);
        }
    }
    /**
     * 根据企业信息ID获取企业管理员
     * */
	@Override
	public List<TUser> findEnterpriseAdmin(String enterpriseId) {
		List<TUser> list = list(getLambdaQueryWrapper().eq(TUser::getEnterpriseId, enterpriseId)
                .eq(TUser::getUserType, 1).eq(TUser::getActive,1));
		
		return list;
	}
}
