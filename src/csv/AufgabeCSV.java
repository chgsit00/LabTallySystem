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

public class AufgabeCSV
{
	private Connection Connection = null;

	public AufgabeCSV(Connection connection)
	{
		Connection = connection;
	}

	public void ImportFromCSV_InsertInto_Aufgabe() throws NoAccessToDataBaseException
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
						new InputStreamReader(new FileInputStream(csvFilePath + "Aufgabe.csv"), "UTF8"));
				String line;
				line = br.readLine();
				while ((line = br.readLine()) != null)
				{
					String[] values = line.split(";"); // your seperator

					statement.executeUpdate("INSERT INTO Aufgabe VALUES('" + values[0] + "','" + values[1] + "','"
							+ values[2] + "','" + values[3] + "');");
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

	public void DeleteAllFrom_Aufgabe() throws NoAccessToDataBaseException
	{
		try
		{
			if (Connection != null)
			{
				Statement statement = Connection.createStatement();
				statement.setQueryTimeout(30);
				statement.executeUpdate("DELETE FROM Aufgabe");
			} else
				throw new NoAccessToDataBaseException();

		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
