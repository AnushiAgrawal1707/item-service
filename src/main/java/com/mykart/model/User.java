package com.mykart.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mykart.validators.number.Phone;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "user")
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@AllArgsConstructor
@NoArgsConstructor
public class User  {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="user_id")
    private int user_id;
    @NotNull
    @Column(name="first_name")
    private String first_name;
    @Column(name="last_name")
    private String last_name;
    @NotNull
    @Column(name="address")
    private String address;
    @NotNull
    @Column(name="mobile_no")
    //@Phone
    private String mobile_no;
    @Column(name="email")
    @Email
    private String email;
    @Column(name="password")
    private String password;
    @Column(name = "cart_id")
    private String cart_id;

    
}
