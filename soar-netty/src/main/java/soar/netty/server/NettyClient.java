package soar.netty.server;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import soar.common.exception.SoarException;
import soar.common.exception.SoarExceptionCode;
import soar.netty.ClientConfig;

import java.util.concurrent.TimeUnit;

/**
 * NettyClient
 *
 * @author xiuyuhang [xiuyuhang]
 * @since 2018-03-14
 */
public class NettyClient extends AbstractNettyClient {

    /**
     * client bootstrap
     */
    private Bootstrap bootstrap;

    /**
     * client loop group
     */
    private EventLoopGroup eventLoopGroup;

    private volatile Channel channel;

    public NettyClient(ClientConfig clientConfig) {
        super(clientConfig);
    }

    public void doOpen() {

        eventLoopGroup = new NioEventLoopGroup(Runtime.getRuntime().availableProcessors());

        bootstrap = new Bootstrap();
        bootstrap.group(eventLoopGroup)
                .option(ChannelOption.SO_KEEPALIVE, Boolean.TRUE)
                .option(ChannelOption.TCP_NODELAY, Boolean.TRUE)
                .option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, clientConfig.getTimeout())
                .channel(NioSocketChannel.class);
        if (clientConfig.getTimeout() < 3000) {
            bootstrap.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, clientConfig.getTimeout());
        } else {
            bootstrap.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 3000);
        }

        bootstrap.handler(new ChannelInitializer() {

            @Override
            protected void initChannel(Channel ch) throws Exception {
                //TODO
            }
        });
    }

    public void doClose() {
        if (eventLoopGroup != null) {
            eventLoopGroup.shutdownGracefully();
        }
    }

    @Override
    public void doConnect() {
        ChannelFuture future = bootstrap.connect(getConnectAddress());
        boolean ret = future.awaitUninterruptibly(3000, TimeUnit.MILLISECONDS);
        if (ret && future.isSuccess()) {
            Channel newChannel = future.channel();

            Channel oldChannel = this.channel; // copy reference
            if (oldChannel != null) {
                oldChannel.close();
            }

            this.channel = newChannel;
        } else if (future.cause() != null) {
            throw new SoarException(SoarExceptionCode.CONNECT_REMOTE_SERVER_FAIL.getCode(), future.cause().getMessage(), future.cause());
        } else {
            throw new SoarException(SoarExceptionCode.CONNECT_REMOTE_SERVER_FAIL.getCode(), SoarExceptionCode.CONNECT_REMOTE_SERVER_FAIL.getDesc());
        }
    }

    @Override
    public void doDisConnect() {
        Channel channel = this.channel;
        if (channel != null) {
            channel.close();
        }
    }

    @Override
    public void send(Object message) {

    }

    @Override
    public boolean isConnect() {
        return !closed && channel.isActive();
    }
}
