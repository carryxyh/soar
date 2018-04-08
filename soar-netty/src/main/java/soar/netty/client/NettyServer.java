package soar.netty.client;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.DefaultThreadFactory;
import soar.common.netty.Server;
import soar.netty.ServerConfig;

/**
 * NettyServer
 *
 * @author xiuyuhang [xiuyuhang]
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

    public void doOpen() {

        //TODO init executor

        serverBootstrap = new ServerBootstrap();
        bossGroup = new NioEventLoopGroup(serverConfig.getBossThreads(), new DefaultThreadFactory("soar-server-boss", true));
        workerGroup = new NioEventLoopGroup(serverConfig.getWorkerThreads(),
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
        ChannelFuture channelFuture = serverBootstrap.bind(serverConfig.getPort());
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
        if (channel != null) {
            channel.close();
        }
        if (serverBootstrap != null) {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
