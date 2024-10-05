package com.example.taskmanager.service.impl;

import com.example.taskmanager.dao.ProjectDao;
import com.example.taskmanager.model.Project;
import com.example.taskmanager.service.ProjectService;

import java.util.List;

public class ProjectServiceImpl implements ProjectService {

    private ProjectDao projectDao;

    public ProjectServiceImpl(ProjectDao projectDao) {
        this.projectDao = projectDao;
    }

    @Override
    public void createProject(Project project) {
        projectDao.createProject(project);
    }

    @Override
    public void updateProject(Project project) {
        projectDao.updateProject(project);
    }

    @Override
    public void deleteProject(int projectId) {
        projectDao.deleteProject(projectId);
    }

    @Override
    public Project findProjectById(int projectId) {
        return projectDao.findProjectById(projectId);
    }

    @Override
    public List<Project> getAllProjects(int page, int pageSize) {
        return projectDao.getAllProjects(page, pageSize);
    }

    @Override
    public List<Project> searchProjects(String keyword, int page, int pageSize) {
        return projectDao.searchProjects(keyword, page, pageSize);
    }

    @Override
    public Project getProjectStatistics(int projectId) {
        return projectDao.getProjectStatistics(projectId);
    }
}
