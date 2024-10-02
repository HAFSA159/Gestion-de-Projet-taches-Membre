package com.example.taskmanager.dao;

import com.example.taskmanager.model.Task;
import java.util.List;

public interface TaskDao {
    void createTask(Task task);
    void updateTask(Task task);
    void deleteTask(int taskId);
    Task findTaskById(int taskId);
    List<Task> getTasksByProjectId(int projectId, int page, int pageSize);
    void assignTaskToMember(int taskId, int memberId);
    void updateTaskStatus(int taskId, String status);
}
