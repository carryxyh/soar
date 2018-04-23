package soar.core;

/**
 * ProxyDelegate
 *
 * @author xiuyuhang [xiuyuhang]
 * @since 2018-04-23
 */
public interface ProxyDelegate<T> {

    /**
     * create a new proxy with handler
     *
     * @param inter   interface
     * @param handler handler
     * @return proxy
     */
    T newProxy(Class<T> inter, Object handler);
}
