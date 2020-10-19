package co.synext.common.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @author xu.ran
 * @date 2020/4/13 14:14
 * @description: 验证码异常
 */
public class ValidationCodeException extends AuthenticationException {

    public ValidationCodeException(String message) {
        super(message);
    }

    public ValidationCodeException(String msg, Throwable t) {
        super(msg, t);
    }
}
