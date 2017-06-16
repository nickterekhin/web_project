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
            &nbsp;<a href="/persons?action=add" class="btn btn-success">Add New</a>
            </h1>
        </div>
    </jsp:attribute>
    <jsp:attribute name="footer">

    </jsp:attribute>
    <jsp:body>

        <table id="persons" class="table table-striped table-hover">
            <thead>
            <tr>
                <th style="width:10%">id</th>
                <th style="width:50%">Name</th>
                <th style="width:25%">Age</th>
                <th style="width:25%">Action</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${persons}" var="personVar">
                <tr>
                    <td><c:out value="${personVar.id}"/></td>
                    <td><c:out value="${personVar.name}"/></td>
                    <td><c:out value="${personVar.age}"/></td>
                    <td>
                        <a href="/persons?action=edit&id=${personVar.id}" class="btn btn-warning" title="Edit Person"><i class="fa fa-pencil-square no-margin"></i></a>
                        <a href="/persons?action=delete&id=${personVar.id}" class="btn btn-danger" title="Delete Person"><i class="fa fa-trash no-margin"></i></a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
            <tfoot>

            </tfoot>
        </table>
    </jsp:body>
</t:_layout>

