package com.google.unizone.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import com.google.gson.Gson;

import javax.servlet.http.HttpServletResponse;

public class ServletUtils {
	
	/*
	 * format of resposne : object.toJson()%object.toJson()%object.toJson()
	 */
	public static void writeToResponseAsJson(HttpServletResponse response, Iterable<? extends Object> list) throws IOException{
		Gson gson = new Gson();
		StringBuilder strBuilder = new StringBuilder();
		boolean wroteObject  = false;
		for (Object object : list) {
			strBuilder.append(gson.toJson(object));
			strBuilder.append("%");
			wroteObject = true;
		}
		if (wroteObject) {
			strBuilder.deleteCharAt(strBuilder.lastIndexOf("%"));
		}
		PrintWriter respOut = response.getWriter();
		respOut.print(strBuilder);
		respOut.close();
	}
	
	/*
	 * format of response : key1.toJson,value1.toJson%key2.toJson,value2.toJson
	 * is used for CoursePageServlet where need to return map of facebookID/AccessToken
	 * for students of the course - facebookID,AccessToken%facebookID,AccessToken.
	 */
	public static void writeToResponseAsJson(HttpServletResponse response, Map<? extends Object, ?extends Object> map) 
		throws IOException {
		Gson gson = new Gson();
		StringBuilder strBuilder = new StringBuilder();
		Set<Object> keySet = new TreeSet<Object>(map.keySet());
		boolean wroteEntry = false;
		for (Object key : keySet) {
			Object value = map.get(key);
			strBuilder.append(gson.toJson(key));
			strBuilder.append(",");
			strBuilder.append(gson.toJson(value));
			strBuilder.append("%");
			wroteEntry = true;
		}
		if (wroteEntry) {
			strBuilder.deleteCharAt(strBuilder.lastIndexOf("%"));
		}
	}

}
