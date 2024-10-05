<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Create/Edit Task</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h1 class="mb-4">${task != null ? "Edit Task" : "Create New Task"}</h1>

    <form action="tasks?action=${task != null ? "update" : "create"}" method="post">
        <input type="hidden" name="id" value="${task != null ? task.id : ''}">

        <div class="form-group">
            <label for="title">Title</label>
            <input type="text" class="form-control" id="title" name="title" value="${task != null ? task.title : ''}" required>
        </div>

        <div class="form-group">
            <label for="description">Description</label>
            <textarea class="form-control" id="description" name="description" required>${task != null ? task.description : ''}</textarea>
        </div>

        <div class="form-group">
            <label for="priority">Priority</label>
            <select class="form-control" id="priority" name="priority">
                <option value="LOW" ${task != null && task.priority == 'LOW' ? 'selected' : ''}>Low</option>
                <option value="MEDIUM" ${task != null && task.priority == 'MEDIUM' ? 'selected' : ''}>Medium</option>
                <option value="HIGH" ${task != null && task.priority == 'HIGH' ? 'selected' : ''}>High</option>
            </select>
        </div>

        <div class="form-group">
            <label for="status">Status</label>
            <select class="form-control" id="status" name="status">
                <option value="NOT_STARTED" ${task != null && task.status == 'NOT_STARTED' ? 'selected' : ''}>Not Started</option>
                <option value="IN_PROGRESS" ${task != null && task.status == 'IN_PROGRESS' ? 'selected' : ''}>In Progress</option>
                <option value="COMPLETED" ${task != null && task.status == 'COMPLETED' ? 'selected' : ''}>Completed</option>
            </select>
        </div>

        <div class="form-group">
            <label for="creationDate">Creation Date</label>
            <input type="date" class="form-control" id="creationDate" name="creationDate" value="${task != null ? task.creationDate : ''}" required>
        </div>

        <div class="form-group">
            <label for="dueDate">Due Date</label>
            <input type="date" class="form-control" id="dueDate" name="dueDate" value="${task != null ? task.dueDate : ''}" required>
        </div>

        <div class="form-group">
            <label for="projectId">Project ID</label>
            <input type="number" class="form-control" id="projectId" name="projectId" value="${task != null ? task.projectId : ''}" required>
        </div>

        <button type="submit" class="btn btn-success">${task != null ? "Update Task" : "Create Task"}</button>
    </form>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
