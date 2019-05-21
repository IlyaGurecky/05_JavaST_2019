<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<c:set var="url">${pageContext.request.requestURL}</c:set>
<c:set var="context"
       value="${fn:substring(url, 0, fn:length(url) - fn:length(pageContext.request.requestURI))}${pageContext.request.contextPath}"/>
<c:url var="home" value="/home"/>
<c:url var="filmslist" value="/films"/>
<c:url var="profile" value="/user/profile"/>
<c:url var="seeLater" value="/user/see_later"/>
<c:url var="filmPage" value="/film"/>
<c:url var="watched" value="/user/watched"/>


<html>
<head>
    <title>See Later</title>
    <link rel="icon" href="${context}/img/seeLaterIcon.png" type="image/png"/>
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <style>
        h1 {
            font-family: 'Montserrat', sans-serif;
            font-size: 60px;
            font-weight: 900;
            color: gold;
            text-transform: uppercase;
            text-shadow: -4px -3px 0 #8400ff, 1px 1px 0px #ff005a;
            letter-spacing: 10px;
        }
    </style>
</head>
<body style="background: url(${context}/img/background/seeLaterBack.jpg) no-repeat; background-size: 100%">
<nav class="navbar fixed-top navbar-dark">
    <a class="navbar-brand" href="${home}" style="background: rgba(243, 214, 151, 1);
     padding: 10px; border-radius: 5px; color: black">KinoMan</a>
    <div class="row">
        <div class="dropdown">
            <button class="btn btn-secondary dropdown-toggle" type="button"
                    id="dropdownMenuButton" data-toggle="dropdown"
                    aria-haspopup="true" aria-expanded="false"
                    style="background: #efd69f; color: black; padding: 13px;
                    border-radius: 5px;
                    margin-right: 10px">
                <c:choose>
                    <c:when test="${not empty user}">
                        ${user.login}
                    </c:when>
                    <c:otherwise>
                        Menu
                    </c:otherwise>
                </c:choose>
            </button>
            <div class="dropdown-menu dropdown-menu-right"
                 aria-labelledby="dropdownMenuButton">
                <c:choose>
                    <c:when test="${not empty user}">
                        <a class=" dropdown-item" href="${home}">Home</a>
                        <c:if test="${not user.role.value.equals('admin')
                        and not user.role.value.equals('editor')}">
                            <a class="dropdown-item" href="${seeLaterList}">See
                                later</a>
                            <a class="dropdown-item" href="${watched}">Watched</a>
                        </c:if>
                        <a class="dropdown-item" href="${profile}">Profile</a>
                        <form action="${home}" method="post"
                              class="dropdown-item"
                              style="padding: 0; margin: 0">
                            <input type="hidden" name="command"
                                   value="signout"/>
                            <input type="submit"
                                   value="LogOut" style="background: none;
                                   border: none; margin-left: 40px;
                           color: black; "></form>
                    </c:when>
                    <c:otherwise>
                        <c:url var="signin" value="/signin"/>
                        <a class=" dropdown-item" href="${home}">Home</a>
                        <a class="dropdown-item" href="${signin}">SignIn</a>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
</nav>
<h1 align="center">See later</h1>
<c:choose>
    <c:when test="${not empty seeLaterFilms}">
        <div class="container">
            <table class="table"
                   style="margin-top: 30px; background: rgba(23,4,0,0.85);">
                <thead>
                <tr style="color: gold; font-size: larger">
                    <th></th>
                    <th>Name</th>
                    <th>Premier Date</th>
                    <th>Category</th>
                    <th>Country</th>
                    <th>Added Date</th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${seeLaterFilms}" var="sFilm">
                    <tr style="font-weight: bold; color: white">
                        <td>
                            <c:choose>
                                <c:when test="${not empty sFilm.film.imageName}">
                                    <img src="${context}/img/films/${sFilm.film.imageName}"
                                         style="width: 50px; height: 70px"/>
                                </c:when>
                                <c:otherwise>
                                    <img src="${context}/img/imageNotFound.jpg"
                                         style="width: 50px; height: 70px"/>
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                            <a href="${filmPage}?fId=${sFilm.film.id}"
                               style="color: white; text-decoration-line: none; font-size: larger">${sFilm.film.name}</a>
                        </td>
                        <td>${sFilm.film.premierDate}</td>
                        <td>${sFilm.film.category}</td>
                        <td>${sFilm.film.country}</td>
                        <td>${sFilm.addedDate}</td>
                        <td>
                            <form action="${seeLater}" method="post"
                                  style="margin: 0"
                                  onsubmit="return confirm('Delete film?');">
                                <input type="hidden" name="command"
                                       value="deleteFromSeeLater" id="delete">
                                <input type="hidden" name="id"
                                       value="${sFilm.film.id}">
                                <input type="submit"
                                       value="Delete"
                                       class="btn btn-danger"/>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </c:when>
    <c:otherwise>
        <h1 style="color: white; margin-top: 180px" align="center">List is empty</h1>
    </c:otherwise>
</c:choose>
</body>
</html>
