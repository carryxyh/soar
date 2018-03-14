package soar.netty;

/**
 * ServerConfig
 *
 * @author xiuyuhang
 */
public final class ServerConfig {

    /**
     * port
     */
    private int port = 9988;

    /**
     * server boss threads
     */
    private int bossThreads = 2;

    /**
     * server worker threads
     */
    private int workerThreads = Runtime.getRuntime().availableProcessors() * 2 + 1;

    /**
     * max threads
     */
    private int maxThreads = 100;

    /**
     * handler core size
     */
    private int coreSize = 30;

    public static ServerConfig getInstance(int port) {
        ServerConfig config = new ServerConfig();
        config.setPort(port);
        return config;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getBossThreads() {
        return bossThreads;
    }

    public void setBossThreads(int bossThreads) {
        this.bossThreads = bossThreads;
    }

    public int getWorkerThreads() {
        return workerThreads;
    }

    public void setWorkerThreads(int workerThreads) {
        this.workerThreads = workerThreads;
    }

    public int getMaxThreads() {
        return maxThreads;
    }

    public void setMaxThreads(int maxThreads) {
        this.maxThreads = maxThreads;
    }

    public int getCoreSize() {
        return coreSize;
    }

    public void setCoreSize(int coreSize) {
        this.coreSize = coreSize;
    }
}
