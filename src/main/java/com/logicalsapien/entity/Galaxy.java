package com.logicalsapien.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;

@Data
@Document
public class Galaxy {

    @Id
    private String id;

    @Indexed(unique = true, direction = IndexDirection.DESCENDING)
    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "Constellation is mandatory")
    private String constellation;

}