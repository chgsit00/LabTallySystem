package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.TeamOverviewLogic;
import objects.TeamOverview;

/**
 * Servlet implementation class servlet1
 */
@WebServlet("/servlet1")
public class servlet1 extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public servlet1()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		TeamOverviewLogic logic = new TeamOverviewLogic();
		List<TeamOverview> list = logic.GetTeamOverview();
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset=\"utf-8\">");
		out.println("<title>Team Overview</title>");
		out.println("<link rel=\"stylesheet\"href=\"//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css\">");
		out.println("<script src=\"//code.jquery.com/jquery-1.10.2.js\"></script>");
		out.println("<script src=\"//code.jquery.com/ui/1.11.4/jquery-ui.js\"></script>");
		out.println("<link rel=\"stylesheet\" href=\"/resources/demos/style.css\">");
		out.println("<script>");
		out.println("$(function() { $(\"#accordion\").accordion(); }); </script>");
		out.println("</head>");
		out.println("<body>");
		out.println("<div id=\"accordion\">");
		int iterator = 1;
		for (TeamOverview team : list)
		{
			out.println("<h3> Platz: " + iterator + "/" + list.size() + "            Team: " + team.TeamNr
					+ "            Aufgaben gelöst: " + team.BestandeneAufgabeCount + "/" + team.Aufgaben.size()
					+ "</h3>");
			out.println("<div>");
			for (String aufgabe : team.Aufgaben)
			{
				out.println("<p>" + aufgabe + "</p>");
			}
			out.println("</div>");
			iterator++;
		}
		out.println("</div>");
		out.println("</body>");
		out.println("<a href='/LabTallySystem/'>zur&uuml;ck</a>");
		out.println("</html>");
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
