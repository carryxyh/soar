package soar.cluster;

import soar.cluster.loadbalance.RandomLoadBalancer;
import soar.cluster.loadbalance.RoundRobinLoadBalancer;

/**
 * LoadBalanceStrategy
 *
 * @author xiuyuhang [xiuyuhang]
 * @since 2018-04-23
 */
public enum LoadBalanceStrategy {

    /**
     * random select
     */
    RANDOM(RandomLoadBalancer.class),

    /**
     * round robin select
     */
    ROUND_ROBIN(RoundRobinLoadBalancer.class),;

    private Class<?> cluster;

    LoadBalanceStrategy(Class<?> cluster) {
        this.cluster = cluster;
    }
}
