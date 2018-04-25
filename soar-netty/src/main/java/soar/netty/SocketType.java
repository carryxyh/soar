package soar.netty;

/**
 * SocketType
 *
 * @author xiuyuhang [xiuyuhang]
 * @since 2018-04-25
 */
public enum SocketType {
    JAVA_NIO,
    NATIVE_EPOLL,   // for linux
    NATIVE_KQUEUE   // for bsd systems
}
