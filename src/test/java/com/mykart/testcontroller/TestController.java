package com.mykart.testcontroller;


import com.mykart.controller.user.UserController;
import com.mykart.exception.ResourceNotFound;
import com.mykart.model.User;
import com.mykart.service.user.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TestController {

  @Mock UserService userService;

  @InjectMocks UserController userController;

 /* @Test
  public void getAllUsersTest() {

    User user = mock(User.class);
    List<User> list = new ArrayList<User>();
    list.add(user);
    when(userService.getAllUsers()).thenReturn(list);
    userController.getAllUsers();
    verify(userService, times(1)).getAllUsers();
  }  */

//  @Test
//  public void saveUserTest() throws ResourceNotFound {
//
//    User user = mock(User.class);
//    when(userService.saveUser(user)).thenReturn(user);
//    userController.saveUser(user);
//    verify(userService, times(1)).saveUser(user);
//  }

/*  @Test
  public void getUserByIdTest() throws ResourceNotFound {

    User user = mock(User.class);
    when(userService.getUserById(101)).thenReturn(user);
    userController.getUserById(101);
    verify(userService, times(1)).getUserById(101);
  }      */

  @Test
  public void updateUserTest() throws ResourceNotFound {

    User user = mock(User.class);
    when(userService.getUserById(101)).thenReturn(user);
    when(userService.updateUser(user)).thenReturn(user);
    userController.updateUser(101, user);
    verify(userService, times(1)).updateUser(user);
  }
//   @Test
//   public void deleteUserTest() throws ResourceNotFound {
//      User user = mock(User.class);
//      when(userService.getUserById(101)).thenReturn(user);
//      userService.deleteUser(user);
//      userController.deleteUser(101);
//      verify(userService,times(2)).deleteUser(user);
//
//  }

}

