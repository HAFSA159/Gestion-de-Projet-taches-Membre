<html>
<head>
    <title>Task Manager - Home</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container">
        <h2>Welcome to Task Manager</h2>
        <p>Select a section to manage:</p>
        <ul class="list-group">
            <li class="list-group-item"><a href="${pageContext.request.contextPath}/projects?action=list">Manage Projects</a></li>
			<li class="list-group-item"><a href="${pageContext.request.contextPath}/tasks?action=list">Manage Tasks</a></li>
			<li class="list-group-item"><a href="${pageContext.request.contextPath}/teams?action=list">Manage Teams</a></li>
        </ul>
    </div>

    <!-- Bootstrap JS (Optional) -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
