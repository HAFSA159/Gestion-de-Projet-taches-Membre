package com.example.taskmanager.model;

import java.time.LocalDate;

public class Task {
    private int id;  // Add an ID field
    private String title;
    private String description;
    private Priority priority;
    private TaskStatus status;
    private LocalDate creationDate;
    private LocalDate dueDate;
    private int projectId;
    private int memberId;

    // Constructor without the id
    public Task() {}

    // Constructor with all fields
    public Task(String title, String description, Priority priority, TaskStatus status, LocalDate creationDate, LocalDate dueDate, int projectId) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.status = status;
        this.creationDate = creationDate;
        this.dueDate = dueDate;
        this.projectId = projectId;
    }

    // Getters and Setters for the ID field
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getters and Setters for other fields
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }
    
    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) { 
        this.projectId = projectId;
    }
}
