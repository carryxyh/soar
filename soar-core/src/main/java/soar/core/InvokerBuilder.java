package soar.core;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import soar.common.Invoker;
import soar.common.InvokerData;

import java.util.List;
import java.util.Map;

/**
 * InvokerBuilder
 * 1.build a invoker with invoker data
 * TODO remove this function
 *
 * @author xiuyuhang [xiuyuhang]
 * @since 2018-03-14
 */
public final class InvokerBuilder {

    /**
     * build a invoker with given data
     *
     * @param datas server data
     * @return SoarInvoker if {@code datas} size is 1
     * , ClusterInvoker if {@code datas} is all from the same room
     * , RoomRouterInvoker if {@code datas} is from different rooms
     */
    public Invoker build(List<InvokerData> datas) {
        if (CollectionUtils.isEmpty(datas)) {
            throw new IllegalArgumentException();
        }
        if (datas.size() == 1) {
            //direct soar invoker
            return buildSoarInvoker(datas.get(0));
        }

        Map<String, List<InvokerData>> dataMap = fromSameRoom(datas);
        if (dataMap.size() < 1) {
            throw new IllegalStateException("invoke data room size is less than 1, data is : " + JSON.toJSONString(datas));
        }
        if (dataMap.size() == 1) {
            //direct cluster invoker
        }
        return null;
    }

    /**
     * key:room  value:invokerData
     *
     * @param datas invoker datas
     * @return room map
     */
    private Map<String, List<InvokerData>> fromSameRoom(List<InvokerData> datas) {
        Map<String, List<InvokerData>> roomMap = Maps.newHashMap();
        for (InvokerData data : datas) {
            List<InvokerData> l = roomMap.computeIfAbsent(data.getRoom(), k -> Lists.newArrayList());
            l.add(data);
        }
        return roomMap;
    }

    /**
     * direct build a soar invoker
     *
     * @param invokerData invoker data
     * @return SoarInvoker
     */
    private Invoker buildSoarInvoker(InvokerData invokerData) {
        return null;
    }
}
