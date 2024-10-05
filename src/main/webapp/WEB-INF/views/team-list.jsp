<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Team Details</title>
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

    <h2>Add Member to Team</h2>
    <form action="teams" method="post">
        <input type="hidden" name="action" value="addMember"/>
        <input type="hidden" name="teamId" value="${team.id}"/>
        <div class="form-group">
            <label for="memberId">Select Member:</label>
            <select class="form-control" id="memberId" name="memberId" required>
                <c:forEach var="availableMember" items="${availableMembers}">
                    <option value="${availableMember.id}">${availableMember.firstName} ${availableMember.lastName}</option>
                </c:forEach>
            </select>
        </div>
        <button type="submit" class="btn btn-primary">Add Member</button>
    </form>

    <a href="teams?action=list" class="btn btn-secondary mt-3">Back to List</a>
</div>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>
</body>
</html>