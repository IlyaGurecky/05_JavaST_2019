<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<fmt:setLocale value="en_US" scope="session"/>
<fmt:setBundle basename="pagecontent"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title><fmt:message key="parserTittle"/></title>
</head>
<body>
<h1><fmt:message key="chooseMessage"/></h1>
<jsp:useBean id="parser"
             class="by.guretsky.webparsing.servlet.ParseServlet"/>
<form action="parser" method="POST">
    <label>
        <select name="parser" required autofocus>
            <option></option>
            <option value="dom">DOM</option>
            <option value="sax">SAX</option>
            <option value="stax">StAX</option>
        </select>
    </label>
    <p><input type="submit" value="<fmt:message key="parseButton"/>"/></p>
</form>
</body>
</html>
