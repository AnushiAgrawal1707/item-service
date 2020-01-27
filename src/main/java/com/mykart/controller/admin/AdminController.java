package com.mykart.controller.admin;

import com.mykart.exception.ResourceNotFound;
import com.mykart.model.Authentication;
import com.mykart.model.User;
import com.mykart.security.JwtRequest;
import com.mykart.security.JwtResponse;
import com.mykart.security.JwtTokenUtil;
import com.mykart.security.JwtUserDetailsService;
import com.mykart.service.authentication.AuthenticationService;
import com.mykart.service.user.UserService;
import com.mykart.validators.Identification;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * @author anuja_harane
 * Admin controller has REST API to get all List of Users
 */
@RestController
@RequestMapping("/v1/admin")
@Log4j2
public class AdminController {
    @Autowired
    private UserService service;
    @Autowired
    private AuthenticationService authenticationService;

    /**
     * get the list of all available users from database
     *
     * @return List of Users
     */
    @GetMapping()
    @ApiOperation(value = "Get list of Users", response = List.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Suceess|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!")})
    public Iterable<User> getAllUsers( @ApiParam(value = "page") @RequestParam(value = "page",required = false,defaultValue = "0") int page,
                                   @ApiParam(value = "size") @RequestParam(value = "size",required = false,defaultValue = "5") int size) {
        log.debug("Executed AdminController.getAllUsers() to retrieve all Users data");

        // List<User> users= service.getAllUsers();
        //CollectionModel<User> resources=new CollectionModel<>(users);
        //resources.add(this.entityLinks.linkToCollectionResource(User.class));
        return service.getAllUsers(page,size);
    }
   

}
