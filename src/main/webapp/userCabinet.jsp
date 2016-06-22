<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${user.fullName} cabinet</title>
</head>
<body>
<p>Hello, ${user.fullName}!</p>

<p align="center">My requests</p>
<table border="black" bgcolor="yellow" align="center">
    <tr>
        <td><b>Number</b></td>
        <td><b>Room class</b></td>
        <td><b>Persons</b></td>
        <td><b>Start</b></td>
        <td><b>End</b></td>
    </tr>
    <c:forEach items="${requestList}" var="request">
        <tr>
            <td>${request.id}</td>
            <td>${request.roomClass}</td>
            <td>${request.personQuantity}</td>
            <td>${request.start}</td>
            <td>${request.end}</td>
        </tr>
    </c:forEach>
</table>
<br>
<div align="center">
    <form action="addRequest" method="POST">
        <select name="roomclass">
            <option>STANDARD</option>
            <option>JUNIOR</option>
            <option>LUX</option>
        </select><p>   </p>
        <select name="personquantity">
            <option>1</option>
            <option>2</option>
            <option>3</option>
        </select><p>   </p>
        <input type="date" name="start" value=""/><br>
        <input type="date" name="end" value=""/><br>
        <input type="submit" name="ok" value="Send Request"/><br>
    </form>
</div>

<br>
<p align="center">My bills to pay</p>
<table border="black" bgcolor="orange" align="center">
    <tr>
        <td><b>Id</b></td>
        <td><b>Request</b></td>
        <td><b>Room</b></td>
        <td><b>Cost</b></td>
        <td><b>Paid</b></td>
        <td><b>Pay</b></td>
    </tr>
    <c:forEach items="${listToPay}" var="bill">
        <tr>
            <td>${bill.id}</td>
            <td>${bill.request.id}</td>
            <td>${bill.room.number}</td>
            <td>${bill.cost}</td>
            <td>${bill.paid}</td>
            <td><form action="pay" method="POST">
                <input type="hidden" name="id" value="${bill.id}">
                <input type="submit" name="ok" value="Pay!">
            </form></td>
        </tr>
    </c:forEach>
</table>
<br>
<p align="center">My ready rooms</p>
<table border="black" bgcolor="green" align="center">
    <tr>
        <td><b>Id</b></td>
        <td><b>Request</b></td>
        <td><b>Room</b></td>
        <td><b>Cost</b></td>
        <td><b>Paid</b></td>
    </tr>
    <c:forEach items="${readyList}" var="bill">
        <tr>
            <td>${bill.id}</td>
            <td>${bill.request.id}</td>
            <td>${bill.room.number}</td>
            <td>${bill.cost}</td>
            <td>${bill.paid}</td>
        </tr>
    </c:forEach>
</table>
<br>

</body>
</html>
