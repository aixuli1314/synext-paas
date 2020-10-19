package co.synext.module.system.service;

import co.synext.mybatis.entity.SysUserConnection;
import com.baomidou.mybatisplus.extension.service.IService;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * <p>
 * 用户社交账号绑定 服务类
 * </p>
 *
 * @author xu.ran
 * @since 2020-03-27
 */
public interface IUserConnectionService extends IService<SysUserConnection> {

    SysUserConnection findByOpenId(String openId, String providerId);

    SysUserConnection findByOpenIdAndUnionId(String openId, String unionId, String providerId);

    SysUserConnection findOrSaveByJsCode(String jsCode, boolean createUser) throws WxErrorException;

}
