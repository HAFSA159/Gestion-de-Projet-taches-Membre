package com.example.taskmanager.dao.impl;

import com.example.taskmanager.dao.TaskDao;
import com.example.taskmanager.model.Task;
import com.example.taskmanager.util.DatabaseConnection;

import java.sql.Connection;
import java.util.List;

public class TaskDaoImpl implements TaskDao {

    private Connection connection;

    public TaskDaoImpl() {
        this.connection = DatabaseConnection.getConnection();
    }

    @Override
    public void createTask(Task task) {
        // Empty implementation
    }

    @Override
    public void updateTask(Task task) {
        // Empty implementation
    }

    @Override
    public void deleteTask(int taskId) {
        // Empty implementation
    }

    @Override
    public Task findTaskById(int taskId) {
        // Empty implementation
        return null;
    }

    @Override
    public List<Task> getTasksByProjectId(int projectId, int page, int pageSize) {
        // Empty implementation
        return null;
    }

    @Override
    public void assignTaskToMember(int taskId, int memberId) {
        // Empty implementation
    }

    @Override
    public void updateTaskStatus(int taskId, String status) {
        // Empty implementation
    }
}
