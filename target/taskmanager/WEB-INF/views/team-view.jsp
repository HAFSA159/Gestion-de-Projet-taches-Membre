<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Team Details</title>
    <!-- Include Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">
    <h1 class="mb-4">Team Details</h1>

    <div class="card mb-4">
        <div class="card-body">
            <h5 class="card-title">Team Name: ${team.name}</h5>
            <p class="card-text">Team ID: ${team.id}</p>
            <h6>Members:</h6>
            <ul>
                <c:forEach var="member" items="${team.members}">
                    <li>${member.firstName} ${member.lastName} - ${member.email}</li>
                </c:forEach>
            </ul>
        </div>
    </div>

    <a href="teams?action=list" class="btn btn-secondary">Back to List</a>
</div>

<!-- Include Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>
</body>
</html>
