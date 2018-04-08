package soar.common;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * SerializableObj
 *
 * @author xiuyuhang [xiuyuhang]
 * @since 2018-04-08
 */
public abstract class SerializableObj implements Serializable {

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
