<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="url">${pageContext.request.requestURL}</c:set>
<c:set var="context"
       value="${fn:substring(url, 0, fn:length(url) - fn:length(pageContext.request.requestURI))}${pageContext.request.contextPath}"/>
<c:url var="home" value="/home"/>
<c:url var="userslist" value="/admin/users"/>
<c:url var="userAdd" value="/admin/user_add"/>
<html>
<head>
    <title>Users</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
</head>


<body style="background: url(${context}/img/background/usersback2.jpg)">
<h1 align="center" style="color: #030005"> Users list</h1>
<br/>
<br/>
<div class="container">
    <form action="${userslist}" method="post"
          onsubmit="return confirm('Delete this user?')">
    <div>
        <a href="${home}" class="btn btn-info">Go Home</a>
        <a href="${userAdd}" class="btn btn-success">Add Account</a>

            <input type="hidden" name="command" value="deleteUser">
            <input type="text" placeholder="Login" name="login" required autocomplete="off"
                   style="border-radius: 5px; border: none; padding: 5px; margin-left: 30px"/>
            <button type="submit" class="btn btn btn-danger">Delete by login
            </button>
    </div>
    </form>
    <br/>
    <c:choose>
        <c:when test="${not empty users}">
            <table class="table ">
                <thead>
                <tr style="font-size: larger; border: #030005; color: #030005">
                    <th>ID</th>
                    <th>Login</th>
                    <th>Email</th>
                    <th>Role</th>
                    <th>Country</th>
                    <th>Birth Date</th>
                    <th>Sex</th>
                    <th></th>
                </tr>
                </thead>
                <tbody
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
                                      onsubmit="return confirm('Delete user?');">
                                    <input type="hidden" name="command"
                                           value="deleteUser" id="delete">
                                    <input type="hidden" name="id"
                                           value="${user.id}">
                                    <input type="submit" name="id"
                                           value="Delete"
                                           class="btn btn-danger"/>
                                </form>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:when>
        <c:otherwise>
            <P>User list is empty</P>
        </c:otherwise>
    </c:choose>
</div>
<br/>
<br/>

</body>
</html>