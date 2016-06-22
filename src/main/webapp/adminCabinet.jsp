<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<p align="center">My requests to process</p>
<table border="black" bgcolor="orange" align="center">
    <tr>
        <td><b>Number</b></td>
        <td><b>Room class</b></td>
        <td><b>Persons</b></td>
        <td><b>Start</b></td>
        <td><b>End</b></td>
        <td><b>Options</b></td>
        <td><b>Process</b></td>
    </tr>
    <c:forEach items="${listToProcess}" var="request">
        <tr>
            <td>${request.id}</td>
            <td>${request.roomClass}</td>
            <td>${request.personQuantity}</td>
            <td>${request.start}</td>
            <td>${request.end}</td>
            <td><form action="process" method="POST">
                <p>Input Room Number</p>
                <input type="text" name="number" value="">
                <input type="hidden" name="id" value="${request.id}">
                <input type="submit" name="ok" value="Make a bill">
            </form></td>
        </tr>
    </c:forEach>
</table>

<p align="center">Available rooms</p>
<table border="black" bgcolor="orange" align="center">
    <tr>
        <td><b>Number</b></td>
        <td><b>Room class</b></td>
        <td><b>Persons</b></td>
        <td><b>Cost per Person</b></td>
        <td><b>Available</b></td>
    </tr>
    <c:forEach items="${listAvailable}" var="room">
        <tr>
            <td>${room.number}</td>
            <td>${room.roomclass}</td>
            <td>${room.personsMax}</td>
            <td>${room.costPerPerson}</td>
            <td>${room.available}</td>
        </tr>
    </c:forEach>
</table>

<div align="center">
    <form action="findAvailableRooms" method="POST">
        <select name="roomclass">
            <option>STANDARD</option>
            <option>JUNIOR</option>
            <option>LUX</option>
        </select><p>   </p>
        <select name="available">
            <option>1</option>
            <option>2</option>
            <option>3</option>
        </select><p>   </p>
        <input type="submit" name="ok" value="Find Available Rooms"/><br>
    </form>
</div>
</body>
</html>
