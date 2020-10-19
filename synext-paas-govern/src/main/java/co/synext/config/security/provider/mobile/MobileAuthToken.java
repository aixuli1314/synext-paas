/**
 *
 */
package co.synext.config.security.provider.mobile;

import co.synext.config.security.provider.BaseAuthToken;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class MobileAuthToken extends BaseAuthToken {

    @Getter
    private String code;

    @Getter
    private String deviceId;

    public MobileAuthToken(String principal, String deviceId, String code) {
        super(principal);
        this.code = code;
        this.deviceId = deviceId;
    }

    public MobileAuthToken(String principal, boolean loginFailCreate) {
        super(principal, loginFailCreate);
    }

    public MobileAuthToken(Collection<? extends GrantedAuthority> authorities, Object principal, boolean loginFailCreate) {
        super(authorities, principal, loginFailCreate);
    }

    public MobileAuthToken(Object principal, Collection<? extends GrantedAuthority> authorities) {
        super(principal, authorities);
    }
}
