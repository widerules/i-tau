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