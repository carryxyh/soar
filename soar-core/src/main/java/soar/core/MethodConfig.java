package soar.core;

import soar.cluster.ClusterStrategy;
import soar.common.SoarConstants;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * MethodConfig
 *
 * @author xiuyuhang [xiuyuhang]
 * @since 2018-04-23
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MethodConfig {

    /**
     * @return timeout
     */
    int timeout() default SoarConstants.DEFAULT_TIMEOUT;

    /**
     * @return retry times
     */
    int retry() default SoarConstants.DEFAULT_RETRY_TIMES;

    /**
     * @return cluster strategy
     */
    ClusterStrategy cluster() default ClusterStrategy.FAIL_OVER;
}
