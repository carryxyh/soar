package soar.registry;

import soar.common.registry.RegistryListener;

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

    /**
     * subscribe a path
     *
     * @param path     path
     * @param listener listener
     */
    void subscribe(String path, RegistryListener listener);
}
