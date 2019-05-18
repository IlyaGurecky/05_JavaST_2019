<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<c:set var="url">${pageContext.request.requestURL}</c:set>
<c:set var="context"
       value="${fn:substring(url, 0, fn:length(url) - fn:length(pageContext.request.requestURI))}${pageContext.request.contextPath}"/>
<c:url value="/user/profile" var="profile"/>
<c:url value="/home" var="home"/>
<%--<c:set value="${sessionScope.user}" var="user"/>--%>

<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
          crossorigin="anonymous">
    <link href="https://fonts.googleapis.com/css?family=Roboto:700"
          rel="stylesheet">
    <link rel="icon" href="${context}/img/profileIcon.png" type="image/png"/>
    <title>
        KM-Profile
    </title>
</head>


<body style="background: url(${context}/img/background/profileBack.jpg) no-repeat; background-size: 100%;">

<div class="container h-75 d-flex justify-content-center"
     style="margin-top: 40px">
    <div class="jumbotron my-auto"
         style="width: 60%; padding: 20px; background: rgba(13, 20, 33, 0.89)">
        <nav>
            <div class="nav nav-tabs" id="nav-tab" role="tablist">
                <a class="nav-item nav-link active" id="nav-home-tab"
                   data-toggle="tab" href="#nav-home" role="tab"
                   aria-controls="nav-home" aria-selected="true"
                   style="color: rgb(269, 83, 90); border: none">Profile</a>
                <a class="nav-item nav-link" id="nav-profile-tab"
                   data-toggle="tab" href="#nav-profile" role="tab"
                   aria-controls="nav-profile" aria-selected="false"
                   style="color: rgb(269, 83, 90); border: none">Change
                    profile info</a>
                <a class="nav-item nav-link" id="nav-contact-tab"
                   data-toggle="tab" href="#nav-contact" role="tab"
                   aria-controls="nav-contact" aria-selected="false"
                   style="color: rgb(269, 83, 90); border: none">Change
                    password</a>
            </div>
        </nav>
        <div class="tab-content" id="nav-tabContent">
            <div class="tab-pane fade show active" id="nav-home" role="tabpanel"
                 aria-labelledby="nav-home-tab">
                <table class="table table" style="margin-top: 3%;">
                    <tbody style="color: #ffffff">
                    <tr>
                        <th scope="row">Login:</th>
                        <td>${user.login}</td>
                    </tr>
                    <tr>
                        <th scope="row">Email:</th>
                        <td>${user.email}</td>
                    </tr>
                    <tr>
                        <th scope="row">Birth date:</th>
                        <td>${user.birthDate}</td>
                    </tr>
                    <tr>
                        <th scope="row">Role:</th>
                        <td>${user.role.value}</td>
                    </tr>
                    <tr>
                        <th scope="row">Sex:</th>
                        <td>${user.sex}</td>
                    </tr>
                    <tr>
                        <th scope="row">Country:</th>
                        <td>${user.country}</td>
                    </tr>
                    </tbody>
                </table>
                <a href="${home}" class="btn btn-danger"
                   style="text-decoration-line: none; float: right">
                    Home</a>
            </div>

            <div class="tab-pane fade" id="nav-profile" role="tabpanel"
                 aria-labelledby="nav-profile-tab">
                <form action="${profile}" method="post"
                      style="padding-top: 7%;">
                    <input type="hidden" name="command" value="editProfile">
                    <div class="form-group">
                        <label for="loginField"
                               style="color: #ffffff">Login*</label>
                        <input type="text" class="form-control" name="login"
                               id="loginField" required
                               placeholder="Enter login"
                               value="${user.login}" style="color: black">
                    </div>
                    <div class="form-group">
                        <label for="exampleInputEmail1" style="color: #ffffff">Email*</label>
                        <input type="email" class="form-control" name="email"
                               id="exampleInputEmail1" required
                               placeholder="Enter email"
                               value="${user.email}" style="color: black">
                    </div>
                    <div class="form-group">
                        <label for="date" style="color: #ffffff">Birth
                            Date</label>
                        <input type="date" class="form-control" name="date"
                               id="date"
                               value="${user.birthDate}" style="color: black">
                    </div>
                    <div class="form-group">
                        <label for="countrySelect"
                               style="color: #ffffff">Country</label>
                        <select class="form-control" id="countrySelect"
                                name="country" style="color: black">
                            <option></option>
                            <option value="США">USA</option>
                            <option value="Великобритания">Great Britain
                            </option>
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
                    <div class="form-group">
                        <div class="form-check-inline">
                            <label class="form-check-label" for="radio1"
                                   style="color: #ffffff; margin-right: 4px">Male</label>
                            <input type="radio" class="form-check-input"
                                   id="radio1" name="sex"
                                   value="м"
                                   <c:if test="${user.sex eq 'м'}">checked</c:if>/>
                        </div>
                        <div class="form-check-inline">
                            <label class="form-check-label" for="radio2"
                                   style="color: #ffffff; margin-right: 4px">Female</label>
                            <input type="radio" class="form-check-input"
                                   id="radio2" name="sex"
                                   value="ж"
                                   <c:if test="${user.sex eq 'ж'}">checked</c:if>
                                   style="color: #ffffff"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <input type="password" class="form-control"
                               name="password"
                               required
                               placeholder="Password">
                        <small class="form-text text-muted"
                               style="color: #ffffff">Confirm password.
                        </small>
                    </div>
                    <div>
                        <button type="submit" class="btn btn-danger">Submit
                        </button>
                        <a href="${home}" class="btn btn-danger"
                           style="text-decoration-line: none; float: right">
                            Home</a>
                    </div>
                </form>
            </div>

            <div class="tab-pane fade" id="nav-contact" role="tabpanel"
                 aria-labelledby="nav-contact-tab">
                <form action="${profile}" method="post"
                      style="padding-top: 7%;">
                    <input type="hidden" name="command" value="changePass">
                    <div class="form-group">
                        <label for="exampleInputEmail1" style="color: #ffffff">Old
                            password*</label>
                        <input type="password" name="old_password"
                               class="form-control" required
                               aria-describedby="emailHelp"
                               placeholder="Enter old password">
                    </div>
                    <div class="form-group">
                        <label for="new_pass" style="color: #ffffff">New
                            password*</label>
                        <input type="password" name="new_password1"
                               id="new_pass"
                               class="form-control" required
                               placeholder="Password">
                    </div>
                    <div class="form-group">
                        <label for="confirm" style="color: #ffffff">Confirm new
                            password*</label>
                        <input type="password" name="new_password2"
                               id="confirm"
                               class="form-control" required
                               placeholder="Password">
                    </div>
                    <div>
                        <button type="submit" class="btn btn-danger">Submit
                        </button>
                        <a href="${home}" class="btn btn-danger"
                           style="text-decoration-line: none; float: right">
                            Home</a>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>
</body>
</html>