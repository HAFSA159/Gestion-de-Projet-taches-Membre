<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Project List</title>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h1 class="mb-4">Project List</h1>
    <a href="projects?action=new" class="btn btn-primary mb-3">Create New Project</a>

    <c:if test="${empty projects}">
        <div class="alert alert-info">No projects found.</div>
    </c:if>

    <c:if test="${not empty projects}">
        <table class="table table-bordered table-striped">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Description</th>
                    <th>Start Date</th>
                    <th>End Date</th>
                    <th>Status</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="project" items="${projects}">
                    <tr>
                        <td>${project.id}</td>
                        <td>${project.name}</td>
                        <td>${project.description}</td>
                        <td>${project.startDate}</td>
                        <td>${project.endDate}</td>
                        <td>${project.status}</td>
                        <td>
                            <a href="projects?action=view&id=${project.id}" class="btn btn-info btn-sm">View</a>
                            <a href="projects?action=edit&id=${project.id}" class="btn btn-warning btn-sm">Edit</a>
                            <a href="projects?action=delete&id=${project.id}" class="btn btn-danger btn-sm" 
                               onclick="return confirm('Are you sure you want to delete this project?');">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>

    <div class="mt-3">
        <p>Total projects: ${projects.size()}</p>
    </div>

    <!-- Debug Information -->
    <div class="mt-5">
        <h3>Debug Information</h3>
        <p>Projects attribute is null: ${projects == null}</p>
        <p>Projects attribute is empty: ${empty projects}</p>
        <c:if test="${not empty projects}">
            <p>Number of projects: ${projects.size()}</p>
            <h4>Project Details:</h4>
            <c:forEach var="project" items="${projects}">
                <p>ID: ${project.id}, Name: ${project.name}</p>
            </c:forEach>
        </c:if>
    </div>
</div>

<!-- Bootstrap JS and dependencies -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>