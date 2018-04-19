package soar.netty.client;

import soar.common.netty.Server;
import soar.netty.AbstractServer;

/**
 * AbstractNettyServer
 *
 * @author xiuyuhang [xiuyuhang]
 * @since 2018-04-19
 */
public abstract class AbstractNettyServer extends AbstractServer implements Server {

    @Override
    public void open() {
        doOpen();
    }

    @Override
    public void close() {
        if (closed) {
            return;
        }
        super.close();
        doClose();
    }

    protected abstract void doClose();

    protected abstract void doOpen();
}
