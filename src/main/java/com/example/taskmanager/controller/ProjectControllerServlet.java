package com.example.taskmanager.controller;

import com.example.taskmanager.dao.impl.ProjectDaoImpl;
import com.example.taskmanager.model.Project;
import com.example.taskmanager.service.ProjectService;
import com.example.taskmanager.model.Status;
import com.example.taskmanager.service.impl.ProjectServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class ProjectControllerServlet extends HttpServlet {

    private ProjectService projectService;

    @Override
    public void init() throws ServletException {
        System.out.println("ProjectControllerServlet: Initializing");
        try {
            projectService = new ProjectServiceImpl(new ProjectDaoImpl());
            System.out.println("ProjectControllerServlet: Initialized successfully");
        } catch (Exception e) {
            System.err.println("ProjectControllerServlet: Initialization failed");
            e.printStackTrace();
            throw new ServletException("Unable to initialize ProjectControllerServlet", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("ProjectControllerServlet: Handling GET request");
        try {
            String action = request.getParameter("action");
            System.out.println("Action: " + action);

            if (action == null || action.isEmpty()) {
                action = "list";
            }

            switch (action) {
                case "new":
                    System.out.println("Showing new form");
                    showNewForm(request, response);
                    break;
                case "edit":
                    System.out.println("Showing edit form");
                    showEditForm(request, response);
                    break;
                case "delete":
                    System.out.println("Deleting project");
                    deleteProject(request, response);
                    break;
                case "view":
                    System.out.println("Viewing project");
                    viewProject(request, response);
                    break;
                default:
                    System.out.println("Listing projects");
                    listProjects(request, response);
                    break;
            }
        } catch (Exception e) {
            System.err.println("Error in doGet: " + e.getMessage());
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("ProjectControllerServlet: Handling POST request");
        try {
            String action = request.getParameter("action");
            System.out.println("Action: " + action);

            if ("create".equals(action)) {
                System.out.println("Creating project");
                createProject(request, response);
            } else if ("update".equals(action)) {
                System.out.println("Updating project");
                updateProject(request, response);
            } else {
                System.out.println("Invalid action for POST request");
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
            }
        } catch (Exception e) {
            System.err.println("Error in doPost: " + e.getMessage());
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred");
        }
    }

    private void listProjects(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Inside listProjects method");
        int page = getIntParameter(request, "page", 1);
        int pageSize = getIntParameter(request, "pageSize", 10);
        List<Project> projects = projectService.getAllProjects(page, pageSize);
        request.setAttribute("projects", projects);
        System.out.println("Forwarding to project-list.jsp");
        request.getRequestDispatcher("/WEB-INF/views/project-list.jsp").forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Forwarding to project-form.jsp for new project");
        request.getRequestDispatcher("/WEB-INF/views/project-form.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Preparing edit form");
        int projectId = getIntParameter(request, "id");
        Project project = projectService.findProjectById(projectId);
        request.setAttribute("project", project);
        System.out.println("Forwarding to project-form.jsp for editing");
        request.getRequestDispatcher("/WEB-INF/views/project-form.jsp").forward(request, response);
    }

    private void createProject(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("Creating new project");
        Project project = getProjectFromRequest(request);
        projectService.createProject(project);
        System.out.println("Project created, redirecting to list");
        response.sendRedirect(request.getContextPath() + "/projects?action=list");
    }

    private void updateProject(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("Updating project");
        int id = getIntParameter(request, "id");
        Project project = getProjectFromRequest(request);
        project.setId(id);
        projectService.updateProject(project);
        System.out.println("Project updated, redirecting to list");
        response.sendRedirect(request.getContextPath() + "/projects?action=list");
    }

    private void deleteProject(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("Deleting project");
        int projectId = getIntParameter(request, "id");
        projectService.deleteProject(projectId);
        System.out.println("Project deleted, redirecting to list");
        response.sendRedirect(request.getContextPath() + "/projects?action=list");
    }

    private void viewProject(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Viewing project details");
        int projectId = getIntParameter(request, "id");
        Project project = projectService.findProjectById(projectId);
        request.setAttribute("project", project);
        System.out.println("Forwarding to project-view.jsp");
        request.getRequestDispatcher("/WEB-INF/views/project-view.jsp").forward(request, response);
    }

    private Project getProjectFromRequest(HttpServletRequest request) {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        LocalDate startDate = LocalDate.parse(request.getParameter("startDate"));
        LocalDate endDate = LocalDate.parse(request.getParameter("endDate"));
        Status status = Status.valueOf(request.getParameter("status"));
        return new Project(name, description, startDate, endDate, status);
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