<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="beans.*"%>
     <%@page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>INTELLIGENT NOTIFICATIONS SYSTEM</title>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.9.2/themes/base/jquery-ui.css" />
<link rel="stylesheet" href="css/normalize.css">
<link rel="stylesheet" href="css/DahBoardMenu.css" media="screen" type="text/css" />
<link rel="stylesheet" type="text/css" href="css/EditPreferences.css" />
 <link rel="stylesheet" href="css/style4.css" type="text/css" media="screen"/>
<script src="http://code.jquery.com/jquery-1.8.3.js"></script>
<script src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>
 
<script type="text/javascript">
function RegisterAction()
{	
	var evntid = document.getElementById("eventID").value;
	var url = document.getElementById("url").value;
	alert(eventid+"  "+url );
window.location= "/Notification_System/RegisterEvent?eventID="+evntid+"&url="+url; 
}	
	function editPreferences()
	{
	 	$.post("ViewPreferences",{},function(data){
	 		alert("data"+ data);
			if(data != "")
			{
				$("#MainDisplayArea").html(data);
				  var htmlCode = "<div id='MainDisplayAreaContent'></div>";
				  $("#MainDisplayArea").append(htmlCode);
				  return true;
			}
		});
	 	//return false;
	}
	
	function viewSelectedPreference()
	{
	 	var selectedPrefID = $("#UserPreferenceID").val();
	 	$.post( "EditPreference",{userPreferenceID:selectedPrefID}, function( data ) {
	  		if(data!="")
	 		{
	  			$("#MainDisplayAreaContent").html(data);
	  			$("#MainDisplayAreaContent").find("script").each(function(){
	  			     eval($(this).text());
	  			   });
	  			return true;
	 		}
		});
	 	return false;
	}
</script>
<% ArrayList<UserPreferenceGroup> userpref = (ArrayList<UserPreferenceGroup>)request.getAttribute("userpref");
User user = (User)request.getAttribute("user");
%>
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
<div style="float:left;width:20%; padding-left: 20px" class="container">
  <ul>
 <li class="dropdown">
  <a href="#" data-toggle="dropdown"><b><i><font size="5">Welcome <%=user.getFirstName()%> </font><i class="icon-arrow"></i></i></b></a>
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
      <a href="#" data-toggle="dropdown">My Preferences <i class="icon-arrow"></i></a>
      <ul class="dropdown-menu">
        <li><a href="EditUserPreference">Edit Preferences</a></li>
      </ul>
    </li>
    <!-- <li class="dropdown">
      <a href="#" data-toggle="dropdown">My Profile <i class="icon-arrow"></i></a>
      <ul class="dropdown-menu">
        <li><a href="#">Edit Profile</a></li>
      </ul>
    </li> -->
  </ul>
</div>


<div id="MainDisplayArea" style="float:left; width:70%; padding-top:25px; padding-left:30px;">
	<font size="6" face="BankGothic"><b>RECOMMENDED EVENTS</b></font>
	<hr style="border-top: dotted 1px;" />
	 <%
		for(int i=0; i<userpref.size(); i++) {
      %>
      <H2><font face="Architect"><%=userpref.get(i).getCategoryName() %></font></H2>
      <%
       	List<Event> eventList = userpref.get(i).getEvents();
      if(eventList!=null && eventList.size()!=0 ){
    	  %><table >
		<%for(int j=0; j<eventList.size(); j++) {
       	%>		
        	
       					 <tr>
       						<td><b><i><font size="4.5"><%=eventList.get(j).getEventTitle() %></font></i></b></td>
       			 		 </tr>
       		 	 		<tr>
       		 	 		<td><b>DESCRIPTION</b></td><td>&nbsp;</td><td><%=eventList.get(j).getEventDescription() %></td>
       		 	 		</tr>
       		 	 		<tr>
       		 	 		<td><b>DATE</b> </td><td>&nbsp;</td><td><%=eventList.get(j).getEventDate() %></td>
       		 	 		</tr>
       		 	 		<tr>
       		 	 		<td>
       		 	 		<form name="frm">
						<a href="/Notification_System/RegisterEvent?eventID=<%=eventList.get(j).getEventID() %>&url=<%=eventList.get(j).getEventURL() %>&categoryName=<%=userpref.get(i).getCategoryName() %>"><input type="button" value="Register For The Event" name="Register"></a>
						</form>
       		 	 		</td>
       		 	 		
       		 	 		</tr>
       		 	 		<%if(eventList.size()!=1 && j != (eventList.size()-1)) {%>
       		 			<tr><td><hr style="border-top: dotted 1px;" /></td></tr>
       		 			<%} %>
        <%
        }%></table>
        <%}else{%>
        	<table >
       					 <tr>
       					 <td>
       					 <b><i><font size="3">There are no registered events under this category!!!</font></i></b>
       					 </td>
       					 </tr>
       					 </table>
      <%  }%>
        <hr style="border-top: dotted 1px;" />
        <%
        } %>
        
        	
       
	
</div>
</div>
</body>

<script src="js/index.js"></script>
