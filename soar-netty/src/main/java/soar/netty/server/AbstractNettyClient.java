package soar.netty.server;

import soar.common.netty.Client;
import soar.netty.ClientConfig;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * AbstractNettyClient
 *
 * @author xiuyuhang [xiuyuhang]
 * @since 2018-03-26
 */
public abstract class AbstractNettyClient implements Client {

    /**
     * connect lock
     */
    private final Lock connectLock = new ReentrantLock();

    /**
     * closed
     */
    protected volatile boolean closed;

    protected final ClientConfig clientConfig;

    protected AbstractNettyClient(ClientConfig clientConfig) {
        this.clientConfig = clientConfig;
    }

    @Override
    public void open() {
        doOpen();
    }

    @Override
    public void close() {
        doClose();
    }

    public abstract void doOpen();

    public abstract void doClose();
}
