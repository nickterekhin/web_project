
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
            &nbsp;<a href="/persons" class="btn btn-warning">Back to Persons List</a>
          </h1>
        </div>
    </jsp:attribute>
    <jsp:attribute name="footer">

    </jsp:attribute>
  <jsp:body>
      <script type="text/javascript">
          $(document).ready(function(){
              $("#add-form").ntValidator({
                  rules:{
                      name:{required:true},
                      age:{required:true,number:true}
                  }
              });

          });
      </script>
      <div class="row">
          <div class="col-md-3">
              <form id="add-form" action="${pageContext.request.getContextPath()}/persons?action=${pageContext.request.getParameter("action")}" method="post">
                    <div class="form-group">
                        <input type="text" name="name" class="form-control" value="${pageContext.request.getParameter("name")}">
                    </div>

                  <div class="form-group">
                      <input type="text" name="age" class="form-control" value="${pageContext.request.getParameter("age")}">
                  </div>

                  <button type="submit" name="add-new" value="Add New" class="btn btn-success"><i class="fa fa-plus"></i>Add New</button>
              </form>
          </div>
      </div>

  </jsp:body>
</t:_layout>