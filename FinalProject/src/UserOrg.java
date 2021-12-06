/**
 * UserOg to make tables form organization database in table views.
 */
public class UserOrg {
    private String userName;

    public String getUser() {
        return userName;
    }

    public void setUser(String user) {
        this.userName = user;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    private String orgName;
    private  String location;

    public UserOrg(String user, String orgName, String location) {
        this.userName = user;
        this.orgName = orgName;
        this.location = location;
    }
    public UserOrg(String orgName, String location) {
        this.orgName = orgName;
        this.location = location;
    }


    
}
