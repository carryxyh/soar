package soar.common.netty;

/**
 * Client
 *
 * @author 修宇航 [xiuyuhang]
 * @since 2018-03-26
 */
public interface Client extends Server {

    /**
     * reconnect
     */
    void reconnect();

    /**
     * disconnect
     */
    void disconnect();

    /**
     * connect to remote
     */
    void connect();

    /**
     * send a message
     *
     * @param message obj
     */
    void send(Object message);

    /**
     * client is connect or not
     *
     * @return connect if true
     */
    boolean isConnect();
}
