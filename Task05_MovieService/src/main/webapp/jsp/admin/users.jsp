<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:if test="${param.language == 'ru'}">
    <fmt:setLocale value="ru_RU" scope="session"/>
</c:if>
<c:if test="${param.language == 'de'}">
    <fmt:setLocale value="de_DE" scope="session"/>
</c:if>
<c:if test="${param.language == 'en'}">
    <fmt:setLocale value="en_EN" scope="session"/>
</c:if>
<fmt:bundle basename="users_page">
    <c:set var="url">${pageContext.request.requestURL}</c:set>
    <c:set var="context"
           value="${fn:substring(url, 0, fn:length(url) - fn:length(pageContext.request.requestURI))}${pageContext.request.contextPath}"/>
    <c:url var="home" value="/home"/>
    <c:url var="userslist" value="/admin/users"/>
    <c:url var="userAdd" value="/admin/user_add"/>
    <html>
    <head>
        <title><fmt:message key="tittle"/></title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet"
              href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    </head>


    <body style="background: url(${context}/img/background/usersback2.jpg)">
    <c:choose>
        <c:when test="${not empty param.u_login}">
            <c:set value="${userslist}?u_login=${param.u_login}&language"
                   var="href_with_lang"/>
        </c:when>
        <c:when test="${not empty param.page}">
            <c:set value="${userslist}?page=${param.page}&language"
                   var="href_with_lang"/>
        </c:when>
        <c:otherwise>
            <c:set value="${userslist}?language"
                   var="href_with_lang"/>
        </c:otherwise>
    </c:choose>
    <div style="margin: 10px 0 0 10px;">
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
    <h1 align="center" style="color: #030005"><fmt:message
            key="main_str"/></h1>

    <br/>
    <br/>
    <div class="container">
        <div style="display: block;">
            <a href="${home}" class="btn btn-info"><fmt:message key="home"/></a>
            <a href="${userAdd}" class="btn btn-success"><fmt:message
                    key="add_account_button"/></a>
            <div style="display: inline-block">
                <form action="${userslist}" method="post"
                      onsubmit="return confirm('<fmt:message
                              key="confirm_message"/>')"
                      style="margin: 0">
                    <input type="hidden" name="command" value="deleteUser">
                    <input type="text"
                           placeholder="<fmt:message key="login_placeholder"/>"
                           name="login" required
                           autocomplete="off"
                           style="border-radius: 5px; border: none; padding: 6px; margin-left: 30px"/>
                    <button type="submit" class="btn btn btn-danger">
                        <fmt:message key="delete_by_login_button"/>
                    </button>
                </form>
            </div>
            <div style="display: inline-block">
                <form action="${userslist}" method="get">
                    <input type="text"
                           placeholder="<fmt:message key="login_placeholder"/>"
                           name="u_login"
                           required
                           autocomplete="off"
                           style="border-radius: 5px; border: none; padding: 6px; margin-left: 30px"/>
                    <button type="submit" class="btn btn btn-success">
                        <fmt:message key="find_by_login_button"/>
                    </button>
                </form>
            </div>
            <c:if test="${isAfterSearch}">
                <div style="display: inline-block">
                    <a href="${userslist}"
                       style="color: black; font-size: 15px; margin-left: 500px"><fmt:message
                            key="after_search_link"/></a>
                </div>
            </c:if>
        </div>
        <br/>
        <c:choose>
            <c:when test="${not empty users}">
                <table class="table ">
                    <thead>
                    <tr style="font-size: larger; border: #030005; color: #030005">
                        <th>ID</th>
                        <th><fmt:message key="table_login"/></th>
                        <th><fmt:message key="table_email"/></th>
                        <th><fmt:message key="table_role"/></th>
                        <th><fmt:message key="table_country"/></th>
                        <th><fmt:message key="table_birth_date"/></th>
                        <th><fmt:message key="table_sex"/></th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${users}" var="user">
                        <tr style="font-weight: bold; color: #030005">
                            <td>${user.id}</td>
                            <td>${user.login}</td>
                            <td>${user.email}</td>
                            <td>${user.role.value}</td>
                            <td>${user.country}</td>
                            <td>${user.birthDate}</td>
                            <td>${user.sex}</td>
                            <td width="50px">
                                <c:if test="${not user.role.value.equals('admin')}">
                                    <form action="${userslist}" method="post"
                                          style="margin: 0"
                                          onsubmit="return confirm('<fmt:message
                                                  key="confirm_message"/>');">
                                        <input type="hidden" name="command"
                                               value="deleteUser" id="delete">
                                        <input type="hidden" name="id"
                                               value="${user.id}">
                                        <input type="submit" name="id"
                                               value="<fmt:message key="delete_button"/>"
                                               class="btn btn-danger"/>
                                    </form>
                                </c:if>
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
                                                         href="${userslist}?page=${pageNumber - 1}"><fmt:message
                                        key="pagin_prev"/></a>
                                </li>
                            </c:if>
                            <c:forEach begin="1" end="${amount_of_pages}"
                                       var="i">
                                <c:choose>
                                    <c:when test="${pageNumber eq i}">
                                        <li class="page-item disabled"><a
                                                class="page-link a-nav"
                                                href="${userslist}?page=${i}">${i}</a>
                                            <span class="sr-only">(current)</span>
                                        </li>
                                    </c:when>
                                    <c:otherwise>
                                        <li class="page-item"><a
                                                class="page-link a-nav"
                                                href="${userslist}?page=${i}">${i}</a>
                                        </li>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                            <c:if test="${pageNumber lt amount_of_pages}">
                                <li class="page-item"><a class="page-link a-nav"
                                                         href="${userslist}?page=${pageNumber + 1}"><fmt:message
                                        key="pagin_next"/></a>
                                </li>
                            </c:if>
                        </ul>
                    </nav>
                </c:if>
            </c:when>
            <c:otherwise>
                <h2 align="center" style="color: black"><fmt:message
                        key="empty_list_str"/></h2>
            </c:otherwise>
        </c:choose>
    </div>
    <br/>
    <br/>
    </body>
    </html>
</fmt:bundle>
