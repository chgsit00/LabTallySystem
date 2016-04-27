package databasesql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import exceptions.NoAccessToDataBaseException;
import objects.Ergebnis;

public class ErgebnisSQL
{
	private Connection Connection = null;
	public final String GETALLERGEBNISSE = "SELECT * FROM ERGEBNIS;";

	public ErgebnisSQL(Connection connection)
	{
		Connection = connection;
	}

	public Ergebnis GetErgebnis_by_AufgabeNr_and_TeamNr(String aufgabeNr, String teamNr)
			throws NoAccessToDataBaseException
	{
		try
		{
			if (Connection != null)
			{
				Statement statement = Connection.createStatement();
				statement.setQueryTimeout(30);
				ResultSet result = statement.executeQuery(
						"SELECT * FROM ERGEBNIS WHERE AufgabeNr = '" + aufgabeNr + "' AND TeamNr = '" + teamNr + "';");
				Ergebnis ergebnis = new Ergebnis();
				while (result.next())
				{
					// read the result set

					String AufgabeNr = result.getString("AufgabeNr");
					String TeamNr = result.getString("TeamNr");
					String RechnerNr = result.getString("Rechner");
					String Eingabe = result.getString("Eingabe");
					boolean Bestanden = result.getBoolean("Bestanden");
					String Zeitstempel = result.getString("Zeitstempel");
					ergebnis.setValues(TeamNr, AufgabeNr, RechnerNr, Eingabe, Bestanden, Zeitstempel);
				}
				return ergebnis;
			} else
				throw new NoAccessToDataBaseException();

		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new NoAccessToDataBaseException();
		}
	}

	public void InsertInto_Ergebnis(String teamNr, String aufgabeNr, String rechnerNr, String eingabe,
			boolean bestanden, String zeitstempel) throws NoAccessToDataBaseException
	{
		int bestanden_int = 0;
		if (bestanden == true)
		{
			bestanden_int = 1;
		}
		try
		{
			if (Connection != null)
			{
				Statement statement = Connection.createStatement();
				statement.setQueryTimeout(30);
				statement.executeUpdate(
						"INSERT INTO ERGEBNIS (TeamNr, AufgabeNr, Rechner, Eingabe, Bestanden, Zeitstempel) VALUES('"
								+ teamNr + "', '" + aufgabeNr + "', '" + rechnerNr + "', '" + eingabe + "', "
								+ bestanden_int + ", '" + zeitstempel + "');");
			} else
				throw new NoAccessToDataBaseException();

		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void Update_Ergebnis(String teamNr, String aufgabeNr, String rechnerNr, String eingabe, boolean bestanden,
			String zeitstempel) throws NoAccessToDataBaseException
	{
		int bestanden_int = 0;
		if (bestanden == true)
		{
			bestanden_int = 1;
		}
		try
		{
			if (Connection != null)
			{
				Statement statement = Connection.createStatement();
				statement.setQueryTimeout(30);
				statement.executeUpdate("UPDATE ERGEBNIS SET TeamNr = '" + teamNr + "', AufgabeNr = '" + aufgabeNr
						+ "',  Rechner = '" + rechnerNr + "', Eingabe = '" + eingabe + "', Bestanden = " + bestanden_int
						+ ", Zeitstempel = '" + zeitstempel + "' WHERE AufgabeNr = '" + aufgabeNr + "' AND TeamNr = '"
						+ teamNr + "';");
			} else
				throw new NoAccessToDataBaseException();

		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<Ergebnis> ReadFrom_Ergebnis() throws NoAccessToDataBaseException, SQLException
	{
		Statement statement;
		try
		{
			if (Connection != null)
			{
				statement = Connection.createStatement();
				ResultSet result = statement.executeQuery(GETALLERGEBNISSE);
				List<Ergebnis> ergebnisse = new ArrayList<Ergebnis>();
				while (result.next())
				{
					String aufgabenNr = result.getString("AufgabeNr");
					String teamNr = result.getString("TeamNr");
					String rechnerNr = result.getString("Rechner");
					String eingabe = result.getString("Eingabe");
					boolean bestanden = result.getBoolean("Bestanden");
					String zeitstempel = result.getString("Zeitstempel");
					Ergebnis ergebnis = new Ergebnis(aufgabenNr, teamNr, rechnerNr, eingabe, bestanden, zeitstempel);
					ergebnisse.add(ergebnis);
				}
				return ergebnisse;
			} else
				throw new NoAccessToDataBaseException();
		} catch (SQLException s)
		{
			// TODO Auto-generated catch block
			System.out.println(s.getMessage());
			throw s;
		}
	}

	public void DeleteFrom_Ergebnis(String teamNr, String aufgabeNr) throws NoAccessToDataBaseException
	{
		try
		{
			if (Connection != null)
			{
				Statement statement = Connection.createStatement();
				statement.setQueryTimeout(30);
				statement.executeUpdate(
						"DELETE FROM ERGEBNIS WHERE AufgabeNr = '" + aufgabeNr + "' AND TeamNr = '" + teamNr + "';");
			} else
				throw new NoAccessToDataBaseException();

		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
