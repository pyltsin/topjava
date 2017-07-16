<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://example.com/functions" prefix="f" %>
<html>
<head>
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<h2>Meals</h2>
<table border="1">
    <tr>
        <th>&nbsp;</th>
        <th>Время</th>
        <th>Калории</th>
    </tr>

    <c:forEach var="meal" items="${meals}">
        <tr bgcolor="<c:out value="${meal.exceed ? 'red' : 'green'}" />">
            <td>${meal.description}</td>
            <td>${f:formatToTable(meal.dateTime)}</td>
            <td>${meal.calories}</td>
        </tr>
    </c:forEach>

</table>


</body>
</html>