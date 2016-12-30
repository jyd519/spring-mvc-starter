<%@tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true" body-content="scriptless" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" scope="application" value="${pageContext.request.contextPath}"/>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<jsp:doBody/>
<!-- Bootstrap -->
<link href="${ctx}/css/bootstrap.min.css" rel="stylesheet">

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
<script src="${ctx}/js/html5shiv.min.js"></script>
<script src="${ctx}/js/respond.min.js"></script>
<![endif]-->
