package soar.common;

/**
 * SoarException
 *
 * @author xiuyuhang [xiuyuhang]
 * @since 2018-03-14
 */
public class SoarException extends RuntimeException {

    private Integer code;

    public SoarException(Integer code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
