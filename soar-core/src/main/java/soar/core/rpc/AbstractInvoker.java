package soar.core.rpc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import soar.common.Invoker;
import soar.common.exception.SoarException;
import soar.common.exception.SoarExceptionCode;
import soar.common.rpc.Request;
import soar.common.rpc.Response;
import soar.common.rpc.SoarResponse;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * AbstractInvoker
 *
 * @author xiuyuhang [xiuyuhang]
 * @since 2018-04-08
 */
public abstract class AbstractInvoker implements Invoker {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * available
     */
    private volatile boolean available = true;

    /**
     * destroyed
     */
    private AtomicBoolean destroyed = new AtomicBoolean(false);

    @Override
    public Response invoke(Request request) throws SoarException {
        if (isDestroyed()) {
            throw new SoarException(SoarExceptionCode.INVOKER_DESTROYED);
        }
        try {
            return doInvoke(request);
        } catch (Throwable throwable) {
            return new SoarResponse(throwable);
        }
    }

    protected abstract Response doInvoke(Request request) throws Throwable;

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
