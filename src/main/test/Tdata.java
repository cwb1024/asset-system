public class Tdata {

    private String name;
    private Integer type;
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Tdata{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", email='" + email + '\'' +
                '}';
    }
}
