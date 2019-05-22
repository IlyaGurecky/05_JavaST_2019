<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<c:url var="signin" value="/signin"/>
<c:url var="signup" value="/signup"/>
<c:url var="home" value="/home"/>
<c:set var="url">${pageContext.request.requestURL}</c:set>
<c:set var="context"
       value="${fn:substring(url, 0, fn:length(url) - fn:length(pageContext.request.requestURI))}${pageContext.request.contextPath}"/>
<c:if test="${param.language == 'en'}">
    <fmt:setLocale value="en_EN" scope="session"/>
</c:if>
<c:if test="${param.language == 'ru'}">
    <fmt:setLocale value="ru_RU" scope="session"/>
</c:if>
<c:if test="${param.language == 'de'}">
    <fmt:setLocale value="de_DE" scope="session"/>
</c:if>
<fmt:bundle basename="signin_page">

    <html>
    <head>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="${context}/css/authorization.css"
              type="text/css">
        <title><fmt:message key="tittle"/></title>
        <link rel="icon"
              href="https://img.icons8.com/metro/26/000000/gender-neutral-user.png"
              type="image/png">
    </head>

    <body style="background: url(${context}/img/background/background.jpg) no-repeat; background-size: 100%">
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css"/>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <div class="container">
        <div class="row">
            <div class="col-md-offset-5 col-md-6">
                <div class="tab" role="tabpanel">
                    <!-- Nav tabs -->
                    <ul class="nav nav-tabs" role="tablist">
                        <li role="presentation" class="active"><a><fmt:message
                                key="signin_nav"/></a></li>
                        <li role="presentation"><a href="${signup}"><fmt:message
                                key="signup_nav"/></a></li>
                    </ul>
                    <!-- Tab panes -->
                    <div class="tab-content tabs">
                        <div role="tabpanel" class="tab-pane fade in active"
                             id="Section1">
                            <form class="form-horizontal" action="${signin}"
                                  method="post">
                                <div class="form-group">
                                    <label for="login"><fmt:message
                                            key="login"/></label>
                                    <input type="text" class="form-control"
                                           id="login" name="login" required
                                           autofocus>
                                </div>
                                <div class="form-group">
                                    <label for="password"><fmt:message
                                            key="password"/></label>
                                    <input type="password" class="form-control"
                                           id="password" name="password"
                                           required>
                                </div>
                                <button type="reset" class="reset-btn">
                                    <fmt:message
                                            key="reset"/>
                                </button>
                                <br>
                                <div class="text">
                                        ${msg}
                                </div>
                                <br/>
                                <div class="form-group">
                                    <input type="hidden" value="signin"
                                           name="command"/>
                                    <button type="submit"
                                            class="btn btn-default">
                                        <fmt:message key="signin_nav"/>
                                    </button>
                                </div>
                                <div class="form-group">
                                    <a href="${home}" class="home"><fmt:message key="home"/></a>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    </body>
    </html>
</fmt:bundle>
