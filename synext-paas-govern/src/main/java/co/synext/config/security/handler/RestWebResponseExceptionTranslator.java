package co.synext.config.security.handler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.oauth2.client.resource.OAuth2AccessDeniedException;
import org.springframework.security.oauth2.common.exceptions.*;
import org.springframework.security.oauth2.provider.error.DefaultWebResponseExceptionTranslator;


@Slf4j
public class RestWebResponseExceptionTranslator extends DefaultWebResponseExceptionTranslator {

	@Override
	public ResponseEntity<OAuth2Exception> translate(Exception e) throws Exception {
		e.printStackTrace();
		OAuth2Exception oAuth2Exception;
		if (e instanceof BadClientCredentialsException) {
			oAuth2Exception = new InvalidGrantException("用户名或密码错误", e);
		} else if (e instanceof InternalAuthenticationServiceException) {
			oAuth2Exception = new InvalidGrantException(e.getMessage(), e);
		} else if (e instanceof RedirectMismatchException) {
			oAuth2Exception = new InvalidGrantException(e.getMessage(), e);
		} else if (e instanceof InvalidScopeException) {
			oAuth2Exception = new InvalidGrantException(e.getMessage(), e);
		}else if (e instanceof InsufficientAuthenticationException){
			oAuth2Exception = new OAuth2AccessDeniedException("无效的令牌！");
		} else {
			oAuth2Exception = new UnsupportedResponseTypeException("服务内部错误", e);
		}
		ResponseEntity<OAuth2Exception> response = super.translate(oAuth2Exception);
		ResponseEntity.status(oAuth2Exception.getHttpErrorCode());
		return response;

	}

}
