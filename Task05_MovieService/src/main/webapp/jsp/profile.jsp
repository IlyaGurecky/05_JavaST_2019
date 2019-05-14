<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</head>
<body>
<%--@elvariable id="client" type="by.epam.site.entity.Client"--%>
<c:set var="elem" value="${client}" />
<div>
    <div class="container">
        <div id="main">
            <div class="row" id="real-estates-detail">
                <div class="col-lg-3 col-md-3 col-xs-8">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <header class="panel-title">
                                <div class="text-center">
                                    <strong>Site user</strong>
                                </div>
                            </header>
                        </div>
                        <div class="panel-body">
                            <div class="text-center" id="author">
                                <img width="80%" height="80%" src="${pageContext.request.contextPath}/${elem.filePath}"/>
                                <h3><c:out value="${ elem.name }"/> <c:out value="${ elem.surname }"/></h3>
                                <small class="label label-warning">Republic of Belarus</small>
                                <p>Put on a happy face!</p>
                                <p class="sosmed-author">
                                    <a href=""><i style="font-size: 30px;" class="fab fa-instagram" title="Instagram"></i></a>
                                    <a href=""><i style="font-size: 30px;" class="far fa-question-circle" title="Ask"></i></a>
                                    <a href=""><i style="font-size: 30px;" class="fab fa-vk" title="VK"></i></a>
                                </p>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-8 col-md-8 col-xs-12">
                    <div class="panel">
                        <div class="panel-body">
                            <ul id="myTab" class="nav nav-pills">
                                <li class="active"><a href="#detail" data-toggle="tab">About Person</a></li>
                                <li class=""><a href="#contact" data-toggle="tab">Change information about person</a></li>
                                <li class=""><a href="#data" data-toggle="tab">Change password</a></li>
                            </ul>
                            <div id="myTabContent" class="tab-content">
                                <hr>
                                <div class="tab-pane fade active in" id="detail">
                                    <h4>Profile history</h4>
                                    <table class="table table-th-block">
                                        <tbody>
                                        <tr><td class="active">First name:</td><td><c:out value="${ elem.name }"/></td></tr>
                                        <tr><td class="active">Second name:</td><td><c:out value="${ elem.surname }"/></td></tr>
                                        <tr><td class="active">Patronymic:</td><td><c:out value="${ elem.patronymic }"/></td></tr>
                                        <tr><td class="active">Date of birth</td><td><c:out value="${ elem.dateOfBirth }"/></td></tr>
                                        <tr><td class="active">Person email:</td><td><c:out value="${ elem.email }"/></td></tr>
                                        <tr><td class="active">Person phone number:</td><td><c:out value="${ elem.phone }"/></td></tr>
                                        </tbody>
                                        <p style="background:#000; color:#fff;" id="elem">Result</p>
                                    </table>
                                </div>
                                <div class="tab-pane fade" id="contact">
                                    <p></p>
                                    <c:url value="/changeValue" var="changeValueURL"/>
                                    <form action="${changeValueURL}" role="form" method="post">
                                        <div class="form-group">
                                            <label>Name</label>
                                            <input type="text" name="changeName" class="form-control rounded" placeholder="Write new name">
                                        </div>
                                        <div class="form-group">
                                            <label>Surname</label>
                                            <input type="text" name="changeSurname" class="form-control rounded" placeholder="Write new surname">
                                        </div>
                                        <div class="form-group">
                                            <label>Patronymic</label>
                                            <input type="text" name="changePatronymic" class="form-control rounded" placeholder="Write new patronymic">
                                        </div>
                                        <div class="form-group">
                                            <label>Date of birth</label>
                                            <input type="text" name="changeDateOfBirth" class="form-control rounded" placeholder="Write new date of birth">
                                        </div>
                                        <div class="form-group">
                                            <label>E-mail</label>
                                            <input type="email" name="changeEmail" class="form-control rounded" placeholder="Write new E-mail address">
                                        </div>
                                        <div class="form-group">
                                            <label>Phone number</label>
                                            <input type="text" name="changePhoneNumber" class="form-control rounded" placeholder="Write new phone number">
                                        </div>
                                        <div class="form-group">
                                            <input type="submit" class="btn btn-success" data-original-title="" title="" value="Change">
                                        </div>
                                    </form>
                                </div>
                                <div class="tab-pane fade" id="data">
                                    <p></p>
                                    <c:url value="" var="changePasswordURL"/>
                                    <form action="${changePasswordURL}" role="form" method="post">
                                        <div class="form-group">
                                            <label>Password</label>
                                            <input type="password" name="changePassword" class="form-control rounded" placeholder="Write new name" required>
                                        </div>
                                        <div class="form-group">
                                            <label>Confirm Password</label>
                                            <input type="password"

                                                   name="changeConfirm" class="form-control rounded" placeholder="Write new surname" required>
                                        </div>
                                        <div class="form-group">
                                            <input type="submit" class="btn btn-success" data-original-title="" title="" value="Change">
                                        </div>
                                        <div style="color:#60c9a8; font-size: 18px;">
                                            ${errorPassword}
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div><!— /.main —>
</div>
</body>
</html>
