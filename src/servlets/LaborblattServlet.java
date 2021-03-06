package servlets;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import exceptions.DataBasePathNotFoundException;
import exceptions.NoAccessToDataBaseException;
import logic.ErgebnisLogic;
import logic.LaborblattLogic;
import objects.Aufgabe;
import objects.LaborblattView;

@WebServlet("/LaborblattServlet")
@MultipartConfig
public class LaborblattServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	private static final String UPLOAD_DIRECTORY = "upload";
	private static final int THRESHOLD_SIZE = 1024 * 1024 * 3; // 3MB
	private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
	private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB

	private List<String> UsedUploadFields;
	private List<String> UsedInputFields;

	public LaborblattServlet()
	{
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		UsedUploadFields = new ArrayList<String>();
		UsedInputFields = new ArrayList<String>();
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
			} else
			{
				LaborblattLogic laborblattlogic = new LaborblattLogic();
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
						out.println("<p>Laborblatt " + laborblatt.LaborblattNr + "</p>");
						out.println("</div>");
						iterator++;
					}
					iterator = 1;

					for (LaborblattView laborblatt : laborblattViews)
					{

						out.println("<div id=\"Blatt" + iterator + "\" class = \"Laborblatt Blatt" + iterator + "\">");
						out.println("<div class=\"LaborblattHeader\"> <h3> Laborblatt: " + iterator + "</h3> </div>");
						out.println(
								"<form action=\"LaborblattServlet\" method=\"post\" enctype=\"multipart/form-data\">");//
						for (Aufgabe aufgabe : laborblatt.Aufgaben)
						{
							out.println("<div id=\"Aufgabe" + aufgabe.AufgabeNr + "\" class = \"Aufgabe\">");
							out.println("<h4> Aufgabe " + aufgabe.AufgabeNr + "</h4>");
							out.println("<p>" + aufgabe.AufgabeText + "</p>");
							if (aufgabe.EingabeArt.equals("ManualInput"))
							{
								out.println("Your Answer: <input type=\"text\" name=\"eingabe" + aufgabe.AufgabeNr
										+ "\"><br>");
								UsedInputFields.add(aufgabe.AufgabeNr);
								// String eingabe =
								// request.getParameter("eingabe" +
								// aufgabe.AufgabeNr);
								// List<String> messages =
								// ergebnislogic.HandleErgebnis(teamNr,
								// aufgabe.AufgabeNr,
								// rechnerNr, eingabe);
								// out.println("<div class = \"Message\">");
								// for (String message : messages)
								// {
								// out.println("<p>" + message + "</p>");
								// }
								// out.println("</div>");
							} else if (aufgabe.EingabeArt.equals("Upload"))
							{
								out.println("Select file to upload: <input type=\"file\" name=\"" + aufgabe.AufgabeNr
										+ "\" />");
								UsedUploadFields.add(aufgabe.AufgabeNr);
							}
							out.println("</div>");
						}
						out.println("<input type=\"submit\" value=\"Check Answers\">");
						out.println("</form>"); //
						out.println("</div>");
						iterator++;
					}
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
			out.print("You must be signed in to see this page");
		}
		out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HttpSession session = request.getSession();
		List<String> messages = new ArrayList<String>();
		ErgebnisLogic ergebnislogic = new ErgebnisLogic();
		if (session != null)
		{
			String rechnerNr = (String) session.getAttribute("rechnerNr");
			String teamNr = (String) session.getAttribute("teamNr");
			List<String> inputList = new ArrayList<String>();
			inputList.add("<h2>Text Input</h2>");

			for (String inputfield : UsedInputFields)
			{
				String eingabe = request.getParameter("eingabe" + inputfield);
				try
				{
					inputList.add("<h3>Aufgabe: " + inputfield + "</h3>");
					inputList.addAll(ergebnislogic.HandleErgebnis(teamNr, inputfield, rechnerNr, eingabe));
					for (String message : inputList)
					{
						System.out.println(message);
					}
				} catch (DataBasePathNotFoundException e)
				{
					e.printStackTrace();
				} catch (NoAccessToDataBaseException e)
				{
					e.printStackTrace();
				}
			}
			messages.addAll(inputList);
			// checks if the request actually contains upload file
			if (ServletFileUpload.isMultipartContent(request))
			{
				// configures upload settings
				DiskFileItemFactory factory = new DiskFileItemFactory();
				factory.setSizeThreshold(THRESHOLD_SIZE);
				factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

				ServletFileUpload upload = new ServletFileUpload(factory);
				upload.setFileSizeMax(MAX_FILE_SIZE);
				upload.setSizeMax(MAX_REQUEST_SIZE);

				// constructs the directory path to store upload file
				String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;
				// creates the directory if it does not exist
				File uploadDir = new File(uploadPath);
				if (!uploadDir.exists())
				{
					uploadDir.mkdir();
				}

				List<String> uploadList = new ArrayList<String>();
				uploadList.add("<h2>Uploads</h2>");
				for (String uploadField : UsedUploadFields)
				{
					try
					{
						Part filePart = request.getPart(uploadField); // Retrieves
																		// <input
						// type="file"
						// name="file">
						InputStream filecontent = filePart.getInputStream();
						System.out.println(uploadField);
						uploadList.add("<h3>Aufgabe: " + uploadField + "</h3>");
						uploadList.addAll(ergebnislogic.HandleErgebnis(teamNr, uploadField, rechnerNr, filecontent));
						for (String message : messages)
						{
							System.out.println(message);
						}

						request.setAttribute("message", "Upload has been done successfully!");
					} catch (Exception ex)
					{
						System.out.println("There was an error: " + ex.getMessage());
						request.setAttribute("message", "There was an error: " + ex.getMessage());
					}
				}
				messages.addAll(uploadList);
			}
		}
		String url = "/Feedback.jsp";
		ServletContext sc = getServletContext();
		RequestDispatcher rd = sc.getRequestDispatcher(url);
		String[] array = messages.toArray(new String[0]);
		request.setAttribute("messages", array);
		rd.forward(request, response);
		// doGet(request, response); //Anzeige f�r die Laborbl�tter
	}

}
