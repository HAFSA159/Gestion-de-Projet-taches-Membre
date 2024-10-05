package com.example.taskmanager.dao;

import com.example.taskmanager.model.Team;
import com.example.taskmanager.model.Member;
import java.util.List;

public interface TeamDao {
    void createTeam(Team team);
    void updateTeam(Team team);
    void deleteTeam(int teamId);
    Team findTeamById(int teamId);
    List<Team> getAllTeams(int page, int pageSize);
    List<Member> getMembersByTeamId(int teamId);
    void addMemberToTeam(int teamId, int memberId);
}
