<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>

<html>
<head>
    <meta charset="UTF-8">
    <title>XML Parser</title>
</head>
<body>
<h1>Choose the parser</h1>
<form action="" method="post">
    <select name="parser" required autofocus>
        <option></option>
        <option value="dom">DOM</option>
        <option value="sax">SAX</option>
        <option value="stax">StAX</option>
    </select>
    <p><input type="submit" value="Parse"></p>
</form>

<%--<jsp:useBean id="calendar" class="java.util.GregorianCalendar"/>--%>
<%--<form name="Simple" action="timeaction" method="POST">--%>
<%--<input type="hidden" name="time" value="${calendar.timeInMillis}"/>--%>
<%--<input type="submit" name="button" value="Посчитать время"/>--%>
<%--</form>--%>
</body>
</html>