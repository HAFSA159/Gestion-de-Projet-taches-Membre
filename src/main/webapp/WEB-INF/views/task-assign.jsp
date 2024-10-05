<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Assign Task</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h1>Assign Task</h1>
    
    <form action="${pageContext.request.contextPath}/tasks?action=assign" method="post">
        <input type="hidden" name="taskId" value="${task.id}">
        
        <div class="form-group">
            <label for="member">Assign to Member:</label>
            <select name="memberId" id="member" class="form-control" required>
                <c:forEach var="member" items="${members}">
                    <option value="${member.id}">${member.firstName} ${member.lastName}</option>
                </c:forEach>
            </select>
        </div>

        <button type="submit" class="btn btn-primary">Assign Task</button>
    </form>
</div>

<!-- Bootstrap JS and dependencies -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
