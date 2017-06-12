<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Nick
  Date: 11.06.17
  Time: 19:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<t:_layout>
    <jsp:attribute name="title">
        <title>Persons List</title>
    </jsp:attribute>
    <jsp:attribute name="header">
        <div>
            <h1>Persons List
            &nbsp;<a href="/persons?action=add" style="font-size: 16px; color:#fff;text-decoration: none;background-color:#008c18;padding:5px 10px">Add New</a>
            </h1>
        </div>
    </jsp:attribute>
    <jsp:attribute name="footer">
        <div>Footer</div>
    </jsp:attribute>
    <jsp:body>
        <table id="persons">
            <tr>
                <th>id</th>
                <th>Name</th>
                <th>Age</th>
                <th>Action</th>
            </tr>
            <c:forEach items="${persons}" var="personVar">
                <tr>
                    <td><c:out value="${personVar.id}"/></td>
                    <td><c:out value="${personVar.name}"/></td>
                    <td><c:out value="${personVar.age}"/></td>
                    <td>
                        <a href="/persons?action=edit&id=${personVar.id}">Edit</a>
                        <a href="/persons?action=delete&id=${personVar.id}">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </jsp:body>
</t:_layout>

