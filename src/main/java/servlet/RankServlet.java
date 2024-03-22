package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Dao;
import model.Userscore;

/**
 * Servlet implementation class RankServlet
 */
@WebServlet("/RankServlet")
public class RankServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RankServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		
//		request.setCharacterEncoding("UTF-8");
//		String scorestr=request.getParameter("data");
//		int score=Integer.parseInt(scorestr);
//		
//		HttpSession session=request.getSession();
//		session.setAttribute("score", score);
//		String username=(String)session.getAttribute("username");
//		
//		Userscore userscore=new Userscore(username,score);
//		
//		Dao dao=new Dao();
//		Map<String,Integer> scoreMap=dao.put(userscore);
//		
//		List<Map.Entry<String, Integer>> sortedEntries = new ArrayList<>(scoreMap.entrySet());
//		Collections.sort(sortedEntries, new Comparator<Map.Entry<String,Integer>>() {
//			@Override
//			public int compare(Map.Entry<String, Integer> entry1, Map.Entry<String, Integer> entry2) {
//				int maxScore1 = entry1.getValue();
//				int maxScore2 = entry2.getValue();
//				return Integer.compare(maxScore2, maxScore1);
//			}
//		});
//
//		Map<String,Integer> sortedScoremap=new HashMap<>();
//		for (Map.Entry<String, Integer> entry : sortedEntries) {
//			sortedScoremap.put(entry.getKey(), entry.getValue());
//			System.out.println(entry.getKey()+"さん:"+entry.getValue()+"点");
//		}
//
//		session.setAttribute("sortedEntries", sortedEntries);
//		
//		RequestDispatcher dispatcher=request.getRequestDispatcher("ranking.jsp");
//		dispatcher.forward(request,response);
		
		RequestDispatcher dispatcher=request.getRequestDispatcher("ranking.jsp");
		dispatcher.forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		System.out.println("RankServlet呼んだ");
		
		request.setCharacterEncoding("UTF-8");
		String scorestr=request.getParameter("data");
		int score=Integer.parseInt(scorestr);
		
		System.out.println("受け取った点数："+score);
		
		HttpSession session=request.getSession();
		String username=(String)session.getAttribute("username");
		Userscore userscore=new Userscore(username,score);
		
		Dao dao=new Dao();
		Map<String,Integer> scoreMap=dao.put(userscore);
		
		List<Map.Entry<String, Integer>> sortedEntries = new ArrayList<>(scoreMap.entrySet());
		Collections.sort(sortedEntries, new Comparator<Map.Entry<String,Integer>>() {
			@Override
			public int compare(Map.Entry<String, Integer> entry1, Map.Entry<String, Integer> entry2) {
				int maxScore1 = entry1.getValue();
				int maxScore2 = entry2.getValue();
				return Integer.compare(maxScore2, maxScore1);
			}
		});

		Map<String,Integer> sortedScoremap=new HashMap<>();
		for (Map.Entry<String, Integer> entry : sortedEntries) {
			sortedScoremap.put(entry.getKey(), entry.getValue());
			//System.out.println(entry.getKey()+"さん:"+entry.getValue()+"点");
		}

		session.setAttribute("sortedEntries", sortedEntries);
		
	}

}
