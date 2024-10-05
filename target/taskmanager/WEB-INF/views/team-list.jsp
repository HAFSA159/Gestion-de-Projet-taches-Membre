<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Team List</title>
    <!-- Include Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">
    <h1 class="mb-4">Team List</h1>

    <a href="teams?action=new" class="btn btn-primary mb-4">Create New Team</a>

    <table class="table table-bordered">
        <thead class="thead-light">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Members</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="team" items="${teams}">
            <tr>
                <td>${team.id}</td>
                <td>${team.name}</td>
                <td>
                    <c:forEach var="member" items="${team.members}">
                        ${member.firstName} ${member.lastName} <br/>
                    </c:forEach>
                </td>
                <td>
                    <a href="teams?action=view&id=${team.id}" class="btn btn-info btn-sm">View</a>
                    <a href="teams?action=edit&id=${team.id}" class="btn btn-warning btn-sm">Edit</a>
                    <a href="teams?action=delete&id=${team.id}" class="btn btn-danger btn-sm">Delete</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<!-- Include Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>
</body>
</html>
