package soar.core.cluster;

import soar.common.*;
import soar.common.exception.SoarException;
import soar.core.rpc.AbstractInvoker;

import java.util.Map;

/**
 * AbstractRoomRouterInovker
 * <p>
 * room router #invoke -> cluster invoker #invoke -> soar invoker #invoke
 *
 * @author xiuyuhang [xiuyuhang]
 * @since 2018-03-29
 */
public abstract class AbstractRoomRouterInovker<T> extends AbstractInvoker<T> {

    /**
     * select machine room
     */
    private RoomSelector roomSelector;

    /**
     * instance of cluster invoker
     */
    private Map<String, Invoker<T>> invokers;

    protected AbstractRoomRouterInovker(Class<T> type) {
        super(type);
    }

    @Override
    public Response invoke(Request request) throws SoarException {
        return null;
    }
}
