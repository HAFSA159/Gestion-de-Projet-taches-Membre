package com.example.taskmanager.dao.impl;

import com.example.taskmanager.dao.MemberDao;
import com.example.taskmanager.model.Member;
import com.example.taskmanager.util.DatabaseConnection;

import java.sql.Connection;
import java.util.List;

public class MemberDaoImpl implements MemberDao {

    private Connection connection;

    public MemberDaoImpl() {
        this.connection = DatabaseConnection.getConnection();
    }

    @Override
    public void createMember(Member member) {
        // Empty implementation
    }

    @Override
    public void updateMember(Member member) {
        // Empty implementation
    }

    @Override
    public void deleteMember(int memberId) {
        // Empty implementation
    }

    @Override
    public Member findMemberById(int memberId) {
        // Empty implementation
        return null;
    }

    @Override
    public List<Member> getAllMembersByTeamId(int teamId, int page, int pageSize) {
        // Empty implementation
        return null;
    }

    @Override
    public List<Member> getTasksByMemberId(int memberId) {
        // Empty implementation
        return null;
    }
}
