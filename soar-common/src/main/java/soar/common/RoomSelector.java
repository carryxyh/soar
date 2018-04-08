package soar.common;

/**
 * RoomSelector
 *
 * @author xiuyuhang [xiuyuhang]
 * @since 2018-03-29
 */
public interface RoomSelector {

    /**
     * select a invoker (the invoker is an instance of cluster invoker)
     *
     * @param <T> T
     * @return the room router result
     */
    <T> Invoker<T> roomRouter();
}
