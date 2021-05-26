package cn.fwhspace.iceblog.config.exception;

/**
 * @author fwh
 * @version 1.0
 * @date 2020/11/24 10:15
 * @description 业务系统异常controller
 */
public class SystemException extends RuntimeException {
    public SystemException() {
        super();
    }

    public SystemException(String message) {
        super(message);
    }

    public SystemException(String message, Throwable cause) {
        super(message, cause);
    }

    public SystemException(Throwable cause) {
        super(cause);
    }

    protected SystemException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
