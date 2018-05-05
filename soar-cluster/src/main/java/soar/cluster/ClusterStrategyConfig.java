package soar.cluster;

/**
 * ClusterStrategyConfig
 *
 * @author xiuyuhang [xiuyuhang]
 * @since 2018-05-05
 */
public class ClusterStrategyConfig {

    private final ClusterStrategy clusterStrategy;

    private final LoadBalanceStrategy loadBalanceStrategy;

    private final int timeout;

    private final int retries;

    public ClusterStrategyConfig(ClusterStrategy clusterStrategy, LoadBalanceStrategy loadBalanceStrategy, int timeout, int retries) {
        this.clusterStrategy = clusterStrategy;
        this.loadBalanceStrategy = loadBalanceStrategy;
        this.timeout = timeout;
        this.retries = retries;
    }

    public ClusterStrategy getClusterStrategy() {
        return clusterStrategy;
    }

    public LoadBalanceStrategy getLoadBalanceStrategy() {
        return loadBalanceStrategy;
    }

    public int getTimeout() {
        return timeout;
    }

    public int getRetries() {
        return retries;
    }
}
