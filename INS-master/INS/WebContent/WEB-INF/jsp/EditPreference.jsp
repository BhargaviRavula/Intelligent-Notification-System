<%@page import="beans.Contact"%>
<%@page import="beans.*"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="beans.Category"%>
<%@page import="beans.UserPreference"%>
<%@page import="beans.UserPreferenceGroup"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
  <%
  	 List<UserPreferenceGroup> listofunregpref = (List<UserPreferenceGroup>)request.getAttribute("preference");
  List<UserPreferenceGroup> userPrefList =(List<UserPreferenceGroup>) request.getAttribute("userPreferencegrp");
  	List<NotificationType> allNotificationTypes = (List<NotificationType>)request.getAttribute("allNotificationTypes");
  	List<Contact> allContacts = (List<Contact>)request.getAttribute("userContacts");

  	
  %>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>INTELLIGENT NOTIFICATIONS SYSTEM</title>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.9.2/themes/base/jquery-ui.css" />
<link rel="stylesheet" href="css/normalize.css">
<link rel="stylesheet" href="css/DahBoardMenu.css" media="screen" type="text/css" />
<link rel="stylesheet" type="text/css" href="css/EditPreferences.css" />
<script src="http://code.jquery.com/jquery-1.8.3.js"></script>
<script src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>
<script type="text/javascript">
var count=0;
function addNewNotification()
{
	$sampleData = $("#NotificationSample").html();
	alert($sampleData);
	count = count+1;
	$sampleData = $sampleData.split('notificationContact0').join('notificationContact'+count);
	alert($sampleData);
	$sampleData = $sampleData.split('notificationUnitCount0').join('notificationUnitCount'+count);
	$sampleData = $sampleData.split('notificationType0').join('notificationType'+count);
	$("#Notification").append($sampleData);
}
</script>
</head>
  

<body>

<header style="display:block; margin:0 20px 10px; padding:20px 30px 10px; position:relative;text-align:center;text-shadow:rgba(0, 0, 0, 0.2) 1px 1px 1px;">
	<h1 style="color:rgba(26, 89, 120, 0.901961);font-family:BebasNeueRegular, 'Arial Narrow', Arial, sans-serif !important;
	font-size:35px;font-weight:400;line-height:35px;padding:0 0 5px;position:relative;
	text-shadow:rgba(0, 0, 0, 0.0980392) 1px 1px 1px;">
	<span style="color:#7CBCD6;text-shadow:rgba(255, 255, 255, 0.8) 0 1px 1px;">INTELLIGENT</span> 
	NOTIFICATION SYSTEM
	</h1>
</header>

<div style="width:100%;">
<!-- LEFT MENU -->
<div style="float:left;width:19%; padding-left: 20px" class="container">
  <ul>
  <li class="dropdown">
  <a href="#" data-toggle="dropdown"><b><i><font size="5">Welcome  </font><i class="icon-arrow"></i></i></b></a>
   <ul class="dropdown-menu">
        <li><a href="Dashboard">Dashboard</a></li>
         <li><a href="Logout">Log Out</a></li>
        </ul>
 </li>
    <li class="dropdown">
      <a href="#" data-toggle="dropdown">My Events <i class="icon-arrow"></i></a>
      <ul class="dropdown-menu">
        <li><a href="RegisteredEvents">Registered Events</a></li>
        <li><a href="RecommendedEvents">Recommended Events</a></li>
      </ul>
    </li>
    <li class="dropdown">
      <a href="EditUserPreference" data-toggle="dropdown">My Preferences <i class="icon-arrow"></i></a>
      <ul class="dropdown-menu">
        <li><a href="#">Edit Preferences</a></li>
      </ul>
    </li>
    
  </ul>
</div>


<div id="MainDisplayArea" style="float:left; width:75%; padding-top:25px; padding-left:10px;">

<h1>Edit Preferences Page</h1>
<h2> Subscribe For Events</h2>


<span class="clr"></span>

<form action="SaveInitialSettings" method="post">
	<div id="selectPreferences">
		<h3>Select the events you want to get notifications for: </h3>
	
			<p id ="eventNotification">
			
			<span>How would you like to be notified?</span><br/><br/>
			<span id="NotificationSample">
			<span>By </span>
			<select name="notificationContact0">
			<%for(Contact contact: allContacts) 
			               		{%> 
			               		<option value="<%=contact.getContactID()%>">
			               				<%=contact.getContactValue()%>
			               		</option>
			               		<%} %>
			</select>
			<input id="notificationCount" name="notificationUnitCount0"
			type="text" value="15">
			
			<select name="notificationType0" id="dropdown">
			                <%for(NotificationType notificationType: allNotificationTypes) 
			               		{%> 
			               		<option value="<%=notificationType.getNotificationTypeID()%>">
			               				<%=notificationType.getNotificationTypeName()%>
			               		</option>
			               		<%} %>
			</select>
			before the event<br>
			</span>
			<span id="Notification">
			
			</span>
				
			<label onclick="addNewNotification()">Add Another Notification</label>
			
			</p>
		<div id="checkboxItems">
		<%for(UserPreferenceGroup grp: listofunregpref) 
		{ %>
		<input type="checkbox" name="EventTypeCheckBox" value="<%=grp.getCategoryID()%>">
		<a onmouseover="this.style.backgroundColor='#2C89C6';this.style.color='#FFF'"
   onmouseout="this.style.backgroundColor='transparent';this.style.color='inherit'"
		href="/Notification_System/EventList?category=<%=grp.getCategoryName()%>">
		<%=grp.getCategoryName()%></a><br>
		<%} %> 
		</div>
		<p><input style="padding-left:12px;padding-right:12px" type="submit" onclick="" value="Save">
		<button style="margin-left:20px;padding-top:2px;padding-bottom:2px;" onclick="Dashboard">Cancel</button>
		</p>
	</div>
</form>

</div>
</div>

<script type="text/javascript" src="js/index.js"></script>
</body>
</html>