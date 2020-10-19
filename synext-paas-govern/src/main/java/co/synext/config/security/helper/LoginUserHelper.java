package co.synext.config.security.helper;

import java.util.Map;
import java.util.Optional;

import org.apache.commons.collections4.MapUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

import com.google.common.collect.Maps;

import co.synext.common.constant.Constant;
import co.synext.config.security.details.LoginUser;
import co.synext.config.security.provider.BaseAuthToken;

public class LoginUserHelper {

    public static LoginUser getCurrentLoginUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = null;
        if (authentication instanceof OAuth2Authentication) {
            OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) authentication;
            if (!"client_credentials".equals(oAuth2Authentication.getOAuth2Request().getGrantType())) {
                loginUser = (LoginUser) oAuth2Authentication.getUserAuthentication().getPrincipal();
            }
        } else if (authentication instanceof BaseAuthToken || authentication instanceof UsernamePasswordAuthenticationToken) {
            loginUser = (LoginUser) authentication.getPrincipal();
        }
        return loginUser;
    }

    public static Optional<LoginUser> getOptionalCurrentLoginUser() {
        LoginUser loginUser = getCurrentLoginUser();
        if (loginUser != null) {
            return Optional.of(loginUser);
        }
        return Optional.empty();
    }

    public static void additionalInformation(OAuth2AccessToken oAuth2AccessToken) {

        Map<String, Object> information = Maps.newHashMap();
        information.put("user_id", LoginUserHelper.getOptionalCurrentLoginUser()
                .map(loginUser -> loginUser.getId().toString()).orElse("no user_id"));
        information.put("user_name", LoginUserHelper.getOptionalCurrentLoginUser()
                .map(loginUser -> loginUser.getAccount()).orElse("no username"));
        information.put("license", Constant.JCLOUDS_LICENSE);

        if (oAuth2AccessToken instanceof DefaultOAuth2AccessToken) {
            DefaultOAuth2AccessToken defaultOAuth2AccessToken = (DefaultOAuth2AccessToken) oAuth2AccessToken;
            if (MapUtils.isNotEmpty(defaultOAuth2AccessToken.getAdditionalInformation())) {
                defaultOAuth2AccessToken.getAdditionalInformation().putAll(information);
            } else {
                defaultOAuth2AccessToken.setAdditionalInformation(information);
            }
        }
    }

}
