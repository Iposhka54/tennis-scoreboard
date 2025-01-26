<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>New Match</title>
    <link rel="stylesheet" href="../../css/style.css">
</head>
<body>
<%@ include file="header.jsp"%>
<main>
    <div class="container">
        <div>
            <h1>Start new match</h1>
            <div class="new-match-image"></div>
            <div class="form-container center">
                <form method="post" action="${pageContext.request.contextPath}/new-match">
                    <c:if test="${not empty sessionScope.error}">
                        <p style="color: red;">${sessionScope.error}</p>
                    </c:if>
                    <label class="label-player" for="player1">Player one</label>
                    <input class="input-player" placeholder="Name" type="text" minlength="5" maxlength="16"  title="Enter a name" id="player1" name="firstPlayer" required>
                    <label class="label-player" for="player2">Player two</label>
                    <input class="input-player" placeholder="Name" type="text" minlength="5" maxlength="16" title="Enter a name" id="player2" name="secondPlayer" required>
                    <input class="form-button" type="submit" value="Start">
                </form>
            </div>
        </div>
    </div>
</main>
<%@include file="footer.jsp"%>
</body>
</html>
