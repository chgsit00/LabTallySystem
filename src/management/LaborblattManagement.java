package management;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import database.DataBaseConnector;
import database.DataBasePropertyInitializer;
import databasesql.LaborblattSQL;
import exceptions.DataBasePathNotFoundException;
import exceptions.NoAccessToDataBaseException;
import objects.Laborblatt;

public class LaborblattManagement
{
	private Connection Connection = null;

	public Laborblatt GetLaborblatt_by_LaborblattNr(String laborBlattNr)
			throws DataBasePathNotFoundException, NoAccessToDataBaseException
	{
		DataBasePropertyInitializer initializer = new DataBasePropertyInitializer();
		String databasePath = initializer.GetDataBasePath();
		DataBaseConnector connector = new DataBaseConnector(databasePath);
		Connection = connector.ConnectToDataBase();
		LaborblattSQL operation = new LaborblattSQL(Connection);
		return operation.GetLaborblatt_by_LaborblattNr(laborBlattNr);
	}

	public String InsertLaborBlatt(String laborBlattNr)
	{

		try
		{
			DataBasePropertyInitializer initializer = new DataBasePropertyInitializer();
			String databasePath = initializer.GetDataBasePath();
			DataBaseConnector connector = new DataBaseConnector(databasePath);
			Connection = connector.ConnectToDataBase();
			LaborblattSQL operation = new LaborblattSQL(Connection);
			Laborblatt blatt = operation.GetLaborblatt_by_LaborblattNr(laborBlattNr);
			String message = "Failure - Save-Operation did not work correctly";
			if (blatt.LaborblattNr == null || blatt.LaborblattNr == "")
			{
				operation.InsertInto_Laborblatt(laborBlattNr);
				message = "Insert - Success";
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

	public List<Laborblatt> ReadAllLaborBlaetter()
			throws SQLException, DataBasePathNotFoundException, NoAccessToDataBaseException
	{
		try
		{
			DataBasePropertyInitializer initializer = new DataBasePropertyInitializer();
			String databasePath = initializer.GetDataBasePath();
			DataBaseConnector connector = new DataBaseConnector(databasePath);
			Connection = connector.ConnectToDataBase();
			LaborblattSQL operation = new LaborblattSQL(Connection);
			return operation.ReadFrom_Laborblatt();
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

	public String DeleteLaborBlatt(String laborblattNr)
	{
		try
		{
			DataBasePropertyInitializer initializer = new DataBasePropertyInitializer();
			String databasePath = initializer.GetDataBasePath();
			DataBaseConnector connector = new DataBaseConnector(databasePath);
			Connection = connector.ConnectToDataBase();
			LaborblattSQL operation = new LaborblattSQL(Connection);
			operation.DeleteFrom_Laborblatt(laborblattNr);
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
