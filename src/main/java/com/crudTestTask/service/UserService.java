package com.crudTestTask.service;

import com.crudTestTask.user.User;
import java.util.List;

public interface UserService {
    public void addUser(User user);
    public void updateUser(User user);
    public void removeUser (int id);
    public User getUserById(int id);
    public List<User> listUser();
    public List<User> listUserBy(User user);
}
