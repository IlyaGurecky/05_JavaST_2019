<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<c:set var="url">${pageContext.request.requestURL}</c:set>
<c:set var="context"
       value="${fn:substring(url, 0, fn:length(url) - fn:length(pageContext.request.requestURI))}${pageContext.request.contextPath}"/>
<c:url var="home" value="/home"/>
<c:url var="filmslist" value="/films"/>
<c:url var="seeLaterList" value="/user/see_later"/>
<c:url var="profile" value="/user/profile"/>
<c:url var="filmPage" value="/film"/>
<c:url var="filmAdd" value="/admin/film_add"/>


<html>
<head>
    <title>Films</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="icon" href="${context}/img/filmsIcon.png" type="image/png"/>
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <link href="${context}/css/filmsList.css" rel="stylesheet" type="text/css">
</head>
<body style="background: url(${context}/img/background/filmsback.jpg); background-size: 100%">
<nav class="navbar fixed-top navbar-dark">
    <a class="navbar-brand" href="${home}" style="background: rgba(243, 214, 151, 1);
     padding: 10px; border-radius: 5px; color: black">KinoMan</a>
    <c:if test="${empty films}"><a href="${filmslist}" style="background: rgba(243, 214, 151, 1);
     padding: 10px; border-radius: 5px; color: black; text-decoration-line: none">Back
        to films list</a></c:if>
    <c:if test="${not empty user and user.role.value.equals('admin')}"><a
            href="${filmAdd}" style="background: rgba(243, 214, 151, 1);
     padding: 10px; border-radius: 5px; color: black; text-decoration-line: none">Add
        Film</a></c:if>
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
                            <a class="dropdown-item" href="#">Watched</a>
                        </c:if>
                        <a class="dropdown-item" href="${profile}">Profile</a>
                        <form action="${home}" method="post"
                              class="dropdown-item"
                              style="padding: 0; margin: 0">
                            <input type="hidden" name="command"
                                   value="signout"/>
                            <input type="submit"
                                   value="LogOut" style="background: none;
                           color: black; "></form>
                        </form>
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
<c:choose>
    <c:when test="${not empty films}">
        <div class="container" style="margin: 100px 100px 100px 130px">
            <form class="form-inline my-2 my-lg-0"
                  style="padding: 10px 10px 10px 0" action="${filmslist}"
                  method="get">
                <input class="form-control mr-sm-1" type="search"
                       placeholder="Film name" aria-label="Search" name="fn"
                       required>
                <button class="btn btn-primary"
                        style="background: rgba(13, 20, 35, 0.9)" type="submit">
                    Search
                </button>
                <c:if test="${isAfterSearch}">
                    <a href="${filmslist}"
                       style="padding: 10px;text-decoration-line: none">Go to
                        films list</a>
                </c:if>
            </form>
            <c:forEach var="film" items="${films}" varStatus="status">
                <div class="row"
                     style="background: rgba(13, 20, 35, 0.9); border-radius: 7px">
                    <tr>
                        <div class="col-1-3">
                            <c:choose>
                                <c:when test="${not empty film.imageName}">
                                    <td>
                                        <img width="255px" height="340px"
                                             src="${context}/img/films/${film.imageName}"/>
                                    </td>
                                </c:when>
                                <c:otherwise>
                                    <td>
                                        <img width="255px" height="340px"
                                             src="${context}/img/imageNotFound.jpg"/>
                                    </td>
                                </c:otherwise>
                            </c:choose>
                        </div>
                        <div style="text-align: left; color: white">
                            <div class="col-2-3">
                                <td><h3><c:out
                                        value="${ film.name }"/></h3>
                                </td>
                                <br/><br/>
                                <td><c:out
                                        value="${ film.premierDate }"/></td>
                                <br/><br/>
                                <td><c:out value="${ film.category }"/></td>
                                <br/><br/>
                                <td><c:out value="${ film.country }"/></td>
                                <br/><br/>
                                <br/><br/>
                                <div class="row" style="margin: 0;">
                                    <form action="${filmPage}" method="get">
                                        <input type="hidden" name="fId"
                                               value="${film.id}">
                                        <td><input type="submit"
                                                   class="btn btn-success"
                                                   value="Open"
                                                   style="background: gold; color: black">
                                        </td>
                                    </form>
                                    <c:if test="${user.role.value.equals('admin')}">
                                        <form action="${filmslist}"
                                              method="post"
                                              style="margin-left: 5px"
                                              onsubmit="return confirm('Delete this film?')">
                                            <td>
                                                <input type="hidden" name="id"
                                                       value="${film.id}"/>
                                                <input type="hidden"
                                                       name="command"
                                                       value="deleteFilm">
                                                <input type="submit"
                                                       value="Delete"
                                                       class="btn btn-danger"/>
                                            </td>
                                        </form>
                                    </c:if>
                                    <c:if test="${not empty user
                                    and not user.role.value.equals('admin')
                                    and not user.role.value.equals('editor')}">
                                        <c:choose>
                                            <c:when test="${not seeLater.contains(film)}">
                                                <form action="${filmslist}"
                                                      method="post"
                                                      style="margin-left: 5px">
                                                    <td>
                                                        <input type="hidden"
                                                               name="filmId"
                                                               value="${film.id}">
                                                        <input type="hidden"
                                                               name="command"
                                                               value="addToSeeLater">
                                                        <input type="submit"
                                                               value="See later"
                                                               class="btn btn-success"/>
                                                    </td>
                                                </form>
                                            </c:when>
                                            <c:otherwise>
                                                <form style="margin-left: 5px">
                                                    <button type="button"
                                                            class="btn btn-outline-success"
                                                            value="In Your List"
                                                            disabled><img
                                                            src="${context}/img/seeLaterButtonIcon.png"
                                                            width="22px"
                                                            style="margin: 0 6px 1px 0">In
                                                        Your List
                                                    </button>
                                                </form>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                    </tr>
                </div>
                <hr/>
            </c:forEach>
            <c:if test="${amount_of_pages gt 0}">
                <nav aria-label="Page navigation example">
                    <ul class="pagination justify-content-center">
                        <c:if test="${pageNumber != 1}">
                            <li class="page-item"><a class="page-link a-nav"
                                                     href="${filmslist}?page=${pageNumber - 1}">Prev</a>
                            </li>
                        </c:if>
                        <c:forEach begin="1" end="${amount_of_pages}" var="i">
                            <c:choose>
                                <c:when test="${pageNumber eq i}">
                                    <li class="page-item disabled"><a
                                            class="page-link a-nav"
                                            href="${filmslist}?page=${i}">${i}</a>
                                        <span class="sr-only">(current)</span>
                                    </li>
                                </c:when>
                                <c:otherwise>
                                    <li class="page-item"><a
                                            class="page-link a-nav"
                                            href="${filmslist}?page=${i}">${i}</a>
                                    </li>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                        <c:if test="${pageNumber lt amount_of_pages}">
                            <li class="page-item"><a class="page-link a-nav"
                                                     href="${filmslist}?page=${pageNumber + 1}">Next</a>
                            </li>
                        </c:if>
                    </ul>
                </nav>
            </c:if>
        </div>
    </c:when>
    <c:otherwise>
        <div class="parent">
            <div class="block">
                <h1 align="center">Films list is empty</h1>
            </div>
        </div>
    </c:otherwise>
</c:choose>
</body>
</html>


