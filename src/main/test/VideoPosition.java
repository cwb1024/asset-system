import java.util.Date;

public class VideoPosition {

    private String id;
    private String videoId;
    private Date timeNode;
    private String nodeDesc;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public Date getTimeNode() {
        return timeNode;
    }

    public void setTimeNode(Date timeNode) {
        this.timeNode = timeNode;
    }

    public String getNodeDesc() {
        return nodeDesc;
    }

    public void setNodeDesc(String nodeDesc) {
        this.nodeDesc = nodeDesc;
    }
}
