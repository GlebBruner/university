<%@ page
        language="java"
        contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8"
%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>

<html>
<head>
    <title>New Student</title>
    <h1 align="center">Adding new student</h1>
    <p>
    <h1 align="center"><a href="/clienthostel/all">All Students</a></h1>
</head>
<body>
<div align="center">
    <form action="/clienthostel/new" method="post">
        <%--<input type="hidden" name="pid" value="<c:out value="${student.pid}"/>">--%>
        <table border="1" cellpadding="5">

            <tr>
                <th>
                    Floor:
                    <select name="floor" required size="1">
                        <c:forEach var="floor" begin="2" end="5">
                            <option>${floor}</option>
                        </c:forEach>
                    </select>
                </th>
                <th>
                    Room:
                    <select name="room" required size="1">
                        <c:forEach var="room" begin="1" end="44">
                            <option>${room}</option>
                        </c:forEach>
                    </select>
                </th>
            </tr>
            <tr>
                <th>Name: </th>
                <td>
                    <input type="text" title="A-z" name="name">
                </td>
            </tr>
            <tr>
                <th>Surname: </th>
                <td>
                    <input type="text" title="A-z" name="surname">
                </td>
            </tr>
            <tr>
                <th>Middleame: </th>
                <td>
                    <input type="text" title="A-z" name="middlename" >
                </td>
            </tr>
            <tr>
                <th>Date of birth: </th>
                <td>
                    <input type="text" title="yyyy-mm-dd" name="dob" >
                </td>
            </tr>
            <tr>
                <th>Phone [(0xx) xxx-xx-xx] : </th>
                <td>
                    <input type="text" title="(0xx) xxx-xx-xx" name="phone">
                </td>
            </tr>
            <tr>
                <th>Email: </th>
                <td>
                    <input type="text" title="a-z@nure.ua" name="email">
                </td>
            </tr>
            <tr>
                <th>Spec: </th>
                <td>
                    <input type="text" title="A-Z" name="spec" >
                </td>
            </tr>
            <tr>
                <th>Level: </th>
                <td>
                    <input type="text" title="1-4" name="level" >
                </td>
            </tr>
            <tr>
                <th>Grade: </th>
                <td>
                    <input type="text" title="Bachelor/Master/Phd" name="grade" >
                </td>
            </tr>

            <tr>
                <th>Studyform: </th>
                <td>
                    <input type="text" title="Evening/Day/Zaochnoe" name="studyForm">
                </td>
            </tr>

            <tr>
                <th>Payment: </th>

                <td>
                    <table>
                        <tr>
                            <th>Balance: </th>
                            <td>
                                <input type="text" title="int" name="balance">
                            </td>
                        </tr>
                        <tr>
                            <th>Subsidy: </th>
                            <td>
                                Price -  <input type="text" title="int" name="price"><br>
                                Estimate -  <input type="text" title="yyyy-mm-dd" name="estimate" >
                            </td>
                        </tr>
                    </table>
                </td>

            </tr>

            <tr>
                <th>Medical: </th>
                <td>
                    <input type="text" title="true/false" name="isExists" >

                    <input type="text" title="yyyy-mm-dd" name="expirationDate">

                </td>

            </tr>

            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="Create">
                </td>
            </tr>


        </table>
    </form>
</div>
</body>
</html>
