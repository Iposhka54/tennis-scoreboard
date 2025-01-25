<%@ page contentType="text/html;charset=UTF-8"%>
<header class="header">
    <section class="nav-header">
        <div class="brand">
            <div class="nav-toggle">
                <img src="${pageContext.request.contextPath}/images/menu.png" alt="Logo" class="logo">
            </div>
            <span class="logo-text">TennisScoreboard</span>
        </div>
        <div>
            <nav class="nav-links">
                <a class="nav-link" href="/">Home</a>
                <a class="nav-link" href="${pageContext.request.contextPath}/matches?page=1&filter_by_player_name=">Matches</a>
            </nav>
        </div>
    </section>
</header>