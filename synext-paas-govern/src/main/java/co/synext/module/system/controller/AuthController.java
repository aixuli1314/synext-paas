package co.synext.module.system.controller;

import cn.hutool.core.util.StrUtil;
import co.synext.common.base.resp.ReturnDatas;
import co.synext.config.security.details.LoginUser;
import co.synext.config.security.helper.ClientDetailsHelper;
import co.synext.config.security.helper.LoginUserHelper;
import co.synext.config.security.provider.mobile.MobileAuthToken;
import co.synext.config.security.provider.wxapp.WxMaAuthToken;
import co.synext.config.security.service.RedisAuthorizationCodeService;
import co.synext.config.security.service.RedisClientDetailsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.UnapprovedClientAuthenticationException;
import org.springframework.security.oauth2.common.exceptions.UnsupportedGrantTypeException;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.client.ClientCredentialsTokenGranter;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeTokenGranter;
import org.springframework.security.oauth2.provider.refresh.RefreshTokenGranter;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Api(tags = "3-OAuth2认证接口")
@Slf4j
@RestController
public class AuthController {

    @Autowired
    private RedisClientDetailsService redisClientDetailsService;

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuthorizationServerTokenServices authorizationServerTokenServices;

    @Autowired
    private RedisAuthorizationCodeService authorizationCodeServices;

    @Autowired
    private ClientDetailsHelper clientDetailsHelper;

    @ApiOperation(value = "密码模式获取令牌")
    @PostMapping(value = "/auth/user/token")
    public ReturnDatas<LoginUser> passwordToken(
            @ApiParam(name = "grant_type", value = "授权类型:password")
            @RequestParam(value = "grant_type", defaultValue = "password") String grantType,
            @ApiParam(name = "username", value = "帐号", required = true)
            @RequestParam(value = "username") @Valid @NotNull String username,
            @ApiParam(name = "password", value = "密码", required = true)
            @RequestParam(value = "password") @Valid @NotNull String password,
            @ApiParam(name = "clientId", value = "clientId", required = true)
            @RequestParam(value = "clientId") @Valid @NotNull String clientId,
            @ApiParam(name = "clientSecret", value = "clientSecret", required = true)
            @RequestParam(value = "clientSecret") @Valid @NotNull String clientSecret
    ) {
        if (!StrUtil.equals(grantType, "password")) throw new UnsupportedGrantTypeException("grant_type 必须为password");
        ClientDetails clientDetails = clientDetailsHelper.getClientDetails(clientId, clientSecret);
        TokenRequest tokenRequest = new TokenRequest(MapUtils.EMPTY_SORTED_MAP, clientId, clientDetails.getScope(),
                grantType);
        OAuth2Request oAuth2Request = tokenRequest.createOAuth2Request(clientDetails);
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        OAuth2Authentication oAuth2Authentication = new OAuth2Authentication(oAuth2Request, authentication);
        OAuth2AccessToken oAuth2AccessToken = authorizationServerTokenServices.createAccessToken(oAuth2Authentication);
        oAuth2Authentication.setAuthenticated(true);
        LoginUserHelper.additionalInformation(oAuth2AccessToken);
        LoginUser loginUser = LoginUserHelper.getCurrentLoginUser();
        String value = oAuth2AccessToken.getValue();
        loginUser.setToken(value);
        return ReturnDatas.ok(loginUser);
    }

    @ApiOperation(value = "手机号获取令牌")
    @PostMapping(value = "/auth/mobile/token")
    public OAuth2AccessToken mobileToken(
            @ApiParam(name = HttpHeaders.AUTHORIZATION, value = "请求头", required = true)
            @RequestHeader(HttpHeaders.AUTHORIZATION) String header,
            @ApiParam(name = "grant_type", value = "授权类型:mobile")
            @RequestParam(value = "grant_type", defaultValue = "mobile") String grantType,
            @ApiParam(name = "deviceId", value = "设备Id", required = true)
            @RequestParam(value = "deviceId") String deviceId,
            @ApiParam(name = "mobile", value = "手机号", required = true)
            @RequestParam(value = "mobile") String mobile,
            @ApiParam(name = "code", value = "短信验证码", required = true)
            @RequestParam(value = "code") String code
    ) {
        ClientDetailsHelper.ClientInfo client = clientDetailsHelper.buildClientInfo(header);
        if (!StrUtil.equals(grantType, "mobile")) throw new UnsupportedGrantTypeException("grant_type 必须为mobile");
        ClientDetails clientDetails = clientDetailsHelper.getClientDetails(client.getClientId(), client.getClientSecret());
        TokenRequest tokenRequest = new TokenRequest(MapUtils.EMPTY_SORTED_MAP, client.getClientId(), clientDetails.getScope(), grantType);
        OAuth2Request oAuth2Request = tokenRequest.createOAuth2Request(clientDetails);
        MobileAuthToken token = new MobileAuthToken(mobile, deviceId, code);
        Authentication authentication = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        OAuth2Authentication oAuth2Authentication = new OAuth2Authentication(oAuth2Request, authentication);
        OAuth2AccessToken oAuth2AccessToken = authorizationServerTokenServices.createAccessToken(oAuth2Authentication);
        oAuth2Authentication.setAuthenticated(true);
        LoginUserHelper.additionalInformation(oAuth2AccessToken);
        return oAuth2AccessToken;
    }

    @ApiOperation(value = "微信小程序Code获取令牌")
    @PostMapping(value = "/auth/wxma/token")
    public OAuth2AccessToken wxMaToken(
            @ApiParam(name = HttpHeaders.AUTHORIZATION, value = "请求头", required = true)
            @RequestHeader(HttpHeaders.AUTHORIZATION) String header,
            @ApiParam(name = "grant_type", value = "授权类型:wxma")
            @RequestParam(value = "grant_type", defaultValue = "wxma") String grantType,
            @ApiParam(name = "js_code", value = "微信jsCode", required = true)
            @RequestParam(value = "js_code") String code
    ) {
        ClientDetailsHelper.ClientInfo client = clientDetailsHelper.buildClientInfo(header);
        if (!StrUtil.equals(grantType, "wxma")) throw new UnsupportedGrantTypeException("grant_type 必须为wxma");
        ClientDetails clientDetails = clientDetailsHelper.getClientDetails(client.getClientId(), client.getClientSecret());
        TokenRequest tokenRequest = new TokenRequest(MapUtils.EMPTY_SORTED_MAP, client.getClientId(), clientDetails.getScope(),grantType);
        OAuth2Request oAuth2Request = tokenRequest.createOAuth2Request(clientDetails);
        WxMaAuthToken token = new WxMaAuthToken(code);
        Authentication authentication = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        OAuth2Authentication oAuth2Authentication = new OAuth2Authentication(oAuth2Request, authentication);
        OAuth2AccessToken oAuth2AccessToken = authorizationServerTokenServices.createAccessToken(oAuth2Authentication);
        oAuth2Authentication.setAuthenticated(true);
        LoginUserHelper.additionalInformation(oAuth2AccessToken);
        return oAuth2AccessToken;
    }

    @ApiOperation(value = "授权码模式获取令牌")
    @PostMapping(value = "/auth/code/token")
    public OAuth2AccessToken authorizationCodeToken(
            @ApiParam(name = "client_id", value = "应用id", required = true)
            @RequestParam(value = "client_id") String clientId,
            @ApiParam(name = "client_secret", value = "应用密钥", required = true)
            @RequestParam(value = "client_secret") String clientSecret,
            @ApiParam(name = "code", value = "授权码", required = true)
            @RequestParam(value = "code") String code,
            @ApiParam(name = "grant_type", value = "授权类型:authorization_code")
            @RequestParam(value = "grant_type", defaultValue = "authorization_code") String grantType,
            @ApiParam(name = "redirect_uri", value = "回调地址")
            @RequestParam(value = "redirect_uri") String redirectUri
    ) {
        ClientDetails clientDetails = clientDetailsHelper.getClientDetails(clientId, clientSecret);
        Map<String, String> map = new HashMap<>();
        map.put("grant_type", "authorization_code");
        map.put("code", code);
        map.put("redirect_uri", redirectUri);
        TokenRequest tokenRequest = new TokenRequest(map, clientDetails.getClientId(), Collections.emptySet(), grantType);
        OAuth2RequestFactory requestFactory = new DefaultOAuth2RequestFactory(redisClientDetailsService);
        AuthorizationCodeTokenGranter authorizationCodeTokenGranter = new AuthorizationCodeTokenGranter(
                authorizationServerTokenServices, authorizationCodeServices, redisClientDetailsService, requestFactory);
        OAuth2AccessToken oAuth2AccessToken = authorizationCodeTokenGranter.grant("authorization_code", tokenRequest);
        LoginUserHelper.additionalInformation(oAuth2AccessToken);
        return oAuth2AccessToken;
    }

    @ApiOperation(value = "客户端模式获取令牌")
    @PostMapping(value = "/auth/client/token")
    public OAuth2AccessToken clientTokenInfo(@ApiParam(name = HttpHeaders.AUTHORIZATION, value = "客户信息", required = true)
                                             @RequestHeader(HttpHeaders.AUTHORIZATION) String header) {

        ClientDetailsHelper.ClientInfo client = clientDetailsHelper.buildClientInfo(header);
        ClientDetails clientDetails = clientDetailsHelper.getClientDetails(client.getClientId(), client.getClientSecret());
        TokenRequest tokenRequest = new TokenRequest(MapUtils.EMPTY_SORTED_MAP, client.getClientId(), clientDetails.getScope(), "client_credentials");
        OAuth2RequestFactory requestFactory = new DefaultOAuth2RequestFactory(redisClientDetailsService);
        ClientCredentialsTokenGranter clientCredentialsTokenGranter = new ClientCredentialsTokenGranter(
                authorizationServerTokenServices, redisClientDetailsService, requestFactory);
        clientCredentialsTokenGranter.setAllowRefresh(true);
        OAuth2AccessToken oAuth2AccessToken = clientCredentialsTokenGranter.grant("client_credentials", tokenRequest);
        LoginUserHelper.additionalInformation(oAuth2AccessToken);
        return oAuth2AccessToken;

    }

    @ApiOperation(value = "刷新令牌")
    @PostMapping(value = "/auth/token/refresh")
    public OAuth2AccessToken refreshToken(
            @ApiParam(name = "token", value = "令牌", required = true)
            @RequestParam(value = "token") String token) {
        OAuth2AccessToken accessToken = tokenStore.readAccessToken(token);
        OAuth2Authentication auth = (OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();
        ClientDetails clientDetails = redisClientDetailsService.loadClientByClientId(auth.getOAuth2Request().getClientId());
        if (clientDetails == null) {
            throw new UnapprovedClientAuthenticationException("clientId对应的信息不存在");
        }
        OAuth2RequestFactory requestFactory = new DefaultOAuth2RequestFactory(redisClientDetailsService);
        RefreshTokenGranter refreshTokenGranter = new RefreshTokenGranter(authorizationServerTokenServices,
                redisClientDetailsService, requestFactory);
        Map<String, String> map = new HashMap<>();
        map.put("grant_type", "refresh_token");
        map.put("refresh_token", accessToken.getRefreshToken().getValue());
        TokenRequest tokenRequest = new TokenRequest(map, auth.getOAuth2Request().getClientId(),
                auth.getOAuth2Request().getScope(), "refresh_token");
        OAuth2AccessToken oAuth2AccessToken = refreshTokenGranter.grant("refresh_token", tokenRequest);
        tokenStore.removeAccessToken(accessToken);
        LoginUserHelper.additionalInformation(oAuth2AccessToken);
        return oAuth2AccessToken;
    }

    @ApiOperation(value = "获取令牌信息")
    @GetMapping(value = "/auth/token/read")
    public OAuth2AccessToken getTokenInfo(
            @ApiParam(name = "token", value = "令牌", required = true)
            @RequestParam(value = "token") String token) {
        if (StringUtils.isEmpty(token)) {
            throw new UnapprovedClientAuthenticationException("token 不能为空！");
        }
        OAuth2AccessToken oAuth2AccessToken = tokenStore.readAccessToken(token);
        LoginUserHelper.additionalInformation(oAuth2AccessToken);
        return oAuth2AccessToken;

    }

    @ApiOperation(value = "移除令牌")
    @PostMapping(value = "/auth/token/remove")
    public ReturnDatas removeToken(
            @ApiParam(name = "令牌", required = true)
            @RequestParam(value = "token") String token) {
        String message = "移除token失败！";
        OAuth2AccessToken accessToken = tokenStore.readAccessToken(token);
        if (accessToken != null) {
            tokenStore.removeAccessToken(accessToken);
            if (accessToken.getRefreshToken() != null) {
                tokenStore.removeRefreshToken(accessToken.getRefreshToken());
                message = "移除token成功！";
            }

        }
        return ReturnDatas.ok(message);
    }

    @ApiOperation(value = "获取用户信息")
    @PostMapping(value = "/auth/userinfo")
    public ReturnDatas getCurrentUserDetail() {
        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("user", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        List permissions = new ArrayList<>();
        SecurityContextHolder.getContext().getAuthentication().getAuthorities().forEach(permissions::add);
        userInfo.put("permissions", permissions);
        List routes = new ArrayList<>();
        userInfo.put("routes", routes);
        return ReturnDatas.ok(userInfo);
    }

    @ApiOperation(value = "登陆视图")
    @GetMapping("/login")
    public ModelAndView loginView() {
        return new ModelAndView("login");
    }

}
