<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>New Match</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<%@ include file="header.jsp"%>
<main>
    <div class="container">
        <h1>Matches</h1>
        <div class="input-container">
            <input class="input-filter" placeholder="Filter by name" type="text"
                   name="filter_by_player_name"
                   value="${param.filter_by_player_name}"
                   oninput="debouncedFilter()"
            />
            <div>
                <a href="${pageContext.request.contextPath}/matches">
                    <button class="btn-filter">Reset Filter</button>
                </a>
            </div>
        </div>

        <table class="table-matches">
            <tr>
                <th>Player One</th>
                <th>Player Two</th>
                <th>Winner</th>
            </tr>
            <c:forEach var="match" items="${requestScope.matches}">
                <tr>
                    <td>${match.player1.name}</td>
                    <td>${match.player2.name}</td>
                    <td><span class="winner-name-td">${match.winner.name}</span> </td>
                </tr>
            </c:forEach>
        </table>

        <div class="pagination">
            <c:if test="${requestScope.countPages > 1}">
                <c:if test="${requestScope.currentPage > 1}">
                    <c:choose>
                        <c:when test="${not empty param.filter_by_player_name}">
                            <a class="prev" href="${pageContext.request.contextPath}/matches?page=${requestScope.currentPage - 1}&filter_by_player_name=${param.filter_by_player_name}"> < </a>
                        </c:when>
                        <c:otherwise>
                            <a class="prev" href="${pageContext.request.contextPath}/matches?page=${requestScope.currentPage - 1}"> < </a>
                        </c:otherwise>
                    </c:choose>
                </c:if>
            </c:if>

            <c:forEach var="page" begin="1" end="${requestScope.countPages}">
                <c:choose>
                    <c:when test="${page == requestScope.currentPage}">
                        <p class="num-page current">${page}</p>
                    </c:when>
                    <c:otherwise>
                        <c:choose>
                            <c:when test="${not empty param.filter_by_player_name}">
                                <a class="num-page" href="${pageContext.request.contextPath}/matches?page=${page}&filter_by_player_name=${param.filter_by_player_name}">${page}</a>
                            </c:when>
                            <c:otherwise>
                                <a class="num-page" href="${pageContext.request.contextPath}/matches?page=${page}">${page}</a>
                            </c:otherwise>
                        </c:choose>
                    </c:otherwise>
                </c:choose>
            </c:forEach>

            <c:if test="${requestScope.currentPage < requestScope.countPages}">
                    <c:choose>
                        <c:when test="${not empty param.filter_by_player_name}">
                            <a class="next" href="${pageContext.request.contextPath}/matches?page=${requestScope.currentPage + 1}&filter_by_player_name=${param.filter_by_player_name}"> > </a>
                        </c:when>
                        <c:otherwise>
                            <a class="next" href="${pageContext.request.contextPath}/matches?page=${requestScope.currentPage + 1}"> > </a>
                        </c:otherwise>
                    </c:choose>
            </c:if>
        </div>
    </div>
</main>
<%@include file="footer.jsp"%>
</body>

<script>
    let debounceTimeout;

    function debouncedFilter() {
        clearTimeout(debounceTimeout);
        debounceTimeout = setTimeout(() => {
            sendFilterRequest();
        }, 500);
    }

    function sendFilterRequest() {
        const input = document.querySelector('.input-filter');
        const filterValue = input.value.trim();

        const url = new URL(window.location.href);

        if (filterValue) {
            url.searchParams.set('filter_by_player_name', filterValue);
        } else {
            url.searchParams.delete('filter_by_player_name');
        }

        url.searchParams.delete('page');

        window.location.href = url.toString();
    }
</script>
</html>