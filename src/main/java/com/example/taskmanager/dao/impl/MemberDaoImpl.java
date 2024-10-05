package com.example.taskmanager.dao.impl;

import com.example.taskmanager.dao.MemberDao;
import com.example.taskmanager.model.Member;
import com.example.taskmanager.model.Role;
import com.example.taskmanager.model.Task;
import com.example.taskmanager.model.TaskStatus;
import com.example.taskmanager.model.Priority;
import com.example.taskmanager.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MemberDaoImpl implements MemberDao {

    @Override
    public void createMember(Member member) {
        String query = "INSERT INTO members (first_name, last_name, email, role, team_id) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, member.getFirstName());
            statement.setString(2, member.getLastName());
            statement.setString(3, member.getEmail());
            statement.setString(4, member.getRole().name());
            statement.setInt(5, member.getTeamId());

            statement.executeUpdate();

            // Retrieve generated ID
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                member.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateMember(Member member) {
        String query = "UPDATE members SET first_name = ?, last_name = ?, email = ?, role = ?, team_id = ? WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, member.getFirstName());
            statement.setString(2, member.getLastName());
            statement.setString(3, member.getEmail());
            statement.setString(4, member.getRole().name());
            statement.setInt(5, member.getTeamId());
            statement.setInt(6, member.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteMember(int memberId) {
        String query = "DELETE FROM members WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, memberId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Member findMemberById(int memberId) {
        Member member = null;
        String query = "SELECT * FROM members WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, memberId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                member = new Member();
                member.setId(resultSet.getInt("id"));
                member.setFirstName(resultSet.getString("first_name"));
                member.setLastName(resultSet.getString("last_name"));
                member.setEmail(resultSet.getString("email"));
                member.setRole(Role.valueOf(resultSet.getString("role")));
                member.setTeamId(resultSet.getInt("team_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return member;
    }
    
    @Override
    public List<Member> getAllMembers() {
        List<Member> members = new ArrayList<>();
        String query = "SELECT * FROM members";  // Fetch all members

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Member member = new Member();
                member.setId(resultSet.getInt("id"));
                member.setFirstName(resultSet.getString("first_name"));
                member.setLastName(resultSet.getString("last_name"));
                member.setEmail(resultSet.getString("email"));
                member.setRole(Role.valueOf(resultSet.getString("role")));  // Assuming Role is an enum
                member.setTeamId(resultSet.getInt("team_id"));

                members.add(member);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return members;
    }
    

    @Override
    public List<Member> getAllMembersByTeamId(int teamId, int page, int pageSize) {
        List<Member> members = new ArrayList<>();
        String query = "SELECT * FROM members WHERE team_id = ? LIMIT ? OFFSET ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, teamId);
            statement.setInt(2, pageSize);
            statement.setInt(3, (page - 1) * pageSize);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Member member = new Member();
                member.setId(resultSet.getInt("id"));
                member.setFirstName(resultSet.getString("first_name"));
                member.setLastName(resultSet.getString("last_name"));
                member.setEmail(resultSet.getString("email"));
                member.setRole(Role.valueOf(resultSet.getString("role")));
                member.setTeamId(resultSet.getInt("team_id"));
                members.add(member);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return members;
    }

    @Override
    public List<Task> getTasksByMemberId(int memberId) {
        List<Task> tasks = new ArrayList<>();
        String query = "SELECT * FROM tasks WHERE assigned_member_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, memberId); 
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

                tasks.add(task);  
            }
        } catch (SQLException e) {
            e.printStackTrace();  
        }
        return tasks;  
    }
    
    @Override
    public List<Member> getAllMembersNotInTeam(int teamId) {
        List<Member> members = new ArrayList<>();
        String query = "SELECT * FROM members WHERE team_id != ? OR team_id IS NULL";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, teamId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Member member = new Member();
                member.setId(resultSet.getInt("id"));
                member.setFirstName(resultSet.getString("first_name"));
                member.setLastName(resultSet.getString("last_name"));
                member.setEmail(resultSet.getString("email"));
                member.setRole(Role.valueOf(resultSet.getString("role")));
                member.setTeamId(resultSet.getInt("team_id"));
                members.add(member);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return members;
    }
}
