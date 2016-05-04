package management;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import database.DataBaseConnector;
import database.DataBasePropertyInitializer;
import databasesql.AufgabeSQL;
import databasesql.LoesungSQL;
import databasesql.RechnerSQL;
import exceptions.DataBasePathNotFoundException;
import exceptions.NoAccessToDataBaseException;
import objects.Aufgabe;
import objects.Loesung;
import objects.Rechner;

public class LoesungManagement
{
	private Connection Connection = null;

	public Loesung GetLoesung_by_RechnerNr_and_AufgabeNr(String aufgabeNr, String rechnerNr)
			throws DataBasePathNotFoundException, NoAccessToDataBaseException
	{
		DataBasePropertyInitializer initializer = new DataBasePropertyInitializer();
		String databasePath = initializer.GetDataBasePath();
		DataBaseConnector connector = new DataBaseConnector(databasePath);
		Connection = connector.ConnectToDataBase();
		LoesungSQL operation = new LoesungSQL(Connection);
		Loesung loesung = operation.GetLoesung_by_AufgabeNr_and_RechnerNr(aufgabeNr, rechnerNr);
		return loesung;
	}

	public String SaveLoesung(String aufgabeNr, String rechnerNr, String loesung)
	{

		try
		{
			String message = "Failure - Save-Operation did not work correctly";
			DataBasePropertyInitializer initializer = new DataBasePropertyInitializer();
			String databasePath = initializer.GetDataBasePath();
			DataBaseConnector connector = new DataBaseConnector(databasePath);
			Connection = connector.ConnectToDataBase();
			AufgabeSQL operation = new AufgabeSQL(Connection);
			RechnerSQL operation2 = new RechnerSQL(Connection);
			LoesungSQL operation3 = new LoesungSQL(Connection);
			Aufgabe aufgabe = operation.GetAufgabe_by_AufgabeNr(aufgabeNr);
			Rechner rechner = operation2.GetRechner_by_RechnerNr(rechnerNr);

			if (aufgabe.AufgabeNr == null)
			{
				message = "Failure - Aufgabe with AufgabeNr = " + aufgabeNr + " does not exist";
			} else if (rechner.RechnerNr == null)
			{
				message = "Failure - Rechner with RechnerNr = " + rechnerNr + " does not exist";
			} else
			{
				Loesung Loesung = operation3.GetLoesung_by_AufgabeNr_and_RechnerNr(aufgabeNr, rechnerNr);
				if (Loesung.AufgabeNr == null && Loesung.RechnerNr == null)
				{
					operation3.InsertInto_Loesung(aufgabeNr, rechnerNr, loesung);
					message = "Insert - Success";
				} else
				{
					operation3.Update_Loesung(aufgabeNr, rechnerNr, loesung);
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

	public List<Loesung> ReadAllLoesungen()
			throws DataBasePathNotFoundException, NoAccessToDataBaseException, SQLException
	{
		try
		{
			DataBasePropertyInitializer initializer = new DataBasePropertyInitializer();
			String databasePath = initializer.GetDataBasePath();
			DataBaseConnector connector = new DataBaseConnector(databasePath);
			Connection = connector.ConnectToDataBase();
			LoesungSQL operation = new LoesungSQL(Connection);
			return operation.ReadFrom_Loesung();
		} catch (DataBasePathNotFoundException ex)
		{
			throw ex;
		} catch (NoAccessToDataBaseException ac)
		{
			throw ac;
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

	public String DeleteLoesung(String aufgabeNr, String rechnerNr)
	{
		try
		{
			DataBasePropertyInitializer initializer = new DataBasePropertyInitializer();
			String databasePath = initializer.GetDataBasePath();
			DataBaseConnector connector = new DataBaseConnector(databasePath);
			Connection = connector.ConnectToDataBase();
			LoesungSQL operation = new LoesungSQL(Connection);
			operation.DeleteFrom_Loesung(aufgabeNr, rechnerNr);
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
