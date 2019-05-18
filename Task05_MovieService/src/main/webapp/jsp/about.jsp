<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="url">${pageContext.request.requestURL}</c:set>
<c:set var="context"
       value="${fn:substring(url, 0, fn:length(url) - fn:length(pageContext.request.requestURI))}${pageContext.request.contextPath}"/>
<c:url var="home" value="/home"/>

<html>
<head>
    <title>About</title>
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <style>
        .footer {
            position: absolute;
            right: 0;
            bottom: 0;
            left: 0;
            padding: 3rem;
            background-color: #222222;
            text-align: center;
        }
    </style>
</head>

<body style="background: url(${context}/img/aboutBack3.jpg); background-size: 100%">

<header class="text-center py-3 mb-4" style="background: #222222">
    <div class="container">
        <a style="color: gold; font-size: 40px; text-decoration-line: none"
           href="${home}">KinoMan</a>
    </div>
</header>

<div class="container">
    <div class="row" style="position: relative">
        <div class="col-xl-4 col-md-6 mb-4"
             style="position: absolute; left:50%; margin-left:-190px">
            <div class="card border-0 shadow"
                 style="background: #222222; color: gold">
                <img src="${context}/img/author.png" width="461" height="270"
                     class="card-img-top" alt="...">
                <div class="card-body text-center">
                    <h5 class="card-title mb-0">Ilya Guretsky</h5>
                    <div class="card-text" style="color: white">EPAM Training
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="footer" style="color: white;">
    This footer will always be positioned at the bottom of the page, but <strong>not fixed</strong>.</div>
</body>
</html>
