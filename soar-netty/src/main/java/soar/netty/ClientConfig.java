package soar.netty;

/**
 * ClientConfig
 *
 * @author xiuyuhang [xiuyuhang]
 * @since 2018-03-26
 */
public final class ClientConfig extends AbstractConfig {

    /**
     * client time out
     */
    private Integer timeout = 3000;

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }
}
