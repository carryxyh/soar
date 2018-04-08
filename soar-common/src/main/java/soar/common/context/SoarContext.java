package soar.common.context;

/**
 * SoarContext
 *
 * @author xiuyuhang [xiuyuhang]
 * @since 2018-03-14
 */
public class SoarContext {

    private static final ThreadLocal<SoarContext> LOCAL = new ThreadLocal<SoarContext>() {
        @Override
        protected SoarContext initialValue() {
            return new SoarContext();
        }
    };
}
