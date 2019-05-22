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
<fmt:bundle basename="film_add_page">
    <c:set var="url">${pageContext.request.requestURL}</c:set>
    <c:set var="context"
           value="${fn:substring(url, 0, fn:length(url) - fn:length(pageContext.request.requestURI))}${pageContext.request.contextPath}"/>
    <c:url var="films" value="/films"/>
    <html>
    <head>
        <title><fmt:message key="tittle"/></title>
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
              rel="stylesheet"
              integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
              crossorigin="anonymous"/>
    </head>
    <body style="background: url(${context}/img/background/useraddback1.jpg)">
    <form action="${films}" method="post" enctype="multipart/form-data">
        <div class="container"
             style="width: 500px; background: rgba(188,196,189,1);
         margin-top: 40px; border-radius: 10px;">
            <h1 align="center" style="margin-top: 10px"><fmt:message
                    key="main_str"/></h1>

            <div class="form-group" style="padding: 10px 10px 0 10px">
                <label for="nameArea"><fmt:message key="name_label"/>*</label>
                <input type="text" name="name" class="form-control"
                       id="nameArea"
                       placeholder="<fmt:message key="name_placeholder"/>"
                       required autocomplete="off">
            </div>

            <div class="form-group" style="padding: 10px 10px 0 10px">
                <label for="premier_date"><fmt:message
                        key="premier_date_label"/>*</label>
                <input type="date" min="1895-01-01" name="premier_date"
                       class="form-control"
                       id="premier_date" required
                       autocomplete="off">
            </div>
            <div class="form-group" style="padding: 10px 10px 0 10px">
                <label for="countrySelect"><fmt:message
                        key="country_label"/></label>
                <select class="form-control" id="countrySelect" name="country">
                    <option></option>
                    <option value="США">USA</option>
                    <option value="Великобритания">Great Britain</option>
                    <option value="Россия">Russia</option>
                    <option value="Новая Зеландия">New Zealand</option>
                    <option value="Австралия">Australia</option>
                    <option value="Испания">Spain</option>
                    <option value="Франция">France</option>
                    <option value="Германия">Germany</option>
                    <option value="Китай">China</option>
                    <option value="Беларусь">Belarus</option>
                    <option value="Украина">Ukraine</option>
                    <option value="Польша">Poland</option>
                </select>
            </div>
            <div class="form-group" style="padding: 10px 10px 0 10px">
                <label for="category"><fmt:message
                        key="category_label"/></label>
                <select class="form-control" id="category" name="category">
                    <option></option>
                    <option value="Комедия">Comedy</option>
                    <option value="Ужасы">Horror</option>
                    <option value="Драмы">Drama</option>
                    <option value="Фэнтези">Fantasy</option>
                    <option value="Боевик">Action</option>
                    <option value="Семейные">Family</option>
                    <option value="Детективы">Detective</option>
                </select>
            </div>
            <div class="form-group custom-file"
                 style="width: 450px; margin-left: 10px">
                <label class="custom-file-label" for="customFile"><fmt:message
                        key="film_photo_placeholder"/></label>
                <input class="custom-file-input" name="filmImage"
                       accept="/image/*"
                       type="file" id="customFile" placeholder="Choose Image">
            </div>
            <div class="form-group" style="padding: 10px 10px 0 10px">
                <input type="hidden" name="command" value="addFilm"/>
                <button type="submit" class="btn btn-primary"
                        style="padding: 5px; margin: 10px 10px 10px 0;"><fmt:message key="submit_button"/>
                </button>
                <c:url var="home" value="/home"/>
                <a href="${home}" style="color: #030005"><fmt:message
                        key="home"/></a>
                <c:url var="filmsList" value="/films"/>
                <a href="${filmsList}"
                   style="color: #030005; margin-left: 20px"><fmt:message
                        key="to_films_list"/></a>
            </div>
    </form>
    </body>
    </html>
</fmt:bundle>
