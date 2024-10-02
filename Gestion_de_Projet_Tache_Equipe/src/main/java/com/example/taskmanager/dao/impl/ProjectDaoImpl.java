package com.example.taskmanager.dao.impl;

import com.example.taskmanager.dao.ProjectDao;
import com.example.taskmanager.model.Project;
import com.example.taskmanager.util.DatabaseConnection;

import java.sql.Connection;
import java.util.List;

public class ProjectDaoImpl implements ProjectDao {

    private Connection connection;

    public ProjectDaoImpl() {
        this.connection = DatabaseConnection.getConnection();
    }

    @Override
    public void createProject(Project project) {
        // Empty implementation
    }

    @Override
    public void updateProject(Project project) {
        // Empty implementation
    }

    @Override
    public void deleteProject(int projectId) {
        // Empty implementation
    }

    @Override
    public Project findProjectById(int projectId) {
        // Empty implementation
        return null;
    }

    @Override
    public List<Project> getAllProjects(int page, int pageSize) {
        // Empty implementation
        return null;
    }

    @Override
    public List<Project> searchProjects(String keyword, int page, int pageSize) {
        // Empty implementation
        return null;
    }

    @Override
    public Project getProjectStatistics(int projectId) {
        // Empty implementation
        return null;
    }
}
