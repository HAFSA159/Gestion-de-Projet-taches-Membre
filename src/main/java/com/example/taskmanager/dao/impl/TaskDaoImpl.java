package com.example.taskmanager.dao.impl;

import com.example.taskmanager.dao.TaskDao;
import com.example.taskmanager.model.Task;
import com.example.taskmanager.model.TaskStatus;
import com.example.taskmanager.model.Priority;
import com.example.taskmanager.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskDaoImpl implements TaskDao {

    @Override
    public void createTask(Task task) {
        String query = "INSERT INTO tasks (title, description, priority, status, creation_date, due_date, project_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, task.getTitle());
            statement.setString(2, task.getDescription());
            statement.setString(3, task.getPriority().name());
            statement.setString(4, task.getStatus().name());
            statement.setDate(5, Date.valueOf(task.getCreationDate()));
            statement.setDate(6, Date.valueOf(task.getDueDate()));
            statement.setInt(7, task.getProjectId());

            statement.executeUpdate();

            // Retrieve generated ID and set it to the task
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                task.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private Task extractTaskFromResultSet(ResultSet resultSet) throws SQLException {
        Task task = new Task();
        task.setId(resultSet.getInt("id"));
        task.setTitle(resultSet.getString("title"));
        task.setDescription(resultSet.getString("description"));
        task.setPriority(Priority.valueOf(resultSet.getString("priority")));
        task.setStatus(TaskStatus.valueOf(resultSet.getString("status")));
        task.setCreationDate(resultSet.getDate("creation_date").toLocalDate());
        task.setDueDate(resultSet.getDate("due_date").toLocalDate());
        task.setProjectId(resultSet.getInt("project_id"));
        return task;
    }

    @Override
    public void updateTask(Task task) {
        String query = "UPDATE tasks SET title = ?, description = ?, priority = ?, status = ?, creation_date = ?, due_date = ? WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, task.getTitle());
            statement.setString(2, task.getDescription());
            statement.setString(3, task.getPriority().name());
            statement.setString(4, task.getStatus().name());
            statement.setDate(5, Date.valueOf(task.getCreationDate()));
            statement.setDate(6, Date.valueOf(task.getDueDate()));
            statement.setInt(7, task.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteTask(int taskId) {
        String query = "DELETE FROM tasks WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, taskId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Task findTaskById(int taskId) {
        Task task = null;
        String query = "SELECT * FROM tasks WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, taskId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                task = new Task();
                task.setId(resultSet.getInt("id"));
                task.setTitle(resultSet.getString("title"));
                task.setDescription(resultSet.getString("description"));
                task.setPriority(Priority.valueOf(resultSet.getString("priority")));
                task.setStatus(TaskStatus.valueOf(resultSet.getString("status")));
                task.setCreationDate(resultSet.getDate("creation_date").toLocalDate());
                task.setDueDate(resultSet.getDate("due_date").toLocalDate());
                task.setProjectId(resultSet.getInt("project_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return task;
    }
    
    @Override
    public List<Task> getAllTasks(int page, int pageSize) {
        List<Task> tasks = new ArrayList<>();
        String query = "SELECT * FROM tasks LIMIT ? OFFSET ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, pageSize);
            statement.setInt(2, (page - 1) * pageSize);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                tasks.add(extractTaskFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    @Override
    public List<Task> getTasksByProjectId(int projectId, int page, int pageSize) {
        List<Task> tasks = new ArrayList<>();
        String query = "SELECT * FROM tasks WHERE project_id = ? LIMIT ? OFFSET ?";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, projectId);
            statement.setInt(2, pageSize);
            statement.setInt(3, (page - 1) * pageSize);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Task task = new Task();
                task.setId(resultSet.getInt("id"));
                task.setTitle(resultSet.getString("title"));
                task.setDescription(resultSet.getString("description"));
                task.setPriority(Priority.valueOf(resultSet.getString("priority")));
                task.setStatus(TaskStatus.valueOf(resultSet.getString("status")));
                task.setCreationDate(resultSet.getDate("creation_date").toLocalDate());
                task.setDueDate(resultSet.getDate("due_date").toLocalDate());
                task.setProjectId(resultSet.getInt("project_id"));
                tasks.add(task);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    @Override
    public void assignTaskToMember(int taskId, int memberId) {
        String query = "UPDATE tasks SET assigned_member_id = ? WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, memberId);
            statement.setInt(2, taskId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateTaskStatus(int taskId, String status) {
        String query = "UPDATE tasks SET status = ? WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, status);
            statement.setInt(2, taskId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
