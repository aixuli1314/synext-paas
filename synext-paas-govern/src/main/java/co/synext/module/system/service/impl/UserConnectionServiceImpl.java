package co.synext.module.system.service.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import co.synext.common.base.service.BaseService;
import co.synext.common.enums.Enums;
import co.synext.common.exception.BizException;
import co.synext.config.nim.entity.NimBaseRespEntity;
import co.synext.config.nim.entity.req.UserCreateRequestEntity;
import co.synext.config.nim.service.INimUserService;
import co.synext.mybatis.entity.SysUserConnection;
import co.synext.mybatis.entity.TUser;
import co.synext.mybatis.mapper.SysUserConnectionMapper;
import co.synext.module.system.service.IUserConnectionService;
import co.synext.module.system.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * <p>
 * 用户社交账号绑定 服务实现类
 * </p>
 *
 * @author xu.ran
 * @since 2020-03-27
 */
@Slf4j
@Service
public class UserConnectionServiceImpl extends BaseService<SysUserConnectionMapper, SysUserConnection> implements IUserConnectionService {

    @Resource
    private WxMaService wxMaService;

    @Resource
    private IUserService userService;

    @Resource
    private INimUserService nimUserService;

    @Override
    public SysUserConnection findByOpenId(String openId, String providerId) {
        return baseMapper.selectOne(getLambdaQueryWrapper()
                .eq(SysUserConnection::getProviderId, providerId)
                .eq(SysUserConnection::getProviderUserId, openId)

        );
    }

    @Override
    public SysUserConnection findByOpenIdAndUnionId(String openId, String unionId, String providerId) {
        return baseMapper.selectOne(getLambdaQueryWrapper()
                .eq(SysUserConnection::getProviderUserId, openId)
                .eq(SysUserConnection::getProviderUnionId, unionId)
                .eq(SysUserConnection::getProviderId, providerId)
        );
    }


    @Transactional
    @Override
    public SysUserConnection findOrSaveByJsCode(String jsCode, boolean createUser) throws UsernameNotFoundException, WxErrorException {

        SysUserConnection userConnection;
        WxMaJscode2SessionResult wxMaJscode2SessionResult = wxMaService.getUserService().getSessionInfo(jsCode);

        //xu.ran 根据openid 或者 unionId 查询用户小程序绑定信息
        if (StringUtils.isNotEmpty(wxMaJscode2SessionResult.getUnionid())) {
            userConnection = findByOpenIdAndUnionId(wxMaJscode2SessionResult.getOpenid(), wxMaJscode2SessionResult.getUnionid(), "wxma");
        } else {
            userConnection = findByOpenId(wxMaJscode2SessionResult.getOpenid(), "wxma");
        }

        //判断是否需要绑定用户信息
        if (userConnection == null) {
            if (createUser) {

                log.info("开始创建用户！");

                TUser user = new TUser();
                user.setId(getWorkId());
                user.setAccount("wxma_".concat(user.getId().toString()));
                user.setActive(Enums.ActiveStateEnum.启用.getCode());
                user.setUserType(Enums.UserTypeEnum.企业个人.getCode());
                userService.save(user);

                log.info("新用户创建完成！{}", user);

                userConnection = new SysUserConnection();
                userConnection.setProviderId("wxma");
                userConnection.setRank(1);
                userConnection.setUserId(user.getAccount());
                userConnection.setProviderUserId(wxMaJscode2SessionResult.getOpenid());
                userConnection.setProviderUnionId(wxMaJscode2SessionResult.getUnionid());
                userConnection.setProviderSessionKey(wxMaJscode2SessionResult.getSessionKey());
                userConnection.setAccessToken("");
                save(userConnection);

                log.info("用户绑定完成！{}", userConnection);

                NimBaseRespEntity nimBaseRespEntity = nimUserService.createNimUser(UserCreateRequestEntity.builder()
                        .accid(user.getId().toString())
                        .name(user.getAccount())
                        .build());

                log.info("创建网易云信用户完成！{}", nimBaseRespEntity);

            } else {
                throw new BizException("用户未绑定小程序信息！");
            }
        }
        return userConnection;
    }
}
