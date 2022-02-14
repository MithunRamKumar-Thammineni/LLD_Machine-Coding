package Splitwise.Service;

import java.util.*;

import Splitwise.Model.User;

public class UserService {
    List<User> userList;

    public UserService(List<User> userList) {
        this.userList = userList;
    }

    public Optional<User> getUser(String username) {
        return userList.stream().filter(user -> user.getName().equals(username)).findFirst();
    }
}
