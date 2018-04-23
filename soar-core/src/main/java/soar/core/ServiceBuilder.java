package soar.core;

import soar.cluster.ClusterStrategy;
import soar.cluster.LoadBalanceStrategy;

import java.util.List;

/**
 * ServiceBuilder
 * service builder
 *
 * @author xiuyuhang [xiuyuhang]
 * @since 2018-04-23
 */
public class ServiceBuilder<T> {

    private final Class<T> service;

    /**
     * ASYNC for default
     */
    private SyncType syncType = SyncType.ASYNC;

    /**
     * round robin for default laodbalance strategy
     */
    private LoadBalanceStrategy loadBalanceStrategy = LoadBalanceStrategy.ROUND_ROBIN;

    /**
     * fail over for default cluster strategy
     */
    private ClusterStrategy clusterStrategy = ClusterStrategy.FAIL_OVER;

    /**
     * consumer filter list
     */
    private List<ConsumerFilter> consumerFilters;

    /**
     * retry times
     */
    private int retries = 2;

    public ServiceBuilder(Class<T> service) {
        this.service = service;
    }

    public T build() {
        return null;
    }
}
