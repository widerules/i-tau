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
  	name: <%= myData.get(1) %>
    <img src = "http://<%= myData.get(0) %>">
    <br><br>
    id: <%= myData.get(2) %>
  
 <!--//create student, save username, pic url
 //move to schedule fill in page
 //save schedule in student field-->
  
 	<%
 		if(Course_DB.checkIfStudentExist(myData.get(2)))
 		{
 		%>
 	
 			
 			<br> Hooray! you're registered to iTAU!;
 			<br> Courses Groups:
 			<% IStudent Tal = Course_DB.getStudent(myData.get(2));
 			String course;
 			String courseID;
 			String fullGroupUrl;
 			String GroupUrl = "CourseGroupPage.jsp?courseID=";
 			String Schedule = Course_DB.scheduleToString(Tal);
 			List<ICourse> courses = Course_DB.getStudentCourses(Tal);
 			for (ICourse iCourse : courses) { 
				course = iCourse.getCourseName();
				courseID = iCourse.getID();
				fullGroupUrl = GroupUrl + courseID;
			%>
				<br>
				<A HREF = <%=fullGroupUrl %>><%=course %></A>
			<% 
			}
			%>
 			
 			<br>Schedule: 	<%= Schedule %>
 			
 			
 		<%
 		}
 		else
 		{
 		%>
 			Oh no, you are not registered!

<%
		}
 %>
 		
 		
  
 
	</body>
	
</html>