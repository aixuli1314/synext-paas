package co.synext.common.exception;

/**
 * @author xu.ran
 * @date 2020/4/9 21:32
 * @description: 自定义业务异常类
 */
public class BizException extends RuntimeException{

    public BizException(String message) {
        super(message);
    }

    public BizException(String message, Throwable cause) {
        super(message, cause);
    }

    public BizException(Throwable cause) {
        super(cause);
    }
}
