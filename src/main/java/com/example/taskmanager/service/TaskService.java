package com.example.taskmanager.service;

import com.example.taskmanager.model.Task;

import java.util.List;

public interface TaskService {
    void createTask(Task task);
    void updateTask(Task task);
    void deleteTask(int taskId);
    Task findTaskById(int taskId);
    List<Task> getAllTasks(int page, int pageSize);
    List<Task> getTasksByProjectId(int projectId, int page, int pageSize);
    void assignTaskToMember(int taskId, int memberId);
    void updateTaskStatus(int taskId, String status);
}
