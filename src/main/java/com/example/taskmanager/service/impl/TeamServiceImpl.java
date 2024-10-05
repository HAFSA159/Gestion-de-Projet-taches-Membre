package com.example.taskmanager.service.impl;

import com.example.taskmanager.dao.TeamDao;
import com.example.taskmanager.model.Team;
import com.example.taskmanager.model.Member;
import com.example.taskmanager.service.TeamService;

import java.util.List;

public class TeamServiceImpl implements TeamService {

    private TeamDao teamDao;

    public TeamServiceImpl(TeamDao teamDao) {
        this.teamDao = teamDao;
    }

    @Override
    public void createTeam(Team team) {
        teamDao.createTeam(team);
    }

    @Override
    public void updateTeam(Team team) {
        teamDao.updateTeam(team);
    }
    
    @Override
    public void addMemberToTeam(int teamId, int memberId) {
        teamDao.addMemberToTeam(teamId, memberId);
    }

    @Override
    public void deleteTeam(int teamId) {
        teamDao.deleteTeam(teamId);
    }

    @Override
    public Team findTeamById(int teamId) {
        return teamDao.findTeamById(teamId);
    }

    @Override
    public List<Team> getAllTeams(int page, int pageSize) {
        return teamDao.getAllTeams(page, pageSize);
    }

    @Override
    public List<Member> getMembersByTeamId(int teamId) {
        return teamDao.getMembersByTeamId(teamId);
    }
}
