package soar.core;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;
import soar.common.exception.SoarException;
import soar.common.exception.SoarExceptionCode;

/**
 * ByteBuddyProxyDelegate
 *
 * @author xiuyuhang [xiuyuhang]
 * @since 2018-04-23
 */
public class ByteBuddyProxyDelegate<T> implements ProxyDelegate<T> {

    @Override
    public T newProxy(Class<T> inter, Object handler) {
        Class<? extends T> cls = new ByteBuddy()
                .subclass(inter)
                .method(ElementMatchers.isDeclaredBy(inter))
                .intercept(MethodDelegation.to(handler, "handler"))
                .make()
                .load(inter.getClassLoader(), ClassLoadingStrategy.Default.INJECTION)
                .getLoaded();

        try {
            return cls.newInstance();
        } catch (Throwable t) {
            throw new SoarException(SoarExceptionCode.CREATE_PROXY_FAIL.getCode(), t.getMessage());
        }
    }
}
