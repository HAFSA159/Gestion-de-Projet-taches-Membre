<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Task Details</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h1 class="mb-4">Task Details</h1>
    
    <div class="form-group">
        <label>Title</label>
        <p>${task.title}</p>
    </div>

    <div class="form-group">
        <label>Description</label>
        <p>${task.description}</p>
    </div>

    <div class="form-group">
        <label>Priority</label>
        <p>${task.priority}</p>
    </div>

    <div class="form-group">
        <label>Status</label>
        <p>${task.status}</p>
    </div>

    <div class="form-group">
        <label>Creation Date</label>
        <p>${task.creationDate}</p>
    </div>

    <div class="form-group">
        <label>Due Date</label>
        <p>${task.dueDate}</p>
    </div>

    <div class="form-group">
        <label>Project ID</label>
        <p>${task.projectId}</p>
    </div>

    <a href="tasks?action=edit&id=${task.id}" class="btn btn-warning">Edit</a>
    <a href="tasks?action=assign&id=${task.id}" class="btn btn-primary">Assign Task</a>
    <a href="tasks?action=list" class="btn btn-secondary">Back to List</a>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>