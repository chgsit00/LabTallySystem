package databasesql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import exceptions.NoAccessToDataBaseException;
import objects.Team;

public class TeamSQL
{
	private Connection Connection = null;
	public final String GETALLTEAMS = "SELECT * FROM TEAM;";

	public TeamSQL(Connection connection)
	{
		Connection = connection;
	}

	public Team GetTeam_by_TeamNr(String teamNr) throws NoAccessToDataBaseException
	{
		try
		{
			if (Connection != null)
			{
				Statement statement = Connection.createStatement();
				statement.setQueryTimeout(30);
				ResultSet result = statement.executeQuery("SELECT * FROM TEAM WHERE TeamNr = '" + teamNr + "';");
				Team team = new Team();
				while (result.next())
				{
					String TeamNr = result.getString("TeamNr");
					String passwort = result.getString("Passwort");
					team.setValues(TeamNr, passwort);
				}
				return team;
			} else
				throw new NoAccessToDataBaseException();

		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new NoAccessToDataBaseException();
		}
	}

	public void InsertInto_Team(String teamNr, String passwort) throws NoAccessToDataBaseException
	{
		try
		{
			if (Connection != null)
			{
				Statement statement = Connection.createStatement();
				statement.setQueryTimeout(30);
				statement.executeUpdate(
						"INSERT INTO Team (TeamNr, Passwort) VALUES('" + teamNr + "' ,'" + passwort + "');");
			} else
				throw new NoAccessToDataBaseException();

		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void InsertInto_Team(String teamNr) throws NoAccessToDataBaseException
	{
		try
		{
			if (Connection != null)
			{
				Statement statement = Connection.createStatement();
				statement.setQueryTimeout(30);
				statement.executeUpdate("INSERT INTO Team (TeamNr) VALUES('" + teamNr + "');");
			} else
				throw new NoAccessToDataBaseException();

		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void Update_Team(String teamNr, String passwort) throws NoAccessToDataBaseException
	{
		try
		{
			if (Connection != null)
			{
				Statement statement = Connection.createStatement();
				statement.setQueryTimeout(30);
				statement.executeUpdate("UPDATE TEAM SET TeamNr = '" + teamNr + "' , Passwort = '" + passwort
						+ "' WHERE TeamNr = '" + teamNr + "';");
			} else
				throw new NoAccessToDataBaseException();

		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<Team> ReadFrom_Team() throws NoAccessToDataBaseException, SQLException
	{
		Statement statement;
		if (Connection != null)
		{
			statement = Connection.createStatement();
			ResultSet result = statement.executeQuery(GETALLTEAMS);
			List<Team> teams = new ArrayList<Team>();
			while (result.next())
			{
				// read the result set
				String teamNr = result.getString("TeamNr");
				String passwort = result.getString("Passwort");
				Team team = new Team(teamNr, passwort);
				teams.add(team);
			}
			return teams;
		} else
			throw new NoAccessToDataBaseException();

	}

	public void DeleteFrom_Team(String teamNr) throws NoAccessToDataBaseException
	{
		try
		{
			if (Connection != null)
			{
				Statement statement = Connection.createStatement();
				statement.setQueryTimeout(30);
				statement.executeUpdate("DELETE FROM TEAM WHERE TeamNr = '" + teamNr + "';");
			} else
				throw new NoAccessToDataBaseException();

		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
