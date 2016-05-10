package databasesql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import exceptions.NoAccessToDataBaseException;
import objects.Aufgabe;

public class AufgabeSQL
{
	private Connection Connection = null;
	public final String GETALLAUFGABEN = "SELECT * FROM AUFGABE;";

	public AufgabeSQL(Connection connection)
	{
		Connection = connection;
	}

	public Aufgabe GetAufgabe_by_AufgabeNr(String aufgabeNr) throws NoAccessToDataBaseException
	{
		try
		{
			if (Connection != null)
			{
				Statement statement = Connection.createStatement();
				statement.setQueryTimeout(30);
				ResultSet result = statement
						.executeQuery("SELECT * FROM AUFGABE WHERE AufgabeNr = '" + aufgabeNr + "';");
				Aufgabe aufgabe = new Aufgabe();
				while (result.next())
				{
					// read the result set

					String AufgabeNr = result.getString("AufgabeNr");
					String LaborblattNr = result.getString("LaborblattNr");
					String AufgabeText = result.getString("AufgabeText");
					String EingabeArt = result.getString("EingabeArt");
					aufgabe.setValues(AufgabeNr, LaborblattNr, AufgabeText, EingabeArt);
				}
				return aufgabe;
			} else
				throw new NoAccessToDataBaseException();

		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new NoAccessToDataBaseException();
		}
	}

	public void InsertInto_Aufgabe(String aufgabeNr, String laborblattNr, String aufgabeText, String eingabeArt)
			throws NoAccessToDataBaseException
	{
		try
		{
			if (Connection != null)
			{
				Statement statement = Connection.createStatement();
				statement.setQueryTimeout(30);
				statement.executeUpdate(
						"INSERT INTO AUFGABE (AufgabeNr, LaborblattNr, AufgabeText, EingabeArt) VALUES('" + aufgabeNr
								+ "', '" + laborblattNr + "', '" + aufgabeText + "', '" + eingabeArt + "');");
			} else
				throw new NoAccessToDataBaseException();

		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void InsertInto_Aufgabe(String aufgabeNr, String aufgabeText) throws NoAccessToDataBaseException
	{
		try
		{
			if (Connection != null)
			{
				Statement statement = Connection.createStatement();
				statement.setQueryTimeout(30);
				statement.executeUpdate("INSERT INTO AUFGABE (AufgabeNr, AufgabeText) VALUES('" + aufgabeNr + "', '"
						+ aufgabeText + "');");
			} else
				throw new NoAccessToDataBaseException();

		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void Update_Aufgabe(String aufgabeNr, String laborblattNr, String aufgabeText, String eingabeArt)
			throws NoAccessToDataBaseException
	{
		try
		{
			if (Connection != null)
			{
				Statement statement = Connection.createStatement();
				statement.setQueryTimeout(30);
				statement.executeUpdate("UPDATE AUFGABE SET AufgabeNr = '" + aufgabeNr + "',  LaborBlattNr = '"
						+ laborblattNr + "', AufgabeText = '" + aufgabeText + "', EingabeArt = '" + eingabeArt
						+ "' WHERE AufgabeNr = '" + aufgabeNr + "';");
			} else
				throw new NoAccessToDataBaseException();

		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void Update_Aufgabe(String aufgabeNr, String aufgabeText) throws NoAccessToDataBaseException
	{
		try
		{
			if (Connection != null)
			{
				Statement statement = Connection.createStatement();
				statement.setQueryTimeout(30);
				statement.executeUpdate("UPDATE AUFGABE SET AufgabeNr = '" + aufgabeNr + "', AufgabeText = '"
						+ aufgabeText + "' WHERE AufgabeNr = '" + aufgabeNr + "';");
			} else
				throw new NoAccessToDataBaseException();

		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<Aufgabe> ReadFrom_Aufgabe() throws NoAccessToDataBaseException, SQLException
	{
		Statement statement;
		try
		{
			if (Connection != null)
			{
				statement = Connection.createStatement();
				ResultSet result = statement.executeQuery(GETALLAUFGABEN);
				List<Aufgabe> aufgaben = new ArrayList<Aufgabe>();
				while (result.next())
				{
					String aufgabeNr = result.getString("AufgabeNr");
					String laborblattNr = result.getString("LaborblattNr");
					String aufgabeText = result.getString("AufgabeText");
					String eingabeArt = result.getString("EingabeArt");
					Aufgabe aufgabe = new Aufgabe(aufgabeNr, laborblattNr, aufgabeText, eingabeArt);
					aufgaben.add(aufgabe);
				}
				return aufgaben;
			} else
				throw new NoAccessToDataBaseException();
		} catch (SQLException s)
		{
			// TODO Auto-generated catch block
			System.out.println(s.getMessage());
			throw s;
		}
	}

	public void DeleteFrom_Aufgabe(String aufgabeNr) throws NoAccessToDataBaseException
	{
		try
		{
			if (Connection != null)
			{
				Statement statement = Connection.createStatement();
				statement.setQueryTimeout(30);
				statement.executeUpdate("DELETE FROM AUFGABE WHERE AufgabeNr = '" + aufgabeNr + "';");
			} else
				throw new NoAccessToDataBaseException();

		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
