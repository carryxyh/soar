package soar.registry;

/**
 * Registry
 *
 * @author 修宇航 [xiuyuhang]
 * @since 2018-03-14
 */
public interface Registry {

    /**
     * registry a path to center
     *
     * @param path the registry path
     */
    void registry(String path);

    /**
     * unregistry a path
     *
     * @param path the path
     */
    void unRegistry(String path);
}
