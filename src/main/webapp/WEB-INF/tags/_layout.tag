<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag description="Overall Page template" pageEncoding="UTF-8"%>
<%@attribute name="header" fragment="true" %>
<%@attribute name="footer" fragment="true" %>
<%@attribute name="title" fragment="true" %>

<html>
<head>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/context/css/bootstrap.min.css"/>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/context/css/font-awesome.min.css"/>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/context/css/style.css"/>
  <script src="${pageContext.request.contextPath}/context/js/jquery-2.1.4.min.js" type="text/javascript"></script>
  <script src="${pageContext.request.contextPath}/context/js/bootstrap.min.js" type="text/javascript"></script>

  <script src="${pageContext.request.contextPath}/context/js/ntValidator.js" type="text/javascript"></script>
    <jsp:invoke fragment="title"/>

  <script type="text/javascript">
    $(document).ready(function(){
      $(".alert").fadeTo(2000, 500).slideUp(500, function(){
        $(".alert").slideUp(500);
      });
    });
  </script>
</head>
<body>
  <header>
    <div class="container-fluid">
      <div class="container">
        <jsp:invoke fragment="header"/>
      </div>
    </div>

  </header>
  <section id="body">
    <div class="container-fluid">
      <div class="container">
        <jsp:include page="/notify/_notifications.jsp"/>
        <jsp:doBody/>
      </div>
    </div>
  </section>
  <footer>
    <div class="container-fluid">
      <div class="container">

    <jsp:invoke fragment="footer"/>
    <jsp:include page="/inc/copyright.jsp"/>
        </div>
      </div>
  </footer>

</body>
</html>
