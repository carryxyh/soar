package soar.netty.client;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.DefaultThreadFactory;
import soar.netty.Server;
import soar.netty.ServerConfig;

/**
 * NettyServer
 *
 * @author 修宇航 [xiuyuhang]
 * @since 2018-03-14
 */
public class NettyServer implements Server {

    /**
     * server bootstrap
     */
    private ServerBootstrap serverBootstrap;

    /**
     * netty channel
     */
    private Channel channel;

    /**
     * boss loop group
     */
    private EventLoopGroup bossGroup;

    /**
     * worker loop group
     */
    private EventLoopGroup workerGroup;

    /**
     * server config
     */
    private ServerConfig serverConfig;

    public NettyServer(ServerConfig serverConfig) {
        this.serverConfig = serverConfig;
    }

    public void doOpen(ServerConfig config) {
        serverBootstrap = new ServerBootstrap();
        bossGroup = new NioEventLoopGroup(2, new DefaultThreadFactory("soar-server-boss", true));
        workerGroup = new NioEventLoopGroup(Runtime.getRuntime().availableProcessors() * 2 + 1,
                new DefaultThreadFactory("soar-server-worker", true));
        serverBootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childOption(ChannelOption.TCP_NODELAY, Boolean.TRUE)
                .childOption(ChannelOption.SO_REUSEADDR, Boolean.TRUE)
                .childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        //TODO
                    }
                });

        // bind
        ChannelFuture channelFuture = serverBootstrap.bind(config.getPort());
        channelFuture.syncUninterruptibly();
        channel = channelFuture.channel();
    }

    public void doClose() {

    }

    @Override
    public void open() {

    }

    @Override
    public void close() {

    }
}
