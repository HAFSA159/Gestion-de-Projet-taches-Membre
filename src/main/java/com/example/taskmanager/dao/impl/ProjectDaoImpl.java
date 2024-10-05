package com.example.taskmanager.dao.impl;

import com.example.taskmanager.dao.ProjectDao;
import com.example.taskmanager.model.Project;
import com.example.taskmanager.model.Task;
import com.example.taskmanager.model.Status;  // Correctly imported Status
import com.example.taskmanager.model.TaskStatus;  // Import TaskStatus
import com.example.taskmanager.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjectDaoImpl implements ProjectDao {

    @Override
    public void createProject(Project project) {
        String query = "INSERT INTO projects (name, description, start_date, end_date, status) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, project.getName());
            statement.setString(2, project.getDescription());
            statement.setDate(3, Date.valueOf(project.getStartDate()));
            statement.setDate(4, Date.valueOf(project.getEndDate()));
            statement.setString(5, project.getStatus().name());

            statement.executeUpdate();

            // Retrieve generated ID
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                project.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateProject(Project project) {
        String query = "UPDATE projects SET name = ?, description = ?, start_date = ?, end_date = ?, status = ? WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, project.getName());
            statement.setString(2, project.getDescription());
            statement.setDate(3, Date.valueOf(project.getStartDate()));
            statement.setDate(4, Date.valueOf(project.getEndDate()));
            statement.setString(5, project.getStatus().name());
            statement.setInt(6, project.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteProject(int projectId) {
        String query = "DELETE FROM projects WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, projectId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Project findProjectById(int projectId) {
        Project project = null;
        String query = "SELECT * FROM projects WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, projectId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                project = new Project();
                project.setId(resultSet.getInt("id"));
                project.setName(resultSet.getString("name"));
                project.setDescription(resultSet.getString("description"));
                project.setStartDate(resultSet.getDate("start_date").toLocalDate());
                project.setEndDate(resultSet.getDate("end_date").toLocalDate());

                // Correct enum reference for Status
                project.setStatus(Status.valueOf(resultSet.getString("status")));

                // Load tasks for this project
                List<Task> tasks = getTasksForProject(projectId);
                project.setTasks(tasks);

                // Calculate the basic statistics for the project
                project.calculateTaskStatistics();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return project;
    }

    @Override
    public List<Project> getAllProjects(int page, int pageSize) {
        List<Project> projects = new ArrayList<>();
        String query = "SELECT * FROM projects LIMIT ? OFFSET ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, pageSize);
            statement.setInt(2, (page - 1) * pageSize);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Project project = new Project();
                project.setId(resultSet.getInt("id"));
                project.setName(resultSet.getString("name"));
                project.setDescription(resultSet.getString("description"));
                project.setStartDate(resultSet.getDate("start_date").toLocalDate());
                project.setEndDate(resultSet.getDate("end_date").toLocalDate());

                // Correct enum reference for Status
                project.setStatus(Status.valueOf(resultSet.getString("status")));

                // Load tasks for this project
                List<Task> tasks = getTasksForProject(project.getId());
                project.setTasks(tasks);

                // Calculate the basic statistics for the project
                project.calculateTaskStatistics();

                projects.add(project);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return projects;
    }

    @Override
    public List<Project> searchProjects(String keyword, int page, int pageSize) {
        List<Project> projects = new ArrayList<>();
        String query = "SELECT * FROM projects WHERE name LIKE ? OR description LIKE ? LIMIT ? OFFSET ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, "%" + keyword + "%");
            statement.setString(2, "%" + keyword + "%");
            statement.setInt(3, pageSize);
            statement.setInt(4, (page - 1) * pageSize);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Project project = new Project();
                project.setId(resultSet.getInt("id"));
                project.setName(resultSet.getString("name"));
                project.setDescription(resultSet.getString("description"));
                project.setStartDate(resultSet.getDate("start_date").toLocalDate());
                project.setEndDate(resultSet.getDate("end_date").toLocalDate());

                // Correct enum reference for Status
                project.setStatus(Status.valueOf(resultSet.getString("status")));

                // Load tasks for this project
                List<Task> tasks = getTasksForProject(project.getId());
                project.setTasks(tasks);

                // Calculate the basic statistics for the project
                project.calculateTaskStatistics();

                projects.add(project);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return projects;
    }

    @Override
    public Project getProjectStatistics(int projectId) {
        // This method can return a project with the task statistics already calculated
        return findProjectById(projectId);
    }

    // Helper method to get the list of tasks for a project
    private List<Task> getTasksForProject(int projectId) {
        List<Task> tasks = new ArrayList<>();
        String query = "SELECT * FROM tasks WHERE project_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, projectId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Task task = new Task();
                task.setId(resultSet.getInt("id"));
                task.setTitle(resultSet.getString("title"));

                // Correct enum reference for TaskStatus
                task.setStatus(TaskStatus.valueOf(resultSet.getString("status")));
                tasks.add(task);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tasks;
    }
}
