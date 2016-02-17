<%@page import="beans.UserPreference"%>
<%@page import="beans.UserPreferenceGroup"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<% UserPreferenceGroup userPreferenceGroup = (UserPreferenceGroup)request.getAttribute("userPreferenceGroup"); %>
<div id="MainDisplayAreaHeader">
<h1>Edit Preferences Page</h1><h4><a id ='logOut' href='Logout'>LOG OUT</a></h4><br/>
<span class='clr'></span>
<%-- <select id="UserPreferenceID">
 			<%for(UserPreference userPreference: userPreferenceGroup.getUserPreference()) 
			 {%> 
	               		<option 
	               		           		value="<%=userPreference%>">
	               				<%=userPreference%>
	               		</option>
			<%} %>
			
</select> --%>
<button onclick='viewSelectedPreference()'>Edit</button>
<a style="margin-left:40px" href="BlankPreference">Add New Preference</a>
</div>