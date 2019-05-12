<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="url">${pageContext.request.requestURL}</c:set>
<c:set var="context"
       value="${fn:substring(url, 0, fn:length(url) - fn:length(pageContext.request.requestURI))}${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Films</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <link href="${context}/css/films.css" rel="stylesheet" type="text/css">
</head>

<body>
<c:choose>
    <c:when test="${not empty films}">
        <c:forEach var="film" items="${films}" varStatus="status">
            <div class="main">
                <div class="container">
                    <div class="row">
                        <tr>
                            <div class="col-1-3">
                                <td><img width="265px" height="310px"
                                         src="${context}/img/films/${film.imageName}"/>
                                </td>
                            </div>
                            <div style="text-align: left;">
                                <div class="col-2-3">
                                    <td><strong><c:out
                                            value="${ film.name }"/> </strong>
                                    </td>
                                    <br><br>
                                    <td><c:out
                                            value="${ film.premierDate }"/></td>
                                    <br><br>
                                    <td><c:out value="${ film.category }"/></td>
                                    <br><br>
                                    <td><c:out value="${ film.country }"/></td>
                                    <br><br>
                                    <br><br>
                                    <form action="" method="post">
                                        <td><input type="submit" value="Open">
                                        </td>
                                    </form>
                                </div>
                            </div>
                        </tr>
                    </div>
                </div>
            </div>
            <hr/>
        </c:forEach>
    </c:when>
    <c:otherwise>
        <h1 align="center">Films list is empty</h1>
    </c:otherwise>
</c:choose>
</body>
</html>
