<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
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
<c:if test="${param.language == 'ru'}">
    <fmt:setLocale value="ru_RU" scope="session"/>
</c:if>
<c:if test="${param.language == 'de'}">
    <fmt:setLocale value="de_DE" scope="session"/>
</c:if>
<c:if test="${param.language == 'en'}">
    <fmt:setLocale value="en_EN" scope="session"/>
</c:if>
<fmt:bundle basename="see_later_page">
    <html>
    <head>
        <title><fmt:message key="tittle"/></title>
        <link rel="icon" href="${context}/img/seeLaterIcon.png"
              type="image/png"/>
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
                        ${user.login}
                </button>
                <div class="dropdown-menu dropdown-menu-right"
                     aria-labelledby="dropdownMenuButton">
                    <a class=" dropdown-item" href="${home}"><fmt:message
                            key="home"/></a>
                    <a class="dropdown-item" href="${seeLaterList}"><fmt:message
                            key="see_later"/></a>
                    <a class="dropdown-item" href="${watched}"><fmt:message
                            key="watched"/></a>
                    <a class="dropdown-item"
                       href="${profile}"><fmt:message key="profile"/></a>
                    <form action="${home}" method="post"
                          class="dropdown-item"
                          style="padding: 0; margin: 0">
                        <input type="hidden" name="command"
                               value="signout"/>
                        <input type="submit"
                               value="<fmt:message key="logout"/>" style="background: none;
                                   border: none; margin-left: 40px;
                           color: black; "></form>
                    <c:choose>
                        <c:when test="${not empty param.page}">
                            <c:set var="href_with_lang"
                                   value="${seeLater}?page=${param.page}&language"/>
                        </c:when>
                        <c:otherwise>
                            <c:set var="href_with_lang"
                                   value="${seeLater}?language"/>
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
    <h1 align="center"><fmt:message key="see_later_str"/></h1>
    <c:choose>
        <c:when test="${not empty seeLaterFilms}">
            <div class="container">
                <table class="table"
                       style="margin-top: 30px; background: rgba(23,4,0,0.85);">
                    <thead>
                    <tr style="color: gold; font-size: larger">
                        <th></th>
                        <th><fmt:message key="table_name"/></th>
                        <th><fmt:message key="table_premier_date"/></th>
                        <th><fmt:message key="table_category"/></th>
                        <th><fmt:message key="table_country"/></th>
                        <th><fmt:message key="table_added_date"/></th>
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
                                      onsubmit="return confirm('<fmt:message
                                              key="confirm_message"/>');">
                                    <input type="hidden" name="command"
                                           value="deleteFromSeeLater"
                                           id="delete">
                                    <input type="hidden" name="id"
                                           value="${sFilm.film.id}">
                                    <input type="submit"
                                           value="<fmt:message key="delete_button"/>"
                                           class="btn btn-danger"/>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <c:if test="${amount_of_pages gt 1}">
                    <nav aria-label="Page navigation example">
                        <ul class="pagination justify-content-center">
                            <c:if test="${pageNumber != 1}">
                                <li class="page-item"><a class="page-link a-nav"
                                                         href="${seeLater}?page=${pageNumber - 1}"><fmt:message
                                        key="pagin_prev"/></a>
                                </li>
                            </c:if>
                            <c:forEach begin="1" end="${amount_of_pages}"
                                       var="i">
                                <c:choose>
                                    <c:when test="${pageNumber eq i}">
                                        <li class="page-item disabled"><a
                                                class="page-link a-nav"
                                                href="${seeLater}?page=${i}">${i}</a>
                                            <span class="sr-only">(current)</span>
                                        </li>
                                    </c:when>
                                    <c:otherwise>
                                        <li class="page-item"><a
                                                class="page-link a-nav"
                                                href="${seeLater}?page=${i}">${i}</a>
                                        </li>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                            <c:if test="${pageNumber lt amount_of_pages}">
                                <li class="page-item"><a class="page-link a-nav"
                                                         href="${seeLater}?page=${pageNumber + 1}"><fmt:message
                                        key="pagin_next"/></a>
                                </li>
                            </c:if>
                        </ul>
                    </nav>
                </c:if>
            </div>
        </c:when>
        <c:otherwise>
            <h1 style="color: white; margin-top: 180px" align="center">
                <fmt:message key="empty_list_str"/></h1>
        </c:otherwise>
    </c:choose>
    </body>
    </html>
</fmt:bundle>
