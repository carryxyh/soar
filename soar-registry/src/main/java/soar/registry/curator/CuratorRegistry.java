package soar.registry.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;
import org.apache.log4j.Logger;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import soar.registry.Registry;
import soar.registry.RegistryListener;

/**
 * CuratorRegistry
 *
 * @author xiuyuhang [xiuyuhang]
 * @since 2018-03-15
 */
public class CuratorRegistry implements Registry {

    private static final Logger logger = Logger.getLogger(CuratorRegistry.class);

    /**
     * client
     */
    private final CuratorFramework client;

    public CuratorRegistry(String server, String digest) {
        CuratorFrameworkFactory.Builder builder = CuratorFrameworkFactory.builder()
                .connectString(server)
                .retryPolicy(new RetryNTimes(1, 1000))
                .connectionTimeoutMs(5000);
        try {
            if (digest != null) {
                builder.authorization("digest", digest.getBytes("utf-8"));
            }
            client = builder.build();
        } catch (Exception e) {
            logger.error("create CuratorRegistry fail !", e);
            throw new IllegalStateException(e.getMessage());
        }
    }

    @Override
    public void registry(String path) {
        try {
            client.create().withMode(CreateMode.EPHEMERAL).forPath(path);
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    @Override
    public void unRegistry(String path) {

    }

    @Override
    public void subscribe(String path, RegistryListener listener) {

    }
}
