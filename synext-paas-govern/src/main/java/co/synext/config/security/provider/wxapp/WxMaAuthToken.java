/**
 *
 */
package co.synext.config.security.provider.wxapp;


import co.synext.config.security.provider.BaseAuthToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class WxMaAuthToken extends BaseAuthToken {

    public WxMaAuthToken(String principal) {
        super(principal);
    }

    public WxMaAuthToken(String principal, boolean loginFailCreate) {
        super(principal, loginFailCreate);
    }

    public WxMaAuthToken(Collection<? extends GrantedAuthority> authorities, Object principal, boolean loginFailCreate) {
        super(authorities, principal, loginFailCreate);
    }

    public WxMaAuthToken(Object principal, Collection<? extends GrantedAuthority> authorities) {
        super(principal, authorities);
    }
}
