package com.logicalsapien.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Data
@Document
public class User {

    @Id
    private String username;
    
    @Indexed(unique = true, direction = IndexDirection.DESCENDING)
    private String email;
    
    private String password;
    
    private String name;
    
    private boolean enabled;
    
    @DBRef
    private Set<Role> roles;

}