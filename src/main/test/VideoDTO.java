import java.util.List;

public class VideoDTO {

    private List<VideoBase> videoList;
    private Integer adopted;
    private List<Kh> labelList;
    private List<VideoPosition> nodeList;
    private String otherLabel;

    public List<VideoBase> getVideoList() {
        return videoList;
    }

    public void setVideoList(List<VideoBase> videoList) {
        this.videoList = videoList;
    }

    public Integer getAdopted() {
        return adopted;
    }

    public void setAdopted(Integer adopted) {
        this.adopted = adopted;
    }

    public List<Kh> getLabelList() {
        return labelList;
    }

    public void setLabelList(List<Kh> labelList) {
        this.labelList = labelList;
    }

    public List<VideoPosition> getNodeList() {
        return nodeList;
    }

    public void setNodeList(List<VideoPosition> nodeList) {
        this.nodeList = nodeList;
    }

    public String getOtherLabel() {
        return otherLabel;
    }

    public void setOtherLabel(String otherLabel) {
        this.otherLabel = otherLabel;
    }
}
