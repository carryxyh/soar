package soar.common;

import java.io.Serializable;

/**
 * Request
 *
 * @author xiuyuhang [xiuyuhang]
 * @since 2018-03-14
 */
public interface Request extends Serializable {

    /**
     * get method name.
     *
     * @return method name.
     */
    String getMethodName();

    /**
     * get parameter types.
     *
     * @return parameter types.
     */
    Class<?>[] getParameterTypes();

    /**
     * get arguments.
     *
     * @return arguments.
     */
    Object[] getArguments();
}
