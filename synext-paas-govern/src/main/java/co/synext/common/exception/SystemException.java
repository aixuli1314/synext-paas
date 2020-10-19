package co.synext.common.exception;

/**
 * @author xu.ran
 * @date 2020/4/9 21:32
 * @description: 自定义系统异常类
 */
public class SystemException extends RuntimeException{

    public SystemException(String message) {
        super(message);
    }

    public SystemException(String message, Throwable cause) {
        super(message, cause);
    }

    public SystemException(Throwable cause) {
        super(cause);
    }
}
