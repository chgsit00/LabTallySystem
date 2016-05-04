package management;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import database.DataBaseConnector;
import database.DataBasePropertyInitializer;
import databasesql.GlobaleViewSQL;
import exceptions.DataBasePathNotFoundException;
import exceptions.NoAccessToDataBaseException;
import objects.GlobaleView;

public class GlobaleViewManagement
{
	private Connection Connection = null;

	public List<GlobaleView> ReadAllGlobaleViews()
			throws DataBasePathNotFoundException, NoAccessToDataBaseException, SQLException
	{
		try
		{
			DataBasePropertyInitializer initializer = new DataBasePropertyInitializer();
			String databasePath = initializer.GetDataBasePath();
			DataBaseConnector connector = new DataBaseConnector(databasePath);
			Connection = connector.ConnectToDataBase();
			GlobaleViewSQL operation = new GlobaleViewSQL(Connection);
			return operation.ReadFrom_GlobaleView();
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

	public GlobaleView GetGlobaleView_for_TeamNr(String teamNr)
			throws DataBasePathNotFoundException, NoAccessToDataBaseException
	{
		try
		{
			DataBasePropertyInitializer initializer = new DataBasePropertyInitializer();
			String databasePath = initializer.GetDataBasePath();
			DataBaseConnector connector = new DataBaseConnector(databasePath);
			Connection = connector.ConnectToDataBase();
			GlobaleViewSQL operation = new GlobaleViewSQL(Connection);
			return operation.GetGlobaleView_by_TeamNr(teamNr);
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
}
