package soar.common;

/**
 * SoarResponse
 *
 * @author xiuyuhang [xiuyuhang]
 * @since 2018-04-08
 */
public class SoarResponse implements Response {

    private static final long serialVersionUID = -7861027722699032059L;

    /**
     * result
     */
    private Object result;

    /**
     * cause
     */
    private Throwable exception;

    public SoarResponse() {
    }

    public SoarResponse(Object result) {
        this.result = result;
    }

    public SoarResponse(Throwable exception) {
        this.exception = exception;
    }

    @Override
    public Object getValue() {
        return result;
    }

    @Override
    public Throwable getException() {
        return exception;
    }

    @Override
    public boolean hasException() {
        return exception != null;
    }
}
