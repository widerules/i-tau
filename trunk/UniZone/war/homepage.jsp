<%@ page import="com.restfb.*" %>
<%@ page import="com.restfb.types.*" %>
<%@ page import="com.google.unizone.client.SessionManager" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.google.unizone.server.Course_DB" %>
<%@ page import="com.google.unizone.server.IShiur" %>
<%@ page import="com.google.unizone.server.*" %>


<!doctype html>
<html>
  <head>
  <meta http-equiv="content-type" content="text/html; charset=UTF-8">

    <script type="text/javascript" language="javascript" src="itauapp6/itauapp6.nocache.js"></script>
  <link type="text/css" rel="stylesheet" href="style.css">
  </head>
  <body>
    <%
  	
  		SessionManager httpSession = new SessionManager(request, response);
  		
  		String APP_ID = System.getProperty("APP_ID");
  		String APP_SECRET = System.getProperty("APP_SECRET_CODE");
  		String CANVAS_APP_URL = System.getProperty("CANVAS_APP_URL");
  		String code = request.getParameter("code");
  		ExtendedFaceBookClient fbclient = new ExtendedFaceBookClient(APP_ID, APP_SECRET);
  		List<String> myData;
  		String name, id;
  		
  		fbclient.readAccessToken(code, CANVAS_APP_URL + "homepage.jsp");
		myData = fbclient.getMyData();
		name = myData.get(1);
		id = myData.get(2);
		Course_DB.startSimulation();
  	%>
  <%=Course_DB.appToString()%>
    
  	
  
 <!--//create student, save username, pic url
 //move to schedule fill in page
 //save schedule in student field-->
  
 	<%
 		if(Course_DB.checkIfStudentExist(myData.get(2)))
 		{
 			IStudent student = Course_DB.getStudent(myData.get(2));
 			String course;
 			String Schedule = Course_DB.scheduleToString(student);
 			List<ICourse> courses = Course_DB.getStudentCourses(student);
 			%>
 			<p><%= student.toString(true) %></p>
 			
 			<p>
 				<h3 id="sub_header">Courses Groups</h3>
	 			<%
	 			for (ICourse iCourse : courses) { 
					course = iCourse.toString(false);
				%>
					<br><br><%=course %>
				<% 
				}
				%>
 			</p>
 			
 			<p>
 				<h3 id="sub_header">Schedule</h3>
 				<%= Schedule %>
 			</p>
 			
 		<%
 		}
 		else
 		{
 		%>
 			<p>You are not registered. Redirecting...</p>
<%
		}
 %>
 		
	</body>
</html>