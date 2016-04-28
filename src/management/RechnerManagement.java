package management;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import database.DataBaseConnector;
import database.DataBasePropertyInitializer;
import databasesql.RechnerSQL;
import exceptions.DataBasePathNotFoundException;
import exceptions.NoAccessToDataBaseException;
import objects.Rechner;

public class RechnerManagement
{
	private Connection Connection = null;

	public String SaveRechner(String rechnerNr, String ipadresse, String account, String passwort)
	{

		try
		{
			DataBasePropertyInitializer initializer = new DataBasePropertyInitializer();
			String databasePath = initializer.GetDataBasePath();
			DataBaseConnector connector = new DataBaseConnector(databasePath);
			Connection = connector.ConnectToDataBase();
			RechnerSQL operation = new RechnerSQL(Connection);
			Rechner rechner = operation.GetRechner_by_RechnerNr(rechnerNr);
			String message = "Failure - Save-Operation did not work correctly";
			if (rechner.RechnerNr == null)
			{
				operation.InsertInto_Rechner(rechnerNr, ipadresse, account, passwort);
				message = "Insert - Success";
			} else
			{
				operation.Update_Rechner(rechnerNr, ipadresse, account, passwort);
				message = "Update - Success";
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

	public List<Rechner> ReadAllRechner()
			throws DataBasePathNotFoundException, SQLException, NoAccessToDataBaseException
	{
		try
		{
			DataBasePropertyInitializer initializer = new DataBasePropertyInitializer();
			String databasePath = initializer.GetDataBasePath();
			DataBaseConnector connector = new DataBaseConnector(databasePath);
			Connection = connector.ConnectToDataBase();
			RechnerSQL operation = new RechnerSQL(Connection);
			return operation.ReadFrom_Rechner();
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

	public String DeleteRechner(String rechnerNr)
	{
		try
		{
			DataBasePropertyInitializer initializer = new DataBasePropertyInitializer();
			String databasePath = initializer.GetDataBasePath();
			DataBaseConnector connector = new DataBaseConnector(databasePath);
			Connection = connector.ConnectToDataBase();
			RechnerSQL operation = new RechnerSQL(Connection);
			operation.DeleteFrom_Rechner(rechnerNr);
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
