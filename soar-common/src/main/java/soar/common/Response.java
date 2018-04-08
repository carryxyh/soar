package soar.common;

import java.io.Serializable;

/**
 * Response
 *
 * @author xiuyuhang [xiuyuhang]
 * @since 2018-03-14
 */
public interface Response extends Serializable {

    /**
     * Get invoke result.
     *
     * @return result. if no result return null.
     */
    Object getValue();

    /**
     * Get exception.
     *
     * @return exception. if no exception return null.
     */
    Throwable getException();

    /**
     * Has exception.
     *
     * @return has exception.
     */
    boolean hasException();
}
