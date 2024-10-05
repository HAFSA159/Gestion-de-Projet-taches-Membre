package com.example.taskmanager.model;

import java.time.LocalDate;
import java.util.List;

public class Project {
    private int id;  
    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private Status status;
    private List<Task> tasks;

    // New fields for basic statistics
    private int totalTasks;
    private int completedTasks;
    private int inProgressTasks;

    public Project() {}

    public Project(String name, String description, LocalDate startDate, LocalDate endDate, Status status) {
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    // Getters and Setters for new fields
    public int getTotalTasks() {
        return totalTasks;
    }

    public void setTotalTasks(int totalTasks) {
        this.totalTasks = totalTasks;
    }

    public int getCompletedTasks() {
        return completedTasks;
    }

    public void setCompletedTasks(int completedTasks) {
        this.completedTasks = completedTasks;
    }

    public int getInProgressTasks() {
        return inProgressTasks;
    }

    public void setInProgressTasks(int inProgressTasks) {
        this.inProgressTasks = inProgressTasks;
    }

    // Method to calculate statistics based on the task list
    public void calculateTaskStatistics() {
        if (tasks == null || tasks.isEmpty()) {
            this.totalTasks = 0;
            this.completedTasks = 0;
            this.inProgressTasks = 0;
            return;
        }

        this.totalTasks = tasks.size();
        this.completedTasks = (int) tasks.stream().filter(task -> task.getStatus() == TaskStatus.DONE).count();
        this.inProgressTasks = (int) tasks.stream().filter(task -> task.getStatus() == TaskStatus.IN_PROGRESS).count();
    }

    // Getters and Setters for other fields
    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
