package com.mykart.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;


/**
 *
 *
 */
@Getter
@Setter
public class UserDTO  {


        private int user_id;
        private String first_name;
        private String last_name;
        private String address;
        private String mobile_no;
        private String email;
        private String password;
        private int cart_id;






}
