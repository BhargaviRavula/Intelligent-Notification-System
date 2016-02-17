package Technical;

import java.io.IOException;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Scheduler
 */
@WebServlet("/Scheduler")
public class Scheduler extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public Scheduler() {
        // TODO Auto-generated constructor stub
    }
    @Override
    public void init(){
    	  	TimerTask timerTask = new TimerTaskExample();
    	 Timer timer = new Timer(true);
    	    	        timer.scheduleAtFixedRate(timerTask,10 *1000, 900 * 1000);
    	
    	       System.out.println("TimerTask begins! :" + new Date());
    	
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	

}
