import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.api.CuratorListener;
import org.apache.curator.framework.api.CuratorWatcher;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.framework.state.ConnectionStateListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;

import java.io.UnsupportedEncodingException;

/**
 * PathChildrenCacheExample
 * <p>
 * PathChildrenCache：
 * （1）永久监听指定节点下的节点
 * （2）只能监听指定节点下一级节点的变化，比如说指定节点”/example”, 在下面添加”node1”可以监听到，但是添加”node1/n1”就不能被监听到了
 * （3）可以监听到的事件：节点创建、节点数据的变化、节点删除等
 * <p>
 * 监听指定节点的子节点的变更包括添加，删除，子节点数据数据变更这三类。
 *
 * @author xiuyuhang [xiuyuhang]
 * @since 2018-03-20
 */
public class PathChildrenCacheExample {

    public static void main(String[] args) throws Exception {
        CuratorFramework client = getClient();
//        String parentPath = "/soar";
        String path = "/soar/service1";

        PathChildrenCache pathChildrenCache = new PathChildrenCache(client, path, true);
        pathChildrenCache.start(PathChildrenCache.StartMode.POST_INITIALIZED_EVENT);

        //guaranteed保证节点被删除
//        client.delete().guaranteed().forPath(parentPath);
        //checkExists 如果返回数据 说明节点存在  为null则不存在
//        if (client.checkExists().forPath(parentPath) == null) {
//            client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath(parentPath);
//        }

        if (client.checkExists().forPath(path) == null) {
            client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath(path);
            client.setData().forPath(path, "test".getBytes("utf-8"));
        }

        Thread.sleep(1000);

        System.out.println(client.getChildren().usingWatcher(new CuratorWatcher() {

            @Override
            public void process(WatchedEvent event) throws Exception {
                System.out.println(event.getPath());
                System.out.println(event.getType());
                System.out.println(event.getState());
            }
            //这里 /soar是父节点，service1是子节点.
        }).forPath("/soar"));


//        Thread.sleep(1000);
//
//        //过一会会自动断掉 会删除这个子节点
//        client.create().withMode(CreateMode.EPHEMERAL).forPath(path);
//        Thread.sleep(1000); // 此处需留意，如果没有现成睡眠则无法触发监听事件  主要应该是因为是异步的原因
//        client.delete().forPath(path);
        //节点设置数据同样会触发监听器
//        client.setData().forPath(path, "test data".getBytes("utf-8"));
        Thread.sleep(2000);

        pathChildrenCache.getListenable().addListener(new PathChildrenCacheListener() {
            @Override
            public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception {
                System.out.println("事件类型：" + event.getType() + "；操作节点：" + event.getData().getPath());
                System.out.println(new String(event.getData().getData()));
            }
        });

        Thread.sleep(5000);
    }

    public static CuratorFramework getClient() throws UnsupportedEncodingException {
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
