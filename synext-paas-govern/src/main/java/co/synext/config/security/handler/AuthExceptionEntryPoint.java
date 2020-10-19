package co.synext.config.security.handler;

import co.synext.common.base.resp.ReturnDatas;
import co.synext.common.constant.Constant;
import co.synext.common.utils.JsonUtils;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
public class AuthExceptionEntryPoint implements AuthenticationEntryPoint {


    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws ServletException {
        Throwable cause = authException.getCause();

        response.setStatus(HttpStatus.OK.value());
        response.setHeader("Content-Type", "application/json;charset=UTF-8");
        try {
            if (cause instanceof InvalidTokenException) {
                response.getWriter().write(JsonUtils.writeValueAsString(
                		ReturnDatas.builder().statusCode(Constant.TOKEN_ERROR).message("token失效!").build()));
            }
            if (cause instanceof BadCredentialsException) {
            	response.getWriter().write(JsonUtils.writeValueAsString(
            			ReturnDatas.builder().statusCode(Constant.ERROR_CODE).message("账号或密码错误!").build()));
            }
            if (cause instanceof AccessDeniedException) {
            	response.getWriter().write(JsonUtils.writeValueAsString(
            			ReturnDatas.builder().statusCode(Constant.ERROR_CODE).message("没有访问权限!").build()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
