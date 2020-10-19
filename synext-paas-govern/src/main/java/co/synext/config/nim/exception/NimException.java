package co.synext.config.nim.exception;

import co.synext.common.exception.BizException;

/**
 * <p>
 * 网易云信接口异常
 * </p>
 *
 * @author xu.ran
 * @since 2020-04-10
 */
public class NimException extends BizException {
    public NimException(String message) {
        super(message);
    }

    public NimException(String message, Throwable cause) {
        super(message, cause);
    }

    public NimException(Throwable cause) {
        super(cause);
    }
}
