<%@ page
        language="java"
        contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8"
%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>

<html>
<head>
    <h1 align="center">Students in hostel</h1>
    <p>
    <h1 align="center"><a href="/clienthostel/new">Add new Student</a> | <a href="/clienthostel/all">All Students</a></h1>
</head>
<body>
<div align="center">
<table width="100%" style="border:1px solid black">
    <c:forEach var="floor" items='${floorList}'>
        <tr>
            <td width="10%" style="border:1px solid black; ">Floor number: <c:out value='${floor.id}'/></td>
            <td width="90%">
                <table width="100%">
                    <c:forEach var="room" items='${floor.room}'>
                        <tr style="border:1px solid black">
                            <td width="25%" style="border:1px solid black; ">
                                Room number: <c:out value='${room.id}'/> <br>
                                State: <c:out value="${room.state}"/>
                            </td>
                            <td width="75%">
                                <table width="100%" style="border:1px solid black">
                                    <c:forEach var="student" items='${room.student}'>
                                        <tr>
                                            <td width="100%" style="border:1px solid black; ">Student id - <c:out value='${student.pid}'/><p>
                                                <a href="/clienthostel/edit?id=<c:out value="${student.pid}"/>">Edit</a>
                                                <a href="/clienthostel/delete?id=<c:out value="${student.pid}"/>">Delete</a>
                                            <ul>
                                                <li>Name - <c:out value='${student.name}'/></li>
                                                <li>Surname - <c:out value='${student.surname}'/> </li>
                                                <li>Middlename - <c:out value="${student.middlename}"/></li>
                                                <li>Email - <c:out value="${student.email}"/></li>
                                                <li>Phone number - <c:out value="${student.phone}"/></li>
                                                <li>Date of birth - <c:out value="${student.dob}"/></li>
                                                <li>Spec - <c:out value="${student.spec}"/>, level - <c:out value="${student.level}"/> , grade - <c:out value="${student.grade}"/>, studyform - <c:out value="${student.studyForm}"/></li>
                                                <li>Payment: balance -  <c:out value="${student.payment.balance}"/> <c:if test="${student.payment.subsidy != null}">, subsidy - <c:out value="${student.payment.subsidy.price}"/>, estimate - <c:out value="${student.payment.subsidy.estimate}"/> </c:if></li>
                                                <li>Medical - <c:if test="${student.medical.expirationDate != null}"><c:out value="${student.medical.expirationDate}"/></c:if> <c:if test="${student.medical.expirationDate == null}"><c:out value="${student.medical.isExists}"/> </c:if> </li>
                                            </ul>
                                            </p>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </table>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </td>
        </tr>
    </c:forEach>

</table>
</div>

</body>
</html>
