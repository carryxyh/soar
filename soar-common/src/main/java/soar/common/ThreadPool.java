package soar.common;

import java.util.concurrent.Executor;

/**
 * ThreadPool
 *
 * @author 修宇航 [xiuyuhang]
 * @since 2018-03-26
 */
public interface ThreadPool {

    /**
     * get a thread pool for server works
     *
     * @param coreSize   core size
     * @param maxThreads max threads
     * @param enhanced   use enhanced thread pool if true , otherwise use fixed thread pool
     * @return the executor
     */
    Executor getExecutor(Integer coreSize, Integer maxThreads, Boolean enhanced);
}
