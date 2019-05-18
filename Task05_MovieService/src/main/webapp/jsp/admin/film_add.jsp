<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="url">${pageContext.request.requestURL}</c:set>
<c:set var="context"
       value="${fn:substring(url, 0, fn:length(url) - fn:length(pageContext.request.requestURI))}${pageContext.request.contextPath}"/>
<c:url var="films" value="/films"/>

<html>
<head>
    <title>Add film</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous"/>
</head>
<body style="background: url(${context}/img/background/useraddback1.jpg)">
<form action="${films}" method="post">
    <div class="container"
         style="width: 500px; background: rgba(188,196,189,1);
         margin-top: 40px; border-radius: 10px;">
        <h1 align="center" style="margin-top: 10px">ADD FILM</h1>

        <div class="form-group" style="padding: 10px 10px 0 10px">
            <label for="nameArea">Name*</label>
            <input type="text" name="name" class="form-control" id="nameArea"
                   placeholder="Enter name" required autocomplete="off">
        </div>

        <div class="form-group" style="padding: 10px 10px 0 10px">
            <label for="premier_date">Premier date*</label>
            <input type="date" min="1895-01-01" name="premier_date"
                   class="form-control"
                   id="premier_date" required
                   autocomplete="off">
        </div>
        <div class="form-group" style="padding: 10px 10px 0 10px">
            <label for="countrySelect">Country</label>
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
            <label for="category">Category</label>
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
        <div class="form-group" style="padding: 10px 10px 0 10px">
            <label for="ImageNameArea">Image name</label>
            <input type="text" name="imageName" class="form-control"
                   id="ImageNameArea"
                   placeholder="Enter future image name">
            <input type="file" id="customFile" placeholder="Choose Image">
        </div>
        <div class="form-group" style="padding: 10px 10px 0 10px">
            <input type="hidden" name="command" value="addFilm"/>
            <button type="submit" class="btn btn-primary"
                    style="padding: 5px; margin: 10px 10px 10px 0;">Submit
            </button>
            <c:url var="home" value="/home"/>
            <a href="${home}" style="color: #030005">Home</a>
            <c:url var="filmsList" value="/films"/>
            <a href="${filmsList}" style="color: #030005; margin-left: 20px">To films list</a>
        </div>
</form>
</body>
</html>
