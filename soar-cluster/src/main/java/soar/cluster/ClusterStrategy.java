package soar.cluster;

import soar.cluster.instance.*;

/**
 * ClusterStrategy
 *
 * @author xiuyuhang [xiuyuhang]
 * @since 2018-04-23
 */
public enum ClusterStrategy {

    FAIL_OVER(FailOverCluster.class),
    FAIL_FAST(FailFastCluster.class),
    BROAD_CAST(BroadcastCluster.class),
    FAIL_BACK(FailBackCluster.class),
    FAIL_SAFE(FailSafeCluster.class),
    FORKING(ForkingCluster.class),;

    private Class<?> cluster;

    ClusterStrategy(Class<?> cluster) {
        this.cluster = cluster;
    }
}
