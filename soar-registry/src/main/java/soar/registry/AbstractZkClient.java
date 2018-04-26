package soar.registry;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import soar.common.collection.ConcurrentSet;

import java.util.List;
import java.util.Set;
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

    private final ConcurrentMap<String, ConcurrentSet<ChildListener>> childListeners = new ConcurrentHashMap<>();

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

    private Set<StateListener> getSessionListeners() {
        return stateListeners;
    }

    protected void stateChanged(int state) {
        for (StateListener sessionListener : getSessionListeners()) {
            sessionListener.stateChanged(state);
        }
    }

    @Override
    public List<String> addChildListener(String path, ChildListener listener) {
        ConcurrentSet<ChildListener> s = childListeners.get(path);
        if (s == null) {
            s = new ConcurrentSet<>();
        }
        s.add(listener);
        return null;
    }

    @Override
    public void removeChildListener(String path, ChildListener listener) {
        ConcurrentSet<ChildListener> s = childListeners.get(path);
        if (CollectionUtils.isNotEmpty(s)) {
            s.remove(listener);
        }
    }

    @Override
    public void addStateListener(StateListener listener) {
        stateListeners.add(listener);
    }

    @Override
    public void create(String path, boolean ephemeral) {
        //TODO check parent path
        if (ephemeral) {
            createEphemeral(path);
        } else {
            createPersistent(path);
        }
    }

    protected abstract void doClose();

    protected abstract void createPersistent(String path);

    protected abstract void createEphemeral(String path);

    protected abstract boolean checkExists(String path);

}
