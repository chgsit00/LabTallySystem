package databasesql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import exceptions.NoAccessToDataBaseException;
import objects.Rechner;

public class RechnerSQL
{
	private Connection Connection = null;
	public final String GETALLRECHNER = "SELECT * FROM RECHNER;";

	public RechnerSQL(Connection connection)
	{
		Connection = connection;
	}

	public Rechner GetRechner_by_RechnerNr(String rechnerNr) throws NoAccessToDataBaseException
	{
		try
		{
			if (Connection != null)
			{
				Statement statement = Connection.createStatement();
				statement.setQueryTimeout(30);
				ResultSet result = statement
						.executeQuery("SELECT * FROM Rechner WHERE RechnerNr = '" + rechnerNr + "';");
				Rechner rechner = new Rechner();
				while (result.next())
				{
					String RechnerNr = result.getString("RechnerNr");
					rechner.setValues(RechnerNr);
				}
				return rechner;
			} else
				throw new NoAccessToDataBaseException();

		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new NoAccessToDataBaseException();
		}
	}

	public void InsertInto_Rechner(String rechnerNr) throws NoAccessToDataBaseException
	{
		try
		{
			if (Connection != null)
			{
				Statement statement = Connection.createStatement();
				statement.setQueryTimeout(30);
				statement.executeUpdate("INSERT INTO Rechner (RechnerNr) VALUES('" + rechnerNr + "');");
			} else
				throw new NoAccessToDataBaseException();

		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<Rechner> ReadFrom_Rechner() throws NoAccessToDataBaseException, SQLException
	{
		Statement statement;
		if (Connection != null)
		{
			statement = Connection.createStatement();
			ResultSet result = statement.executeQuery(GETALLRECHNER);
			List<Rechner> rechner = new ArrayList<Rechner>();
			while (result.next())
			{
				String rechnerNr = result.getString("RechnerNr");
				Rechner Rechner = new Rechner(rechnerNr);
				rechner.add(Rechner);
			}
			return rechner;
		} else
			throw new NoAccessToDataBaseException();

	}

	public void DeleteFrom_Rechner(String rechnerNr) throws NoAccessToDataBaseException
	{
		try
		{
			if (Connection != null)
			{
				Statement statement = Connection.createStatement();
				statement.setQueryTimeout(30);
				statement.executeUpdate("DELETE FROM RECHNER WHERE RechnerNr = '" + rechnerNr + "';");
			} else
				throw new NoAccessToDataBaseException();

		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
