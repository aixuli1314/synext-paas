package co.synext.config.security.provider;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public abstract class BaseAuthProvider<T extends AbstractAuthenticationToken> implements AuthenticationProvider {

    protected abstract T process(Authentication authentication) throws Exception;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        try {
            return process(authentication);
        } catch (Exception e) {
            throw new InternalAuthenticationServiceException(e.fillInStackTrace().toString());
        }
    }
}
