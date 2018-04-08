package soar.common;

/**
 * InvokerData
 * the provider data in zk
 *
 * @author xiuyuhang [xiuyuhang]
 * @since 2018-03-29
 */
public final class InvokerData extends SerializableObj {

    private static final long serialVersionUID = -4623888574784520442L;

    /**
     * host
     */
    private String host;

    /**
     * port
     */
    private Integer port = 9988;

    /**
     * service
     */
    private String service;

    /**
     * room
     */
    private String room = "";

    /**
     * tag
     */
    private String tag;

    /**
     * the provider`s weight
     */
    private Integer weight = 1;

    /**
     * timeout
     */
    private Integer timeOut = 3000;

    /**
     * extra info from provider
     */
    private String extraInfo;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(Integer timeOut) {
        this.timeOut = timeOut;
    }

    public String getExtraInfo() {
        return extraInfo;
    }

    public void setExtraInfo(String extraInfo) {
        this.extraInfo = extraInfo;
    }
}
