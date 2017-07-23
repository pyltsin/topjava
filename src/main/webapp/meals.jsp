<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://topjava.javawebinar.ru/functions" %>
<html>
<head>
    <title>Meal list</title>

    <style>
        .normal {
            color: green;
        }

        .exceeded {
            color: red;
        }

        .filter-container {
            width: 800px;
        }


    </style>
</head>
<body>
<section>
    <h3><a href="index.html">Home</a></h3>
    <h2>Meal list</h2>
    <a href="meals?action=filterDate">Add Meal</a>
    <hr/>
    <div class="filter-container">
        <div class="filter">
            Фильтрация по датам
            <form action="meals" method="get">
                <input type="hidden" name="action" value="filter">
                <p>
                    Date Start :
                    <input type="date" name="startDate">
                </p>
                <p>
                    Date End :
                    <input type="date" name="endDate">
                </p>
                <p>
                    Time Start :
                    <input type="time" name="startTime">
                </p>
                <p>
                    Time End :
                    <input type="time" name="endTime">
                </p>

                <input type="submit">
            </form>
        </div>

        <div>
            <a href="meals">Сброс</a>
        </div>
        <table border="1" cellpadding="8" cellspacing="0">
            <thead>
            <tr>
                <th>Date</th>
                <th>Description</th>
                <th>Calories</th>
                <th></th>
                <th></th>
            </tr>
            </thead>
            <c:forEach items="${meals}" var="meal">
                <jsp:useBean id="meal" scope="page" type="ru.javawebinar.topjava.to.MealWithExceed"/>
                <tr class="${meal.exceed ? 'exceeded' : 'normal'}">
                    <td>
                            <%--${meal.dateTime.toLocalDate()} ${meal.dateTime.toLocalTime()}--%>
                            <%--<%=TimeUtil.toString(meal.getDateTime())%>--%>
                            ${fn:formatDateTime(meal.dateTime)}
                    </td>
                    <td>${meal.description}</td>
                    <td>${meal.calories}</td>
                    <td><a href="meals?action=update&id=${meal.id}">Update</a></td>
                    <td><a href="meals?action=delete&id=${meal.id}">Delete</a></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</section>
</body>
</html>