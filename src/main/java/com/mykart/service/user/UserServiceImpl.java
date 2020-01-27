package com.mykart.service.user;

import com.mykart.model.Authentication;
import com.mykart.model.User;
import com.mykart.repository.authentication.AuthenticationRepository;
import com.mykart.repository.user.UserRepository;

import com.mykart.service.authentication.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthenticationService authService;

    /**
     *
     * @param pageNo
     * @param pageSize
     * @return   List of all available user
     */
    @Override
    public Iterable<User> getAllUsers(int pageNo, int pageSize)
    {                           
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<User> users = userRepository.findAll(pageable);
        System.out.println(users);
        if(users.hasContent()) {
            return users.getContent();
        } else {
            return new ArrayList<User>();
        }

    }

    /**
     *
     * @param user_id
     * @return
     */
    @Override
    public User getUserById(int user_id) {
        return userRepository.findById(user_id);
    }

    /**
     *
     * @param user
     * @return
     */
    @Override
    public User saveUser(User user) {
        String result = RandomStringUtils.random(10, 0, 20, true, true, "qw32rfHIJk9iQ8Ud7h0XABC1DeLMnOpRSTvW".toCharArray());
       user.setCart_id(result);
        User savedUser= userRepository.save(user);
        Authentication auth=new Authentication(user.getUser_id(),user.getFirst_name()+user.getUser_id(),user.getPassword());
        authService.saveUsername(auth);
        return savedUser;
    }

    /**
     *
     * @param user
     */
    @Override
    public void deleteUser(User user)
    {   authService.deleteById(user.getUser_id());
        userRepository.delete(user);
    }

    /**
     * 
     * @param user
     * @return
     */
    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    /*@Override
    public User getUserByFirstNameAndLatName(String first_name, String last_name) {
        return  userRepository.findByFirst_NameAndLast_Name(first_name,last_name);
    } */
}
