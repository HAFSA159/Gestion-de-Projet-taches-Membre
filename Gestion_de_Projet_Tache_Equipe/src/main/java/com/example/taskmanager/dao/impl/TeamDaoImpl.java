package com.example.taskmanager.dao.impl;

import com.example.taskmanager.dao.TeamDao;
import com.example.taskmanager.model.Team;
import com.example.taskmanager.util.DatabaseConnection;

import java.sql.Connection;
import java.util.List;

public class TeamDaoImpl implements TeamDao {

    private Connection connection;

    public TeamDaoImpl() {
        this.connection = DatabaseConnection.getConnection();
    }

    @Override
    public void createTeam(Team team) {
        // Empty implementation
    }

    @Override
    public void updateTeam(Team team) {
        // Empty implementation
    }

    @Override
    public void deleteTeam(int teamId) {
        // Empty implementation
    }

    @Override
    public Team findTeamById(int teamId) {
        // Empty implementation
        return null;
    }

    @Override
    public List<Team> getAllTeams(int page, int pageSize) {
        // Empty implementation
        return null;
    }
}
