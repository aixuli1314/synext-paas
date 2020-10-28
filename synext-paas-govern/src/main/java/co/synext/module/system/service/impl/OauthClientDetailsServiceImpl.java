package co.synext.module.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import co.synext.common.exception.BizException;
import co.synext.common.utils.SpringContextHolder;
import co.synext.common.base.resp.ReturnDatas;
import co.synext.mybatis.entity.TOauthClientDetails;
import co.synext.mybatis.mapper.TOauthClientDetailsMapper;
import co.synext.module.system.service.IOauthClientDetailsService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xu.ran
 * @date 2019-06-19
 */
@Service
public class OauthClientDetailsServiceImpl extends ServiceImpl<TOauthClientDetailsMapper, TOauthClientDetails> implements IOauthClientDetailsService {


    @Override
    public ReturnDatas createClient(TOauthClientDetails sysOauthClientDetails) {
        if (!StringUtils.isNotEmpty(sysOauthClientDetails.getClientSecretStr())) {
            throw new BizException("ClientSecret不能为空!");
        }
        sysOauthClientDetails.setClientSecret(SpringContextHolder.getBean(PasswordEncoder.class).encode(sysOauthClientDetails.getClientSecret()));
        save(sysOauthClientDetails);
        return ReturnDatas.ok();
    }

    @Override
    public ReturnDatas updateClient(TOauthClientDetails sysOauthClientDetails) {
        if (sysOauthClientDetails.getId() == null) {
            throw new BizException("更新失败，没有主键参数！!");
        }
        if (StringUtils.isNotEmpty(sysOauthClientDetails.getClientSecretStr())) {
            sysOauthClientDetails.setClientSecret(SpringContextHolder.getBean(PasswordEncoder.class).encode(sysOauthClientDetails.getClientSecretStr()));
        }
        updateById(sysOauthClientDetails);
        return ReturnDatas.ok();
    }
}
