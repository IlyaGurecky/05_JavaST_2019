<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<c:set var="url">${pageContext.request.requestURL}</c:set>
<c:set var="context"
       value="${fn:substring(url, 0, fn:length(url) - fn:length(pageContext.request.requestURI))}${pageContext.request.contextPath}"/>
<c:url var="signin" value="/signin"/>
<c:url var="home" value="/home"/>
<c:url var="logout" value="/logout"/>
<c:url var="films" value="/films"/>
<c:url var="category" value="/category"/>

<html>
<head>
    <title>KinoMan</title>
    <link rel="stylesheet" type="text/css"
          href="${context}/css/navigationBar.css"/>
    <link rel="icon" href="${context}/img/mainTittleIcon.png" type="image/png"/>
    <link href="https://fonts.googleapis.com/css?family=Work+Sans:400,600"/>
</head>


<body style="background: url(${context}/img/background/mainback2.jpg) no-repeat; background-size: 100%">
<header>
    <div class="container">
        <nav>
            <ul>
                <li>
                    <div class="dropdown">
                        <a href="#">CATEGORIES</a>
                        <div align="center">
                            <a href="${category}"> Action</a>
                            <a href="#"> Fantasy</a>
                            <a href="#"> Comedy</a>
                            <a href="#"> Horror</a>
                            <a href="#"> Family</a>
                            <a href="#"> Detective</a>
                            <a href="#"> Dramas</a>
                        </div>
                    </div>
                </li>
                <li><a href="${films}">FILMS</a></li>
                <li><a href="#">ABOUT</a></li>
                <c:choose>
                    <c:when test="${user == null}">
                        <li class="auth"><a href="${signin}">SignIn</a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li>
                            <div class="dropdown">
                                <a href="#">${user.login}</a>
                                <div>
                                    <a href="#"><img
                                            src="https://img.icons8.com/material-outlined/48/000000/visible.png"
                                            width="25px" align="center"> Watched
                                        films</a>
                                    <hr>
                                    <a href="#"><img
                                            src="https://img.icons8.com/material-outlined/48/000000/clock.png"
                                            width="25px" align="center"> See
                                        later</a>
                                    <hr>
                                    <a href="#"><img
                                            src="https://img.icons8.com/material-outlined/48/000000/settings.png"
                                            width="25px" align="center">
                                        Preferences</a>
                                    <a href="${logout}"><img
                                            src="https://img.icons8.com/material-outlined/48/000000/shutdown.png"
                                            width="25px" align="center"> LogOut</a>
                                </div>
                            </div>
                        </li>
                        <c:if test="${user.role.value.equals('admin')}">
                            <li><a href="${films}">USERS LIST</a></li>
                        </c:if>
                    </c:otherwise>
                </c:choose>
            </ul>
        </nav>
    </div>
</header>
</body>

</html>
