package soar.registry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import soar.common.collection.ConcurrentSet;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * AbstractZkClient
 *
 * @author xiuyuhang [xiuyuhang]
 * @since 2018-04-26
 */
public abstract class AbstractZkClient implements ZkClient {

    protected static final Logger logger = LoggerFactory.getLogger(AbstractZkClient.class);

    private ConcurrentSet<StateListener> stateListeners = new ConcurrentSet<>();

    private final ConcurrentMap<String, List<ChildListener>> childListeners = new ConcurrentHashMap<>();

    private volatile boolean closed = false;

    @Override
    public void close() {
        if (closed) {
            return;
        }
        closed = true;
        try {
            doClose();
        } catch (Throwable t) {
            logger.warn(t.getMessage(), t);
        }
    }

    protected abstract void doClose();
}
