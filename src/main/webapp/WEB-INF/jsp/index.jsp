<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Welcome to Spring Web MVC project</title>
</head>

<body bgcolor="#DDDDDD">

<h3>htmlEscape is false </h3>
<h3>escapeBody is false </h3>
<spring:htmlEscape defaultHtmlEscape="false">
  <spring:escapeBody htmlEscape="false">

    <h5>  less than < </h5>
    <h5>  greater than > </h5>
  </spring:escapeBody>
</spring:htmlEscape>

<h3>htmlEscape is false </h3>
<h3>escapeBody is true </h3>
<spring:htmlEscape defaultHtmlEscape="false">
  <spring:escapeBody htmlEscape="true">

    <h5>  less than < </h5>
    <h5>  greater than > </h5>
  </spring:escapeBody>
</spring:htmlEscape>

<h3>htmlEscape is true </h3>
<h3>escapeBody is false </h3>
<spring:htmlEscape defaultHtmlEscape="true">
  <spring:escapeBody htmlEscape="false">

    <h5>  less than < </h5>
    <h5>  greater than > </h5>
  </spring:escapeBody>
</spring:htmlEscape>

<h3>htmlEscape is true </h3>
<h3>escapeBody is true </h3>
<spring:htmlEscape defaultHtmlEscape="true">
  <spring:escapeBody htmlEscape="true">

    <h5>  less than < </h5>
    <h5>  greater than > </h5>
  </spring:escapeBody>
</spring:htmlEscape>


<h3>escapeBody is false </h3>
<spring:escapeBody htmlEscape="false">
  <h5>  less than < </h5>
  <h5>  greater than > </h5>
</spring:escapeBody>

<h3>escapeBody is true </h3>
<spring:escapeBody htmlEscape="true">
  <h5>  less than < </h5>
  <h5>  greater than > </h5>
</spring:escapeBody>
</body>
</html>

