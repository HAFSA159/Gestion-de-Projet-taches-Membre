<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Members List</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">
    <h2 class="mb-4">Members List</h2>

    <a href="members?action=new" class="btn btn-primary mb-3">Add New Member</a>

    <table class="table table-bordered">
        <thead>
            <tr>
                <th>ID</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Email</th>
                <th>Role</th>
                <th>Team</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach var="member" items="${members}">
            <tr>
                <td>${member.id}</td>
                <td>${member.firstName}</td>
                <td>${member.lastName}</td>
                <td>${member.email}</td>
                <td>${member.role}</td>
                <td>${member.teamId}</td>
                <td>
                    <a href="members?action=edit&id=${member.id}" class="btn btn-warning btn-sm">Edit</a>
                    <a href="members?action=delete&id=${member.id}" class="btn btn-danger btn-sm" 
                       onclick="return confirm('Are you sure you want to delete this member?');">Delete</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <!-- Pagination Controls (optional) -->
    <nav aria-label="Page navigation">
        <ul class="pagination">
            <!-- Add pagination logic if necessary -->
        </ul>
    </nav>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
