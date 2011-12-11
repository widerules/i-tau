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
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">

    <script type="text/javascript" language="javascript" src="itauapp6/itauapp6.nocache.js"></script>
  </head>
  <body>
  		<%     
  		String courseID = request.getParameter("courseID");
  		ICourse course = Course_DB.getCourse(courseID);
  		String courseName= course.getCourseName();
%>
	  		this is <%=courseName %> page!!! 
	  		<br>
	  		<%
	  		Set<String> courseStudents = course.getStudents();
	  		for (String studentID : courseStudents) {
				IStudent student = Course_DB.getStudent(studentID);
			%>	
				<br><A HREF = http://www.one.co.il><%=student.getName()%></A>
				<img src = "<%=student.getPic() %>">
    
			<%
			}
			%>
	  		
	</body>
	
</html>