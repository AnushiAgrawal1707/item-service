package com.mykart.service.authentication;

import com.mykart.model.Authentication;
import com.mykart.model.User;
import com.mykart.repository.authentication.AuthenticationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    @Autowired
    private AuthenticationRepository authRepository;

    /**
     * when user trys to login with username and password then system first check if username is exists 
     * @param username
     * @return  Authentication if user with given username exits
     */
    public Authentication getUserByUsername(String username)
    {
        return authRepository.findByUsername(username);
    }

    /**
     *  when users register it will generate username and password and it will get saved into authentication database
     * @param auth Username and Password
     * @return   Username and Password
     */
    public Authentication saveUsername(Authentication auth)
    {
        return authRepository.save(auth);
    }

    /**
     * this service is accessed by admin if user forgets his/her username and password
     * admin find user_id for the given user then it will return username and password
     * @param user_id
     * @return
     */
    public Authentication findById(int user_id )
    {
        return authRepository.findById(user_id);
    }

    /**
     * delete authentication(username and password) if user deletes his/her account
     * @param user_id
     */
    public void deleteById(int user_id )
    {
        authRepository.deleteById(user_id);
    }
}
