package soar.netty.server;

import io.netty.channel.ChannelFactory;
import io.netty.channel.ServerChannel;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.kqueue.KQueueServerSocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import soar.common.netty.Server;
import soar.netty.AbstractServer;
import soar.netty.SocketType;

/**
 * AbstractNettyServer
 *
 * @author xiuyuhang [xiuyuhang]
 * @since 2018-04-19
 */
public abstract class AbstractNettyServer extends AbstractServer implements Server {

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

    protected abstract void doClose();

    protected abstract void doOpen();

    protected ChannelFactory<? extends ServerChannel> initServerChannelFactory() {
        SocketType socketType = socketType();
        switch (socketType) {
            case NATIVE_EPOLL:
                return (ChannelFactory<EpollServerSocketChannel>) EpollServerSocketChannel::new;
            case NATIVE_KQUEUE:
                return (ChannelFactory<KQueueServerSocketChannel>) KQueueServerSocketChannel::new;
            case JAVA_NIO:
                return (ChannelFactory<NioServerSocketChannel>) NioServerSocketChannel::new;
            default:
                throw new IllegalStateException("Invalid socket type: " + socketType);
        }
    }
}
