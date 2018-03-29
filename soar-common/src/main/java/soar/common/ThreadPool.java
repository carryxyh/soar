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
     * get a pool pool for server works
     *
     * @param coreSize   core size
     * @param maxThreads max threads
     * @param queues     queues capacity
     * @param enhanced   use enhanced pool pool if true , otherwise use fixed pool pool
     * @return the executor
     */
    Executor getExecutor(Integer coreSize, Integer maxThreads, Integer queues, Boolean enhanced);
}
