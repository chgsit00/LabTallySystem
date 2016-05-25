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
import logic.LaborgruppenViewLogic;
import management.LaborslotManagement;
import objects.AufgabenDisplayLaborgruppen;
import objects.LaborgruppenViewBestandeneAufgaben;
import objects.Laborslot;

/**
 * Servlet implementation class LaborgruppenViewServlet
 */
@WebServlet("/LaborgruppenViewServlet")
public class LaborgruppenViewServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LaborgruppenViewServlet()
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

		HttpSession session = request.getSession();
		if (session != null)
		{
			String slot = request.getParameter("slot");
			if (slot != null)
			{
				LaborslotManagement laborslotManagement = new LaborslotManagement();
				Laborslot laborslot = null;
				try
				{
					laborslot = laborslotManagement.GetLaborslot_by_Slot(slot);
				} catch (DataBasePathNotFoundException e1)
				{
					out.print(e1.GetMessage());
				} catch (NoAccessToDataBaseException e1)
				{
					out.print(e1.GetMessage());
				}
				if (laborslot.SlotNr != null)
				{
					String rechnerNr = (String) session.getAttribute("rechnerNr");
					String teamNr = (String) session.getAttribute("teamNr");
					if (rechnerNr == null || teamNr == null)
					{
						out.print("You must be signed in to see this page");
					} else
					{
						LaborgruppenViewLogic logic = new LaborgruppenViewLogic();
						List<LaborgruppenViewBestandeneAufgaben> list;
						try
						{
							list = logic.GetLaborgruppenViewSorted(laborslot.SlotNr);
							out.println("<html>");
							out.println("<head>");
							out.println("<meta charset=\"utf-8\">");
							out.println("<title>Laborgruppen Overview</title>");
							out.println(
									"<link rel=\"stylesheet\"href=\"//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css\">");
							out.println("<script src=\"//code.jquery.com/jquery-1.10.2.js\"></script>");
							out.println("<script src=\"//code.jquery.com/ui/1.11.4/jquery-ui.js\"></script>");
							out.println("<link rel=\"stylesheet\" href=\"/resources/demos/style.css\">");
							out.println("<script>");
							out.println("$(function() { $(\"#accordion\").accordion(); }); </script>");
							out.println("</head>");
							out.println("<body>");
							out.println("<div id=\"accordion\">");
							int iterator = 1;
							for (LaborgruppenViewBestandeneAufgaben team : list)
							{
								out.println("<h3> Platz: " + iterator + "/" + list.size() + " Team: " + team.TeamNr
										+ "<span id='aufgabenGeloest'>" + "Aufgaben gelöst: "
										+ team.BestandeneAufgabeCount + "/" + team.Aufgaben.size() + "</span>"
										+ "</h3>");
								out.println("<div id='container'>");
								for (AufgabenDisplayLaborgruppen aufgabe : team.Aufgaben)
								{
									if (aufgabe.Bestanden)
									{
										out.println("<p>" + "<div id='links'>" + aufgabe.AufgabeNr + " "
												+ aufgabe.AufgabeText + "</div>" + "<div id='rechts'>"
												+ "<img class=\"userimage\" src='" + request.getContextPath()
												+ "/images/green-img.jpg' width='16dpi' alt='image' />" + "</div>"
												+ "</p>");
									} else
									{
										out.println("<p>" + "<div id='links'>" + aufgabe.AufgabeNr + " "
												+ aufgabe.AufgabeText + "</div>" + "<div id='rechts'>"
												+ "<img class=\"userimage\" src='" + request.getContextPath()
												+ "/images/red-img.jpg' width='16dpi' alt='image' />" + "</div>"
												+ "</p>");
									}
								}
								out.println("</div>");
								iterator++;
							}
							out.println("</div>");
							out.println("</body>");
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
					out.print("This Laborslot does not exist");
				}
			}
		} else
		{
			out.print("You must be signed in to see this page");
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