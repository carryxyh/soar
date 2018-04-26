package soar.registry.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;
import org.apache.log4j.Logger;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import soar.registry.AbstractZkClient;
import soar.registry.StateListener;

import java.util.List;

/**
 * CuratorClient
 *
 * @author xiuyuhang [xiuyuhang]
 * @since 2018-03-15
 */
public class CuratorClient extends AbstractZkClient {

    private static final Logger logger = Logger.getLogger(CuratorClient.class);

    /**
     * client
     */
    private final CuratorFramework client;

    public CuratorClient(String server, String digest) {
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
            logger.error("create CuratorClient fail !", e);
            throw new IllegalStateException(e.getMessage());
        }
    }

    @Override
    protected void doClose() {
        client.close();
    }

    @Override
    protected void createPersistent(String path) {
        try {
            client.create().forPath(path);
        } catch (KeeperException.NodeExistsException e) {
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    @Override
    protected void createEphemeral(String path) {
        try {
            client.create().withMode(CreateMode.EPHEMERAL).forPath(path);
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    @Override
    protected boolean checkExists(String path) {
        try {
            if (client.checkExists().forPath(path) != null) {
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }

    @Override
    public void delete(String path) {
        try {
            client.delete().forPath(path);
        } catch (KeeperException.NoNodeException e) {
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    @Override
    public List<String> getChildren(String path) {
        try {
            return client.getChildren().forPath(path);
        } catch (KeeperException.NoNodeException e) {
            return null;
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    @Override
    public void removeStateListener(StateListener listener) {

    }

    @Override
    public boolean isConnected() {
        return client.getZookeeperClient().isConnected();
    }
}
