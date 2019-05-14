<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<c:set var="url">${pageContext.request.requestURL}</c:set>
<c:set var="context"
       value="${fn:substring(url, 0, fn:length(url) - fn:length(pageContext.request.requestURI))}${pageContext.request.contextPath}"/>
<c:url var="signin" value="/signin"/>
<c:url var="home" value="/home"/>
<c:url var="films" value="/films"/>
<c:url var="users" value="/admin/users"/>
<c:url var="categories" value="/category"/>
<c:url var="seeLater" value="/user/see_later"/>

<html>
<head>
    <title>KinoMan</title>
    <link rel="stylesheet" type="text/css"
          href="${context}/css/navBar.css"/>
    <link rel="icon" href="${context}/img/mainTittleIcon.png" type="image/png"/>
    <link href="https://fonts.googleapis.com/css?family=Work+Sans:400,600"/>
</head>


<body style="background: url(${context}/img/background/mainback2.jpg) no-repeat; background-size: 100%">

<header>
    <div class="container">
        <nav>
            <ul>
                <c:if test="${not user.role.value.equals('admin')}">
                    <li>
                        <div class="dropdown">
                            <a href="#">CATEGORIES</a>
                            <div align="center">
                                <form action="${categories}" method="GET">
                                    <button type="submit" name="category"
                                            value="Боевик"> Action
                                    </button>
                                    <button type="submit" name="category"
                                            value="Фэнтези"> Fantasy
                                    </button>
                                    <button type="submit" name="category"
                                            value="Комедия"> Comedy
                                    </button>
                                    <button type="submit" name="category"
                                            value="Ужасы">
                                        Horror
                                    </button>
                                    <button type="submit" name="category"
                                            value="Семейные"> Family
                                    </button>
                                    <button type="submit" name="category"
                                            value="Детективы"> Detective
                                    </button>
                                    <button type="submit" name="category"
                                            value="Драмы">
                                        Drama
                                    </button>
                                </form>
                            </div>
                        </div>
                    </li>
                </c:if>
                <li><a href="${films}">FILMS</a></li>
                <li><a href="#">ABOUT</a></li>
                <c:choose>
                    <c:when test="${user == null}">
                        <li class="auth"><a href="${signin}">SignIn</a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <c:if test="${user.role.value.equals('admin')}">
                            <li><a href="${users}">USERS CONFIG</a></li>
                        </c:if>
                        <li>
                            <div class="dropdown">
                                <a href="#">${user.login}</a>
                                <div>
                                    <c:if test="${not user.role.value.equals('admin')}">
                                    <a href="#"><img
                                            src="https://img.icons8.com/material-outlined/48/000000/visible.png"
                                            width="25px" align="center"> Watched
                                        films</a>
                                    <hr>
                                    <a href="${seeLater}"><img
                                            src="https://img.icons8.com/material-outlined/48/000000/clock.png"
                                            width="25px" align="center"> See
                                        later</a>
                                    <hr>
                                    </c:if>
                                    <a href="#"><img
                                            src="https://img.icons8.com/material-outlined/48/000000/settings.png"
                                            width="25px" align="center">
                                        Preferences</a>

                                    <form action="${home}" method="post">
                                        <input type="hidden" name="command"
                                               value="signout"/>
                                        <img src="https://img.icons8.com/material-outlined/48/000000/shutdown.png"
                                             width="25px" align="center"
                                             style="margin-left: 9px; margin-right: 0"/>
                                        <input type="submit"
                                               value="LogOut"></form>
                                    </form>
                                </div>
                            </div>
                        </li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </nav>
    </div>
</header>
</body>

</html>
