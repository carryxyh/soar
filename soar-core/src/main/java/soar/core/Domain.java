package soar.core;

/**
 * Domain
 *
 * @author 修宇航 [xiuyuhang]
 * @since 2018-03-26
 */
public interface Domain {

    /**
     * is available
     *
     * @return available if true
     */
    boolean isAvailable();

    /**
     * destroy.
     */
    void destroy();
}
