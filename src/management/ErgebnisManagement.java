package management;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import database.DataBaseConnector;
import database.DataBasePropertyInitializer;
import databasesql.AufgabeSQL;
import databasesql.ErgebnisSQL;
import databasesql.TeamSQL;
import exceptions.DataBasePathNotFoundException;
import exceptions.NoAccessToDataBaseException;
import objects.Aufgabe;
import objects.Ergebnis;
import objects.Team;

public class ErgebnisManagement
{
	private Connection Connection = null;

	public Ergebnis GetErgebnis_by_AufgabeNr_and_TeamNr(String aufgabeNr, String teamNr)
			throws DataBasePathNotFoundException, NoAccessToDataBaseException
	{
		DataBasePropertyInitializer initializer = new DataBasePropertyInitializer();
		String databasePath = initializer.GetDataBasePath();
		DataBaseConnector connector = new DataBaseConnector(databasePath);
		Connection = connector.ConnectToDataBase();
		ErgebnisSQL operation = new ErgebnisSQL(Connection);
		Ergebnis ergebnis = operation.GetErgebnis_by_AufgabeNr_and_TeamNr(aufgabeNr, teamNr);
		return ergebnis;
	}

	public String SaveErgebnis(String teamNr, String aufgabeNr, String rechnerNr, String eingabe, boolean bestanden,
			String zeitstempel)
	{

		try
		{
			String message = "Failure - Save-Operation did not work correctly";
			DataBasePropertyInitializer initializer = new DataBasePropertyInitializer();
			String databasePath = initializer.GetDataBasePath();
			DataBaseConnector connector = new DataBaseConnector(databasePath);
			Connection = connector.ConnectToDataBase();
			AufgabeSQL operation = new AufgabeSQL(Connection);
			TeamSQL operation2 = new TeamSQL(Connection);
			ErgebnisSQL operation3 = new ErgebnisSQL(Connection);
			Aufgabe aufgabe = operation.GetAufgabe_by_AufgabeNr(aufgabeNr);
			Team team = operation2.GetTeam_by_TeamNr(teamNr);

			if (aufgabe.AufgabeNr == null)
			{
				message = "Failure - Aufgabe with AufgabeNr = " + aufgabeNr + " does not exist";
			} else if (team.TeamNr == null)
			{
				message = "Failure - Team with TeamNr = " + teamNr + " does not exist";
			} else
			{
				Ergebnis ergebnis = operation3.GetErgebnis_by_AufgabeNr_and_TeamNr(aufgabeNr, teamNr);
				if (ergebnis.AufgabeNr == null && ergebnis.TeamNr == null)
				{
					operation3.InsertInto_Ergebnis(teamNr, aufgabeNr, rechnerNr, eingabe, bestanden, zeitstempel);
					message = "Insert - Success";
				} else
				{
					operation3.Update_Ergebnis(teamNr, aufgabeNr, rechnerNr, eingabe, bestanden, zeitstempel);
					message = "Update - Success";
				}
			}
			System.out.println(message);
			return message;
		} catch (DataBasePathNotFoundException ex)
		{
			return ex.getMessage();
		} catch (NoAccessToDataBaseException ac)
		{
			return ac.getMessage();
		} finally
		{
			try
			{
				if (Connection != null)
					Connection.close();
			} catch (SQLException e)
			{
				// connection close failed.
				System.err.println(e);
			}
		}
	}

	public List<Ergebnis> ReadAllErgebnisse()
			throws DataBasePathNotFoundException, NoAccessToDataBaseException, SQLException
	{
		try
		{
			DataBasePropertyInitializer initializer = new DataBasePropertyInitializer();
			String databasePath = initializer.GetDataBasePath();
			DataBaseConnector connector = new DataBaseConnector(databasePath);
			Connection = connector.ConnectToDataBase();
			ErgebnisSQL operation = new ErgebnisSQL(Connection);
			return operation.ReadFrom_Ergebnis();
		} catch (DataBasePathNotFoundException ex)
		{
			throw ex;
		} catch (NoAccessToDataBaseException ac)
		{
			throw ac;
		} catch (SQLException s)
		{
			throw s;
		} finally
		{
			try
			{
				if (Connection != null)
					Connection.close();
			} catch (SQLException e)
			{
				// connection close failed.
				System.err.println(e);
			}
		}
	}

	public String DeleteErgebnis(String teamNr, String aufgabeNr)
	{
		try
		{
			DataBasePropertyInitializer initializer = new DataBasePropertyInitializer();
			String databasePath = initializer.GetDataBasePath();
			DataBaseConnector connector = new DataBaseConnector(databasePath);
			Connection = connector.ConnectToDataBase();
			ErgebnisSQL operation = new ErgebnisSQL(Connection);
			operation.DeleteFrom_Ergebnis(teamNr, aufgabeNr);
			return "Success";
		} catch (DataBasePathNotFoundException ex)
		{
			return ex.getMessage();
		} catch (NoAccessToDataBaseException ac)
		{
			return ac.getMessage();
		} finally
		{
			try
			{
				if (Connection != null)
					Connection.close();
			} catch (SQLException e)
			{
				// connection close failed.
				System.err.println(e);
			}
		}
	}
}
