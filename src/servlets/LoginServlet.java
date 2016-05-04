package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import exceptions.DataBasePathNotFoundException;
import exceptions.NoAccessToDataBaseException;
import management.RechnerManagement;
import management.TeamManagement;
import objects.Rechner;
import objects.Team;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet()
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
		request.getRequestDispatcher("link.html").include(request, response);

		String teamNr = request.getParameter("team");
		String rechnerNr = request.getParameter("rechnernr");
		String password = request.getParameter("password");
		RechnerManagement rechnerManagement = new RechnerManagement();
		TeamManagement teamManagement = new TeamManagement();
		try
		{
			Rechner rechner = rechnerManagement.GetRechner_by_RechnerNr(rechnerNr);
			Team team = teamManagement.GetTeam_by_TeamNr(teamNr);
			String errorMessage = "Login failed. Messages:  ";
			boolean loginsuccess = true;
			if (team.TeamNr == null)
			{
				loginsuccess = false;
				errorMessage += "\n - Team with TeamNr: " + teamNr + " does not exist";
			}
			if (rechner.RechnerNr == null)
			{
				loginsuccess = false;
				errorMessage += "\n - Rechner with RechnerNr: " + rechnerNr + " does not exist";
			}
			if (!password.equals(team.Passwort))
			{
				loginsuccess = false;
				errorMessage += "\n - Wrong password";
			}
			if (loginsuccess == true)
			{
				out.print("Welcome, " + teamNr);
				HttpSession session = request.getSession();
				session.setAttribute("teamNr", teamNr);
				session.setAttribute("rechnerNr", rechnerNr);
			} else
			{
				out.print(errorMessage);
				request.getRequestDispatcher("login.html").include(request, response);
			}
		} catch (DataBasePathNotFoundException e)
		{
			e.printStackTrace();
			out.print(e.GetMessage());
		} catch (NoAccessToDataBaseException e)
		{
			e.printStackTrace();
			out.print(e.GetMessage());
		}
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
