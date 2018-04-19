package soar.netty;

import soar.common.SoarConstants;

/**
 * AbstractConfig
 *
 * @author xiuyuhang [xiuyuhang]
 * @since 2018-03-26
 */
public abstract class AbstractConfig {

    /**
     * port
     */
    private Integer port = 9988;

    /**
     * machine room info
     */
    private String room = System.getProperty("soar.room", SoarConstants.DEFAULT_ROOM);

    /**
     * tag . for soft separate
     */
    private String tag = System.getProperty("soar.tag", SoarConstants.DEFAULT_TAG);

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
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
}
