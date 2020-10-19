
package co.synext.config.security.provider.mobile;

import co.synext.config.security.provider.BaseAuthProvider;
import co.synext.config.captch.ValidateCodeService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

@AllArgsConstructor
public class MobileAuthProvider extends BaseAuthProvider<MobileAuthToken> {
    private final UserDetailsService userDetailsService;
    private final ValidateCodeService smsValidateCodeService;

    @Override
    protected MobileAuthToken process(Authentication authentication) {

        MobileAuthToken authenticationToken = (MobileAuthToken) authentication;

        //验证短信验证码
        smsValidateCodeService.verifySmsCode(authenticationToken.getDeviceId(), (String) authenticationToken.getPrincipal(), authenticationToken.getCode());

        UserDetails user = userDetailsService.loadUserByUsername((String) authenticationToken.getPrincipal());

        if (user == null) {
            throw new InternalAuthenticationServiceException("无法获取用户信息");
        }

        MobileAuthToken mobileAuthToken = new MobileAuthToken(user, user.getAuthorities());
        mobileAuthToken.setDetails(authenticationToken.getDetails());
        return mobileAuthToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return MobileAuthToken.class.isAssignableFrom(authentication);
    }

}
