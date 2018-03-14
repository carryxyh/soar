package soar.netty.server;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import soar.netty.Server;

/**
 * NettyClient
 *
 * @author xiuyuhang [xiuyuhang]
 * @since 2018-03-14
 */
public class NettyClient implements Server {

    /**
     * client bootstrap
     */
    private Bootstrap bootstrap;

    /**
     * client loop group
     */
    private EventLoopGroup eventLoopGroup;

    public void doOpen() {

        eventLoopGroup = new NioEventLoopGroup(Runtime.getRuntime().availableProcessors());

        bootstrap = new Bootstrap();
        bootstrap.group(eventLoopGroup)
                .option(ChannelOption.SO_KEEPALIVE, Boolean.TRUE)
                .option(ChannelOption.TCP_NODELAY, Boolean.TRUE)
                .option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 3000)
                .channel(NioSocketChannel.class);
//        if (config.getTimeout() < 3000) {
//            bootstrap.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 3000);
//        } else {

//        }

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
    public void open() {
        doOpen();
    }

    @Override
    public void close() {
        doClose();
    }
}
