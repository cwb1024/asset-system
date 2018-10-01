public class VideoBase {

    private String id;
    private String original_name;
    private String caption;
    private String cover;
    private String video_group_id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOriginal_name() {
        return original_name;
    }

    public void setOriginal_name(String original_name) {
        this.original_name = original_name;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getVideo_group_id() {
        return video_group_id;
    }

    public void setVideo_group_id(String video_group_id) {
        this.video_group_id = video_group_id;
    }
}
