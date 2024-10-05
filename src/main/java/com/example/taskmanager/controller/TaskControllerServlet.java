package com.example.taskmanager.controller;

import com.example.taskmanager.model.Priority;
import com.example.taskmanager.model.Task;
import com.example.taskmanager.model.Member;
import com.example.taskmanager.service.TaskService;
import com.example.taskmanager.service.MemberService;
import com.example.taskmanager.model.TaskStatus;
import com.example.taskmanager.service.impl.TaskServiceImpl;
import com.example.taskmanager.service.impl.MemberServiceImpl;
import com.example.taskmanager.dao.impl.TaskDaoImpl;
import com.example.taskmanager.dao.impl.MemberDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class TaskControllerServlet extends HttpServlet {

    private TaskService taskService;
    private MemberService memberService;

    @Override
    public void init() throws ServletException {
        taskService = new TaskServiceImpl(new TaskDaoImpl());
        memberService = new MemberServiceImpl(new MemberDaoImpl());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
                deleteTask(request, response);
                break;
            case "view":
                viewTask(request, response);
                break;
            case "assign":
                showAssignForm(request, response);
                break;
            default:
                listTasks(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("create".equals(action)) {
            createTask(request, response);
        } else if ("update".equals(action)) {
            updateTask(request, response);
        } else if ("assign".equals(action)) {
            assignTaskToMember(request, response);
        }
    }

    private void listTasks(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int page = getIntParameter(request, "page", 1);
        int pageSize = getIntParameter(request, "pageSize", 10);
        List<Task> tasks = taskService.getAllTasks(page, pageSize);
        request.setAttribute("tasks", tasks);
        request.getRequestDispatcher("/WEB-INF/views/task-list.jsp").forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/task-form.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int taskId = getIntParameter(request, "id");
        Task task = taskService.findTaskById(taskId);
        request.setAttribute("task", task);
        request.getRequestDispatcher("/WEB-INF/views/task-form.jsp").forward(request, response);
    }

    private void createTask(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Task task = getTaskFromRequest(request);
        taskService.createTask(task);
        response.sendRedirect(request.getContextPath() + "/tasks?action=list&projectId=" + task.getProjectId());
    }

    private void updateTask(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = getIntParameter(request, "id");
        Task task = getTaskFromRequest(request);
        task.setId(id);
        taskService.updateTask(task);
        response.sendRedirect(request.getContextPath() + "/tasks?action=list&projectId=" + task.getProjectId());
    }

    private void deleteTask(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int taskId = getIntParameter(request, "id");
        taskService.deleteTask(taskId);
        response.sendRedirect(request.getContextPath() + "/tasks?action=list");
    }

    private void viewTask(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int taskId = getIntParameter(request, "id");
        Task task = taskService.findTaskById(taskId);
        request.setAttribute("task", task);
        request.getRequestDispatcher("/WEB-INF/views/task-view.jsp").forward(request, response);
    }

    private void showAssignForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int taskId = getIntParameter(request, "id");
        Task task = taskService.findTaskById(taskId);
        request.setAttribute("task", task);
        
        List<Member> members = memberService.getAllMembers();
        request.setAttribute("members", members);

        request.getRequestDispatcher("/WEB-INF/views/task-assign.jsp").forward(request, response);
    }

    private void assignTaskToMember(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int taskId = getIntParameter(request, "taskId");
        int memberId = getIntParameter(request, "memberId");
        taskService.assignTaskToMember(taskId, memberId);
        response.sendRedirect(request.getContextPath() + "/tasks?action=view&id=" + taskId);
    }

    private Task getTaskFromRequest(HttpServletRequest request) {
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        Priority priority = Priority.valueOf(request.getParameter("priority").toUpperCase());
        TaskStatus status = TaskStatus.valueOf(request.getParameter("status").toUpperCase());
        LocalDate creationDate = LocalDate.parse(request.getParameter("creationDate"));
        LocalDate dueDate = LocalDate.parse(request.getParameter("dueDate"));
        int projectId = Integer.parseInt(request.getParameter("projectId"));

        return new Task(title, description, priority, status, creationDate, dueDate, projectId);
    }

    private int getIntParameter(HttpServletRequest request, String name) {
        return Integer.parseInt(request.getParameter(name));
    }

    private int getIntParameter(HttpServletRequest request, String name, int defaultValue) {
        try {
            return Integer.parseInt(request.getParameter(name));
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
}