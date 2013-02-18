package com.example.sampletodo.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_project")
public class Project extends BaseEntity {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
}
