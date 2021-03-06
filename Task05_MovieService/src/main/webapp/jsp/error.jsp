<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<c:set var="url">${pageContext.request.requestURL}</c:set>
<c:url var="home" value="/home"/>
<c:set var="context"
       value="${fn:substring(url, 0, fn:length(url) - fn:length(pageContext.request.requestURI))}${pageContext.request.contextPath}"/>
<c:if test="${param.language == 'en'}">
    <fmt:setLocale value="en_EN" scope="session"/>
</c:if>
<c:if test="${param.language == 'ru'}">
    <fmt:setLocale value="ru_RU" scope="session"/>
</c:if>
<c:if test="${param.language == 'de'}">
    <fmt:setLocale value="de_DE" scope="session"/>
</c:if>
<fmt:bundle basename="error_page">
    <html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>404 NOT FOUND</title>
        <link href="https://fonts.googleapis.com/css?family=Montserrat:700,900"
              rel="stylesheet">
        <link rel="icon" href="${context}/img/errorTittleIcon.png"
              type="image/png"/>
        <link rel="stylesheet" href="${context}/css/error.css" type="text/css"/>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    </head>

    <body style="padding: 0; margin: 0; background: url(${context}/img/eback2.jpg) no-repeat; background-size: 100%">
    <div id="notfound">
        <div class="notfound">
            <div class="notfound-404">
                <h1>404</h1>
                <h2><fmt:message key="page_not_found"/></h2>
            </div>
            <h3>${error}</h3>
            <br/>
            <a href="${home}"><fmt:message key="home"/></a>
        </div>
    </div>
    </body>
    </html>
</fmt:bundle>
