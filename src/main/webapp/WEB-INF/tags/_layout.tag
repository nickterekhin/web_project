<%@tag description="Overall Page template" pageEncoding="UTF-8"%>
<%@attribute name="header" fragment="true" %>
<%@attribute name="footer" fragment="true" %>
<%@attribute name="title" fragment="true" %>
<html>
<head>
  <link rel="stylesheet" href="context/css/style.css"/>
    <jsp:invoke fragment="title"/>
</head>
<body>
  <header>
    <jsp:invoke fragment="header"/>
  </header>
  <section id="body">
    <jsp:doBody/>
  </section>
  <footer>
    <jsp:invoke fragment="footer"/>
  </footer>

</body>
</html>
