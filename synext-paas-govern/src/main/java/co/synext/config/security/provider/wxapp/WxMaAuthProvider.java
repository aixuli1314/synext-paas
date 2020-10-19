
package co.synext.config.security.provider.wxapp;

import co.synext.config.security.provider.BaseAuthProvider;
import co.synext.mybatis.entity.SysUserConnection;
import co.synext.module.system.service.IUserConnectionService;
import lombok.AllArgsConstructor;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

@AllArgsConstructor
public class WxMaAuthProvider extends BaseAuthProvider<WxMaAuthToken> {
    private final UserDetailsService userDetailsService;
    private final IUserConnectionService userConnectionService;

    @Override
    protected WxMaAuthToken process(Authentication authentication) throws WxErrorException {
        WxMaAuthToken authenticationToken = (WxMaAuthToken) authentication;
        SysUserConnection userConnection = userConnectionService.findOrSaveByJsCode(authenticationToken.getPrincipal().toString(), authenticationToken.isLoginFailCreate());
        UserDetails user = userDetailsService.loadUserByUsername(userConnection.getUserId());
        if (user == null) {
            throw new InternalAuthenticationServiceException("无法获取用户信息");
        }
        WxMaAuthToken wxMaAuthToken = new WxMaAuthToken(user, user.getAuthorities());
        wxMaAuthToken.setDetails(authenticationToken.getDetails());
        return wxMaAuthToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return WxMaAuthToken.class.isAssignableFrom(authentication);
    }


}
