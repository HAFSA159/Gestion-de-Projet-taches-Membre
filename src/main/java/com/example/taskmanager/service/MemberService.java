package com.example.taskmanager.service;

import com.example.taskmanager.model.Member;
import com.example.taskmanager.model.Task;
import java.util.List;

public interface MemberService {
    void createMember(Member member);
    void updateMember(Member member);
    void deleteMember(int memberId);
    Member findMemberById(int memberId);
    List<Member> getAllMembers(); 
    List<Member> getAllMembersByTeamId(int teamId, int page, int pageSize);
    List<Task> getTasksByMemberId(int memberId);
    List<Member> getAllMembersNotInTeam(int teamId);
}
