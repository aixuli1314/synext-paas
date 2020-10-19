package co.synext.module.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import co.synext.common.base.resp.ReturnDatas;
import co.synext.mybatis.entity.TOauthClientDetails;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xu.ran
 * @date 2019-06-19
 */
public interface IOauthClientDetailsService extends IService<TOauthClientDetails> {

    ReturnDatas createClient(TOauthClientDetails sysOauthClientDetails);

    ReturnDatas updateClient(TOauthClientDetails sysOauthClientDetails);

}
