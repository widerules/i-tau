<%@ page import="com.restfb.*" %>
<%@ page import="com.restfb.types.*" %>
<%@ page import="com.google.unizone.client.SessionManager" %>
<%@ page import="java.util.*" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.google.unizone.server.Course_DB" %>
<%@ page import="com.google.unizone.server.IShiur" %>
<%@ page import="com.google.unizone.server.*" %>


<!doctype html>
<html xmlns="http://www.w3.org/1999/xhtml" style="overflow: hidden">
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





  
  
  <meta http-equiv="content-type" content="text/html; charset=UTF-8">

    <script type="text/javascript" language="javascript" src="itauapp6/itauapp6.nocache.js"></script>
  <link type="text/css" rel="stylesheet" href="style.css">
  </head>
  <body style="overflow: hidden">
  
	
		    
    
    
    <%
  	
  		SessionManager httpSession = new SessionManager(request, response);
  		
  		String APP_ID = System.getProperty("APP_ID");
  		String APP_SECRET = System.getProperty("APP_SECRET_CODE");
  		String CANVAS_APP_URL = System.getProperty("CANVAS_APP_URL");
  		String code = request.getParameter("code");
  		ExtendedFaceBookClient fbclient = new ExtendedFaceBookClient(APP_ID, APP_SECRET);
  		//fbclient.Canvas.setAutoResize(100);
  		List<String> myData;
  		String name, id, pic, appFriends;
  		fbclient.readAccessToken(code, CANVAS_APP_URL + "homepage.jsp");
		myData = fbclient.getMyData();
		name = myData.get(1);
		id = myData.get(2);
		pic = myData.get(0);
		Course_DB.startSimulation();
		appFriends = fbclient.appFriendsToString();
		
  	%>
  	
 
 <!--//create student, save username, pic url
 //move to schedule fill in page
 //save schedule in student field-->
  
 	<%
 		if(Course_DB.checkIfStudentExist(myData.get(2)))
 		{
 			IStudent student = Course_DB.getStudent(myData.get(2));
 			student.setPic(pic);
 			student.setName(name);
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
 				<h3 id="sub_header">Available Friends</h3>
 				<%= appFriends %>
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