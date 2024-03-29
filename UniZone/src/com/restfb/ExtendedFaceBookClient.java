package com.restfb;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.google.unizone.client.SessionManager;
import com.google.unizone.server.DB_Logic;
import com.google.unizone.server.Student;

import com.restfb.types.OAuth;
import com.restfb.types.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
/***
 * 
 * An extendet RestFB facebook client.
 * 
 * Includes functions for retrieving object's access token.
 *  
 * 
 * @author Nitzan Bar
 *
 */
public class ExtendedFaceBookClient extends DefaultFacebookClient {


	protected static final String EXCHANGE_SESSIONS_OBJECT = "oauth/exchange_sessions";
	protected static final String ACCESS_TOKEN_OBJECT = "oauth/access_token";
	protected static final String AUTHORIZE_OBJECT = "oauth/authorize";
	protected static final String SCOPE_PARAM_NAME = "scope";

	protected String APP_ID;
	protected String APP_SECRET;
	private static final String FB_CANVAS_PARAM_PREFIX = "fb_sig";
	protected HashMap<String,String> fb_params;

	/**
	 * Exchange facebook session_key with access_token 
	 * @param session_key
	 * @return An OAuth access token
	 * @throws FacebookException
	 */
	public OAuth exchangeSession(String session) throws FacebookException {
		verifyParameterPresence("session", session);


		String response = makeRequest(EXCHANGE_SESSIONS_OBJECT, false, true, false, null, Parameter.with("client_id", this.APP_ID),
				Parameter.with("client_secret", this.APP_SECRET),
				Parameter.with("sessions", session));

		List<String> list = jsonMapper.toJavaList(response, String.class);

		if (list != null){
			return jsonMapper.toJavaObject(list.get(0), OAuth.class);
		}
		else
			return null;
	}


	/**
	 * Exchange facebook code with access_token 
	 * @param code
	 * @param redirect_uri - The uri used to obtain the code. MUST match the previous request!
	 * @return An OAuth access token
	 * @throws FacebookException
	 */


	public void readAccessToken(String code, String redirect_uri) {
		Pattern p = Pattern.compile("(.*)&.*");
		Matcher matcher;
		try
		{
			String fb_accessTokenSeq = "access_token=";

			String response = makeRequest(ACCESS_TOKEN_OBJECT, false, true, false, null, Parameter.with("client_id", this.APP_ID),
					Parameter.with("client_secret", this.APP_SECRET),
					Parameter.with("redirect_uri", redirect_uri),
					Parameter.with("code", code));

			if (response != null & response.startsWith(fb_accessTokenSeq)){
				response =  response.substring(fb_accessTokenSeq.length());
				matcher = p.matcher(response);
				matcher.find();
				response = matcher.group(1);
				this.accessToken = response;
			}
		}
		catch(FacebookException e) {}

	}
	
	public void setAccessToken(String accessToken){
		this.accessToken = accessToken;
	}
	

	

	public List<Student> parseFriends () {
		//Pattern friendUID = Pattern.compile("{\"uid2\":\"(\\d*)\"}" );
		Pattern friendUID = Pattern.compile(":\"(.*)\"}" );
		Matcher matcher;
		FacebookClient fbclient = new DefaultFacebookClient(this.getAccessToken());
		String temp = null;
		List<String> query = new ArrayList<String>();
		List<String> myFriends = new ArrayList<String>();
		List<Student> appFriends = new ArrayList<Student>(); 
		List<Student> allStudents;
		try{
			String fqlFriends = "SELECT uid2 FROM friend WHERE uid1 = me()";
			query = fbclient.executeQuery(fqlFriends, String.class);
			
		}
		catch (Exception e) {
			return null;
		}
		String uid;
		for (String string : query) {
			matcher = friendUID.matcher(string);
			matcher.find();
			string = matcher.group(1);
			myFriends.add(string);
			temp += string + "\n";
		};
		temp+= "\n";
		 allStudents = DB_Logic.getAllStudents();
		 for (Student iStudent : allStudents) {
			temp += iStudent.getFacebookID() + "\n";
			 uid = iStudent.getFacebookID();
			
			if (myFriends.contains(uid)){
				appFriends.add(iStudent);
			}
		}
		
		 // return temp;
		return appFriends;
	}
	
	
	
	
	
	public String appFriendsToString(){
		List<Student> appFriends = parseFriends();
		String output = "";
		for (Student iStudent : appFriends) {
			output += "<div id=\"line\">" + iStudent.toString(false) + "</div>";
		}
		return output;
	}
	
	
	
	public List<String> getMutualFriends(Student student){
		
		FacebookClient fbclient = new DefaultFacebookClient(this.accessToken); //AAADu2olM2swBADtIAeQqZB8tTZAxVHwtxabX95UM63xP65JabfTdOtAzstSt2AoHTOkGEQm6JxcdkJC8gqErWReIDbZBAax4JgpUT9FDXVhXXr242fN
		List<String> mutualFriends = new ArrayList<String>();
		
		User u;
		String fqlMututal = "SELECT uid, name, pic_square FROM user where uid IN (SELECT uid1 FROM friend WHERE uid2=" + student.getFacebookID() + " AND uid1 IN (SELECT uid2 FROM friend WHERE uid1=me()))";
		//String fqlMututal  = "SELECT uid, name, pic_square FROM user WHERE uid IN (SELECT uid2 FROM friend WHERE uid1 = me()) AND uid IN (SELECT uid2 FROM friend WHERE uid1 =" + student.getFacebookID() + ")";
		try{
			u = fbclient.fetchObject("me", User.class);
			if(u == null)
				return null;
			    
		//	String fqlMututal  = "SELECT pic_square From user WHERE uid = me()";
			mutualFriends = fbclient.executeQuery(fqlMututal, String.class);
		}
		catch (FacebookException e){
			return null;
		}
		
		return mutualFriends;
	}
	
	public String getMyPic (){
		Pattern pa = Pattern.compile("profile.*jpg");
		Matcher match;
		FacebookClient fbclient = new DefaultFacebookClient(this.getAccessToken());
		List<String> myPic = new ArrayList<String>();
		
		User u;
		String output = "";
		try{
			u = fbclient.fetchObject("me", User.class);
			String fqlPic = "SELECT pic_square From user WHERE uid = me()";
			myPic = fbclient.executeQuery(fqlPic, String.class);
		}
		catch (FacebookException e){
			return null;
		}
		output =  myPic.get(0);
		match = pa.matcher(output);
		match.find();
		return "http://" + match.group();
	}
	
	
	
	
	public List<String> getMyData () {
		Pattern name = Pattern.compile("name\":\"(.*)\",");
		Pattern id = Pattern.compile("uid\":(\\d*)");
		Pattern pa = Pattern.compile("profile.*jpg");
		Matcher matcher, matcher2, matcher3;
		String temp;
		FacebookClient fbclient = new DefaultFacebookClient(this.getAccessToken());
		List<String> myData,query = new ArrayList<String>();
		
		User u;
		String output = "";
		try{
			u = fbclient.fetchObject("me", User.class);
			//String fql = "SELECT uid, name, pic_square FROM user WHERE uid = me() OR uid IN (SELECT uid2 FROM friend WHERE uid1 = me())";
			String fql = "SELECT name, pic_square, uid FROM user WHERE uid = me()";
			//String fql = "select uid2 from friend where uid1 = " + u.getId();
			
			myData = fbclient.executeQuery(fql, String.class);
			
		}
		catch (FacebookException e){
			query.add("glaaa");
			return query;
		}
		
	
		
		
		output =  myData.get(0);
		matcher = pa.matcher(output);
		matcher.find();
		temp = "http:////" + matcher.group();
		query.add(0, temp);
		matcher2 = name.matcher(output);
		matcher2.find();
		temp = matcher2.group(1);
		query.add(1, temp);
		matcher3 = id.matcher(output);
		matcher3.find();
		temp = matcher3.group(1);
		query.add(2, temp);
		//query.add(3,myFriends)
		
		
		return query;
		
	}
	
	
	
	
	


	public String exchangeCode(String code, String redirect_uri) throws FacebookException {

		//Facebook returns "access_code=XXX"
		//This function returns XXX
		String fb_accessTokenSeq = "access_token=";

		verifyParameterPresence("code", code);
		verifyParameterPresence("redirect_uri", redirect_uri);

		String response = makeRequest(ACCESS_TOKEN_OBJECT, false, true, false, null, Parameter.with("client_id", this.APP_ID),
				Parameter.with("client_secret", this.APP_SECRET),
				Parameter.with("redirect_uri", redirect_uri),
				Parameter.with("code", code));

		if (response != null & response.startsWith(fb_accessTokenSeq)){
			return response.substring(fb_accessTokenSeq.length());
		}
		else
			return null;

	}


	public ExtendedFaceBookClient(String access_token){
		super(access_token);
	}

	public ExtendedFaceBookClient(String APP_ID, String APP_SECRET){
		super();
		this.APP_ID = APP_ID;	 
		this.APP_SECRET = APP_SECRET;
		this.fb_params = new HashMap<String, String>();



	}

	public String getAccessToken(){
		return this.accessToken;
	}

	/** 
	 * This function handles authentication procedure for canvas & non-canvas apps in facebook
	 * @param session
	 * @param isCanvas
	 * @param scope
	 * @return true if authentication procedure sucedeed false other wise. Note: Some procedures make involve sending a redirect  returning false.
	 */
	public boolean checkUser(SessionManager session, boolean isCanvas, Parameter scope){ //TODO: Build ENUM for scope


		verifyParameterPresence("scope", scope);

		OAuth oauth;


		//TODO: Handle case user has not authorized the app
		if (isCanvas){ //Find out if user is logged on & verify canvas params

			//Get Canvas Parameters
			if(!getAndValidateCanvasFBParams(session.getRequest())){
				return false; 
			}

			//Check that app is added and we have permissions
			//TODO: Verify we have all permissions
			if (!fb_params.containsKey("added") ||  !fb_params.get("added").equals("1")){ 
				return false;
			}

			if (!fb_params.containsKey("user")){
				return false;
			}

			//Exchange session key for an OAuth token
			try {
				oauth = exchangeSession(fb_params.get("session_key"));
			}
			catch (FacebookException e){
				return false;
			}

			this.accessToken = StringUtils.trimToNull(oauth.getAccessToken());


			return true;


		}
		else { //Non canvas method


			//Check for "code" parameter in order to exchange with access_token
			String code = session.getRequest().getParameter("code");

			//No code yet - redirect user to authorize app page
			if (code == null){ 

				//Build redirect url (Include queryString in redirect_uri
				//This is the url facebook will redirect back after authorization
				String redirect_uri = session.getRequest().getRequestURL() + "?" + session.getRequest().getQueryString();

				//Get url to redirect user to
				String url = getCanvasAuthorizeURL(redirect_uri);	  

				try {
					session.getResponse().sendRedirect(url);
				} catch (IOException e) { //Error redirecting
					return false;
				}  

			}

			else{ //We have a CODE! Exchange for access_token


				//Rebuild redirect_uri to exchange "code=" in access_token  
				String redirect_uri =  session.getRequest().getRequestURL() + "?";

				//Remove &code= from QueryString (In order to send facebook the exact redirect_uri as before)
				String subString = "&code=";
				int x = session.getRequest().getQueryString().lastIndexOf(subString);
				redirect_uri += session.getRequest().getQueryString().substring(0, x);


				//Exchange code for access_token
				String access_token = null;
				try{
					access_token = exchangeCode(code, redirect_uri);
				}
				catch (FacebookException e){
					return false;
				}

				if (access_token != null){					
					this.accessToken = StringUtils.trimToNull(access_token);
					return true;

				}				
			}	  

		}


		return false;

	}

	/**
	 * Get Http request params and parse into HasMap<String,String>
	 * @param The HttpServletRequest 
	 * @return A HashMap containin all the request's parameters
	 */
	private HashMap<String,String> getRequestParams(HttpServletRequest request){

		HashMap<String,String> params = new HashMap<String, String>();
		Map<String,String[]> map = request.getParameterMap();

		for (Entry<String,String[]> entry : map.entrySet()){
			params.put(entry.getKey(), entry.getValue()[0]);
		}

		return params;

	}


	/**
	 * Get Facebook parameters from request and validate signature 
	 * @param request
	 * @return True iff parameters match signature. this.fb_params will hold all of the parameters
	 */
	private boolean getAndValidateCanvasFBParams(HttpServletRequest request) { 

		//TODO: Implement OAuth 2.0 
		//TODO: Verify Timeout 

		HashMap<String,String> params = getRequestParams(request);


		//Get all parameters, remove fb_sig_ from key name and put in HashMap
		String prefix = FB_CANVAS_PARAM_PREFIX + "_";
		int prefix_length = prefix.length();				

		for (Entry<String,String> param : params.entrySet()){			

			if (param.getKey().indexOf(prefix) == 0){
				String key = param.getKey().substring(prefix_length);
				this.fb_params.put(key, param.getValue());
			}

		}

		String str = generateCanvasSignature(this.fb_params);

		String expectedSig = params.get(FB_CANVAS_PARAM_PREFIX);

		return verifyCanvasSignature(str, expectedSig);		


	}


	/**
	 * @return
	 */
	private String generateCanvasSignature(HashMap<String,String> map) {
		//Sort Keys to verify signature
		SortedSet<String> keys = new TreeSet<String>(map.keySet());
		String str = new String();
		for (String key : keys){
			str += key + "=" + fb_params.get(key);
		}

		str += this.APP_SECRET;
		return str;
	}


	private boolean verifyCanvasSignature(String str, String expectedSig) {
		byte[] hash;	   
		try {
			MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
			digest.update(StringUtils.toBytes(str));
			hash = digest.digest();
		}
		catch (NoSuchAlgorithmException e){
			throw new IllegalStateException("The platform does nto support MD5" , e);
		}

		StringBuilder result = new StringBuilder();

		for ( byte b : hash ) {
			result.append( Integer.toHexString( ( b & 0xf0 ) >>> 4 ) );
			result.append( Integer.toHexString( b & 0x0f ) );
		}			   

		return result.toString().equals(expectedSig);
	}


	/**
	 * 
	 * Generate Facebook App Authorize URL based on parameters (For Canvas App)
	 * 
	 * @param redirect_url The URL to redirect after login
	 * @param scope The permissions to request from User
	 * 
	 */
	public String getCanvasAuthorizeURL(String redirect_url){

		StringBuilder url = new StringBuilder();


		//https://www.facebook.com/dialog/oauth?
		//client_id=YOUR_APP_ID&redirect_uri=YOUR_URL&scope=email,read_stream
		url.append("https://www.facebook.com/dialog/oauth?");
		url.append("client_id=" + this.APP_ID);
		url.append("&redirect_uri=" + StringUtils.urlEncode(redirect_url));
		url.append("&scope=" + "email");

		return url.toString();

	}

	public String getAccessTokenURL(String redirect_url, String code){

		//https://graph.facebook.com/oauth/access_token?
		//client_id=YOUR_APP_ID&redirect_uri=YOUR_URL&
		//client_secret=YOUR_APP_SECRET&code=THE_CODE_FROM_ABOVE
		StringBuilder url = new StringBuilder();

		url.append("https://www.facebook.com/oauth/access_token?");
		url.append("client_id=" + this.APP_ID);
		url.append("&redirect_uri=" + StringUtils.urlEncode(redirect_url));
		url.append("&client_secret=" + this.APP_SECRET);
		url.append("&code=" + code);

		return url.toString();

	}

	//	  /**
	//	   * 
	//	   * 
	//	   * Generate Facebook Authorization URL (TO authorize an APP)
	//	   *	   
	//	   */
	//	  public String getAuthorizeURL(HttpServletRequest request, Parameter scope){
	//		  
	//		  StringBuilder url = new StringBuilder();
	//		  		  		  
	//		  url.append(FACEBOOK_GRAPH_ENDPOINT_URL + "/");
	//		  url.append("oauth/authorize?");
	//		  url.append("client_id=" + this.APP_ID);
	//		  url.append("&redirect_uri=" + this.getCurrentURL(request));
	//		  url.append("&scope=" + scope.value);
	//		  
	//		  return url.toString();
	//		  
	//	  }

	/**
	 * Return the HTTP request URL (URL Encoded)
	 * @return
	 */
	private String getCurrentURL(HttpServletRequest request){


		return StringUtils.urlEncode(request.getRequestURL().toString());		

	}

	/*
	public String getLoginStatusUrl() {

		StringBuilder url = new StringBuilder();

		url.append("http://www.facebook.com/");
		url.append("extern/login_status.php?");
		url.append("api_key=164864586891890");
		url.append("&ok_session=http://check-me-in.appspot.com/dump");
		url.append("&no_session=http://check-me-in.appspot.com/dump");
		url.append("&no_user=http://check-me-in.appspot.com/dump");
		url.append("&session_version=3");

		return url.toString();

	}*/

}
