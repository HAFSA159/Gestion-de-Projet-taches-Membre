package com.example.taskmanager.model;

import java.util.List;

public class Team {
    private String name;
    private List<Member> members;

    public Team() {}

    public Team(String name, List<Member> members) {
        this.name = name;
        this.members = members;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }
}
