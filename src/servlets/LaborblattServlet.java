package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import exceptions.DataBasePathNotFoundException;
import exceptions.NoAccessToDataBaseException;
import logic.ErgebnisLogic;
import logic.LaborblattLogic;
import objects.Aufgabe;
import objects.LaborblattView;

/**
 * Servlet implementation class LaborblattServlet
 */
@WebServlet("/LaborblattServlet")
public class LaborblattServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LaborblattServlet()
	{
		super();
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

		HttpSession session = request.getSession();
		if (session != null)
		{
			String rechnerNr = (String) session.getAttribute("rechnerNr");
			String teamNr = (String) session.getAttribute("teamNr");
			if (rechnerNr == null || teamNr == null)
			{
				out.print("<p>You must be signed in to see this page</p>");
				out.println("<p><a href='/LabTallySystem/'>zur&uuml;ck</a></p>");
			} else
			{
				LaborblattLogic laborblattlogic = new LaborblattLogic();
				ErgebnisLogic ergebnislogic = new ErgebnisLogic();
				try
				{
					List<LaborblattView> laborblattViews = laborblattlogic.GetLaborblattViews();
					out.println("<html>");
					out.println("<head>");
					out.println("<meta charset=\"utf-8\">");
					out.println("<title>Laborblatt</title>");
					out.println(
							"<link rel=\"stylesheet\"href=\"//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css\">");

					out.println("<link rel=\"stylesheet\" href=\"/LabTallySystem/LabTallySystemStyle.css\" />");
					out.println("<script src=\"//code.jquery.com/jquery-1.10.2.js\"></script>");
					out.println("<script src=\"/LabTallySystem/LabTallySystemScript.js\"></script>");
					out.println("<script src=\"//code.jquery.com/ui/1.11.4/jquery-ui.js\"></script>");
					out.println("<link rel=\"stylesheet\" href=\"/resources/demos/style.css\">");
					out.println("<script>");
					out.println("</script>");
					out.println("</head>");
					out.println("<body>");
					int iterator = 1;
					for (LaborblattView laborblatt : laborblattViews)
					{
						out.println("<div class = \"LaborblattNav Blatt" + iterator + "\">");
						out.println("<p>Laborblatt " + iterator + "</p>");
						out.println("</div>");
						iterator++;
					}
					iterator = 1;

					for (LaborblattView laborblatt : laborblattViews)
					{

						out.println("<div id=\"Blatt" + iterator + "\" class = \"Laborblatt Blatt" + iterator + "\">");
						out.println("<h3> Laborblatt: " + iterator + "</h3>");
						for (Aufgabe aufgabe : laborblatt.Aufgaben)
						{
							out.println("<div id=\"Aufgabe" + aufgabe.AufgabeNr + "\" class = \"Aufgabe\">");
							out.println("<h4> Aufgabe " + aufgabe.AufgabeNr + "</h4>");
							out.println("<p>" + aufgabe.AufgabeText + "</p>");
							out.println("<form action=\"LaborblattServlet\" method=\"post\">");
							out.println(
									"Your Answer: <input type=\"text\" name=\"eingabe" + aufgabe.AufgabeNr + "\"><br>");
							out.println("<input type=\"submit\" value=\"Check Answer\">");
							out.println("</form>");
							String eingabe = request.getParameter("eingabe" + aufgabe.AufgabeNr);
							List<String> messages = ergebnislogic.HandleErgebnis(teamNr, aufgabe.AufgabeNr, rechnerNr,
									eingabe);
							out.println("<div class = \"Message\">");
							for (String message : messages)
							{
								out.println("<p>" + message + "</p>");
							}
							out.println("</div>");
							out.println("</div>");
						}
						out.println("</div>");
						iterator++;
					}
					out.println("</body>");
					out.println("<a href='/LabTallySystem/'>zur&uuml;ck</a>");
					out.println("</html>");
				} catch (DataBasePathNotFoundException e)
				{
					out.println(e.GetMessage());
				} catch (NoAccessToDataBaseException e)
				{
					out.println(e.GetMessage());
				} catch (SQLException e)
				{
					out.println(e.getMessage());
				}
			}
		} else
		{
			out.print("You must be signed in to see this page");
			out.println("<a href='/LabTallySystem/login.html'>zur&uuml;ck</a>");
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
