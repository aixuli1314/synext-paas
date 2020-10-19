package co.synext.module.system.service;


import java.util.Collection;
import java.util.List;

import co.synext.module.system.dto.UserDTO;
import com.baomidou.mybatisplus.extension.service.IService;

import co.synext.common.base.resp.ReturnDatas;
import co.synext.common.base.vo.UserVo;
import co.synext.config.security.details.LoginUser;
import co.synext.mybatis.entity.TUser;
import java.util.Map;

/**
 * <p>
 * 基础账户表 服务类
 * </p>
 *
 * @author xu.ran
 * @date 2019-05-23
 */
public interface IUserService extends IService<TUser> {

	LoginUser login(String username);

    UserVo findByUid(String uid);

    UserVo findByNameAndMobile(String name,String mobile);

    ReturnDatas saveUser(UserDTO userDto);

    ReturnDatas updateUser(UserDTO userDto);

    ReturnDatas page(UserDTO userDTO);

    ReturnDatas batchDelete(Collection<String> ids);

    List<TUser> findEnterpriseAdmin(String enterpriseId);
}
