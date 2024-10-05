package com.example.taskmanager.service;

import com.example.taskmanager.model.Project;

import java.util.List;

public interface ProjectService {
    void createProject(Project project);
    void updateProject(Project project);
    void deleteProject(int projectId);
    Project findProjectById(int projectId);
    List<Project> getAllProjects(int page, int pageSize);
    List<Project> searchProjects(String keyword, int page, int pageSize);
    Project getProjectStatistics(int projectId);
}
