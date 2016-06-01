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
import management.EinteilungManagement;

public class EinteilungCSV
{
	private Connection Connection = null;

	public EinteilungCSV(Connection connection)
	{
		Connection = connection;
	}

	public void ImportFromCSV_InsertInto_Einteilung() throws NoAccessToDataBaseException
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
						new InputStreamReader(new FileInputStream(csvFilePath + "Einteilung.csv"), "UTF8"));
				String line;
				line = br.readLine();
				while ((line = br.readLine()) != null)
				{
					String[] values = line.split(";"); // your seperator

					EinteilungManagement einteilungManagement = new EinteilungManagement();
					einteilungManagement.SaveEinteilung(values[0], values[1]);
					// statement.executeUpdate("INSERT INTO Einteilung VALUES('"
					// + values[0] + "','" + values[1] + "');");
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

	public void DeleteAllFrom_Einteilung() throws NoAccessToDataBaseException
	{
		try
		{
			if (Connection != null)
			{
				Statement statement = Connection.createStatement();
				statement.setQueryTimeout(30);
				statement.executeUpdate("DELETE FROM EINTEILUNG");
			} else
				throw new NoAccessToDataBaseException();

		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
