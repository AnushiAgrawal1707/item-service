package com.mykart.unit.service;

import com.mykart.model.Authentication;
import com.mykart.model.User;
import com.mykart.repository.authentication.AuthenticationRepository;
import com.mykart.repository.user.UserRepository;
import com.mykart.service.authentication.AuthenticationService;
import com.mykart.service.user.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringJUnit4ClassRunner.class)
public class AuthenticationServiceTest {
    @InjectMocks
    private AuthenticationService authenticationService;
    @Mock
    private AuthenticationRepository authenticationRepository;
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);

    }
    @Test
    public void testSaveUsername() {
        Authentication authentication= Mockito.mock(Authentication.class);
        Mockito.when(authenticationRepository.save(Mockito.any(Authentication.class))).thenReturn(authentication);
        Authentication result = authenticationService.saveUsername(authentication);
        assertEquals(result, authentication);
    }
    @Test
    public void testGetByUsername() {
        Authentication authentication= Mockito.mock(Authentication.class);
        Mockito.when(authenticationRepository.findByUsername("anuja")).thenReturn(authentication);
        Authentication result = authenticationService.getUserByUsername("anuja");
        assertEquals(result, authentication);
    }
    @Test
    public void testDeleteById() {
        Authentication authentication= Mockito.mock(Authentication.class);
        // Mockito.when(userRepository.findById(101)).thenReturn(user);
        authenticationService.deleteById(authentication.getUser_id());
        verify(authenticationRepository, times(1)).deleteById(authentication.getUser_id());
    }

}
