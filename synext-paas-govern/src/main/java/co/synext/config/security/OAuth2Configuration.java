package co.synext.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import co.synext.common.constant.Constant;
import co.synext.config.security.handler.AuthExceptionEntryPoint;
import co.synext.config.security.handler.RestOauth2LogoutHandler;
import co.synext.config.security.handler.RestResourceAuthExceptionEntryPoint;
import co.synext.config.security.handler.RestWebResponseExceptionTranslator;
import co.synext.config.security.properties.PermitUrlProperties;
import co.synext.config.security.service.RedisAuthorizationCodeService;
import co.synext.config.security.service.RedisClientDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Configuration
public class OAuth2Configuration {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private PermitUrlProperties permitUrlProperties;

    @Bean
    public RedisTokenStore redisTokenStore() {
        RedisTokenStore redisTokenStore = new RedisTokenStore(redisTemplate.getConnectionFactory());
        redisTokenStore.setPrefix(Constant.JCLOUDS_PREFFIX + Constant.OAUTH_PREFFIX);
        return redisTokenStore;
    }


    @Bean
    public RestOauth2LogoutHandler oauthLogoutHandler() {
        RestOauth2LogoutHandler restOauth2LogoutHandler = new RestOauth2LogoutHandler();
        restOauth2LogoutHandler.setTokenStore(redisTokenStore());
        return restOauth2LogoutHandler;
    }


    @Configuration
    @EnableAuthorizationServer
    @AutoConfigureAfter(AuthorizationServerEndpointsConfigurer.class)
    public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

        @Autowired
        private AuthenticationManager authenticationManager;

        @Autowired
        private UserDetailsService userDetailsServiceImpl;

        @Autowired
        private RedisTokenStore redisTokenStore;

        @Autowired
        private RedisClientDetailsService redisClientDetailsService;

        @Autowired
        private RedisAuthorizationCodeService redisAuthorizationCodeService;


        @Override
        public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
            endpoints
                    .tokenStore(redisTokenStore)
                    .authenticationManager(authenticationManager)
                    .userDetailsService(userDetailsServiceImpl)
                    .authorizationCodeServices(redisAuthorizationCodeService)
                    .reuseRefreshTokens(false)
                    .exceptionTranslator(new RestWebResponseExceptionTranslator());
        }

        @Override
        public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
            clients.withClientDetails(redisClientDetailsService);
            redisClientDetailsService.loadAllClientToCache();
        }

        @Override
        public void configure(AuthorizationServerSecurityConfigurer serverSecurityConfigurer) {
            serverSecurityConfigurer
                    .allowFormAuthenticationForClients()
                    .tokenKeyAccess("permitAll()")
                    .checkTokenAccess("isAuthenticated()");
        }

    }

    @Configuration
    @EnableResourceServer
    public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

        @Autowired
        private ObjectMapper objectMapper;
        @Override
        public void configure(ResourceServerSecurityConfigurer resources) {
            //resources.tokenServices(tokenServices());
            resources.authenticationEntryPoint(new AuthExceptionEntryPoint());

        }
        @Override
        public void configure(HttpSecurity httpSecurity) throws Exception {
            httpSecurity
                    .requestMatcher(new RequestMatcher() {
                        private AntPathMatcher antPathMatcher = new AntPathMatcher();
                        @Override
                        public boolean matches(HttpServletRequest request) {

                            String header = request.getHeader("Authorization");
                            if (header != null) {
                                if (header.startsWith(OAuth2AccessToken.BEARER_TYPE)) {
                                    return true;
                                }
                            }

                            if (antPathMatcher.match("/auth/user/token", request.getRequestURI())) {
                                return true;
                            }

                            if (antPathMatcher.match("/auth/wxma/token", request.getRequestURI())) {
                                return true;
                            }

                            if (antPathMatcher.match("/auth/mobile/token", request.getRequestURI())) {
                                return true;
                            }

                            if (antPathMatcher.match("/auth/code/token", request.getRequestURI())) {
                                return true;
                            }

                            if (antPathMatcher.match("/auth/client/token", request.getRequestURI())) {
                                return true;
                            }


                            if (antPathMatcher.match("/auth/remove/token", request.getRequestURI())) {
                                return true;
                            }

                            if (antPathMatcher.match("/auth/refresh/token", request.getRequestURI())) {
                                return true;
                            }

                            if (antPathMatcher.match("/auth/userinfo", request.getRequestURI())) {
                                return true;
                            }

                            return false;
                        }
                    })
                    .authorizeRequests()
                    .antMatchers(permitUrlProperties.getIgnored())
                    .permitAll()
                    .antMatchers(HttpMethod.OPTIONS)
                    .permitAll()
                    .anyRequest()
                    .authenticated()
                    .and()
                    .headers()
                    .frameOptions()
                    .disable()
                    .and()
                    .csrf()
                    .disable()
                    .exceptionHandling()
                    .authenticationEntryPoint(new RestResourceAuthExceptionEntryPoint(objectMapper));
            ;
        }
    }
}
