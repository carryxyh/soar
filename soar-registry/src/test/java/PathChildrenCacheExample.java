import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

import java.io.UnsupportedEncodingException;

/**
 * PathChildrenCacheExample
 * <p>
 * PathChildrenCache：
 * （1）永久监听指定节点下的节点
 * （2）只能监听指定节点下一级节点的变化，比如说指定节点”/example”, 在下面添加”node1”可以监听到，但是添加”node1/n1”就不能被监听到了
 * （3）可以监听到的事件：节点创建、节点数据的变化、节点删除等
 *
 * @author xiuyuhang [xiuyuhang]
 * @since 2018-03-20
 */
public class PathChildrenCacheExample {

    public static void main(String[] args) throws Exception {
        CuratorFramework client = getClient();
        String parentPath = "/soar";
        String path = "/soar/p1";

        PathChildrenCache pathChildrenCache = new PathChildrenCache(client, parentPath, true);
        pathChildrenCache.start(PathChildrenCache.StartMode.POST_INITIALIZED_EVENT);
        pathChildrenCache.getListenable().addListener((client1, event) -> System.out.println("事件类型：" + event.getType() + "；操作节点：" + event.getData().getPath()));

//        client.delete().guaranteed().forPath(parentPath);
//
        //过一会会自动断掉
        client.create().withMode(CreateMode.EPHEMERAL).forPath(path);
        Thread.sleep(1000); // 此处需留意，如果没有现成睡眠则无法触发监听事件
//        client.delete().forPath(path);

    }

    private static CuratorFramework getClient() throws UnsupportedEncodingException {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .authorization("digest", "1xife@F1FXX".getBytes("utf-8"))
                .connectString("10.165.124.48:2181,10.165.124.50:2181,10.165.124.51:2181")
                .retryPolicy(retryPolicy)
                .sessionTimeoutMs(6000)
                .connectionTimeoutMs(3000)
                .build();
        client.start();
        return client;
    }
}
