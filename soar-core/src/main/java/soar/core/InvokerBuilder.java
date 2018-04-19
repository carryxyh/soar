package soar.core;

import org.apache.commons.collections.CollectionUtils;
import soar.common.Invoker;
import soar.common.InvokerData;

import java.util.List;

/**
 * InvokerBuilder
 * 1.build a invoker with invoker data
 *
 * @author xiuyuhang [xiuyuhang]
 * @since 2018-03-14
 */
public final class InvokerBuilder<T> {

    /**
     * build a invoker with given data
     *
     * @param datas server data
     * @return SoarInvoker if {@code datas} size is 1
     * , ClusterInvoker if {@code datas} is all from the same room
     * , RoomRouterInvoker if {@code datas} is from different rooms
     */
    public Invoker<T> build(List<InvokerData> datas) {
        if (CollectionUtils.isEmpty(datas)) {
            throw new IllegalArgumentException();
        }
        return null;
    }
}
