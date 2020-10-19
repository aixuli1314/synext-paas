package co.synext.config.captch.filter;

import co.synext.common.constant.SecurityConstant;
import co.synext.common.exception.ValidationCodeException;
import co.synext.config.security.helper.ClientDetailsHelper;
import co.synext.config.captch.ValidateCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static co.synext.common.constant.SecurityConstant.DEFAULT_TEST_CLIENT;


/**
 * @author xu.ran
 * @date 2020/4/27 21:28
 * @description: TODO
 */
//@Component
public class ValidateCodeFilter extends GenericFilterBean {

    @Autowired
    private ValidateCodeService imageValidateCodeService;

    @Autowired
    private ValidateCodeService smsValidateCodeService;

    @Autowired
    private AuthenticationFailureHandler loginFailureHandler;

    @Autowired
    private ClientDetailsHelper clientDetailsHelper;

    private AntPathMatcher pathMatcher = new AntPathMatcher();


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        //手机号登陆必须做短信校验
        String deviceId = request.getParameter(SecurityConstant.VALIDATE_PARAMETER_NAME_DEVICE_ID);
        String inputCodeValue = request.getParameter(SecurityConstant.VALIDATE_PARAMETER_NAME_CODE);
        try {
            if (pathMatcher.match("/oauth/mobile/token", httpServletRequest.getRequestURI()) && httpServletRequest.getMethod().equals("POST")) {
                String mobile = request.getParameter(SecurityConstant.DEFAULT_PARAMETER_NAME_MOBILE);
                smsValidateCodeService.verifySmsCode(deviceId, mobile, inputCodeValue);
            } else if (pathMatcher.match("/oauth/user/token", httpServletRequest.getRequestURI()) && httpServletRequest.getMethod().equals("POST")) {
                if (request.getParameter("grant_type") != null) {
                    //密码模式需要验证码
                    if ("password".toUpperCase().equals(request.getParameter("grant_type").toUpperCase())) {
                        String header = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
                        if (!StringUtils.isEmpty(header)) {
                            ClientDetailsHelper.ClientInfo client = clientDetailsHelper.buildClientInfo(header);
                            //test 放行不做验证码校验
                            if (!client.getClientId().equals(DEFAULT_TEST_CLIENT)) {
                                imageValidateCodeService.verifyCaptcha(deviceId, inputCodeValue);
                            }
                        } else {
                            throw new ValidationCodeException("无法获取clientId！");
                        }
                    }
                }
            }
        } catch (AuthenticationException e) {
            loginFailureHandler.onAuthenticationFailure(httpServletRequest, httpServletResponse, e);
            return;
        }
        chain.doFilter(request, response);
    }
}
