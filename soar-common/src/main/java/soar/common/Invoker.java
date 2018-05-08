package soar.common;

import soar.common.exception.SoarException;
import soar.common.rpc.Request;
import soar.common.rpc.Response;

/**
 * Invoker
 * the provider service for consumer
 *
 * @author xiuyuhang [xiuyuhang]
 * @since 2018-03-14
 */
public interface Invoker extends Domain {

    /**
     * invoke a remote request
     *
     * @param request request
     * @return response
     */
    Response invoke(Request request) throws SoarException;
}
