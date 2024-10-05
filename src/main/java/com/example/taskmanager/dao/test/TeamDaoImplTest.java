package com.example.taskmanager.dao.test;

import com.example.taskmanager.dao.TeamDao;
import com.example.taskmanager.dao.impl.TeamDaoImpl;
import com.example.taskmanager.model.Team;
import com.example.taskmanager.model.Member;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TeamDaoImplTest {

    private static TeamDao teamDao;

    @BeforeAll
    static void setUp() {
        teamDao = new TeamDaoImpl();
        // Initialize test database
    }

    @AfterAll
    static void tearDown() {
        // Clean up test database
    }

    @Test
    void testCreateAndFindTeam() {
        Team team = new Team("Test Team");
        teamDao.createTeam(team);

        Team foundTeam = teamDao.findTeamById(team.getId());
        assertNotNull(foundTeam);
        assertEquals("Test Team", foundTeam.getName());
    }

    @Test
    void testUpdateTeam() {
        Team team = new Team("Update Team");
        teamDao.createTeam(team);

        team.setName("Updated Team");
        teamDao.updateTeam(team);

        Team updatedTeam = teamDao.findTeamById(team.getId());
        assertEquals("Updated Team", updatedTeam.getName());
    }

    @Test
    void testDeleteTeam() {
        Team team = new Team("Delete Team");
        teamDao.createTeam(team);

        teamDao.deleteTeam(team.getId());

        Team deletedTeam = teamDao.findTeamById(team.getId());
        assertNull(deletedTeam);
    }

    @Test
    void testGetAllTeams() {
        List<Team> teams = teamDao.getAllTeams(1, 10);
        assertNotNull(teams);
        assertTrue(teams.size() > 0);
    }

    @Test
    void testAddMemberToTeam() {
        Team team = new Team("Member Team");
        teamDao.createTeam(team);

        Member member = new Member("John", "Doe", "john@example.com", Role.DEVELOPER, 0);
        memberDao.createMember(member);

        teamDao.addMemberToTeam(team.getId(), member.getId());

        Team updatedTeam = teamDao.findTeamById(team.getId());
        assertTrue(updatedTeam.getMembers().stream().anyMatch(m -> m.getId() == member.getId()));
    }

    @Test
    void testGetMembersByTeamId() {
        Team team = new Team("Team with Members");
        teamDao.createTeam(team);

        Member member1 = new Member("Alice", "Smith", "alice@example.com", Role.DEVELOPER, team.getId());
        Member member2 = new Member("Bob", "Jones", "bob@example.com", Role.MANAGER, team.getId());
        memberDao.createMember(member1);
        memberDao.createMember(member2);

        List<Member> members = teamDao.getMembersByTeamId(team.getId());
        assertNotNull(members);
        assertEquals(2, members.size());
    }
}