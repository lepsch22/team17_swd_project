/**
 * User class used to make User rows in table view
 */
public class User {
    private String username;
    private String vaccine;
    private String first;
    private String last;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getVaccine() {
        return vaccine;
    }

    public void setVaccine(String vaccine) {
        this.vaccine = vaccine;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }


    public User(String username,String first,String last, String vaccine){
        this.first = first;
        this.username = username;
        this.last = last;
        this.vaccine = vaccine;
        
        
    }
}
