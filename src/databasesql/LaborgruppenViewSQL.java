package databasesql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import exceptions.NoAccessToDataBaseException;
import objects.LaborgruppenView;

public class LaborgruppenViewSQL
{
	private Connection Connection = null;
	public final String GETALLLABORGRUPPENVIEW = "SELECT * FROM LaborgruppenView;";

	public LaborgruppenViewSQL(Connection connection)
	{
		Connection = connection;
	}

	public LaborgruppenView GetLaborgruppenView_by_TeamNr(String teamNr) throws NoAccessToDataBaseException
	{
		try
		{
			if (Connection != null)
			{
				Statement statement = Connection.createStatement();
				statement.setQueryTimeout(30);
				ResultSet result = statement
						.executeQuery("SELECT * FROM LaborgruppenView WHERE TeamNr = '" + teamNr + "';");
				LaborgruppenView laborgruppenView = new LaborgruppenView();
				while (result.next())
				{
					// read the result set

					String TeamNr = result.getString("TeamNr");
					boolean Bestanden = result.getBoolean("Bestanden");
					String Zeitstempel = result.getString("Zeitstempel");
					String AufgabeNr = result.getString("AufgabeNr");
					String AufgabeText = result.getString("AufgabeText");
					String Slot = result.getString("Slot");
					laborgruppenView.setValues(TeamNr, Bestanden, Zeitstempel, AufgabeNr, AufgabeText, Slot);
				}
				return laborgruppenView;
			} else
				throw new NoAccessToDataBaseException();

		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new NoAccessToDataBaseException();
		}
	}

	public List<LaborgruppenView> ReadFrom_LaborgruppenView() throws NoAccessToDataBaseException, SQLException
	{
		Statement statement;
		try
		{
			if (Connection != null)
			{
				statement = Connection.createStatement();
				ResultSet result = statement.executeQuery(GETALLLABORGRUPPENVIEW);
				List<LaborgruppenView> laborgruppenViews = new ArrayList<LaborgruppenView>();
				while (result.next())
				{
					String teamNr = result.getString("TeamNr");
					boolean bestanden = result.getBoolean("Bestanden");
					String zeitstempel = result.getString("Zeitstempel");
					String aufgabeNr = result.getString("AufgabeNr");
					String aufgabeText = result.getString("AufgabeText");
					String slot = result.getString("Slot");
					LaborgruppenView laborgruppenView = new LaborgruppenView(teamNr, bestanden, zeitstempel, aufgabeNr,
							aufgabeText, slot);
					laborgruppenViews.add(laborgruppenView);
				}
				return laborgruppenViews;
			} else
				throw new NoAccessToDataBaseException();
		} catch (SQLException s)
		{
			// TODO Auto-generated catch block
			System.out.println(s.getMessage());
			throw s;
		}
	}
}
