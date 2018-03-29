package soar.cluster;


import soar.common.Invoker;

/**
 * Cluster
 *
 * @author 修宇航 [xiuyuhang]
 * @since 2018-03-14
 */
public interface Cluster {

    /**
     * construct a cluster invoker
     *
     * @param aggregator the aggregator
     * @param <T>        service
     * @return the cluster invoker
     */
    <T> Invoker<T> construct(Aggregator<T> aggregator);
}
