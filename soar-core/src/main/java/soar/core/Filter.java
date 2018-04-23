package soar.core;

/**
 * Filter
 *
 * @author xiuyuhang [xiuyuhang]
 * @since 2018-03-14
 */
public interface Filter {

    /**
     * @return filter priority
     */
    int priority();

    /**
     * @return filter name
     */
    String name();
}
