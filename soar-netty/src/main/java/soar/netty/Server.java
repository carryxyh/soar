package soar.netty;

/**
 * Server
 *
 * @author 修宇航 [xiuyuhang]
 * @since 2018-03-14
 */
public interface Server {

    /**
     * open server
     *
     * @param config server config
     */
    void doOpen(ServerConfig config);

    /**
     * close server
     */
    void doClose();
}
