package soar.cluster;

import soar.core.Domain;
import soar.core.Invoker;
import soar.core.Request;

import java.util.List;

/**
 * Aggregator
 * invoker lists
 *
 * @author 修宇航 [xiuyuhang]
 * @since 2018-03-26
 */
public interface Aggregator<T> extends Domain {

    /**
     * part of invokers to use
     *
     * @param request request
     * @return part of invokers
     */
    List<Invoker<T>> part(Request request);
}
