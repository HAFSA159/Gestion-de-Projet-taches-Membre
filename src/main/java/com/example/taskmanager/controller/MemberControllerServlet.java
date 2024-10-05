package com.example.taskmanager.controller;

import com.example.taskmanager.model.Member;
import com.example.taskmanager.model.Role;
import com.example.taskmanager.service.MemberService;
import com.example.taskmanager.service.impl.MemberServiceImpl;
import com.example.taskmanager.dao.impl.MemberDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class MemberControllerServlet extends HttpServlet {

    private MemberService memberService;

    @Override
    public void init() throws ServletException {
        memberService = new MemberServiceImpl(new MemberDaoImpl());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null || action.isEmpty()) {
            action = "list";
        }

        switch (action) {
            case "new":
                showNewForm(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "delete":
                deleteMember(request, response);
                break;
            default:
                listMembers(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("create".equals(action)) {
            createMember(request, response);
        } else if ("update".equals(action)) {
            updateMember(request, response);
        }
    }

    private void listMembers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int teamId = getIntParameter(request, "teamId", 0);
        int page = getIntParameter(request, "page", 1);
        int pageSize = getIntParameter(request, "pageSize", 10);
        
        List<Member> members;
        if (teamId > 0) {
            // If a teamId is provided, get members for that team
            members = memberService.getAllMembersByTeamId(teamId, page, pageSize);
        } else {
            // If no teamId is provided, get all members
            members = memberService.getAllMembers();
        }
        
        request.setAttribute("members", members);
        request.getRequestDispatcher("/WEB-INF/views/member-list.jsp").forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/member-form.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int memberId = getIntParameter(request, "id", 0);
        Member member = memberService.findMemberById(memberId);
        request.setAttribute("member", member);
        request.getRequestDispatcher("/WEB-INF/views/member-form.jsp").forward(request, response);
    }

    private void createMember(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Member member = getMemberFromRequest(request);
        memberService.createMember(member);
        response.sendRedirect(request.getContextPath() + "/members?action=list");
    }

    private void updateMember(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = getIntParameter(request, "id", 0);
        Member member = getMemberFromRequest(request);
        member.setId(id);
        memberService.updateMember(member);
        response.sendRedirect(request.getContextPath() + "/members?action=list");
    }

    private void deleteMember(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int memberId = getIntParameter(request, "id", 0);
        memberService.deleteMember(memberId);
        response.sendRedirect(request.getContextPath() + "/members?action=list");
    }

    private Member getMemberFromRequest(HttpServletRequest request) {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        Role role = Role.valueOf(request.getParameter("role"));
        int teamId = getIntParameter(request, "teamId", 0);
        return new Member(firstName, lastName, email, role, teamId);
    }

    private int getIntParameter(HttpServletRequest request, String name, int defaultValue) {
        String paramValue = request.getParameter("name");
        if (paramValue == null || paramValue.isEmpty()) {
            return defaultValue;
        }
        try {
            return Integer.parseInt(paramValue);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
}