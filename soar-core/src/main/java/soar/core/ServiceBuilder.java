package soar.core;

import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import soar.cluster.ClusterStrategy;
import soar.cluster.ClusterStrategyConfig;
import soar.cluster.LoadBalanceStrategy;
import soar.common.SoarConstants;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * ServiceBuilder
 * service builder for client
 * create a proxy with byte buddy
 * <p>
 * <p>
 * - - - - - - -        - - - - - - - -       - - - - - - - -
 * |            |  		|               |     |               |
 * | room-router|  ——>  |cluster-invoker|  -> | soar-invoker  |
 * |            |  		|               |     |               |
 * - - - - - -  	     - - - - - - - -       - - - - - - - -
 * <p>
 *
 * @author xiuyuhang [xiuyuhang]
 * @since 2018-04-23
 */
public class ServiceBuilder<T> {

    private final Class<T> service;

    /**
     * default tag
     */
    private String tag = SoarConstants.DEFAULT_TAG;

    /**
     * default client machine room
     */
    private String room = SoarConstants.DEFAULT_ROOM;

    /**
     * cacher
     */
    private Cacher cacher = null;

    /**
     * ASYNC for default
     */
    private SyncType syncType = SyncType.ASYNC;

    /**
     * service version
     */
    private String version = SoarConstants.DEFAULT_VERSION;

    /**
     * consumer filter list
     */
    private List<ConsumerFilter> consumerFilters;

    /*-----------------------------------sth about cluster invoke-------------------------------------------*/

    /**
     * round robin for default laodbalance strategy
     */
    private LoadBalanceStrategy loadBalanceStrategy = LoadBalanceStrategy.ROUND_ROBIN;

    /**
     * fail over for default cluster strategy
     */
    private ClusterStrategy clusterStrategy = ClusterStrategy.FAIL_OVER;

    /**
     * retry times
     */
    private int retries = SoarConstants.DEFAULT_RETRY_TIMES;

    /**
     * timeout
     */
    private int timeout = SoarConstants.DEFAULT_TIMEOUT;

    /**
     * constructor
     */
    public ServiceBuilder(Class<T> service) {
        this.service = service;
    }

    public ServiceBuilder<T> setTag(String tag) {
        if (tag == null) {
            throw new NullPointerException();
        }
        this.tag = tag;
        return this;
    }

    public ServiceBuilder<T> setRoom(String room) {
        if (room == null) {
            throw new NullPointerException();
        }
        this.room = room;
        return this;
    }

    public ServiceBuilder<T> setSyncType(SyncType syncType) {
        if (syncType == null) {
            throw new NullPointerException();
        }
        this.syncType = syncType;
        return this;
    }

    public ServiceBuilder<T> setLoadBalanceStrategy(LoadBalanceStrategy loadBalanceStrategy) {
        if (loadBalanceStrategy == null) {
            throw new NullPointerException();
        }
        this.loadBalanceStrategy = loadBalanceStrategy;
        return this;
    }

    public ServiceBuilder<T> setCacher(Cacher cacher) {
        this.cacher = cacher;
        return this;
    }

    public ServiceBuilder<T> setClusterStrategy(ClusterStrategy clusterStrategy) {
        if (clusterStrategy == null) {
            throw new NullPointerException();
        }
        this.clusterStrategy = clusterStrategy;
        return this;
    }

    public ServiceBuilder<T> setConsumerFilters(List<ConsumerFilter> consumerFilters) {
        if (consumerFilters == null) {
            throw new NullPointerException();
        }
        this.consumerFilters = consumerFilters;
        return this;
    }

    public ServiceBuilder<T> setRetries(int retries) {
        this.retries = retries;
        return this;
    }

    public ServiceBuilder<T> setTimeout(int timeout) {
        this.timeout = timeout;
        return this;
    }

    public T build() {
        ClusterStrategyConfig clusterStrategyConfig = new ClusterStrategyConfig(clusterStrategy, loadBalanceStrategy, timeout, retries);
        Object methodHandler = null;

        Method[] methods = service.getDeclaredMethods();
        if (methods == null || methods.length == 0) {
            //do not have method inter
            return Proxies.getDefault().newProxy(service, methodHandler);
        }
        Map<String, MethodConfig> methodConfigMap = Maps.newHashMap();
        for (Method m : methods) {
            MethodConfig mc = m.getAnnotation(MethodConfig.class);
            if (mc != null) {
                methodConfigMap.put(m.getName(), mc);
            }
        }

        switch (syncType) {
            case SYNC:

                break;
            case ASYNC:

                break;
            default:
                throw reject("unsupport synctype : " + syncType);
        }
        return Proxies.getDefault().newProxy(service, methodHandler);
    }

    private static UnsupportedOperationException reject(String message) {
        return new UnsupportedOperationException(message);
    }
}
