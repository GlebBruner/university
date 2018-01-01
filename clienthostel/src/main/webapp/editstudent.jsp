<%@ page
        language="java"
        contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8"
%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>

<html>
<head>
    <title>Edit Student</title>
    <h1 align="center">Editing student.</h1>
    <p>
    <h1 align="center"><a href="/clienthostel/all">All Students</a></h1>
</head>
<body>
<div align="center">
    <form action="/clienthostel/edit" method="post">
        <input type="hidden" name="pid" value="<c:out value="${student.pid}"/>">
        <table border="1" cellpadding="5">
            <tr>
                <th>Name: </th>
                <td>
                    <input type="text" title="A-z" name="name" value="<c:out value="${student.name}"/>">
                </td>
            </tr>
            <tr>
                <th>Surname: </th>
                <td>
                    <input type="text" title="A-z" name="surname" value="<c:out value="${student.surname}"/>">
                </td>
            </tr>
            <tr>
                <th>Middleame: </th>
                <td>
                    <input type="text" title="A-z" name="middlename" value="<c:out value="${student.middlename}"/>">
                </td>
            </tr>
            <tr>
                <th>Date of birth: </th>
                <td>
                    <input type="text" title="yyyy-mm-dd" name="dob" value="<c:out value="${student.dob}"/>">
                </td>
            </tr>
            <tr>
                <th>Phone [(0xx) xxx-xx-xx] : </th>
                <td>
                    <input type="text" title="(0xx) xxx-xx-xx" name="phone" value="<c:out value="${student.phone}"/>">
                </td>
            </tr>
            <tr>
                <th>Email: </th>
                <td>
                    <input type="text" title="a-z@nure.ua" name="email" value="<c:out value="${student.email}"/>">
                </td>
            </tr>
            <tr>
                <th>Spec: </th>
                <td>
                    <input type="text" title="A-Z" name="spec" value="<c:out value="${student.spec}"/>">
                </td>
            </tr>
            <tr>
                <th>Level: </th>
                <td>
                    <input type="text" title="1-4" name="level" value="<c:out value="${student.level}"/>">
                </td>
            </tr>
            <tr>
                <th>Grade: </th>
                <td>
                    <input type="text" title="Bachelor/Master/Phd" name="grade" value="<c:out value="${student.grade}"/>">
                </td>
            </tr>

            <tr>
                <th>Studyform: </th>
                <td>
                    <input type="text" title="Evening/Day/Zaochnoe" name="studyForm" value="<c:out value="${student.studyForm}"/>">
                </td>
            </tr>

            <tr>
                <th>Payment: </th>

                <td>
                    <table>
                        <tr>
                            <th>Balance: </th>
                            <td>
                                <input type="text" title="int" name="balance" value="<c:out value="${student.payment.balance}"/>">
                            </td>
                        </tr>
                        <c:if test="${student.payment.subsidy != null}">
                            <tr>
                                <th>Subsidy: </th>
                                <td>
                                    Price -  <input type="text" title="int" name="price" value="<c:out value="${student.payment.subsidy.price}"/>"><br>
                                    Estimate -  <input type="text" title="yyyy-mm-dd" name="estimate" value="<c:out value="${student.payment.subsidy.estimate}"/>">
                                </td>
                            </tr>
                        </c:if>
                    </table>
                </td>

            </tr>

            <tr>
                <th>Medical: </th>
                <td>
                    <c:if test="${student.medical.expirationDate == null}">
                        <input type="text" title="true/false" name="isExists" value="<c:out value="${student.medical.isExists}"/>">
                    </c:if>

                    <c:if test="${student.medical.expirationDate != null}">
                        <input type="text" title="yyyy-mm-dd" name="expirationDate" value="<c:out value="${student.medical.expirationDate}"/>">
                    </c:if>
                </td>

            </tr>

            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="Save">
                </td>
            </tr>


        </table>
    </form>
</div>
</body>
</html>
