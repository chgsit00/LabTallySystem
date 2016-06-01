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
import management.LaborslotManagement;

public class LaborslotCSV
{
	private Connection Connection = null;

	public LaborslotCSV(Connection connection)
	{
		Connection = connection;
	}

	public void ImportFromCSV_InsertInto_Laborslot() throws NoAccessToDataBaseException
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
						new InputStreamReader(new FileInputStream(csvFilePath + "Laborslots.csv"), "UTF8"));
				String line;
				line = br.readLine();
				while ((line = br.readLine()) != null)
				{
					String[] values = line.split(";"); // your seperator

					LaborslotManagement laborslotManagement = new LaborslotManagement();
					Boolean belegt = false;
					if (values[2].equals("1"))
					{
						belegt = true;
					} else
					{
						belegt = false;
					}
					laborslotManagement.SaveLaborSlot(values[0], belegt, values[1], values[3]);
					// statement.executeUpdate("INSERT INTO Laborslots VALUES('"
					// + values[0] + "','" + values[1] + "','" + values[2] +
					// "','" + values[3] + "');");
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

	public void DeleteAllFrom_Laborslot() throws NoAccessToDataBaseException
	{
		try
		{
			if (Connection != null)
			{
				Statement statement = Connection.createStatement();
				statement.setQueryTimeout(30);
				statement.executeUpdate("DELETE FROM Laborslots");
			} else
				throw new NoAccessToDataBaseException();

		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
