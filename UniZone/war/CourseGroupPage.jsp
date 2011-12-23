<%@ page import="com.restfb.*" %>
<%@ page import="com.restfb.types.*" %>
<%@ page import="com.google.unizone.client.SessionManager" %>
<%@ page import="java.util.*" %>
<%@ page import="com.google.unizone.server.Course_DB" %>
<%@ page import="com.google.unizone.server.IShiur" %>
<%@ page import="com.google.unizone.server.*" %>

<!doctype html>
<html>
  <head>
  
  
  <script type="text/javascript" src="jquery-1.2.2.pack.js"></script>

<style type="text/css">

div.htmltooltip{
position: absolute; /*leave this and next 3 values alone*/
z-index: 1000;
left: -1000px;
top: -1000px;
background: #272727;
border: 10px solid black;
color: white;
padding: 3px;
width: 250px; /*width of tooltip*/
}

</style>

<script type="text/javascript" src="htmltooltip.js">

/***********************************************
* Inline HTML Tooltip script- by JavaScript Kit (http://www.javascriptkit.com)
* This notice must stay intact for usage
* Visit JavaScript Kit at http://www.javascriptkit.com/ for this script and 100s more
***********************************************/

</script>
  <link type="text/css" rel="stylesheet" href="style.css">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">

    <script type="text/javascript" language="javascript" src="itauapp6/itauapp6.nocache.js"></script>
  </head>
  <body>
  		<%=Course_DB.appToString()%>
  		<%     
  		String courseID = request.getParameter("courseID");
  		ICourse course = Course_DB.getCourse(courseID);
		%>
			<%=course.toString(true) %> 
	  		<table id="course_friends"><tr>
	  		<%
	  		Set<String> courseStudents = course.getStudents();
	  		for (String studentID : courseStudents) {
				IStudent student = Course_DB.getStudent(studentID);
			%>	
				<%= student.toString(false) %>
			<%
			}
			%>
			</tr></table>
			<br><br>
			<a href = <%="ITAUApp6.jsp" %>>back to home</A>
	  		
	</body>
	
</html>