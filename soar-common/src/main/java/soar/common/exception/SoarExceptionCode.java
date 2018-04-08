package soar.common.exception;

/**
 * SoarExceptionCode
 *
 * @author xiuyuhang [xiuyuhang]
 * @since 2018-04-08
 */
public enum SoarExceptionCode {

    /**
     * invoke a destroyed invoker
     */
    INVOKER_DESTROYED(101, "invoker for service %s on consumer %s has destroyed");

    /**
     * error code
     */
    private Integer code;

    /**
     * error description
     */
    private String desc;

    SoarExceptionCode(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public String getErrorCode() {
        return this.name();
    }
}
