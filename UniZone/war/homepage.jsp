<%@ page import="com.restfb.*" %>
<%@ page import="com.restfb.types.*" %>
<%@ page import="com.google.unizone.client.SessionManager" %>
<%@ page import="java.util.*" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.google.unizone.server.DB_Logic" %>
<%@ page import="com.google.unizone.server.IShiur" %>
<%@ page import="com.google.unizone.server.*" %>


<!doctype html>
<html xmlns="http://www.w3.org/1999/xhtml" >
  <head>


  <script language="JavaScript" type="text/javascript">
<!-- Copyright 2005, Sandeep Gangadharan -->
<!-- For more free scripts go to http://www.sivamdesign.com/scripts/ -->
<!--
if (document.getElementById) {
document.writeln('<style type="text/css"><!--')
document.writeln('.texter {display:none} @media print {.texter {display:block;}}')
document.writeln('//--></style>') }

function openClose(theID) {
if (document.getElementById(theID).style.display == "block") { document.getElementById(theID).style.display = "none" }
else { document.getElementById(theID).style.display = "block" } }
// -->
</script>


  
  
  
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
  <link type="text/css" rel="stylesheet" href="style.css">
  </head>
	<div id = allWhiteBox>


	<script type="text/javascript">
function toggleMe(a){
var e=document.getElementById(a);
if(!e)return true;
if(e.style.display=="none"){
e.style.display="block"
}
else{
e.style.display="none"
}
return true;
}
</script>

    <%
 		SessionManager httpSession = new SessionManager(request, response);
  		
  		String APP_ID = System.getProperty("APP_ID");
  		String APP_SECRET = System.getProperty("APP_SECRET_CODE");
  		String CANVAS_APP_URL = System.getProperty("CANVAS_APP_URL");
  		String code = request.getParameter("code");
  		ExtendedFaceBookClient fbclient = new ExtendedFaceBookClient(APP_ID, APP_SECRET);
  		List<String> myData;
  		String name, FBid, pic, appFriends;
  		fbclient.readAccessToken(code, CANVAS_APP_URL + "homepage.jsp");
		myData = fbclient.getMyData();
		
		name = myData.get(1);
		FBid = myData.get(2);
		pic = myData.get(0);
		//DB_Logic.startSimulation();
		
 		String course;
 		List<Course> courses;
 		String url = CANVAS_APP_URL + "register.jsp?FBid="+FBid;
 		Student student = DB_Logic.getStudent(FBid);
 		
 		if (student == null)
 		{%>
 				<script type="text/javascript">
           			top.location = "<%=url%>";
  				</script>
		<%}
 		else
 		{
 			appFriends = fbclient.appFriendsToString();
 			student.setPic(pic);
 			student.setName(name);
 			student.setFBFriends(Utils.FBFriendsToSet(fbclient.parseFriends()));
 			student.setAccessToken(fbclient.getAccessToken());
 			DB_Logic.updateStudent(student);
 			courses = DB_Logic.getStudentCourses(student);
 			%>
 			
 			<%=Utils.appToString("homepage")%>
 			<div id= "headline"><p><%= student.toString(true) %></p></div>
 			
 			 				 			
 			<p>
 				<div onClick="openClose('a2')" style="cursor:hand; cursor:pointer"><h3 id="sub_header">Available Friends</h3></div>
				<div id="a2" class="texter"> 				
 				<%= appFriends %>
				</div>
 			</p>
 			
 			<p>

 				<div onClick="openClose('a1')" value="open" style="cursor:hand; cursor:pointer"><h3 id="sub_header">Courses Groups</h3></div>
	 			<div id="a1" class="texter";>
	 			<%
	 
	 			for (Course c : courses) { 
					course = c.toString(myData.get(2),false);
				%>
					<div id="line"><%=course %></div>
				<% 
				}
				%>
				</div>
 			</p>
 			
 		<%
 		}%>
 		</div>
	</body>
</html>