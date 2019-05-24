<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<fmt:bundle basename="user_add_page">
    <c:set var="url">${pageContext.request.requestURL}</c:set>
    <c:set var="context"
           value="${fn:substring(url, 0, fn:length(url) - fn:length(pageContext.request.requestURI))}${pageContext.request.contextPath}"/>
    <c:url var="userslist" value="/admin/users"/>
    <c:url var="add_user" value="/admin/user_add"/>
    <html>
    <head>
        <title><fmt:message key="tittle"/></title>
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
              rel="stylesheet"
              integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
              crossorigin="anonymous"/>
    </head>
    <body style="background: url(${context}/img/background/useraddback1.jpg)">
    <div style="margin: 10px 0 0 10px;">
        <a href="${add_user}?language=en"><img
                src="${context}/img/america.png"
                width="22" height="19" alt="EN"/></a>
        <a href="${add_user}?language=ru"
           style="margin-left: 5px"><img
                src="${context}/img/russia.png"
                width="22" height="19" alt="RU"/></a>
        <a href="${add_user}?language=de"
           style="margin-left: 5px"><img
                src="${context}/img/germany.png"
                width="22" height="19" alt="DE"/></a>
    </div>
    <form action="${userslist}" method="post">
        <div class="container"
             style="width: 500px; background: rgba(188,196,189,1);
         margin-top: 60px; border-radius: 10px;">
            <h1 align="center" style="margin-top: 10px"><fmt:message
                    key="main_str"/></h1>
            <div class="form-group" style="padding: 10px 10px 0 10px">
                <label for="LoginArea"><fmt:message key="login_label"/>*</label>
                <input type="text" name="login" class="form-control"
                       id="LoginArea"
                       placeholder="<fmt:message key="login_placeholder"/>"
                       required autocomplete="off">
            </div>
            <div class="form-group" style="padding: 10px 10px 0 10px">
                <label for="email"><fmt:message key="email_label"/>*</label>
                <input type="email" name="email" class="form-control" id="email"
                       aria-describedby="emailHelp"
                       placeholder="<fmt:message key="email_placeholder"/>"
                       required
                       autocomplete="off">
            </div>
            <div class="form-group" style="padding: 10px 10px 0 10px">
                <label for="pass"><fmt:message key="password_label"/>*</label>
                <input type="password" name="password" class="form-control"
                       id="pass"
                       placeholder="<fmt:message key="password_placeholder"/>"
                       required>
            </div>
            <div class="form-group" style="padding: 10px 10px 0 10px">
                <label for="select"><fmt:message key="role"/>*</label>
                <select class="form-control" id="select" name="role">
                    <option value="0">Admin</option>
                    <option value="1">Editor</option>
                    <option value="2">User</option>
                </select>
                <input type="hidden" name="command" value="addUser"/>
                <button type="submit" class="btn btn-primary"
                        style="padding: 5px; margin: 10px 10px 10px 0;">
                    <fmt:message key="submit_button"/>
                </button>
                <c:url var="home" value="/home"/>
                <a href="${home}" style="color: #030005"><fmt:message
                        key="home"/></a>
                <a href="${userslist}"
                   style="color: #030005; margin-left: 35px"><fmt:message
                        key="back_to_list"/></a>
            </div>
    </form>
    </body>
    </html>
</fmt:bundle>
