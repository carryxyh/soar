package soar.netty;

import soar.common.netty.Server;

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

    @Override
    public void close() {
        closed = true;
    }
}
