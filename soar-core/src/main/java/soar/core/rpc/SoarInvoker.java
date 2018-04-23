package soar.core.rpc;

import soar.common.rpc.Request;
import soar.common.rpc.Response;

/**
 * SoarInvoker
 *
 * @author xiuyuhang [xiuyuhang]
 * @since 2018-04-23
 */
public class SoarInvoker<T> extends AbstractInvoker<T> {

    protected SoarInvoker(Class<T> type) {
        super(type);
    }

    @Override
    protected Response doInvoke(Request request) throws Throwable {
        return null;
    }
}
