package soar.cluster;


import soar.common.Invoker;

/**
 * Cluster
 * TODO Cluster strategy when build invoker
 *
 * @author 修宇航 [xiuyuhang]
 * @since 2018-03-14
 */
public interface Cluster {

    /**
     * construct a cluster invoker
     *
     * @param aggregator the aggregator
     * @return the cluster invoker
     */
    Invoker construct(Aggregator aggregator);
}
