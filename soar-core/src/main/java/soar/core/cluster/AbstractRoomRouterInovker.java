package soar.core.cluster;

import soar.common.RoomSelector;
import soar.common.exception.SoarException;
import soar.common.rpc.Request;
import soar.common.rpc.Response;
import soar.core.rpc.AbstractInvoker;

/**
 * AbstractRoomRouterInovker
 * <p>
 * room router #invoke -> cluster invoker #invoke -> soar invoker #invoke
 *
 * @author xiuyuhang [xiuyuhang]
 * @since 2018-03-29
 */
public abstract class AbstractRoomRouterInovker extends AbstractInvoker {

    /**
     * select machine room
     */
    private RoomSelector roomSelector;

    @Override
    public Response invoke(Request request) throws SoarException {
        return null;
    }
}
