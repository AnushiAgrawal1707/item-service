package com.mykart.unit.service;

import com.mykart.model.Authentication;
import com.mykart.model.User;
import com.mykart.repository.authentication.AuthenticationRepository;
import com.mykart.repository.user.UserRepository;
import com.mykart.service.authentication.AuthenticationService;
import com.mykart.service.user.UserService;
import com.mykart.service.user.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceTest {

    @InjectMocks
    private UserServiceImpl userService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private AuthenticationService authenticationService;

    
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);

    }
    @Test
    public void testSaveUser() {
        User user = Mockito.mock(User.class);
        Authentication authentication= Mockito.mock(Authentication.class);
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user);
        Mockito.when(authenticationService.saveUsername(Mockito.any(Authentication.class))).thenReturn(authentication);
        User result = userService.saveUser(user);
        assertEquals(result, user);
    }
    @Test
        public void testUserById() {
        User user = Mockito.mock(User.class);
        Mockito.when(userRepository.findById(101)).thenReturn(user);
        User result = userService.getUserById(101);
        assertEquals(result, user);
    }

    @Test
    public void testUpdateUser() {
        User user = Mockito.mock(User.class);
        Mockito.when(userRepository.save(user)).thenReturn(user);
        User result = userService.saveUser(user);
        assertEquals(result, user);
    }


    @Test
    public void testDeleteEmployee() {
        User user = Mockito.mock(User.class);
       // Mockito.when(userRepository.findById(101)).thenReturn(user);
        userService.deleteUser(user);
        authenticationService.deleteById(user.getUser_id());
        verify(userRepository, times(1)).delete(user);
        
    }

}
