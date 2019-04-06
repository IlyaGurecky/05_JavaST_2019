<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<fmt:setLocale value="ru_RU" scope="session"/>
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <title><fmt:message key="tittle"/></title>
    <link rel="icon"
          href="http://localhost:8080/Task04_XML_Web_Parsing/img/icon.png"
          type="image/png">
</head>
<body>
<a href="http://localhost:8080/Task04_XML_Web_Parsing"><fmt:message
        key="backButton"/> </a>
<table border="3" width="100%" cellpadding="10">
    <tr align="center" bgcolor="#deb887">
        <th><fmt:message key="id"/></th>
        <th><fmt:message key="name"/></th>
        <th><fmt:message key="operator"/></th>
        <th><fmt:message key="payroll"/></th>
        <th><fmt:message key="startDate"/></th>
        <th><fmt:message key="endDate"/></th>
        <th colspan="2"><fmt:message key="parameters"/></th>
        <th><fmt:message key="tariffication"/></th>
        <th width="10%"><fmt:message key="callPrices"/></th>
        <th><fmt:message key="smsPrice"/></th>
        <th><fmt:message key="freeMb"/></th>
        <th><fmt:message key="speed"/></th>
    </tr>
    <c:forEach var="elem" items="${res}" varStatus="status">
        <tr align="center">
            <td><c:out value="${ elem.tariffId }"/></td>
            <td><c:out value="${ elem.name }"/></td>
            <td><c:out value="${ elem.operator }"/></td>
            <td><c:out value="${ elem.payroll }"/></td>
            <td><c:out value="${ elem.tariffDate }"/></td>
            <td><c:out value="${ elem.endDate }"/></td>
            <td><c:out value="${ elem.parameters.connectionPrice }"/></td>
            <td><c:out value="${ elem.parameters.blockingWithDebt }"/></td>
            <c:choose>
                <c:when test="${elem.getClass().getSimpleName() == 'Calls'}">
                    <td><c:out value="${ elem.tariffication }"/></td>
                    <td><c:out value="${ elem.callPrices }"/></td>
                    <td><c:out value="${ elem.smsPrice}"/></td>
                    <td><c:out value="-"/></td>
                    <td><c:out value="-"/></td>
                </c:when>
                <c:when test="${elem.getClass().getSimpleName() == 'Internet'}">
                    <td><c:out value="-"/></td>
                    <td><c:out value="-"/></td>
                    <td><c:out value="-"/></td>
                    <td><c:out value="${ elem.freeMb }"/></td>
                    <td><c:out value="${ elem.speedLimit }"/></td>
                </c:when>
                <c:otherwise>
                    <td><c:out value="${ elem.tariffication }"/></td>
                    <td><c:out value="${ elem.callPrices }"/></td>
                    <td><c:out value="${ elem.smsPrice}"/></td>
                    <td><c:out value="${ elem.freeMb }"/></td>
                    <td><c:out value="${ elem.speedLimit }"/></td>
                </c:otherwise>
            </c:choose>
        </tr>
    </c:forEach>
</table>
</body>
</html>
