package management;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import database.DataBaseConnector;
import database.DataBasePropertyInitializer;
import databasesql.TeamSQL;
import exceptions.DataBasePathNotFoundException;
import exceptions.NoAccessToDataBaseException;
import objects.Team;

public class TeamManagement
{
	private Connection Connection = null;

	public Team GetTeam_by_TeamNr(String teamNr) throws DataBasePathNotFoundException, NoAccessToDataBaseException
	{
		DataBasePropertyInitializer initializer = new DataBasePropertyInitializer();
		String databasePath = initializer.GetDataBasePath();
		DataBaseConnector connector = new DataBaseConnector(databasePath);
		Connection = connector.ConnectToDataBase();
		TeamSQL operation = new TeamSQL(Connection);
		Team team = operation.GetTeam_by_TeamNr(teamNr);
		return team;
	}

	public String SaveTeam(String teamNr, String passwort)
	{

		try
		{
			String message = "Failure - Save-Operation did not work correctly";
			DataBasePropertyInitializer initializer = new DataBasePropertyInitializer();
			String databasePath = initializer.GetDataBasePath();
			DataBaseConnector connector = new DataBaseConnector(databasePath);
			Connection = connector.ConnectToDataBase();
			TeamSQL operation = new TeamSQL(Connection);
			Team team = operation.GetTeam_by_TeamNr(teamNr);

			if (team.TeamNr == null)
			{
				operation.InsertInto_Team(teamNr, passwort);
				;
				message = "Insert - Success";
			} else
			{
				operation.Update_Team(teamNr, passwort);
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

	public String SaveTeam(String teamNr)
	{

		try
		{
			String message = "Failure - Save-Operation did not work correctly";
			DataBasePropertyInitializer initializer = new DataBasePropertyInitializer();
			String databasePath = initializer.GetDataBasePath();
			DataBaseConnector connector = new DataBaseConnector(databasePath);
			Connection = connector.ConnectToDataBase();
			TeamSQL operation = new TeamSQL(Connection);
			Team team = operation.GetTeam_by_TeamNr(teamNr);
			if (team.TeamNr == null)
			{
				operation.InsertInto_Team(teamNr);
				;
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

	public List<Team> ReadAllTeam() throws DataBasePathNotFoundException, NoAccessToDataBaseException, SQLException
	{
		try
		{
			DataBasePropertyInitializer initializer = new DataBasePropertyInitializer();
			String databasePath = initializer.GetDataBasePath();
			DataBaseConnector connector = new DataBaseConnector(databasePath);
			Connection = connector.ConnectToDataBase();
			TeamSQL operation = new TeamSQL(Connection);
			return operation.ReadFrom_Team();
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

	public String DeleteTeam(String teamNr)
	{
		try
		{
			DataBasePropertyInitializer initializer = new DataBasePropertyInitializer();
			String databasePath = initializer.GetDataBasePath();
			DataBaseConnector connector = new DataBaseConnector(databasePath);
			Connection = connector.ConnectToDataBase();
			TeamSQL operation = new TeamSQL(Connection);
			operation.DeleteFrom_Team(teamNr);
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
