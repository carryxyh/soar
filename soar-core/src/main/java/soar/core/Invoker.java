package soar.core;

import soar.common.SoarException;

/**
 * Invoker
 *
 * @author xiuyuhang [xiuyuhang]
 * @since 2018-03-14
 */
public interface Invoker {

    /**
     * invoke a remote request
     *
     * @param request request
     * @return response
     */
    Response invoke(Request request) throws SoarException;
}
