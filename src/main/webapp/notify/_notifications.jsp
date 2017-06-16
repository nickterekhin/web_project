<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Nick
  Date: 16.06.17
  Time: 01:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--<jsp:useBean id="message" scope="request" type="com.helpers.Notification"/>--%>
<c:if test="${not empty message}">
<div class="alert ${message.type}">
    <p>${message.message}</p>
</div>
</c:if>