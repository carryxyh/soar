package soar.netty.server;

import soar.common.netty.Client;
import soar.netty.AbstractServer;
import soar.netty.ClientConfig;

import java.net.InetSocketAddress;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * AbstractNettyClient
 *
 * @author xiuyuhang [xiuyuhang]
 * @since 2018-03-26
 */
public abstract class AbstractNettyClient extends AbstractServer implements Client {

    /**
     * connect lock
     */
    protected final Lock connectLock = new ReentrantLock();

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
        if (closed) {
            return;
        }
        super.close();
        doClose();
    }

    @Override
    public void reconnect() {
        close();
        connect();
    }

    public abstract void doOpen();

    public abstract void doClose();

    public abstract void doConnect();

    public abstract void doDisConnect();

    @Override
    public void disconnect() {
        connectLock.lock();
        try {
            if (!isConnect()) {
                return;
            }
            doDisConnect();
        } finally {
            connectLock.unlock();
        }
    }

    @Override
    public void connect() {
        connectLock.lock();
        try {
            if (isConnect()) {
                return;
            }
            doConnect();
        } finally {
            connectLock.unlock();
        }
    }


    protected String getRemoteServer() {
        return clientConfig.getServerHost();
    }

    protected Integer getRemotePort() {
        return clientConfig.getPort();
    }

    protected InetSocketAddress getConnectAddress() {
        return new InetSocketAddress(getRemoteServer(), getRemotePort());
    }
}
