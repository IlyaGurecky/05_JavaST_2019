<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<c:if test="${param.language == 'ru'}">
    <fmt:setLocale value="ru_RU" scope="session"/>
</c:if>
<c:if test="${param.language == 'de'}">
    <fmt:setLocale value="de_DE" scope="session"/>
</c:if>
<c:if test="${param.language == 'en'}">
    <fmt:setLocale value="en_EN" scope="session"/>
</c:if>
<fmt:bundle basename="home_page">
    <c:set var="url">${pageContext.request.requestURL}</c:set>
    <c:set var="context"
           value="${fn:substring(url, 0, fn:length(url) - fn:length(pageContext.request.requestURI))}${pageContext.request.contextPath}"/>
    <c:url var="signin" value="/signin"/>
    <c:url var="home" value="/home"/>
    <c:url var="films" value="/films"/>
    <c:url var="users" value="/admin/users"/>
    <c:url var="categories" value="/category"/>
    <c:url var="seeLater" value="/user/see_later"/>
    <c:url var="profile" value="/user/profile"/>
    <c:url var="addFilm" value="/admin/film_add"/>
    <c:url var="about" value="/about"/>
    <c:url var="watched" value="/user/watched"/>

    <html>
    <head>
        <title>KinoMan</title>
        <link rel="stylesheet" type="text/css"
              href="${context}/css/navBar.css"/>
        <link rel="icon" href="${context}/img/mainTittleIcon.png"
              type="image/png"/>
        <link href="https://fonts.googleapis.com/css?family=Work+Sans:400,600"/>
        <style>
            .footer {
                position: fixed;
                left: 0;
                bottom: 0;
                width: 100%;
                background-color: rgba(16, 22, 37, 0.65);
                text-align: center;
            }
        </style>
    </head>

    <body style="background: url(${context}/img/background/mainback2.jpg) no-repeat;
            background-size: 100%;">
    <header>
        <div class="container">
            <nav>
                <ul>
                    <c:if test="${not user.role.value.equals('admin')}">
                        <li>
                            <div class="dropdown">
                                <a href="#"><fmt:message key="category"/></a>
                                <div align="center">
                                    <form action="${categories}" method="GET">
                                        <button type="submit" name="cName"
                                                value="Боевик"> Action
                                        </button>
                                        <button type="submit" name="cName"
                                                value="Фэнтези"> Fantasy
                                        </button>
                                        <button type="submit" name="cName"
                                                value="Комедия"> Comedy
                                        </button>
                                        <button type="submit" name="cName"
                                                value="Ужасы">
                                            Horror
                                        </button>
                                        <button type="submit" name="cName"
                                                value="Семейные"> Family
                                        </button>
                                        <button type="submit" name="cName"
                                                value="Детективы"> Detective
                                        </button>
                                        <button type="submit" name="cName"
                                                value="Драмы">
                                            Drama
                                        </button>
                                    </form>
                                </div>
                            </div>
                        </li>
                    </c:if>
                    <c:if test="${user.role.value.equals('admin')}">
                        <li>
                            <a href="${addFilm}"
                               style="color: rgba(255,145,0,1)">
                                <fmt:message key="add_film"/></a>
                        </li>
                    </c:if>
                    <li><a href="${films}"><fmt:message key="films"/></a></li>
                    <li><a href="${about}"><fmt:message key="about"/></a></li>
                    <li>
                        <div class="dropdown">
                            <a href="#"><fmt:message key="language"/></a>
                            <div>
                                <a href="${home}?language=en">English</a>
                                <a href="${home}?language=ru">Русский</a>
                                <a href="${home}?language=de">Deutsch</a>
                            </div>
                        </div>
                    </li>
                    <c:choose>
                        <c:when test="${empty user}">
                            <li class="auth"><a href="${signin}"><fmt:message
                                    key="signin"/></a>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <c:if test="${user.role.value.equals('admin')}">
                                <li><a href="${users}"><fmt:message
                                        key="users_config"/></a></li>
                            </c:if>
                            <li>
                                <div class="dropdown">
                                    <a href="${profile}">${user.login}</a>
                                    <div>
                                        <c:if test="${not user.role.value.equals('admin')
                                    and not user.role.value.equals('editor')}">
                                            <a href="${watched}"><img
                                                    src="https://img.icons8.com/material-outlined/48/000000/visible.png"
                                                    width="25px" align="center">
                                                <fmt:message key="watched"/></a>
                                            <hr>
                                            <a href="${seeLater}"><img
                                                    src="https://img.icons8.com/material-outlined/48/000000/clock.png"
                                                    width="25px" align="center">
                                                <fmt:message
                                                        key="see_later"/></a>
                                            <hr>
                                        </c:if>
                                        <c:url value="/user/profile"
                                               var="profile"/>
                                        <a href="${profile}"><img
                                                src="https://img.icons8.com/material-outlined/48/000000/settings.png"
                                                width="25px" align="center">
                                            <fmt:message key="preferences"/></a>

                                        <form action="${home}" method="post">
                                            <input type="hidden" name="command"
                                                   value="signout"/>
                                            <img src="https://img.icons8.com/material-outlined/48/000000/shutdown.png"
                                                 width="25px" align="center"
                                                 style="margin-left: 9px; margin-right: 0"/>
                                            <input type="submit"
                                                   value="<fmt:message key="logout"/>">
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
    <div class="footer">
        <p style="font-size: 25px; color: gold; font-weight: bold">
            KinoMan</p>
    </div>
    </body>
    </html>
</fmt:bundle>
