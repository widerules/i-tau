<%@ page import="com.restfb.*" %>
<%@ page import="com.restfb.types.*" %>
<%@ page import="com.google.unizone.client.SessionManager" %>
<%@ page import="java.util.*" %>
<%@ page import="com.google.unizone.server.DB_Logic" %>
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
  	<div id=allWhiteBox>
  		<%=Utils.appToString("course")%>
  		<%     
  		String courseID = request.getParameter("courseID");
  		String FBid = request.getParameter("FBid");
  		List<String> mutualFriends;
  		String APP_ID = System.getProperty("APP_ID");
  		String APP_SECRET = System.getProperty("APP_SECRET_CODE");
  		
  		ExtendedFaceBookClient fbclient = new ExtendedFaceBookClient(APP_ID, APP_SECRET);
  		
  		Student meStudent = DB_Logic.getStudent(FBid);
  		fbclient.setAccessToken(meStudent.getAccessToken());
  		Course course = DB_Logic.getCourse(courseID);
		%>
			<%=course.toString(FBid,true) %> 
	  		
	  		<%	  		Set<String> courseStudents = course.getStudents();
	  		for (String studentID : courseStudents) {
				
				Student student = DB_Logic.getStudent(studentID);
			if (meStudent != null && student != null && !student.equals(meStudent)){
				%><br><div id="line"><%= student.toString(meStudent,false,fbclient) %></div>
			<%}
			}
			%>
		</div>
	</body>
	
</html>