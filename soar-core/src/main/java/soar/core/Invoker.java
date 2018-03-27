package soar.core;

import soar.common.SoarException;

/**
 * Invoker
 * the provider service for consumer
 *
 * @author xiuyuhang [xiuyuhang]
 * @since 2018-03-14
 */
public interface Invoker<T> extends Domain {

    /**
     * invoke a remote request
     *
     * @param request request
     * @return response
     */
    Response invoke(Request request) throws SoarException;
}
