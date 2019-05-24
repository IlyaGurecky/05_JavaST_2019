<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<fmt:bundle basename="films_page">
    <c:set var="url">${pageContext.request.requestURL}</c:set>
    <c:set var="context"
           value="${fn:substring(url, 0, fn:length(url) - fn:length(pageContext.request.requestURI))}${pageContext.request.contextPath}"/>
    <c:url var="home" value="/home"/>
    <c:url var="filmslist" value="/films"/>
    <c:url var="seeLaterList" value="/user/see_later"/>
    <c:url var="profile" value="/user/profile"/>
    <c:url var="filmPage" value="/film"/>
    <c:url var="filmAdd" value="/admin/film_add"/>
    <c:url var="watched" value="/user/watched"/>

    <html>
    <head>
        <title><fmt:message key="tittle"/></title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="icon" href="${context}/img/filmsIcon.png" type="image/png"/>
        <link rel="stylesheet"
              href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
        <link href="${context}/css/filmsList.css" rel="stylesheet"
              type="text/css">
    </head>
    <body style="background: url(${context}/img/background/filmsback.jpg); background-size: 100%">
    <nav class="navbar fixed-top navbar-dark">
        <a class="navbar-brand" href="${home}" style="background: gold;
     padding: 10px; border-radius: 5px; color: black">KinoMan</a>
        <c:if test="${empty films}"><a href="${filmslist}" style="background: gold;
     padding: 10px; border-radius: 5px; color: black; text-decoration-line: none"><fmt:message
                key="back_to_list"/></a></c:if>
        <c:if test="${not empty user and user.role.value.equals('admin')}"><a
                href="${filmAdd}" style="background: gold;
     padding: 10px; border-radius: 5px; color: black; text-decoration-line: none"><fmt:message
                key="add_film"/></a></c:if>
        <div class="row">
            <div class="dropdown">
                <button class="btn btn-secondary dropdown-toggle" type="button"
                        id="dropdownMenuButton" data-toggle="dropdown"
                        aria-haspopup="true" aria-expanded="false"
                        style="background: gold; color: black; padding: 13px;
                    border-radius: 5px;
                    margin-right: 10px">
                    <c:choose>
                        <c:when test="${not empty user}">
                            ${user.login}
                        </c:when>
                        <c:otherwise>
                            <fmt:message key="menu"/>
                        </c:otherwise>
                    </c:choose>
                </button>
                <div class="dropdown-menu dropdown-menu-right"
                     aria-labelledby="dropdownMenuButton">
                    <c:choose>
                        <c:when test="${not empty user}">
                            <a class=" dropdown-item"
                               href="${home}"><fmt:message key="home"/></a>
                            <c:if test="${not user.role.value.equals('admin')
                        and not user.role.value.equals('editor')}">
                                <a class="dropdown-item" href="${seeLaterList}"><fmt:message
                                        key="see_later"/></a>
                                <a class="dropdown-item"
                                   href="${watched}"><fmt:message
                                        key="watched"/></a>
                            </c:if>
                            <a class="dropdown-item"
                               href="${profile}"><fmt:message
                                    key="profile"/></a>
                            <form action="${home}" method="post"
                                  class="dropdown-item"
                                  style="padding: 0; margin: 0">
                                <input type="hidden" name="command"
                                       value="signout"/>
                                <input type="submit"
                                       value="<fmt:message key="logout"/>"
                                       style="background: none;
                           color: black; "></form>
                        </c:when>
                        <c:otherwise>
                            <c:url var="signin" value="/signin"/>
                            <a class=" dropdown-item"
                               href="${home}"><fmt:message key="home"/></a>
                            <a class="dropdown-item"
                               href="${signin}"><fmt:message key="signin"/></a>
                        </c:otherwise>
                    </c:choose>
                    <c:choose>
                        <c:when test="${not empty param.fn}">
                            <c:set value="${filmslist}?fn=${param.fn}&language"
                                   var="href_with_lang"/>
                        </c:when>
                        <c:when test="${not empty param.page}">
                            <c:set value="${filmslist}?page=${param.page}&language"
                                   var="href_with_lang"/>
                        </c:when>
                        <c:otherwise>
                            <c:set value="${filmslist}?language"
                                   var="href_with_lang"/>
                        </c:otherwise>
                    </c:choose>
                    <div class="dropdown-item">
                        <a href="${href_with_lang}=en"><img
                                src="${context}/img/america.png"
                                width="22" height="19" alt="EN"/></a>
                        <a href="${href_with_lang}=ru"
                           style="margin-left: 5px"><img
                                src="${context}/img/russia.png"
                                width="22" height="19" alt="RU"/></a>
                        <a href="${href_with_lang}=de"
                           style="margin-left: 5px"><img
                                src="${context}/img/germany.png"
                                width="22" height="19" alt="DE"/></a>
                    </div>
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
                           placeholder="<fmt:message key="film_search_place_holder"/>"
                           aria-label="Search" name="fn"
                           required>
                    <button class="btn btn-primary"
                            style="background: rgba(13, 20, 35, 0.9)"
                            type="submit">
                        <fmt:message key="search"/>
                    </button>
                    <c:if test="${isAfterSearch}">
                        <a href="${filmslist}"
                           style="padding: 10px;text-decoration-line: none"><fmt:message
                                key="back_to_list"/></a>
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
                                                       value="<fmt:message key="open_button"/>"
                                                       style="background: gold; color: black">
                                            </td>
                                        </form>
                                        <c:if test="${user.role.value.equals('admin')}">
                                            <form action="${filmslist}"
                                                  method="post"
                                                  style="margin-left: 5px"
                                                  onsubmit="return confirm('Delete this film?')">
                                                <td>
                                                    <input type="hidden"
                                                           name="id"
                                                           value="${film.id}"/>
                                                    <input type="hidden"
                                                           name="command"
                                                           value="deleteFilm">
                                                    <input type="submit"
                                                           value="<fmt:message key="delete_button"/>"
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
                                                                   value="<fmt:message key="see_later"/>"
                                                                   class="btn btn-success"
                                                                   style="width: 100%"/>
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
                                                                style="margin: 0 6px 1px 0"><fmt:message
                                                                key="text_on_disabled_button"/>
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
                <c:if test="${amount_of_pages gt 1}">
                    <nav aria-label="Page navigation example">
                        <ul class="pagination justify-content-center">
                            <c:if test="${pageNumber != 1}">
                                <li class="page-item"><a class="page-link a-nav"
                                                         href="${filmslist}?page=${pageNumber - 1}"><fmt:message
                                        key="prev"/></a>
                                </li>
                            </c:if>
                            <c:forEach begin="1" end="${amount_of_pages}"
                                       var="i">
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
                                                         href="${filmslist}?page=${pageNumber + 1}"><fmt:message
                                        key="next"/></a>
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
                    <h1 align="center"><fmt:message key="empty_list"/></h1>
                </div>
            </div>
        </c:otherwise>
    </c:choose>
    </body>
    </html>
</fmt:bundle>
