package servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//login
		
		request.setCharacterEncoding("UTF-8");
		String username=request.getParameter("username");
		String password=request.getParameter("password");

		HttpSession session=request.getSession();

		Map<String,String> usermap=new HashMap<>();

		try {
			usermap=(Map)session.getAttribute("usermap");
			String pwfrommap=(String)usermap.get(username);
			if(pwfrommap!=null) {
				if(pwfrommap.equals(password)) {
					session.setAttribute("username", username);
					RequestDispatcher dispatcher=request.getRequestDispatcher("game.jsp");
					dispatcher.forward(request,response);
				}else{
					RequestDispatcher dispatcher=request.getRequestDispatcher("loginfail.jsp");
					dispatcher.forward(request,response);
				}
			}else {
				RequestDispatcher dispatcher=request.getRequestDispatcher("loginfail.jsp");
				dispatcher.forward(request,response);
			}

		}catch(Exception e){
			RequestDispatcher dispatcher=request.getRequestDispatcher("loginfail.jsp");
			dispatcher.forward(request,response);
		}

//		for(String key : usermap.keySet()) {
//			System.out.println(key+":"+usermap.get(key));
//		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//signin
		
		request.setCharacterEncoding("UTF-8");
		String newusername=request.getParameter("username");
		String newpassword=request.getParameter("password");

		HttpSession session=request.getSession();
		int month = 30 * 24 * 60 * 60;
		session.setMaxInactiveInterval(month);
		
		Map<String,String> usermap=new HashMap<>();
		if((Map)session.getAttribute("usermap")!=null) {
			usermap=(Map)session.getAttribute("usermap");
		}

		if(usermap.get(newusername)!=null) {
			RequestDispatcher dispatcher=request.getRequestDispatcher("signinfail.jsp");
			dispatcher.forward(request,response);
		}else {
			usermap.put(newusername,newpassword);
			session.setAttribute("usermap",usermap);
			session.setAttribute("username",newusername);
			RequestDispatcher dispatcher=request.getRequestDispatcher("game.jsp");
			dispatcher.forward(request,response);
		}
		
//		for(String key : usermap.keySet()) {
//			System.out.println(key+":"+usermap.get(key));
//		}

	}

}
