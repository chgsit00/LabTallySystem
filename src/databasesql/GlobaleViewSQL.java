package databasesql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import exceptions.NoAccessToDataBaseException;
import objects.GlobaleView;

public class GlobaleViewSQL
{
	private Connection Connection = null;
	public final String GETALLGLOBALEVIEW = "SELECT * FROM GlobaleView;";

	public GlobaleViewSQL(Connection connection)
	{
		Connection = connection;
	}

	public GlobaleView GetGlobaleView_by_TeamNr(String teamNr) throws NoAccessToDataBaseException
	{
		try
		{
			if (Connection != null)
			{
				Statement statement = Connection.createStatement();
				statement.setQueryTimeout(30);
				ResultSet result = statement.executeQuery("SELECT * FROM GlobaleView WHERE TeamNr = '" + teamNr + "';");
				GlobaleView globaleView = new GlobaleView();
				while (result.next())
				{
					// read the result set

					String TeamNr = result.getString("TeamNr");
					boolean Bestanden = result.getBoolean("Bestanden");
					String Zeitstempel = result.getString("Zeitstempel");
					String AufgabeNr = result.getString("AufgabeNr");
					String AufgabeText = result.getString("AufgabeText");
					globaleView.setValues(TeamNr, Bestanden, Zeitstempel, AufgabeNr, AufgabeText);
				}
				return globaleView;
			} else
				throw new NoAccessToDataBaseException();

		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new NoAccessToDataBaseException();
		}
	}

	public List<GlobaleView> ReadFrom_GlobaleView() throws NoAccessToDataBaseException, SQLException
	{
		Statement statement;
		try
		{
			if (Connection != null)
			{
				statement = Connection.createStatement();
				ResultSet result = statement.executeQuery(GETALLGLOBALEVIEW);
				List<GlobaleView> globaleViews = new ArrayList<GlobaleView>();
				while (result.next())
				{
					String teamNr = result.getString("TeamNr");
					boolean bestanden = result.getBoolean("Bestanden");
					String zeitstempel = result.getString("Zeitstempel");
					String aufgabeNr = result.getString("AufgabeNr");
					String aufgabeText = result.getString("AufgabeText");
					GlobaleView globaleView = new GlobaleView(teamNr, bestanden, zeitstempel, aufgabeNr, aufgabeText);
					globaleViews.add(globaleView);
				}
				return globaleViews;
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
