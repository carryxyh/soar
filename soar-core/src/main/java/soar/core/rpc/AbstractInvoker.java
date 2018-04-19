package soar.core.rpc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import soar.common.Invoker;
import soar.common.exception.SoarException;
import soar.common.exception.SoarExceptionCode;
import soar.common.rpc.Request;
import soar.common.rpc.Response;
import soar.common.rpc.SoarResponse;
import soar.common.utils.NetUtils;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * AbstractInvoker
 *
 * @author xiuyuhang [xiuyuhang]
 * @since 2018-04-08
 */
public abstract class AbstractInvoker<T> implements Invoker<T> {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

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
        if (isDestroyed()) {
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

    public void setAvailable(boolean available) {
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
