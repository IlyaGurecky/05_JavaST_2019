<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<fmt:bundle basename="film_page">
    <c:set var="url">${pageContext.request.requestURL}</c:set>
    <c:set var="context"
           value="${fn:substring(url, 0, fn:length(url) - fn:length(pageContext.request.requestURI))}${pageContext.request.contextPath}"/>
    <c:url var="filmsList" value="/films"/>
    <c:url var="signin" value="/signin"/>
    <c:url var="home" value="/home"/>
    <c:url var="seeLaterList" value="/user/see_later"/>
    <c:url var="profile" value="/user/profile"/>
    <c:url var="watched" value="/user/watched"/>
    <c:url var="filmPage" value="/film"/>

    <html>
    <head>
        <title>${film.name}</title>
        <link rel="icon" href="${context}/img/filmPageIcon.png"
              type="image/png"/>
        <link rel="stylesheet"
              href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    </head>

    <body style="background: url(${context}/img/background/filmPageBack.jpg);
            background-size: 100%;">
    <nav class="navbar fixed-top navbar-dark">
        <a class="navbar-brand btn btn-warning" href="${home}" style="background: gold;
     padding: 10px; border-radius: 5px; color: black">KinoMan</a>
        <a href="${filmsList}" style="background: gold;
     padding: 5px; border-radius: 5px; color: black; margin-bottom: 20px; text-decoration-line: none"><fmt:message key="to_films_list"/></a>
        <div class="row">
            <div class="dropdown">
                <button class="btn btn-secondary dropdown-toggle" type="button"
                        id="dropdownMenuButton" data-toggle="dropdown"
                        aria-haspopup="true" aria-expanded="false"
                        style="background: gold; color: black; padding: 13px;
                    border-radius: 5px;
                    margin-right: 10px">
                    <c:choose>
                        <c:when test="${not empty user}">
                            ${user.login}
                        </c:when>
                        <c:otherwise>
                            <fmt:message key="menu"/>
                        </c:otherwise>
                    </c:choose>
                </button>
                <div class="dropdown-menu dropdown-menu-right"
                     aria-labelledby="dropdownMenuButton">
                    <c:choose>
                        <c:when test="${not empty user}">
                            <a class=" dropdown-item" href="${home}"><fmt:message key="home"/></a>
                            <c:if test="${not user.role.value.equals('admin')
                        and not user.role.value.equals('editor')}">
                                <a class="dropdown-item" href="${seeLaterList}"><fmt:message key="see_later"/></a>
                                <a class="dropdown-item"
                                   href="${watched}"><fmt:message key="watched"/></a>
                            </c:if>
                            <a class="dropdown-item"
                               href="${profile}"><fmt:message key="profile"/></a>
                            <form action="${home}" method="post"
                                  class="dropdown-item"
                                  style="padding: 0; margin: 0">
                                <input type="hidden" name="command"
                                       value="signout"/>
                                <input type="submit"
                                       value="<fmt:message key="logout"/>" style="background: none;
                           color: black; border: none; margin-left: 40px"></form>
                        </c:when>
                        <c:otherwise>
                            <c:url var="signin" value="/signin"/>
                            <a class=" dropdown-item" href="${home}"><fmt:message key="home"/></a>
                            <a class="dropdown-item" href="${signin}"><fmt:message key="signin"/></a>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
    </nav>

    <div class="container" style="padding-top: 50px;
            padding-bottom: 50px;">
        <div class="row">
            <div class="col-12">
                <div class="card" style="padding: 10px;
            background: rgba(13, 20, 35, 0.9); border-radius: 10px;">
                    <div class="card-body">
                        <div class="card-title mb-4">
                            <div class="d-flex justify-content-start">
                                <div class="image-container">
                                    <c:choose>
                                        <c:when test="${not empty film.imageName}">
                                            <img src="${context}/img/films/${film.imageName}"
                                                 id="imgProfile"
                                                 style="width: 270px; height: 340px;"
                                                 class="img-thumbnail"/>
                                        </c:when>
                                        <c:otherwise>
                                            <img src="${context}/img/imageNotFound.jpg"
                                                 id="imgProfile"
                                                 style="width: 270px; height: 340px;"
                                                 class="img-thumbnail"/>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                                <div class="userData ml-3"
                                     style="position: relative">
                                    <h2 class="d-block"
                                        style="font-size: 1.5rem; color: white; font-weight: bold">
                                            ${film.name}
                                    </h2>
                                    <c:if test="${user.role.value.equals('admin')}">
                                        <div style="position: absolute; bottom: 0; height: 40px;">
                                            <form action="${filmsList}"
                                                  method="post"
                                                  style="margin-left: 5px"
                                                  onsubmit="return confirm('Delete this film?')">
                                                <td>
                                                    <input type="hidden"
                                                           name="id"
                                                           value="${film.id}"/>
                                                    <input type="hidden"
                                                           name="command"
                                                           value="deleteFilm">
                                                    <input type="submit"
                                                           value="<fmt:message key="delete_button"/>"
                                                           class="btn btn-danger"
                                                           style="color: white; width: 132px"/>
                                                </td>
                                            </form>
                                        </div>
                                    </c:if>
                                    <c:if test="${not empty user
                                    and not user.role.value.equals('admin')
                                    and not user.role.value.equals('editor')}">
                                    <form action="${filmPage}?fId=${film.id}"
                                          method="post"
                                          style="margin-left: 5px; position: absolute; bottom: 40px">
                                        <input type="hidden" name="id"
                                               value="${film.id}"/>
                                        <input type="hidden"
                                               name="command"
                                               value="watchFilm">
                                        <input type="submit"
                                               value="<fmt:message key="watch_button"/>"
                                               class="btn btn-warning"
                                               style="color: white"/>
                                    </form>
                                    <div style="position: absolute; bottom: 0; height: 40px;">
                                        <c:choose>
                                        <c:when test="${not isInList}">
                                        <form action="${filmsList}"
                                              method="post"
                                              style="margin-left: 5px">
                                            <td>
                                                <input type="hidden"
                                                       name="filmId"
                                                       value="${film.id}">
                                                <input type="hidden"
                                                       name="command"
                                                       value="addToSeeLater">
                                                <input type="submit"
                                                       value="<fmt:message key="see_later"/>"
                                                       class="btn btn-success"
                                                       style="color: white; width: 132px"/>
                                            </td>
                                        </form>
                                    </div>
                                    </c:when>
                                    <c:otherwise>
                                        <form style="margin-left: 5px;">
                                            <button type="button"
                                                    class="btn btn-outline-success"
                                                    disabled><img
                                                    src="${context}/img/seeLaterButtonIcon.png"
                                                    width="22px"
                                                    style="margin: 0 6px 1px 0; float: bottom; color: white"><fmt:message key="text_on_disabled_button"/>
                                            </button>
                                        </form>
                                    </c:otherwise>
                                    </c:choose>
                                </div>
                                </c:if>
                                <c:if test="${empty user}">
                                    <form style="margin-left: 5px; position: absolute;
                                 bottom: 0; height: 94px; width: 150px"
                                          action="${signin}" method="get">
                                        <button type="submit"
                                                class="btn btn-outline-warning">
                                             <fmt:message key="text_for_unauthorized_user"/>
                                        </button>
                                    </form>
                                </c:if>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-12">
                            <ul class="nav nav-tabs mb-4" id="myTab"
                                role="tablist">
                                <li class="nav-item">
                                    <a class="nav-link active"
                                       id="basicInfo-tab" data-toggle="tab"
                                       href="#basicInfo" role="tab"
                                       aria-controls="basicInfo"
                                       aria-selected="true"
                                       style="color: gold; background: none"><fmt:message key="info"/></a>
                                </li>
                                <c:if test="${user.role.value.equals('editor')}">
                                    <li class="nav-item">
                                        <a class="nav-link"
                                           id="connectedServices-tab"
                                           data-toggle="tab"
                                           href="#connectedServices" role="tab"
                                           aria-controls="connectedServices"
                                           aria-selected="false"
                                           style="color: gold; background: none"><fmt:message key="edit_film"/></a>
                                    </li>
                                </c:if>
                            </ul>
                            <div class="tab-content ml-1" id="myTabContent">
                                <div class="tab-pane fade show active"
                                     id="basicInfo" role="tabpanel"
                                     aria-labelledby="basicInfo-tab">
                                    <div class="row">
                                        <div class="col-sm-3 col-md-2 col-5">
                                            <label style="font-weight:bold; color: gold">
                                                <fmt:message key="name"/></label>
                                        </div>
                                        <div class="col-md-8 col-6"
                                             style="color: white">
                                                ${film.name}
                                        </div>
                                    </div>
                                    <hr/>

                                    <div class="row">
                                        <div class="col-sm-3 col-md-2 col-5">
                                            <label style="font-weight:bold;color: gold"><fmt:message key="premier_date"/></label>
                                        </div>
                                        <div class="col-md-8 col-6"
                                             style="color: white">
                                                ${film.premierDate}
                                        </div>
                                    </div>
                                    <hr/>


                                    <div class="row">
                                        <div class="col-sm-3 col-md-2 col-5">
                                            <label style="font-weight:bold;color: gold"><fmt:message key="category"/></label>
                                        </div>
                                        <div class="col-md-8 col-6"
                                             style="color: white">
                                                ${film.category}
                                        </div>
                                    </div>
                                    <hr/>
                                    <div class="row">
                                        <div class="col-sm-3 col-md-2 col-5">
                                            <label style="font-weight:bold;color: gold"><fmt:message key="country"/></label>
                                        </div>
                                        <div class="col-md-8 col-6"
                                             style="color: white">
                                                ${film.country}
                                        </div>
                                    </div>
                                    <hr/>
                                    <div class="row">
                                        <div class="col-sm-3 col-md-2 col-5">
                                            <label style="font-weight:bold;color: gold"><fmt:message key="description"/></label>
                                        </div>
                                        <div class="col-md-8 col-6"
                                             style="color: white">
                                                ${film.description}
                                        </div>
                                    </div>
                                    <hr/>

                                </div>
                                <div class="tab-pane fade"
                                     id="connectedServices" role="tabpanel"
                                     aria-labelledby="ConnectedServices-tab">
                                    <form action="${filmPage}?fId=${film.id}"
                                          method="post"
                                          enctype="multipart/form-data">
                                        <input type="hidden" name="command"
                                               value="editFilm">
                                        <div class="form-group">
                                            <label for="nameField"
                                                   style="color: #ffffff"><fmt:message key="name"/>*</label>
                                            <input type="text"
                                                   class="form-control"
                                                   name="film_name"
                                                   id="nameField" required
                                                   placeholder="Enter name"
                                                   value="${film.name}"
                                                   style="color: black">
                                        </div>
                                        <div class="form-group">
                                            <label for="premierDate"
                                                   style="color: #ffffff"><fmt:message key="premier_date"/>*</label>
                                            <input type="date"
                                                   class="form-control"
                                                   name="film_premier_date"
                                                   id="premierDate" required
                                                   value="${film.premierDate}"
                                                   style="color: black">
                                        </div>
                                        <div class="form-group">
                                            <label for="countrySelect"
                                                   style="color: #ffffff"><fmt:message key="country"/></label>
                                            <select class="form-control"
                                                    id="countrySelect"
                                                    name="film_country"
                                                    style="color: black">
                                                <option></option>
                                                <option value="США">USA</option>
                                                <option value="Великобритания">
                                                    Great
                                                    Britain
                                                </option>
                                                <option value="Россия">Russia
                                                </option>
                                                <option value="Новая Зеландия">
                                                    New
                                                    Zealand
                                                </option>
                                                <option value="Австралия">
                                                    Australia
                                                </option>
                                                <option value="Испания">Spain
                                                </option>
                                                <option value="Франция">France
                                                </option>
                                                <option value="Германия">Germany
                                                </option>
                                                <option value="Китай">China
                                                </option>
                                                <option value="Беларусь">Belarus
                                                </option>
                                                <option value="Украина">Ukraine
                                                </option>
                                                <option value="Польша">Poland
                                                </option>
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <label for="category"
                                                   style="color: white"><fmt:message key="category"/></label>
                                            <select class="form-control"
                                                    id="category"
                                                    name="film_category">
                                                <option></option>
                                                <option value="Комедия">Comedy
                                                </option>
                                                <option value="Ужасы">Horror
                                                </option>
                                                <option value="Драмы">Drama
                                                </option>
                                                <option value="Фэнтези">Fantasy
                                                </option>
                                                <option value="Боевик">Action
                                                </option>
                                                <option value="Семейные">Family
                                                </option>
                                                <option value="Детективы">
                                                    Detective
                                                </option>
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <label for="exampleFormControlTextarea1"
                                                   style="color: white"><fmt:message key="description"/></label>
                                            <textarea class="form-control"
                                                      id="exampleFormControlTextarea1"
                                                      name="description"
                                                      rows="3">${film.description}</textarea>
                                        </div>
                                        <div class="form-group custom-file"
                                             style="margin-bottom: 10px">
                                            <label class="custom-file-label"
                                                   for="customFile"><fmt:message key="film_image"/></label>
                                            <input class="custom-file-input"
                                                   name="filmImage"
                                                   accept="/image/*"
                                                   type="file" id="customFile">
                                        </div>
                                        <button type="submit" class="btn"
                                                style="background: gold"><fmt:message key="submit_button"/>
                                        </button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    </body>
    </html>
</fmt:bundle>
