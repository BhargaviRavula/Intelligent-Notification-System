package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Event;
import beans.User;
import domain.EventService;

/**
 * Servlet implementation class EventList
 */
@WebServlet("/EventList")
public class EventList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EventList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String category_name= (String) request.getParameter("category");
		EventService  eventService = new EventService();
		User user = (User)request.getSession().getAttribute("user");
		List<Event> eventList = eventService.retrieveEventsforCategory(category_name);
		request.setAttribute("category", category_name);
		request.setAttribute("user", user);
		request.setAttribute("eventList", eventList);
		String nextJSP = "/WEB-INF/jsp/EventList.jsp";
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
		dispatcher.forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
