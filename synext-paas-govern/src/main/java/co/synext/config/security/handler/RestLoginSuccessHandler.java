package co.synext.config.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import co.synext.config.security.helper.ClientDetailsHelper;
import co.synext.config.security.service.RedisClientDetailsService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.UnapprovedClientAuthenticationException;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestValidator;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;


/**
 * @author xu.ran
 * @date 2020/4/27 22:54
 * @description: TODO
 */
@Slf4j
@Builder
@AllArgsConstructor
public class RestLoginSuccessHandler implements AuthenticationSuccessHandler {
	private ObjectMapper objectMapper;
	private PasswordEncoder passwordEncoder;
	private RedisClientDetailsService redisClientDetailsService;
	private AuthorizationServerTokenServices defaultAuthorizationServerTokenServices;
	private ClientDetailsHelper clientDetailsHelper;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		try {
			ClientDetailsHelper.ClientInfo client = clientDetailsHelper.buildClientInfo(request.getHeader(HttpHeaders.AUTHORIZATION));

			ClientDetails clientDetails = redisClientDetailsService.loadClientByClientId(client.getClientId());

			if (clientDetails == null) {
				throw new UnapprovedClientAuthenticationException("client不存在！");
	        } else if (!passwordEncoder.matches(client.getClientSecret(), clientDetails.getClientSecret())) {
				throw new UnapprovedClientAuthenticationException("clientSecret不匹配！");
			}

			TokenRequest tokenRequest = new TokenRequest(MapUtils.EMPTY_SORTED_MAP, client.getClientId(), clientDetails.getScope(), "mobile");
			OAuth2RequestValidator oAuth2RequestValidator =new DefaultOAuth2RequestValidator();
			oAuth2RequestValidator.validateScope(tokenRequest, clientDetails);
			OAuth2Request oAuth2Request = tokenRequest.createOAuth2Request(clientDetails);
			OAuth2Authentication oAuth2Authentication = new OAuth2Authentication(oAuth2Request, authentication);
			OAuth2AccessToken oAuth2AccessToken = defaultAuthorizationServerTokenServices.createAccessToken(oAuth2Authentication);
			oAuth2Authentication.setAuthenticated(true);
			response.setCharacterEncoding("utf-8");
			response.setContentType("application/json;charset=UTF-8");
			PrintWriter printWriter = response.getWriter();
			printWriter.append(objectMapper.writeValueAsString(oAuth2AccessToken));

		} catch (Exception e) {
			throw new UnapprovedClientAuthenticationException(e.getMessage());
		}
	}
}
