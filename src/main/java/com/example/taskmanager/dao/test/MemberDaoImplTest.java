package com.example.taskmanager.dao.test;

import com.example.taskmanager.dao.MemberDao;
import com.example.taskmanager.dao.impl.MemberDaoImpl;
import com.example.taskmanager.model.Member;
import com.example.taskmanager.model.Role;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MemberDaoImplTest {

    private static MemberDao memberDao;

    @BeforeAll
    static void setUp() {
        memberDao = new MemberDaoImpl();
        // Initialize test database
    }

    @AfterAll
    static void tearDown() {
        // Clean up test database
    }

    @Test
    void testCreateAndFindMember() {
        Member member = new Member("John", "Doe", "john@example.com", Role.DEVELOPER, 1);
        memberDao.createMember(member);
        
        Member foundMember = memberDao.findMemberById(member.getId());
        assertNotNull(foundMember);
        assertEquals("John", foundMember.getFirstName());
    }

    @Test
    void testUpdateMember() {
        Member member = new Member("Jane", "Doe", "jane@example.com", Role.MANAGER, 1);
        memberDao.createMember(member);

        member.setFirstName("Janet");
        memberDao.updateMember(member);

        Member updatedMember = memberDao.findMemberById(member.getId());
        assertEquals("Janet", updatedMember.getFirstName());
    }

    @Test
    void testDeleteMember() {
        Member member = new Member("Bob", "Smith", "bob@example.com", Role.DEVELOPER, 1);
        memberDao.createMember(member);

        memberDao.deleteMember(member.getId());

        Member deletedMember = memberDao.findMemberById(member.getId());
        assertNull(deletedMember);
    }

    @Test
    void testGetAllMembers() {
        List<Member> members = memberDao.getAllMembers();
        assertNotNull(members);
        assertTrue(members.size() > 0);
    }

    @Test
    void testGetAllMembersByTeamId() {
        List<Member> members = memberDao.getAllMembersByTeamId(1, 1, 10);
        assertNotNull(members);
    }

    @Test
    void testGetTasksByMemberId() {
        List<Task> tasks = memberDao.getTasksByMemberId(1);
        assertNotNull(tasks);
    }
}