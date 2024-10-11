package com.itacademy.hackatonProva.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "activities")
public class Activity {
    @Id
    private String id;
    private String name;
    private String description;
    private int maxCapacity;
    private List<String> enrolledUsers;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public List<String> getEnrolledUsers() {
        return enrolledUsers;
    }

    public void setEnrolledUsers(List<String> enrolledUsers) {
        this.enrolledUsers = enrolledUsers;
    }
}