package com.example.taskmanager.service.impl;

import com.example.taskmanager.dao.MemberDao;
import com.example.taskmanager.model.Member;
import com.example.taskmanager.model.Task;
import com.example.taskmanager.service.MemberService;

import java.util.List;

public class MemberServiceImpl implements MemberService {

    private MemberDao memberDao;

    public MemberServiceImpl(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    @Override
    public void createMember(Member member) {
        memberDao.createMember(member);
    }

    @Override
    public void updateMember(Member member) {
        memberDao.updateMember(member);
    }

    @Override
    public void deleteMember(int memberId) {
        memberDao.deleteMember(memberId);
    }

    @Override
    public Member findMemberById(int memberId) {
        return memberDao.findMemberById(memberId);
    }

    @Override
    public List<Member> getAllMembersByTeamId(int teamId, int page, int pageSize) {
        return memberDao.getAllMembersByTeamId(teamId, page, pageSize);
    }
    
    public List<Member> getAllMembers(){
    	return memberDao.getAllMembers();
    }
    
    @Override
    public List<Member> getAllMembersNotInTeam(int teamId) {
        return memberDao.getAllMembersNotInTeam(teamId);
    }

    @Override
    public List<Task> getTasksByMemberId(int memberId) {
        return memberDao.getTasksByMemberId(memberId);
    }
}
