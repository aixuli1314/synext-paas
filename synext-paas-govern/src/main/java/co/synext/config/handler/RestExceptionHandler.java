package co.synext.config.handler;

import co.synext.common.constant.Constant;
import co.synext.common.base.resp.ReturnDatas;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.oauth2.common.exceptions.UnapprovedClientAuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ReturnDatas exception(Throwable throwable) {
//        SysLog sysLog = LogBuilder.buildSysLog(JCloudsEnums.LogTypeEnum.错误日志);
//        sysLog.setTitle(throwable.getMessage());
//        sysLog.setException(throwable.toString());
//        SpringContextHolder.publishEvent(new LogEvent(sysLog));
        throwable.printStackTrace();
        return ReturnDatas.error().setMessage(throwable.toString());
    }

    @ExceptionHandler({BadCredentialsException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ReturnDatas badCredentialsException() {
        return ReturnDatas.builder().statusCode(Constant.ERROR_CODE).message("账号或密码错误！").build();
    }

    @ExceptionHandler({UnapprovedClientAuthenticationException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ReturnDatas unapprovedClientAuthenticationException(UnapprovedClientAuthenticationException exception) {
        return ReturnDatas.builder().statusCode(Constant.ERROR_CODE).message(exception.getMessage()).build();
    }

    @ExceptionHandler({IllegalArgumentException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ReturnDatas illegalArgumentException(IllegalArgumentException exception) {
        return ReturnDatas.builder().statusCode(Constant.ERROR_CODE).message(exception.getMessage()).build();
    }

    @ExceptionHandler({AccessDeniedException.class})
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ReturnDatas accessDeniedException() {
        return ReturnDatas.builder().statusCode(Constant.ERROR_CODE).message("没有访问权限！").build();
    }

}
