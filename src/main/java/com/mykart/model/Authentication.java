package com.mykart.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "authenticate")
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
public class Authentication {

    @Id
    @Column(name="user_id")
    private int user_id;
    @Column(name="username")
    @NonNull
    private String username;
    @Column(name = "password")
    @NonNull
    private String password;
    
}
