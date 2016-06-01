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
import management.ErgebnisManagement;

public class ErgebnisCSV
{
	private Connection Connection = null;

	public ErgebnisCSV(Connection connection)
	{
		Connection = connection;
	}

	public void ImportFromCSV_InsertInto_Ergebnis() throws NoAccessToDataBaseException
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
						new InputStreamReader(new FileInputStream(csvFilePath + "Ergebnis.csv"), "UTF8"));
				String line;
				line = br.readLine();
				while ((line = br.readLine()) != null)
				{
					String[] values = line.split(";"); // your seperator

					ErgebnisManagement ergebnisManagement = new ErgebnisManagement();
					Boolean bestanden = false;
					if (values[2].equals("1"))
					{
						bestanden = true;
					} else
					{
						bestanden = false;
					}
					ergebnisManagement.SaveErgebnis(values[3], values[4], values[0], values[1], bestanden, values[5]);
					// statement.executeUpdate("INSERT INTO Ergebnis VALUES('" +
					// values[0] + "','" + values[1] + "','" + values[2] + "','"
					// + values[3] + "','" + values[4] + "','" + values[5] +
					// "');");
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

	public void DeleteAllFrom_Ergebnis() throws NoAccessToDataBaseException
	{
		try
		{
			if (Connection != null)
			{
				Statement statement = Connection.createStatement();
				statement.setQueryTimeout(30);
				statement.executeUpdate("DELETE FROM Ergebnis");
			} else
				throw new NoAccessToDataBaseException();

		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
