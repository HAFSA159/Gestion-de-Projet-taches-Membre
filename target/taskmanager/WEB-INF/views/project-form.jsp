<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Project Form</title>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h1 class="mb-4">Project Form</h1>

    <form action="projects" method="post">
        <input type="hidden" name="action" value="${project != null ? 'update' : 'create'}">
        <input type="hidden" name="id" value="${project != null ? project.id : ''}">

        <div class="form-group">
            <label for="name">Project Name</label>
            <input type="text" class="form-control" id="name" name="name" 
                   value="${project != null ? project.name : ''}" required>
        </div>

        <div class="form-group">
            <label for="description">Description</label>
            <textarea class="form-control" id="description" name="description" rows="3">${project != null ? project.description : ''}</textarea>
        </div>

        <div class="form-group">
            <label for="startDate">Start Date</label>
            <input type="date" class="form-control" id="startDate" name="startDate" 
                   value="${project != null ? project.startDate : ''}" required>
        </div>

        <div class="form-group">
            <label for="endDate">End Date</label>
            <input type="date" class="form-control" id="endDate" name="endDate" 
                   value="${project != null ? project.endDate : ''}" required>
        </div>

        <div class="form-group">
            <label for="status">Status</label>
            <select class="form-control" id="status" name="status" required>
                <option value="IN_PREPARATION" ${project != null && project.status == 'IN_PREPARATION' ? 'selected' : ''}>In Preparation</option>
                <option value="IN_PROGRESS" ${project != null && project.status == 'IN_PROGRESS' ? 'selected' : ''}>In Progress</option>
                <option value="PAUSED" ${project != null && project.status == 'PAUSED' ? 'selected' : ''}>Paused</option>
                <option value="COMPLETED" ${project != null && project.status == 'COMPLETED' ? 'selected' : ''}>Completed</option>
                <option value="CANCELED" ${project != null && project.status == 'CANCELED' ? 'selected' : ''}>Canceled</option>
            </select>
        </div>

        <button type="submit" class="btn btn-primary">Save</button>
        <a href="projects?action=list" class="btn btn-secondary">Cancel</a>
    </form>
</div>

<!-- Bootstrap JS and dependencies -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
