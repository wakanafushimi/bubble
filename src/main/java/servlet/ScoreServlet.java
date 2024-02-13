package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Userscore;

/**
 * Servlet implementation class ScoreServlet
 */
@WebServlet("/ScoreServlet")
public class ScoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ScoreServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String scorestr=request.getParameter("data");
		int score=Integer.parseInt(scorestr);

		HttpSession session=request.getSession();
		int day = 24 * 60 * 60;
		session.setMaxInactiveInterval(day);

		String username=(String)session.getAttribute("username");

		Map<String,Userscore> scoremap=new HashMap<String,Userscore>();

		if((Map<String,Userscore>)session.getAttribute("scoremap")!=null) {
			scoremap=(Map<String,Userscore>)session.getAttribute("scoremap");
		}

		Userscore userscore = scoremap.getOrDefault(username, new Userscore(username));
		userscore.addscore(score);

		scoremap.put(username,userscore);

		List<Map.Entry<String, Userscore>> sortedEntries = new ArrayList<>(scoremap.entrySet());
		Collections.sort(sortedEntries, new Comparator<Map.Entry<String, Userscore>>() {
			@Override
			public int compare(Map.Entry<String, Userscore> entry1, Map.Entry<String, Userscore> entry2) {
				int maxScore1 = entry1.getValue().getMax();
				int maxScore2 = entry2.getValue().getMax();
				return Integer.compare(maxScore2, maxScore1);
			}
		});

		Map<String,Userscore> sortedScoremap=new HashMap<>();
		for (Map.Entry<String, Userscore> entry : sortedEntries) {
			sortedScoremap.put(entry.getKey(), entry.getValue());
			System.out.println(entry.getKey()+"さん:"+entry.getValue().getMax()+"点");
		}

		session.setAttribute("scoremap", sortedScoremap);



	}

}
