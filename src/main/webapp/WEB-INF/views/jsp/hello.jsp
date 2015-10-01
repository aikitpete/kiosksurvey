<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Virtual Service Agent Survey Results</title>
 
<spring:url value="/resources/core/css/core.css" var="coreCss" />
<link href="${coreCss}" rel="stylesheet" />
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
    <script type="text/javascript">

      // Load the Visualization API and the piechart package.
      google.load('visualization', '1.0', {'packages':['corechart']});

      // Set a callback to run when the Google Visualization API is loaded.
      google.setOnLoadCallback(drawChart);

      // Callback that creates and populates a data table,
      // instantiates the pie chart, passes in the data and
      // draws it.
      function drawChart() {

        // Create the data table.
        var data = new google.visualization.DataTable();
        data.addColumn('string', 'Topping');
        data.addColumn('number', 'Slices');
        data.addRows([
          ['Mushrooms', 3],
          ['Onions', 1],
          ['Olives', 1],
          ['Zucchini', 1],
          ['Pepperoni', 2]
        ]);

        // Set chart options
        var options = {'title':'How Much Pizza I Ate Last Night',
                       'width':400,
                       'height':300};

        // Instantiate and draw our chart, passing in some options.
        var chart = new google.visualization.PieChart(document.getElementById('chart_div'));
        chart.draw(data, options);
      }
    </script>
</head>
 
<!--<h1 class="centeredtext"><a href="http://www.petegerhat.com/kiosksurvey">Virtual Service Agent Survey - Japan and Sweden</a></h1>-->

<h2 class="centeredtext">Participant Stats</h2>

<div style="width:100%;overflow: hidden">
	<div class="column left">
		<h2 style="display:block" class="centered">Group 1: (${SEName}${group1Participants.getName()})</h2>
		<input type="checkbox">SE</input>
		<input type="checkbox">GR</input>
		<input type="checkbox">AL</input>
		<input type="checkbox">US</input>
	</div>
	<div class="column right">
    	<h2 style="display:block" class="centered">Group 2: (${JPName}${group2Participants.getName()})</h2>
    	<input type="checkbox">JP</input>
    	<input type="checkbox">TW</input>
    </div>
</div>
<h3 style="display:block" class="centered">Demographics</h3>
<div style="width:100%;overflow: hidden">
	<div class="column left">
		<table class="centered">
			<tr><th>Measure</th><th>All Participants</th><th>Valid Participants</th><th>Selected Participants</th></tr>
			<tr><td>Total Responses</td><td>${group1Participants.getTotal()}</td><td>${group1Participants.getTotalValid()}</td><td>${group1Participants.getTotalValidActive()}</td></tr>
			<tr><td>Gender</td><td>M:${group1Participants.getMaleTotal()}/F:${group1Participants.getFemaleTotal()}</td><td>M:${group1Participants.getMaleTotalValid()}/F:${group1Participants.getFemaleTotalValid()}</td><td>M:${group1Participants.getMaleTotalValidActive()}/F:${group1Participants.getFemaleTotalValidActive()}</td></tr>
			<tr><td>Percentage Valid</td><td>n/a</td><td>${group1Participants.getPercentageValid()}%</td><td>${group1Participants.getPercentageValidActive()}%</td></tr>
			<tr><td>Average Age</td><td>${group1Participants.getAverageAge()}</td><td>${group1Participants.getAverageAgeValid()}</td><td>${group1Participants.getAverageAgeValidActive()}</td></tr>
			<tr><td>Time Duration</td><td>n/a</td><td>${group1Participants.getAverageDurationValid()}</td><td>${group1Participants.getAverageDurationValidActive()}</td></tr>
		</table>
	</div>
	<div class="column right">
		<table class="centered">
        	<tr><th>Measure</th><th>All Participants</th><th>Valid Participants</th><th>Selected Participants</th></tr>
        	<tr><td>Total Responses</td><td>${group2Participants.getTotal()}</td><td>${group2Participants.getTotalValid()}</td><td>${group2Participants.getTotalValidActive()}</td></tr>
        	<tr><td>Gender</td><td>M:${group2Participants.getMaleTotal()}/F:${group2Participants.getFemaleTotal()}</td><td>M:${group2Participants.getMaleTotalValid()}/F:${group2Participants.getFemaleTotalValid()}</td><td>M:${group2Participants.getMaleTotalValidActive()}/F:${group2Participants.getFemaleTotalValidActive()}</td></tr>
        	<tr><td>Percentage Valid</td><td>n/a</td><td>${group2Participants.getPercentageValid()}%</td><td>${group2Participants.getPercentageValidActive()}%</td></tr>
        	<tr><td>Average Age</td><td>${group2Participants.getAverageAge()}</td><td>${group2Participants.getAverageAgeValid()}</td><td>${group2Participants.getAverageAgeValidActive()}</td></tr>
    		<tr><td>Time Duration</td><td>n/a</td><td>${group2Participants.getAverageDurationValid()}</td><td>${group2Participants.getAverageDurationValidActive()}</td></tr>
        </table>
	</div>
</div>
<h3 class="centeredtext">Responses</h3>
<h4 style="display:block" class="centered">Serious</h4>
<div style="width:100%;overflow: hidden">
	<div class="column left">
		<table class="centered">
			<tr><th>Username</th><th>Gender</th><th>Age</th><th>Valid Responses</th><th>Survey Duration</th><th>Selected</th></tr>
   			<c:forEach items="${seriousGroup1Participants}" var="participant">
   				<tr><td>${participant.getUsername()}</td><td>${participant.getGender()}</td><td>${participant.getAge()}</td>
   				<td>${participant.getRecordCount('serious')}</td><td>${participant.getDuration('serious')}</td><td>
   				<input type="checkbox"/></td></tr>
    		</c:forEach>
		</table>
	</div>
	<div class="column right">
   		<table class="centered">
   			<tr><th>Username</th><th>Gender</th><th>Age</th><th>Valid Responses</th><th>Survey Duration</th><th>Selected</th></tr>
       		<c:forEach items="${seriousGroup2Participants}" var="participant">
       			<tr><td>${participant.getUsername()}</td><td>${participant.getGender()}</td><td>${participant.getAge()}</td>
       			<td>${participant.getRecordCount('serious')}</td><td>${participant.getDuration('serious')}</td>
       			<td><input type="checkbox"/></td></tr>
       		</c:forEach>
    	</table>
   	</div>
</div>
<h4 style="display:block" class="centered">Smiling</h4>
<div style="width:100%;overflow: hidden">
	<div class="column left">
		<table class="centered">
			<tr><th>Username</th><th>Gender</th><th>Age</th><th>Valid Responses</th><th>Survey Duration</th><th>Selected</th></tr>
   			<c:forEach items="${smilingGroup1Participants}" var="participant">
   				<tr><td>${participant.getUsername()}</td><td>${participant.getGender()}</td><td>${participant.getAge()}</td>
   				<td>${participant.getRecordCount('smiling')}</td><td>${participant.getDuration('smiling')}</td>
   				<td><input type="checkbox"/></td></tr>
    		</c:forEach>
		</table>
	</div>
	<div class="column right">
   		<table class="centered">
   			<tr><th>Username</th><th>Gender</th><th>Age</th><th>Valid Responses</th><th>Survey Duration</th><th>Selected</th></tr>
       		<c:forEach items="${smilingGroup2Participants}" var="participant">
       			<tr><td>${participant.getUsername()}</td><td>${participant.getGender()}</td><td>${participant.getAge()}</td>
       			<td>${participant.getRecordCount('smiling')}</td><td>${participant.getDuration('smiling')}</td>
       			<td><input type="checkbox"/></td></tr>
       		</c:forEach>
    	</table>
   	</div>
</div>
<h2 class="centeredtext centered"><button class="centeredtext centered" name="name" value="update" type="submit">Update View</button></h2>
<h2 class="centeredtext centered"><button class="centeredtext centered" name="name" value="save" type="submit">Save Changes</button></h2>

<div id="chart_div"></div>

<p>${config}</p>
        <p>${request}</p>
        <p>LuzaA</p>
        <p>${te1}</p>
        <p>${te2}</p>
        <p>LuzaB</p>
        <p>${session}</p>
        <p>${response}</p>
        <p>LuzaC</p>

<spring:url value="/resources/core/css/core.js" var="coreJs" />
 
<script src="${coreJs}"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
 
</body>
</html>