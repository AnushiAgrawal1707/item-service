package com.mykart.controller.user;

import com.mykart.dto.UserDTO;
import com.mykart.exception.ResourceNotFound;
import com.mykart.model.Cart;
import com.mykart.model.User;
import com.mykart.service.user.UserService;
import com.mykart.validators.Identification;
import io.swagger.annotations.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * @author anuja_harane 
 */
@RestController
@RequestMapping("/v1/users")
@Log4j2
@Validated
@Api(value = "User Data Service",
        description = "Operations pertaining to User in User Data Service")
public class UserController {

    

    @Autowired
    private UserService service;

    
    /**
     * to save User data into database
     *
     * @param userDTO User Object that is going to persist into database
     * @return user return User Object
     *
     *
     */
    @PostMapping("/register")
    @ApiOperation(value = "Save User object into the database", response = UserDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Suceess|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!")})
    public UserDTO saveUser(
            @ApiParam(value = "User object", required = true) @RequestBody UserDTO userDTO)  {
        log.debug("Executed UserController.saveUsers(user) to save User object");
        User user = new User();
        BeanUtils.copyProperties(userDTO,user);
        user=service.saveUser(user);
        BeanUtils.copyProperties(user,userDTO);
        return userDTO;

    }




    /**
     * Delete User with given id, it will first check whether User with given identifier is
     * exits into database or not
     *
     * @param user_id identifier of the User you want to delete
     *
     * @throws ResourceNotFound If system doesn't find any User with given identifier then it will
     *         throw ResourceNotFound Exception
     */
    @ApiOperation(value = "Delete User by id")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Suceess|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!")})
    @DeleteMapping("/delete/{user_id}")
    public ResponseEntity<Object> deleteUser(
            @ApiParam(value = "User id", required = true) @PathVariable("user_id") @Identification int user_id)
            throws ResourceNotFound {
        User user = service.getUserById(user_id);
        log.debug(
                "Executed UserController.deleteUser(id) to delete User object with id " + user_id);
        if (user == null)
            throw new ResourceNotFound("Resource Not Found");
        else {
            service.deleteUser(user);
            return new ResponseEntity<Object>(HttpStatus.OK);
        }

    }

    /**
     * update the existing User into the database, it will first check whether User with given
     * identifier is exits into database or not
     *
     * @param user_id identifier of the User you want to update
     * @param  userDTO User object with the data you want to update
     * @return updated return updated User object
     * @throws ResourceNotFound If User with the specified id is not exits then it will throw
     *         ResorceNotFound Exception
     */
    @ApiOperation(value = "Update User by id")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Suceess|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!")})
    @PutMapping("update/{user_id}")
    public ResponseEntity<Object> updateUser(
            @ApiParam(value = "User id", required = true) @PathVariable("user_id") @Identification int user_id,
            @ApiParam(value = "User object", required = true) @RequestBody UserDTO userDTO)
            throws ResourceNotFound {
        User user=service.getUserById(user_id);
        log.debug("Executed UserController.updateUser(id,user) to update User object with id"
                + user_id);
        if(user==null)
            throw new ResourceNotFound();
        user.setFirst_name(userDTO.getFirst_name());
        user.setLast_name(userDTO.getLast_name());
        service.updateUser(user);
        BeanUtils.copyProperties(user,userDTO);
        return new ResponseEntity<Object>(userDTO,HttpStatus.OK);
        }

    }



