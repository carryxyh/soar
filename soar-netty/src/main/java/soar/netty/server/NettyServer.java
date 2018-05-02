package soar.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.DefaultThreadFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import soar.common.utils.NetUtils;
import soar.netty.ServerConfig;

import java.net.InetSocketAddress;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * NettyServer
 *
 * @author xiuyuhang [xiuyuhang]
 * @since 2018-03-14
 */
public class NettyServer extends AbstractNettyServer {

    private static final Logger logger = LoggerFactory.getLogger(NetUtils.class);

    /**
     * server bootstrap
     */
    private ServerBootstrap serverBootstrap;

    /**
     * netty channel
     */
    private Channel channel;

    /**
     * remoting channels key{ip:port} value:{channel}
     */
    private Map<String, Channel> channels;

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

    @Override
    public void doOpen() {

        //TODO init executor

        serverBootstrap = new ServerBootstrap();
        bossGroup = initEventLoopGroup(serverConfig.getBossThreads(), new DefaultThreadFactory("soar-server-boss", true));
        workerGroup = initEventLoopGroup(serverConfig.getWorkerThreads(), new DefaultThreadFactory("soar-server-worker", true));
        serverBootstrap.group(bossGroup, workerGroup)
                .channelFactory(initServerChannelFactory())
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

    @Override
    public void doClose() {
        try {
            //close channel
            if (channel != null) {
                channel.close();
            }

            //close channel which connect to client
            Set<Channel> remoteChannel = allConnectedChannel();
            for (Channel c : remoteChannel) {
                c.close();
            }

            //close server
            if (serverBootstrap != null) {
                bossGroup.shutdownGracefully();
                workerGroup.shutdownGracefully();
            }

            //clear channels
            if (channels != null) {
                channels.clear();
            }
        } catch (Exception e) {
            logger.warn("server close error!", e);
        }
    }

    private Set<Channel> allConnectedChannel() {
        Set<Channel> chs = new HashSet<Channel>();
        for (Channel channel : this.channels.values()) {
            if (channel.isActive()) {
                chs.add(channel);
            } else {
                channels.remove(NetUtils.toAddressString((InetSocketAddress) channel.remoteAddress()));
            }
        }
        return chs;
    }
}
