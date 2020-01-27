package com.mykart.service.user;

import com.mykart.model.User;

import java.util.List;

public interface UserService {
    public Iterable<User> getAllUsers(int page, int size);
    public User getUserById(int user_id);
    public User saveUser(User emp);
    public void deleteUser(User emp);
    public User updateUser(User emp);
   // public  User getUserByFirstNameAndLatName(String first_name,String last_name);

}
