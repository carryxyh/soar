package soar.common.exception;

/**
 * SoarException
 *
 * @author xiuyuhang [xiuyuhang]
 * @since 2018-03-14
 */
public class SoarException extends RuntimeException {

    private static final long serialVersionUID = -5119488048315764051L;

    /**
     * exception code
     */
    private Integer code;

    public SoarException(Integer code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public SoarException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public SoarException(SoarExceptionCode soarExceptionCode) {
        super(soarExceptionCode.getDesc());
        this.code = soarExceptionCode.getCode();
    }

    public Integer getCode() {
        return code;
    }
}
