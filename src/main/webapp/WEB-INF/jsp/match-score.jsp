<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Match Score</title>
    <link rel="stylesheet" href="../../css/style.css">
</head>
<body>
<%@include file="header.jsp"%>
<main>
    <div class="container">
        <h1>Current match</h1>
        <div class="current-match-image"></div>
        <section class="score">
            <table class="table">
                <thead class="result">
                <tr>
                    <th class="table-text">Player</th>
                    <th class="table-text">Sets</th>
                    <th class="table-text">Games</th>
                    <th class="table-text">Points</th>
                </tr>
                </thead>
                <tbody>
                <tr class="player1">
                    <td class="table-text">${requestScope.match.player1.name}</td>
                    <td class="table-text">${requestScope.match.player1Score.sets}</td>
                    <td class="table-text">${requestScope.match.player1Score.games}</td>
                    <td class="table-text">${requestScope.match.player1Score.points}</td>
                    <td class="table-text">
                        <div class="score-btn">Score</div>
                    </td>
                </tr>
                <tr class="player2">
                    <td class="table-text">${requestScope.match.player2.name}</td>
                    <td class="table-text">${requestScope.match.player2Score.sets}</td>
                    <td class="table-text">${requestScope.match.player2Score.games}</td>
                    <td class="table-text">${requestScope.match.player2Score.points}</td>
                    <td class="table-text">
                        <div class="score-btn">Score</div>
                    </td>
                </tr>
                </tbody>
            </table>
        </section>
    </div>
</main>
<%@include file="footer.jsp"%>
</body>
</html>
