package soar.core.rpc;

import soar.common.Invoker;
import soar.common.Request;
import soar.common.Response;
import soar.common.SoarResponse;
import soar.common.exception.SoarException;
import soar.common.exception.SoarExceptionCode;
import soar.common.utils.NetUtils;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * AbstractInvoker
 *
 * @author xiuyuhang [xiuyuhang]
 * @since 2018-04-08
 */
public abstract class AbstractInvoker<T> implements Invoker<T> {

    /**
     * the interface
     */
    private final Class<T> type;

    /**
     * available
     */
    private volatile boolean available = true;

    /**
     * destroyed
     */
    private AtomicBoolean destroyed = new AtomicBoolean(false);

    protected AbstractInvoker(Class<T> type) {
        this.type = type;
    }

    @Override
    public Response invoke(Request request) throws SoarException {
        if (destroyed.get()) {
            throw new SoarException(SoarExceptionCode.INVOKER_DESTROYED.getCode(), String.format(SoarExceptionCode.INVOKER_DESTROYED.getDesc(), type, NetUtils.getLocalHost()));
        }
        try {
            return doInvoke(request);
        } catch (Throwable throwable) {
            return new SoarResponse(throwable);
        }
    }

    protected abstract Response doInvoke(Request request) throws Throwable;

    @Override
    public Class<T> getInterface() {
        return type;
    }

    @Override
    public boolean isAvailable() {
        return available;
    }

    protected void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public void destroy() {
        if (!destroyed.compareAndSet(false, true)) {
            return;
        }
        setAvailable(false);
    }

    public boolean isDestroyed() {
        return destroyed.get();
    }
}
