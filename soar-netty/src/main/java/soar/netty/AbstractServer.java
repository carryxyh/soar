package soar.netty;

import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.kqueue.KQueueEventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import soar.common.netty.Server;

import java.util.concurrent.ThreadFactory;

/**
 * AbstractServer
 *
 * @author xiuyuhang [xiuyuhang]
 * @since 2018-04-19
 */
public abstract class AbstractServer implements Server {

    /**
     * closed
     */
    protected volatile boolean closed;

    protected SocketType socketType() {
        if (NativeSupport.isNativeEPollAvailable()) {
            // netty provides the native socket transport for Linux using JNI.
            return SocketType.NATIVE_EPOLL;
        }
        if (NativeSupport.isNativeKQueueAvailable()) {
            // netty provides the native socket transport for BSD systems such as MacOS using JNI.
            return SocketType.NATIVE_KQUEUE;
        }
        return SocketType.JAVA_NIO;
    }

    protected EventLoopGroup initEventLoopGroup(int nThreads, ThreadFactory tFactory) {
        SocketType socketType = socketType();
        switch (socketType) {
            case NATIVE_EPOLL:
                return new EpollEventLoopGroup(nThreads, tFactory);
            case NATIVE_KQUEUE:
                return new KQueueEventLoopGroup(nThreads, tFactory);
            case JAVA_NIO:
                return new NioEventLoopGroup(nThreads, tFactory);
            default:
                throw new IllegalStateException("Invalid socket type: " + socketType);
        }
    }

    @Override
    public void close() {
        closed = true;
    }
}
