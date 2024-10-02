package com.example.taskmanager.dao;

import com.example.taskmanager.model.Member;
import java.util.List;

public interface MemberDao {
    void createMember(Member member);
    void updateMember(Member member);
    void deleteMember(int memberId);
    Member findMemberById(int memberId);
    List<Member> getAllMembersByTeamId(int teamId, int page, int pageSize);
    List<Member> getTasksByMemberId(int memberId);
}
