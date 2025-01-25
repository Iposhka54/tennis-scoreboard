<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>New Match</title>
    <link rel="stylesheet" href="../css/style.css">
</head>
<body>
<%@ include file="header.jsp"%>
<main>
    <div class="container">
        <div>
            <h1>Start new match</h1>
            <div class="new-match-image"></div>
            <div class="form-container center">
                <form method="post" action="#">
<%--                    <p style="color: red;">Sample error message</p>--%>
                    <label class="label-player" for="playerOne">Player one</label>
                    <input class="input-player" placeholder="Name" type="text" required title="Enter a name" id="playerOne">
                    <label class="label-player" for="playerTwo">Player two</label>
                    <input class="input-player" placeholder="Name" type="text" required title="Enter a name" id="playerTwo">
                    <input class="form-button" type="submit" value="Start">
                </form>
            </div>
        </div>
    </div>
</main>
<%@include file="footer.jsp"%>
</body>
</html>
