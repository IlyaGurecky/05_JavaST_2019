<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<c:if test="${param.language == 'ru'}">
    <fmt:setLocale value="ru_RU" scope="session"/>
</c:if>
<c:if test="${param.language == 'de'}">
    <fmt:setLocale value="de_DE" scope="session"/>
</c:if>
<c:if test="${param.language == 'en'}">
    <fmt:setLocale value="en_EN" scope="session"/>
</c:if>
<fmt:bundle basename="profile_page">
    <c:set var="url">${pageContext.request.requestURL}</c:set>
    <c:set var="context"
           value="${fn:substring(url, 0, fn:length(url) - fn:length(pageContext.request.requestURI))}${pageContext.request.contextPath}"/>
    <c:url value="/user/profile" var="profile"/>
    <c:url value="/home" var="home"/>

    <html>
    <head>
        <meta charset="UTF-8">
        <link rel="stylesheet"
              href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
              integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
              crossorigin="anonymous">
        <link href="https://fonts.googleapis.com/css?family=Roboto:700"
              rel="stylesheet">
        <link rel="icon" href="${context}/img/profileIcon.png"
              type="image/png"/>
        <title>
            KM-<fmt:message key="tittle"/>
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
                       style="color: rgb(269, 83, 90); border: none"><fmt:message
                            key="profile"/></a>
                    <a class="nav-item nav-link" id="nav-profile-tab"
                       data-toggle="tab" href="#nav-profile" role="tab"
                       aria-controls="nav-profile" aria-selected="false"
                       style="color: rgb(269, 83, 90); border: none"><fmt:message
                            key="edit_profile"/></a>
                    <a class="nav-item nav-link" id="nav-contact-tab"
                       data-toggle="tab" href="#nav-contact" role="tab"
                       aria-controls="nav-contact" aria-selected="false"
                       style="color: rgb(269, 83, 90); border: none"><fmt:message
                            key="change_password"/></a>
                    <div style="margin-left: 60px">
                        <a href="${profile}?language=en"><img
                                src="${context}/img/america.png"
                                width="22" height="19" alt="EN"/></a>
                        <a href="${profile}?language=ru"
                           style="margin-left: 5px"><img
                                src="${context}/img/russia.png"
                                width="22" height="19" alt="RU"/></a>
                        <a href="${profile}?language=de"
                           style="margin-left: 5px"><img
                                src="${context}/img/germany.png"
                                width="22" height="19" alt="DE"/></a>
                    </div>

                </div>
            </nav>
            <div class="tab-content" id="nav-tabContent">
                <div class="tab-pane fade show active" id="nav-home"
                     role="tabpanel"
                     aria-labelledby="nav-home-tab">
                    <table class="table table" style="margin-top: 3%;">
                        <tbody style="color: #ffffff">
                        <tr>
                            <th scope="row"><fmt:message key="login"/>:</th>
                            <td>${user.login}</td>
                        </tr>
                        <tr>
                            <th scope="row"><fmt:message key="email"/>:</th>
                            <td>${user.email}</td>
                        </tr>
                        <tr>
                            <th scope="row"><fmt:message key="birth_date"/>:
                            </th>
                            <td>${user.birthDate}</td>
                        </tr>
                        <tr>
                            <th scope="row"><fmt:message key="role"/>:</th>
                            <td>${user.role.value}</td>
                        </tr>
                        <tr>
                            <th scope="row"><fmt:message key="sex"/>:</th>
                            <td>${user.sex}</td>
                        </tr>
                        <tr>
                            <th scope="row"><fmt:message key="country"/>:</th>
                            <td>${user.country}</td>
                        </tr>
                        </tbody>
                    </table>
                    <a href="${home}" class="btn btn-danger"
                       style="text-decoration-line: none; float: right">
                        <fmt:message key="home"/></a>
                </div>

                <div class="tab-pane fade" id="nav-profile" role="tabpanel"
                     aria-labelledby="nav-profile-tab">
                    <form action="${profile}" method="post"
                          style="padding-top: 7%;">
                        <input type="hidden" name="command" value="editProfile">
                        <div class="form-group">
                            <label for="loginField"
                                   style="color: #ffffff"><fmt:message
                                    key="login"/>*</label>
                            <input type="text" class="form-control" name="login"
                                   id="loginField" required
                                   placeholder="<fmt:message key="enter_login_placeholder"/>"
                                   value="${user.login}" style="color: black">
                        </div>
                        <div class="form-group">
                            <label for="exampleInputEmail1"
                                   style="color: #ffffff"><fmt:message
                                    key="email"/>*</label>
                            <input type="email" class="form-control"
                                   name="email"
                                   id="exampleInputEmail1" required
                                   placeholder="<fmt:message key="enter_email_placeholder"/>"
                                   value="${user.email}" style="color: black">
                        </div>
                        <div class="form-group">
                            <label for="date"
                                   style="color: #ffffff"><fmt:message
                                    key="birth_date"/></label>
                            <input type="date" class="form-control" name="date"
                                   id="date"
                                   value="${user.birthDate}"
                                   style="color: black">
                        </div>
                        <div class="form-group">
                            <label for="countrySelect"
                                   style="color: #ffffff"><fmt:message
                                    key="country"/></label>
                            <select class="form-control" id="countrySelect"
                                    name="country" style="color: black">
                                <option></option>
                                <option value="США">USA</option>
                                <option value="Великобритания">Great Britain
                                </option>
                                <option value="Россия">Russia</option>
                                <option value="Новая Зеландия">New Zealand
                                </option>
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
                                       style="color: #ffffff; margin-right: 4px"><fmt:message
                                        key="male"/></label>
                                <input type="radio" class="form-check-input"
                                       id="radio1" name="sex"
                                       value="м"
                                       <c:if test="${user.sex eq 'м'}">checked</c:if>/>
                            </div>
                            <div class="form-check-inline">
                                <label class="form-check-label" for="radio2"
                                       style="color: #ffffff; margin-right: 4px"><fmt:message
                                        key="female"/></label>
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
                                   placeholder="<fmt:message key="password_placeholder"/>">
                            <small class="form-text text-muted"
                                   style="color: #ffffff"><fmt:message
                                    key="confirm_password_label"/>.
                            </small>
                        </div>
                        <div>
                            <button type="submit" class="btn btn-danger">
                                <fmt:message key="submit_button"/>
                            </button>
                            <a href="${home}" class="btn btn-danger"
                               style="text-decoration-line: none; float: right">
                                <fmt:message key="home"/></a>
                        </div>
                    </form>
                </div>

                <div class="tab-pane fade" id="nav-contact" role="tabpanel"
                     aria-labelledby="nav-contact-tab">
                    <form action="${profile}" method="post"
                          style="padding-top: 7%;">
                        <input type="hidden" name="command" value="changePass">
                        <div class="form-group">
                            <label for="exampleInputEmail1"
                                   style="color: #ffffff"><fmt:message
                                    key="old_password_label"/>*</label>
                            <input type="password" name="old_password"
                                   class="form-control" required
                                   aria-describedby="emailHelp"
                                   placeholder="<fmt:message key="enter_old_password_placeholder"/>">
                        </div>
                        <div class="form-group">
                            <label for="new_pass"
                                   style="color: #ffffff"><fmt:message
                                    key="new_password_label"/>*</label>
                            <input type="password" name="new_password1"
                                   id="new_pass"
                                   class="form-control" required
                                   placeholder="<fmt:message key="password_placeholder"/>">
                        </div>
                        <div class="form-group">
                            <label for="confirm"
                                   style="color: #ffffff"><fmt:message
                                    key="confirm_new_password"/>*</label>
                            <input type="password" name="new_password2"
                                   id="confirm"
                                   class="form-control" required
                                   placeholder="<fmt:message key="password_placeholder"/>">
                        </div>
                        <div>
                            <button type="submit" class="btn btn-danger">
                                <fmt:message key="submit_button"/>
                            </button>
                            <a href="${home}" class="btn btn-danger"
                               style="text-decoration-line: none; float: right">
                                <fmt:message key="home"/></a>
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
</fmt:bundle>