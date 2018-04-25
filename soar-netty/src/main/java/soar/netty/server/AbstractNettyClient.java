package soar.netty.server;

import io.netty.channel.ChannelFactory;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollSocketChannel;
import io.netty.channel.kqueue.KQueueEventLoopGroup;
import io.netty.channel.kqueue.KQueueServerSocketChannel;
import io.netty.channel.kqueue.KQueueSocketChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import soar.common.netty.Client;
import soar.netty.AbstractServer;
import soar.netty.ClientConfig;
import soar.netty.SocketType;

import java.net.InetSocketAddress;
import java.util.concurrent.ThreadFactory;
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

    protected ChannelFactory<? extends SocketChannel> initChannelFactory() {
        SocketType socketType = socketType();
        switch (socketType) {
            case NATIVE_EPOLL:
                return new ChannelFactory<EpollSocketChannel>() {
                    @Override
                    public EpollSocketChannel newChannel() {
                        return new EpollSocketChannel();
                    }
                };
            case NATIVE_KQUEUE:
                return new ChannelFactory<KQueueSocketChannel>() {
                    @Override
                    public KQueueSocketChannel newChannel() {
                        return new KQueueSocketChannel();
                    }
                };
            case JAVA_NIO:
                return new ChannelFactory<NioSocketChannel>() {
                    @Override
                    public NioSocketChannel newChannel() {
                        return new NioSocketChannel();
                    }
                };
            default:
                throw new IllegalStateException("Invalid socket type: " + socketType);
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
