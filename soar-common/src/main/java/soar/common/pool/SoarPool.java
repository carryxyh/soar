package soar.common.pool;

import soar.common.SoarConstants;
import soar.common.ThreadPool;

import java.util.concurrent.*;

/**
 * SoarPool
 *
 * @author xiuyuhang [xiuyuhang]
 * @since 2018-03-29
 */
public final class SoarPool implements ThreadPool {

    @Override
    public Executor getExecutor(Integer coreSize, Integer maxThreads, Integer queues, Boolean enhanced) {
        if (enhanced) {
            //enhanced thread pool
            return null;
        } else {
            return new ThreadPoolExecutor(coreSize, coreSize, 0, TimeUnit.MILLISECONDS,
                    queues == 0 ? new SynchronousQueue<Runnable>() :
                            (queues < 0 ? new LinkedBlockingQueue<Runnable>()
                                    : new LinkedBlockingQueue<Runnable>(queues)),
                    new NamedThreadFactory(SoarConstants.SOAR_THREAD_FACTORY_NAME, true));
        }
    }
}
