/**
 *
 */
package co.synext.config.security.provider;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;

import java.util.Collection;

public class BaseAuthToken extends AbstractAuthenticationToken {

    private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;
    private final Object principal;
    private boolean loginFailCreate = false;

    public BaseAuthToken(String principal) {
        super(null);
        this.principal = principal;
        setAuthenticated(false);
    }

    public BaseAuthToken(String principal, boolean loginFailCreate) {
        super(null);
        this.principal = principal;
        this.loginFailCreate = loginFailCreate;
        setAuthenticated(false);
    }

    public BaseAuthToken(Collection<? extends GrantedAuthority> authorities, Object principal, boolean loginFailCreate) {
        super(authorities);
        this.principal = principal;
        this.loginFailCreate = loginFailCreate;
        super.setAuthenticated(true);
    }

    public BaseAuthToken(Object principal,
                         Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        super.setAuthenticated(true);
    }

    public Object getCredentials() {
        return null;
    }

    public Object getPrincipal() {
        return this.principal;
    }

    public boolean isLoginFailCreate() {
        return loginFailCreate;
    }

    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        if (isAuthenticated) {
            throw new IllegalArgumentException(
                    "Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        }
        super.setAuthenticated(false);
    }

    @Override
    public void eraseCredentials() {
        super.eraseCredentials();
    }
}
