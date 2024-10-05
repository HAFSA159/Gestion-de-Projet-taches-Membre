<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Team Form</title>
    <!-- Include Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">
    <h1 class="mb-4"><c:choose><c:when test="${team != null}">Edit Team</c:when><c:otherwise>Create New Team</c:otherwise></c:choose></h1>

    <form action="teams" method="post">
        <input type="hidden" name="action" value="<c:choose><c:when test="${team != null}">update</c:when><c:otherwise>create</c:otherwise></c:choose>"/>
        <input type="hidden" name="id" value="${team.id}"/>

        <div class="form-group">
            <label for="name">Team Name</label>
            <input type="text" class="form-control" id="name" name="name" value="${team.name}" required>
        </div>

        <button type="submit" class="btn btn-primary">Save</button>
        <a href="teams?action=list" class="btn btn-secondary">Cancel</a>
    </form>
</div>

<!-- Include Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>
</body>
</html>
