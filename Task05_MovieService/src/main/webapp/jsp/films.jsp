<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" %>
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

    <style>
        .col-1-3 {
            width: 33.3333333333%;
            float: left;
        }

        .col-2-3 {
            width: 100%;
            float: left;
            font-size: 18px;
        }

        .container:after, .row:after {
            content: "";
            display: table;
            clear: both;
        }

        .row {
            margin-bottom: 5px;
        }

        hr {
            width: 50%;
            border: none; /* Убираем границу */
            background-color: red; /* Цвет линии */
            color: #3bf8db; /* Цвет линии для IE6-7 */
            height: 2px; /* Толщина линии */
        }

        input[type=submit]:hover {
            color:#fff;
            background-color: black;
        }

        input {
            width: 140px;
            text-align: center;
            background: #ff4a48;
            border: none;
            font-family: 'Play', sans-serif;
            font-size: 16px;
            padding: 10px 0;
            transition: border 0.5s;
            outline: none;
            color: white;
            border-radius: 10px;
        }
    </style>
</head>
<body>
<c:forEach var="film" items="${films}" varStatus="status">
    <div class="main">
        <div class="container">
            <div class="row">
                <tr>
                    <div class="col-1-3">
                        <td ><img width="255px" height="310px" src="${context}/img/films/${film.imageName}"/></td>
                    </div>
                    <div style="text-align: left;">
                        <div class="col-2-3">
                            <td><h3><c:out value="${ film.name }"/> </h3></td><br><br>
                            <td><c:out value="${ film.premierDate }"/> </td><br><br>
                            <td><c:out value="${ film.category }"/> </td><br><br>
                            <td><c:out value="${ film.country }"/> </td><br><br>
                            <br><br>
                            <form action="" method="post">
                                <td><input type="submit" value="Open"></td>
                            </form>
                        </div>
                    </div>
                </tr>
            </div>
        </div>
    </div>
    <hr/>
</c:forEach>

</body>
</html>


