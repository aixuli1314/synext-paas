package co.synext.module.system.service;

import co.synext.mybatis.entity.TUser;
import co.synext.mybatis.entity.TUserOrg;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface IUserOrgService extends IService<TUserOrg> {

    List<TUserOrg> findByOrgId(String orgId);

    void updateUserRole(String uid, List<String> roleIds, Integer managerType);
}
