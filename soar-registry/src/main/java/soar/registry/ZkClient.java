package soar.registry;

import java.util.List;

/**
 * ZkClient
 *
 * @author 修宇航 [xiuyuhang]
 * @since 2018-04-26
 */
public interface ZkClient {

    /**
     * create
     */
    void create(String path, boolean ephemeral);

    /**
     * delete path
     */
    void delete(String path);

    /**
     * get path children nodes
     */
    List<String> getChildren(String path);

    /**
     * add a listener for path's children
     */
    List<String> addChildListener(String path, ChildListener listener);

    /**
     * remove
     */
    void removeChildListener(String path, ChildListener listener);

    /**
     * add state listener
     */
    void addStateListener(StateListener listener);

    /**
     * remove state listener
     */
    void removeStateListener(StateListener listener);

    /**
     * is connected
     */
    boolean isConnected();

    /**
     * close
     */
    void close();
}
