package Splitwise.Model;

public class User {
    int user_id;
    String name;
    String email;
    String mobile;

    public User(int user_id, String name, String email, String mobile) {
        this.user_id = user_id;
        this.name = name;
        this.email = email;
        this.mobile = mobile;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
