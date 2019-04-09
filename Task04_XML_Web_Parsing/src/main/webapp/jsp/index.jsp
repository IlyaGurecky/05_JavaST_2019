<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${sessionScope.sessionLocale == null}">
    <c:set var="sessionLocale" value="en_US" scope="session"/>
</c:if>
<fmt:setLocale value="${sessionScope.sessionLocale}"/>
<fmt:setBundle basename="pagecontent"/>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title><fmt:message key="parserTittle"/></title>
    <link rel="icon"
          href="http://localhost:8080/Task04_XML_Web_Parsing/img/xml-1.png"
          type="image/png">
    <link rel="stylesheet" href="css/format.css" type="text/css"/>
</head>

<body>
<p></p>
<h1><fmt:message key="chooseMessage"/></h1>

<form method="post" action="servlet" enctype="multipart/form-data">
    <label for="servlet">
        <select name="parser" id="servlet" class="parserSelect">
            <option value="dom" selected>DOM</option>
            <option value="sax">SAX</option>
            <option value="stax">StAX</option>
        </select>
        <p><input type="file" accept=".xml" name="file" class="fileChoice">
    </label>
    <p><input type="submit"
              class="parseButton"
              value="<fmt:message key="parse"/>"/>
</form>

<form action="servlet" method="GET" class="langChoice">
    <label for="lang">
        <select name="locale" id="lang">
            <option value="en_US">ENG</option>
            <option value="ru_RU">RUS</option>
            <option value="be_BY">BEL</option>
        </select>
    </label>
    <input type="submit" value="<fmt:message key="choose"/>"/>
</form>
</body>
</html>
