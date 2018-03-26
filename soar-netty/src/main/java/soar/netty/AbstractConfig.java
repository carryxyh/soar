package soar.netty;

/**
 * AbstractConfig
 *
 * @author xiuyuhang [xiuyuhang]
 * @since 2018-03-26
 */
public abstract class AbstractConfig {

    private static final String DEFAULT_ROOM = "SOAR_DEFAULT_ROOM";

    private static final String DEFAULT_TAG = "SOAR_DEFAULT_TAG";

    /**
     * machine room info
     */
    private String room = System.getProperty("soar.room", DEFAULT_ROOM);

    /**
     * tag . for soft separate
     */
    private String tag = System.getProperty("soar.tag", DEFAULT_TAG);

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
