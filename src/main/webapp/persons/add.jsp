
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
        <title>Add Person</title>
    </jsp:attribute>
    <jsp:attribute name="header">
        <div>
          <h1>Add New Person
            &nbsp;<a href="/persons">Back to Persons List</a>
          </h1>
        </div>
    </jsp:attribute>
    <jsp:attribute name="footer">
        <div>Footer</div>
    </jsp:attribute>
  <jsp:body>
    <form action="${pageContext.request.getContextPath()}/persons?action=${pageContext.request.getParameter("action")}" method="post">

       <input type="text" name="name">
      <br>
      <input type="number" name="age">
      <br>
      <button type="submit" name="add-new" value="Add New">Add New</button>
    </form>
  </jsp:body>
</t:_layout>