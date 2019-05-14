<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="url">${pageContext.request.requestURL}</c:set>
<c:set var="context"
       value="${fn:substring(url, 0, fn:length(url) - fn:length(pageContext.request.requestURI))}${pageContext.request.contextPath}"/>
<c:url var="userslist" value="/admin/users"/>
<html>
<head>
    <title>Add user</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous"/>
</head>
<body style="background: url(${context}/img/background/useraddback1.jpg)">
<form action="${userslist}" method="post">
    <div class="container"
         style="width: 500px; background: rgba(188,196,189,1);
         margin-top: 100px; border-radius: 10px;">
        <h1 align="center" style="margin-top: 10px">CREATE ACCOUNT</h1>
        <div class="form-group" style="padding: 10px 10px 0 10px">
            <label for="LoginArea">Login</label>
            <input type="text" name="login" class="form-control" id="LoginArea"
                   placeholder="Enter login" required autocomplete="off">
        </div>
        <div class="form-group" style="padding: 10px 10px 0 10px">
            <label for="email">Email address</label>
            <input type="email" name="email" class="form-control" id="email"
                   aria-describedby="emailHelp" placeholder="Enter email" required
                   autocomplete="off">
        </div>
        <div class="form-group" style="padding: 10px 10px 0 10px">
            <label for="pass">Password</label>
            <input type="password" name="password" class="form-control"
                   id="pass" placeholder="Password" required>
        </div>
        <div class="form-group" style="padding: 10px 10px 0 10px">
            <label for="select">Role</label>
            <select class="form-control" id="select" name="role">
                <option value="0">Admin</option>
                <option value="1">Editor</option>
                <option value="2">User</option>
            </select>
            <input type="hidden" name="command" value="addUser"/>
            <button type="submit" class="btn btn-primary"
                    style="padding: 5px; margin: 10px 10px 10px 0;">Submit
            </button>
            <c:url var="home" value="/home"/>
            <a href="${home}" style="color: #030005" >Home</a>
            <a href="${userslist}" style="color: #030005; margin-left: 35px">Back to users list</a>
        </div>
</form>
</body>
</html>
