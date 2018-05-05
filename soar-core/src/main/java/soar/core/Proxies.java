package soar.core;

/**
 * Proxies
 *
 * @author xiuyuhang [xiuyuhang]
 * @since 2018-05-05
 */
public enum Proxies {

    BYTE_BUDDY(new ByteBuddyProxyDelegate());

    private final ProxyDelegate delegate;

    Proxies(ProxyDelegate delegate) {
        this.delegate = delegate;
    }

    public static ProxyDelegate getDefault() {
        return BYTE_BUDDY.delegate;
    }
}
