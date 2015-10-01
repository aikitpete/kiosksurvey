<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Maven + Spring MVC</title>
 
<spring:url value="/resources/core/css/core.css" var="coreCss" />
<link href="${coreCss}" rel="stylesheet" />
</head>
 
<h1><a class="centeredtext" href="http://www.petegerhat.com/kiosksurvey">Virtual Service Agent Survey - Japan and Sweden</a></h1>

<h2 class="centeredtext">Participant Stats</h2>

<table>
	<th><td>Name</td><td>Value</td></th>
	<tr><td>Name1</td><td>Value1</td></tr>
	<tr><td>Name2</td><td>Value2</td></tr>
	<tr><td>Name3</td><td>Value3</td></tr>
<table>
 
<spring:url value="/resources/core/css/core.js" var="coreJs" />
 
<script src="${coreJs}"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
 
</body>
</html>