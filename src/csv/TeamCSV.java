package csv;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import exceptions.CSVFilePathNotFoundException;
import exceptions.NoAccessToDataBaseException;
import management.TeamManagement;

public class TeamCSV
{
	private Connection Connection = null;

	public TeamCSV(Connection connection)
	{
		Connection = connection;
	}

	public void ImportFromCSV_InsertInto_Team() throws NoAccessToDataBaseException
	{
		try
		{
			if (Connection != null)
			{
				Statement statement = Connection.createStatement();
				statement.setQueryTimeout(30);

				CSVFilePathPropertyInitializer initializer = new CSVFilePathPropertyInitializer();
				String csvFilePath = initializer.GetCSVFilePath();

				BufferedReader br = new BufferedReader(
						new InputStreamReader(new FileInputStream(csvFilePath + "Team.csv"), "UTF8"));
				String line;
				line = br.readLine();
				while ((line = br.readLine()) != null)
				{
					String[] values = line.split(";"); // your seperator

					TeamManagement teamManagement = new TeamManagement();
					teamManagement.SaveTeam(values[0], values[1]);
					// statement.executeUpdate("INSERT INTO Team VALUES('" +
					// values[0] + "','" + values[1] + "');");
				}
				br.close();
			} else
				throw new NoAccessToDataBaseException();

		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CSVFilePathNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void DeleteAllFrom_Team() throws NoAccessToDataBaseException
	{
		try
		{
			if (Connection != null)
			{
				Statement statement = Connection.createStatement();
				statement.setQueryTimeout(30);
				statement.executeUpdate("DELETE FROM Team");
			} else
				throw new NoAccessToDataBaseException();

		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
