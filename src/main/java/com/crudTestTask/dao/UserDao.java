package com.crudTestTask.dao;


import com.crudTestTask.user.User;
import java.util.List;

public interface UserDao {

    public void createUser(User user);
    public void updateUser(User user);
    public void deleteUser(int id);
    public User getUserById(int id);
    public List<User> listUser();
    public List<User> listUserBy(User user);
}

