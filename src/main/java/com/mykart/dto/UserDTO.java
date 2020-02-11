package com.mykart.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO  {


        private int user_id;
        private String first_name;
        private String last_name;
        private String address;
        private String mobile_no;
        private String email;
        private String password;
        private String cart_id;






}
