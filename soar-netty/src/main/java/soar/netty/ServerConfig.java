package soar.netty;

/**
 * ServerConfig
 * 隔离方式：
 * tag -> room
 * 同tag下的所有服务直接连接，出问题后同机房容错，机房错误率高之后切换
 *
 * @author xiuyuhang
 */
public final class ServerConfig extends AbstractConfig {

    /**
     * port
     */
    private Integer port = 9988;

    /**
     * server boss threads
     */
    private Integer bossThreads = 2;

    /**
     * server worker threads
     */
    private Integer workerThreads = Runtime.getRuntime().availableProcessors() * 2 + 1;

    /**
     * max threads
     */
    private Integer maxThreads = 100;

    /**
     * handler core size
     */
    private Integer coreSize = 30;


    /**
     * use enhanced pool pool
     * if false use fixed pool pool
     */
    private Boolean useEnhancedPool = true;

    private ServerConfig() {
    }

    public static ServerConfig getInstance(Integer port) {
        ServerConfig config = new ServerConfig();
        config.setPort(port);
        return config;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public Integer getBossThreads() {
        return bossThreads;
    }

    public void setBossThreads(Integer bossThreads) {
        this.bossThreads = bossThreads;
    }

    public Integer getWorkerThreads() {
        return workerThreads;
    }

    public void setWorkerThreads(Integer workerThreads) {
        this.workerThreads = workerThreads;
    }

    public Integer getMaxThreads() {
        return maxThreads;
    }

    public void setMaxThreads(Integer maxThreads) {
        this.maxThreads = maxThreads;
    }

    public Integer getCoreSize() {
        return coreSize;
    }

    public void setCoreSize(Integer coreSize) {
        this.coreSize = coreSize;
    }

    public Boolean isUseEnhancedPool() {
        return useEnhancedPool;
    }

    public void setUseEnhancedPool(Boolean useEnhancedPool) {
        this.useEnhancedPool = useEnhancedPool;
    }
}
