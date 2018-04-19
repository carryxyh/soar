package soar.common.context;

import io.netty.util.concurrent.FastThreadLocal;

/**
 * SoarContext
 * use fast thread local instead of thread local to provide more speed
 *
 * @author xiuyuhang [xiuyuhang]
 * @since 2018-03-14
 */
public class SoarContext {

    private static final FastThreadLocal<SoarContext> LOCAL = new FastThreadLocal<SoarContext>() {
        @Override
        protected SoarContext initialValue() {
            return new SoarContext();
        }
    };
}
