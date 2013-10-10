<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <title><decorator:title default="Tickets"/></title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet"  type="text/css" />
  <link href="<c:url value="/resources/css/font-awesome.min.css" />" rel="stylesheet"  type="text/css" />
  <script src="<c:url value="/resources/js/jquery.js" />"></script>
  <decorator:head/>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div class="container">
      <decorator:body/>
      <footer>
      	<hr> <p><small class="text-muted">&copy; By the B7 team.</small></p>
      </footer>
    </div>
</body>

</html>  