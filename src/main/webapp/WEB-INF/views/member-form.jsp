<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Member Form</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">
    <h2 class="mb-4">${member.id == null ? 'Add New Member' : 'Edit Member'}</h2>

    <form action="members?action=${member.id == null ? 'create' : 'update'}" method="post">
        <!-- Include the member ID for editing -->
        <input type="hidden" name="id" value="${member.id}"/>

        <div class="form-group">
            <label for="firstName">First Name</label>
            <input type="text" class="form-control" id="firstName" name="firstName" value="${member.firstName}" required>
        </div>

        <div class="form-group">
            <label for="lastName">Last Name</label>
            <input type="text" class="form-control" id="lastName" name="lastName" value="${member.lastName}" required>
        </div>

        <div class="form-group">
            <label for="email">Email</label>
            <input type="email" class="form-control" id="email" name="email" value="${member.email}" required>
        </div>

        <div class="form-group">
            <label for="role">Role</label>
            <select class="form-control" id="role" name="role">
                <option value="PROJECT_MANAGER" ${member.role == 'PROJECT_MANAGER' ? 'selected' : ''}>Project Manager</option>
                <option value="DEVELOPER" ${member.role == 'DEVELOPER' ? 'selected' : ''}>Developer</option>
                <option value="DESIGNER" ${member.role == 'DESIGNER' ? 'selected' : ''}>Designer</option>
            </select>
        </div>

        <div class="form-group">
            <label for="teamId">Team ID</label>
            <input type="number" class="form-control" id="teamId" name="teamId" value="${member.teamId}" required>
        </div>

        <button type="submit" class="btn btn-success">${member.id == null ? 'Create Member' : 'Update Member'}</button>
        <a href="members?action=list" class="btn btn-secondary">Cancel</a>
    </form>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
