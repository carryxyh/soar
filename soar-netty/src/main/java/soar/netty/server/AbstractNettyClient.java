package soar.netty.server;

import soar.netty.Client;

/**
 * AbstractNettyClient
 *
 * @author xiuyuhang [xiuyuhang]
 * @since 2018-03-26
 */
public abstract class AbstractNettyClient implements Client {

    @Override
    public void open() {
        doOpen();
    }

    @Override
    public void close() {
        doClose();
    }

    public abstract void doOpen();

    public abstract void doClose();
}
