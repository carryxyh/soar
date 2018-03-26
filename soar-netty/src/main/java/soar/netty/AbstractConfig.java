package soar.netty;

/**
 * AbstractConfig
 *
 * @author xiuyuhang [xiuyuhang]
 * @since 2018-03-26
 */
public abstract class AbstractConfig {

    /**
     * machine room info
     */
    private String room = System.getProperty("soar.room", "");

    /**
     * tag . for soft separate
     */
    private String tag = System.getProperty("soar.tag", "");

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
