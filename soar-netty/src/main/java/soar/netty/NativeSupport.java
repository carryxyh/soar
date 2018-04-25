package soar.netty;

import io.netty.channel.epoll.Epoll;
import io.netty.channel.kqueue.KQueue;

public final class NativeSupport {

    /**
     * The native socket transport for Linux using JNI.
     */
    public static boolean isNativeEPollAvailable() {
        return Epoll.isAvailable();
    }

    /**
     * The native socket transport for BSD systems such as MacOS using JNI.
     */
    public static boolean isNativeKQueueAvailable() {
        return KQueue.isAvailable();
    }
}