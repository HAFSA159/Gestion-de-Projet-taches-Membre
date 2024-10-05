package com.example.taskmanager.service.impl;

import com.example.taskmanager.dao.TaskDao;
import com.example.taskmanager.model.Task;
import com.example.taskmanager.service.TaskService;

import java.util.List;

public class TaskServiceImpl implements TaskService {

    private TaskDao taskDao;

    public TaskServiceImpl(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    @Override
    public void createTask(Task task) {
        taskDao.createTask(task);
    }

    @Override
    public void updateTask(Task task) {
        taskDao.updateTask(task);
    }

    @Override
    public void deleteTask(int taskId) {
        taskDao.deleteTask(taskId);
    }

    @Override
    public Task findTaskById(int taskId) {
        return taskDao.findTaskById(taskId);
    }
    
    @Override
    public List<Task> getAllTasks(int page, int pageSize) {
        return taskDao.getAllTasks(page, pageSize);
    }

    @Override
    public List<Task> getTasksByProjectId(int projectId, int page, int pageSize) {
        return taskDao.getTasksByProjectId(projectId, page, pageSize);
    }

    @Override
    public void assignTaskToMember(int taskId, int memberId) {
        taskDao.assignTaskToMember(taskId, memberId);
    }

    @Override
    public void updateTaskStatus(int taskId, String status) {
        taskDao.updateTaskStatus(taskId, status);
    }
}
