<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head><title>Result Page</title></head>
<body>
<table>
    <c:forEach var="elem" items="${res}" varStatus="status">
        <tr>
            <td><c:out value="${ elem }"/>
        </tr>
    </c:forEach>
</table>
</body>
</html>
