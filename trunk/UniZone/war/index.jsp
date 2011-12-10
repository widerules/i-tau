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
		Parameter scope = Parameter.with("scope", SCOPE);
	
		if (httpSession.getCustomerAccessToken() == null){			
			
			//Verify user has installed our app. If not Redirect to Installation + Redirect Back
			if (!fbclient.checkUser(httpSession, true, scope)){	%>
			 	<h1>Login failed (should never happen)</h1>
			<%
				return;
			}
			
			else{
				//Set Access Customer access_token in session for future use
				httpSession.setCustomerAccessToken(fbclient.getAccessToken());
			}
		}
		%>
		
		<h1><%= fbclient.getAccessToken() %> </h1>
  </body>
</html>
