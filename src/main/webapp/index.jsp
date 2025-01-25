<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <%@include file="WEB-INF/jsp/header.jsp"%>
    <main>
        <div class="container">
            <h1>Welcome to Tennis Scoreboard</h1>
            <p>Manage your tennis matches, record results, and track rankings</p>
            <div class="welcome-image"></div>
            <div class="form-container center">
                <a class="homepage-action-button" href="${pageContext.request.contextPath}/new-match">
                    <button class="btn start-match">
                        Start a new match
                    </button>
                </a>
                <a class="homepage-action-button" href="${pageContext.request.contextPath}/matches?page=1&filter_by_player_name=">
                    <button class="btn view-results">
                        View match results
                    </button>
                </a>
            </div>
        </div>
    </main>
    <%@include file="WEB-INF/jsp/footer.jsp"%>
</body>
</html>