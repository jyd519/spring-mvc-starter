<%@include file="/WEB-INF/fragements/top.jsp"%>
<ata:head>
  <title>Welcome to Spring Web MVC project</title>
</ata:head>

<body>

<div class="container">

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
</div>

<%@include file="/WEB-INF/fragements/footer.jsp"%>

</body>
</html>

