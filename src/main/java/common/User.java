package common;

public class User {
    private String id;
    private String name;
    private String ipAddress;

    public User(String channelId, String userName, String ipAddress) {
        this.id=channelId;
        this.name = userName;
        this.ipAddress = ipAddress;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
}
