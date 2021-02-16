package com.logicalsapien.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@Document
public class User {

    @Id
    @NotBlank(message = "Username is mandatory")
    private String username;
    
    @Indexed(unique = true, direction = IndexDirection.DESCENDING)
    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email format not correct")
    private String email;

    private String password;
    
    private String name;
    
    private boolean enabled;
    
    @DBRef
    private Set<Role> roles;

}