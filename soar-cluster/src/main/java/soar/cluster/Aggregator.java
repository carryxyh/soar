package soar.cluster;

import soar.common.Domain;
import soar.common.Invoker;
import soar.common.Request;

import java.util.List;

/**
 * Aggregator
 * soar invoker lists
 * subscribe router data from zk
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
