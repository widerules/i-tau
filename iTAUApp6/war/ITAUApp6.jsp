<%@ page import="com.restfb.*" %>
<%@ page import="com.google.itau.client.SessionManager" %>

<!doctype html>
<!-- The DOCTYPE declaration above will set the     -->
<!-- browser's rendering engine into                -->
<!-- "Standards Mode". Replacing this declaration   -->
<!-- with a "Quirks Mode" doctype is not supported. -->

<html> 
  <head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">

    <!--                                                               -->
    <!-- Consider inlining CSS to reduce the number of requested files -->
    <!--                                                               -->
    <link type="text/css" rel="stylesheet" href="ITAUApp6.css">

    <!--                                           -->
    <!-- Any title is fine                         -->
    <!--                                           -->
    <title>Web Application Starter Project</title>
    
    <!--                                           -->
    <!-- This script loads your compiled module.   -->
    <!-- If you add any GWT meta tags, they must   -->
    <!-- be added before this line.                -->
    <!--                                           -->
    <script type="text/javascript" language="javascript" src="itauapp6/itauapp6.nocache.js"></script>
  </head>

  <!--                                           -->
  <!-- The body can have arbitrary html, or      -->
  <!-- you can leave the body empty if you want  -->
  <!-- to create a completely dynamic UI.        -->
  <!--                                           -->
  <body>

    <!-- OPTIONAL: include this if you want history support -->
    <iframe src="javascript:''" id="__gwt_historyFrame" tabIndex='-1' style="position:absolute;width:0;height:0;border:0"></iframe>
    
    <!-- RECOMMENDED if your web app will not function without JavaScript enabled -->
    <noscript>
      <div style="width: 22em; position: absolute; left: 50%; margin-left: -11em; color: red; background-color: white; border: 1px solid red; padding: 4px; font-family: sans-serif">
        Your web browser must have JavaScript enabled
        in order for this application to display correctly.
      </div>
    </noscript>
    
    <% 
    	String APP_ID = System.getProperty("APP_ID");
		String APP_SECRET = System.getProperty("APP_SECRET");
		String SCOPE = System.getProperty("SCOPE");
		String CANVAS_APP_URL = System.getProperty("CANVAS_APP_URL");
		SessionManager httpSession = new SessionManager(request, response);
		ExtendedFaceBookClient fbclient = new ExtendedFaceBookClient(APP_ID, APP_SECRET);
		Parameter scope = Parameter.with("scope", SCOPE); %>
		// top.location = "<%= "https://graph.facebook.com/oauth/access_token?client_id=289095354463141&redirect_uri=http://apps.facebook.com/itau_application/homepage.jsp&client_secret=d66e6579629895f958012fd28f41e1d0&code=AQANEF0csVF83deqjBe7fYwNEJaOfowBh8OTaqfE4Dwti6TnqVLYbIjaZ6yvKsHg2VbZXch57eqR-Cg10cyW6BgatbiK0qloS71JE3fI-SFOcJPjPKh-787YMXjP_aO-X441dxJQ4teUtE2lLRYQ2KS20pITm1hyA87fkdwCfy4l_Y8dbHgzKpgvo-XHUY_UN5A#_=_" %>";
		top.location = "<%= "http://www.one.co.il %>";
	
		//if (httpSession.getCustomerAccessToken() == null){ %>
				<script type="text/javascript">
			        top.location = "<%= fbclient.getCanvasAuthorizeURL(CANVAS_APP_URL + "homepage.jsp", scope) %>";
			 	</script>
			 	
			 	</script>
			 	</body>
			 	</html>
		//	<%
		//		return;
			//}
			
		//	else{
				//Set Access Customer access_token in session for future use
	//			httpSession.setCustomerAccessToken(fbclient.getAccessToken());
	//		}
	//	%>
		
		<h1><%= fbclient.getAccessToken() %> </h1>
    <h1>Web Application Starter Project</h1>

    <table align="center">
      <tr>
        <td colspan="2" style="font-weight:bold;">Please enter your name:</td>        
      </tr>
      <tr>
        <td id="nameFieldContainer"></td>
        <td id="sendButtonContainer"></td>
      </tr>
      <tr>
        <td colspan="2" style="color:red;" id="errorLabelContainer"></td>
      </tr>
    </table>
  </body>
</html>
