package co.synext.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import co.synext.common.base.resp.ReturnDatas;
import co.synext.config.security.handler.RestOauth2LogoutHandler;
import co.synext.config.security.handler.RestLoginSuccessHandler;
import co.synext.config.security.handler.RestResourceAuthExceptionEntryPoint;
import co.synext.config.security.helper.ClientDetailsHelper;
import co.synext.config.security.properties.PermitUrlProperties;
import co.synext.config.security.provider.mobile.MobileAuthProvider;
import co.synext.config.security.provider.wxapp.WxMaAuthProvider;
import co.synext.config.security.service.RedisClientDetailsService;
import co.synext.config.captch.ValidateCodeService;
import co.synext.module.system.service.IUserConnectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;


@Configuration
@EnableWebSecurity
@EnableConfigurationProperties(PermitUrlProperties.class)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Qualifier("UserDetailsServiceImpl")
    @Autowired
    private UserDetailsService userDetailsServiceImpl;

    @Autowired
    private IUserConnectionService userConnectionService;


    @Autowired
    private RestOauth2LogoutHandler restOauth2LogoutHandler;

    @Autowired
    private PermitUrlProperties permitUrlProperties;

    @Autowired
    private RedisClientDetailsService redisClientDetailsService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AuthorizationServerTokenServices authorizationServerTokenServices;

    @Autowired
    private ClientDetailsHelper clientDetailsHelper;

    @Autowired
    private  ValidateCodeService smsValidateCodeService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 登陆成功处理器
     *
     * @return
     */
    @Bean
    public AuthenticationSuccessHandler loginSuccessHandler() {
        return new SavedRequestAwareAuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                super.onAuthenticationSuccess(request, response, authentication);
                return;
            }
        };
    }

    /***
     *
     * 手机号认证成功处理器
     *
     * @return
     */
    @Bean
    public AuthenticationSuccessHandler restLoginSuccessHandler() {
        return RestLoginSuccessHandler.builder()
                .objectMapper(objectMapper)
                .redisClientDetailsService(redisClientDetailsService)
                .passwordEncoder(passwordEncoder())
                .clientDetailsHelper(clientDetailsHelper)
                .defaultAuthorizationServerTokenServices(authorizationServerTokenServices).build();
    }

    /**
     * 登陆失败处理器
     *
     * @return
     */
    @Bean
    public AuthenticationFailureHandler restLoginFailureHandler() {
        return (request, response, exception) -> {
            response.setContentType("application/json;charset=UTF-8");
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.getWriter().write(objectMapper.writeValueAsString(ReturnDatas.error().setMessage(exception.getMessage())));
            response.getWriter().flush();
            response.getWriter().close();
        };

    }

    /**
     * 手机登陆配置
     *
     * @return
     */
    @Bean
    public AuthenticationProvider mobileAuthProvider() {
        MobileAuthProvider mobileAuthProvider = new MobileAuthProvider(userDetailsServiceImpl,smsValidateCodeService);
        return mobileAuthProvider;
    }

    /**
     * 微信登陆配置
     *
     * @return
     */
    @Bean
    public AuthenticationProvider wxMaAuthProvider() {
        AuthenticationProvider wxMaAuthProvider = new WxMaAuthProvider(userDetailsServiceImpl,userConnectionService);
        return wxMaAuthProvider;
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsServiceImpl)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(WebSecurity webSecurity) {
        webSecurity
                .ignoring()
                .antMatchers("/templates/login.html")
                .antMatchers("/index.html")
                .antMatchers("/error")
                .antMatchers("/auth/user/token")
                .antMatchers("/auth/mobile/token")
                .antMatchers("/auth/wxma/token")
                .antMatchers("/auth/code/token")
                .antMatchers("/auth/client/token")
                .antMatchers("/verify/**")
                .mvcMatchers("/signup/**")
                .antMatchers(HttpMethod.OPTIONS)
                .antMatchers(permitUrlProperties.getIgnored());

    }


    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .formLogin()
//                .loginPage("/login.html")
//                .loginProcessingUrl("/user/login")
                .permitAll()
                .successHandler(loginSuccessHandler())
                .failureHandler(restLoginFailureHandler())
                .and()
                .logout()
//                .logoutSuccessUrl("/login.html")
                .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler())
                .addLogoutHandler(restOauth2LogoutHandler)
                .clearAuthentication(true)
                .and()
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(new RestResourceAuthExceptionEntryPoint(objectMapper))
                .and()
                .csrf()
                .disable()
                .headers()
                .frameOptions()
                .disable()
                .cacheControl();
        //微信小程序登录认证容器
        httpSecurity.authenticationProvider(wxMaAuthProvider());
        //微信手机号登录认证容器
        httpSecurity.authenticationProvider(mobileAuthProvider());
    }

}
