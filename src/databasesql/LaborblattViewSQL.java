package databasesql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import exceptions.NoAccessToDataBaseException;
import objects.LaborblattViewOutput;

public class LaborblattViewSQL
{
	private Connection Connection = null;
	public final String GETALLLABORBLATTVIEW = "SELECT * FROM LaborblattView;";

	public LaborblattViewSQL(Connection connection)
	{
		Connection = connection;
	}

	public LaborblattViewOutput GetLaborblattViewOutput_by_TeamNr(String teamNr) throws NoAccessToDataBaseException
	{
		try
		{
			if (Connection != null)
			{
				Statement statement = Connection.createStatement();
				statement.setQueryTimeout(30);
				ResultSet result = statement
						.executeQuery("SELECT * FROM LaborblattView WHERE TeamNr = '" + teamNr + "';");
				LaborblattViewOutput laborblattView = new LaborblattViewOutput();
				while (result.next())
				{
					// read the result set

					String TeamNr = result.getString("TeamNr");
					boolean Bestanden = result.getBoolean("Bestanden");
					String Zeitstempel = result.getString("Zeitstempel");
					String AufgabeNr = result.getString("AufgabeNr");
					String AufgabeText = result.getString("AufgabeText");
					String LaborblattNr = result.getString("LaborblattNr");
					laborblattView.setValues(TeamNr, Bestanden, Zeitstempel, AufgabeNr, AufgabeText, LaborblattNr);
				}
				return laborblattView;
			} else
				throw new NoAccessToDataBaseException();

		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new NoAccessToDataBaseException();
		}
	}

	public List<LaborblattViewOutput> ReadFrom_LaborblattViewOutput() throws NoAccessToDataBaseException, SQLException
	{
		Statement statement;
		try
		{
			if (Connection != null)
			{
				statement = Connection.createStatement();
				ResultSet result = statement.executeQuery(GETALLLABORBLATTVIEW);
				List<LaborblattViewOutput> laborblattViews = new ArrayList<LaborblattViewOutput>();
				while (result.next())
				{
					String teamNr = result.getString("TeamNr");
					boolean bestanden = result.getBoolean("Bestanden");
					String zeitstempel = result.getString("Zeitstempel");
					String aufgabeNr = result.getString("AufgabeNr");
					String aufgabeText = result.getString("AufgabeText");
					String laborblattNr = result.getString("LaborblattNr");
					LaborblattViewOutput laborblattView = new LaborblattViewOutput(teamNr, bestanden, zeitstempel,
							aufgabeNr, aufgabeText, laborblattNr);
					laborblattViews.add(laborblattView);
				}
				return laborblattViews;
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
