<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<c:set var="url">${pageContext.request.requestURL}</c:set>
<c:set var="context"
       value="${fn:substring(url, 0, fn:length(url) - fn:length(pageContext.request.requestURI))}${pageContext.request.contextPath}"/>
<c:url var="home" value="/home"/>
<c:url var="filmslist" value="/films"/>
<html>
<head>
    <title>Title</title>

    <style>
        .my-custom-scrollbar {
            position: relative;
            height: 200px;
            overflow: auto;
        }
        .table-wrapper-scroll-y {
            display: block;
        }
    </style>
</head>
<body style="background: url(${context}/img/background/seeLaterBack.jpg) no-repeat; background-size: 100%">
<div class="table-wrapper-scroll-y my-custom-scrollbar">

    <table class="table table-bordered table-striped mb-0">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">First</th>
            <th scope="col">Last</th>
            <th scope="col">Handle</th>
            <th scope="col">Handle</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${seeLaterFilms}" var="sFilm">
            <tr style="font-weight: bold; color: #030005">
                <td>${sFilm.film.id}</td>
                <td>${sFilm.film.name}</td>
                <td>${sFilm.film.premierDate}</td>
                <td>${sFilm.film.country}</td>
                <td>${sFilm.addedDate}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</div>
</body>
</html>
