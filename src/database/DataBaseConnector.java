package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import exceptions.DataBasePathNotFoundException;

public class DataBaseConnector
{
	public String path = "";

	public DataBaseConnector(String inputpath)
	{
		path = inputpath;
	}

	public Connection ConnectToDataBase() throws DataBasePathNotFoundException
	{
		Connection connection = null;
		try
		{
			if (path != "")
			{
				Class.forName("org.sqlite.JDBC");
				connection = DriverManager.getConnection("jdbc:sqlite:" + path);
				return connection;
			} else
				throw new DataBasePathNotFoundException();
		} catch (ClassNotFoundException c)
		{
			System.out.println(c.getMessage());
			throw new DataBasePathNotFoundException();
		} catch (SQLException s)
		{
			System.out.println(s.getMessage());
			throw new DataBasePathNotFoundException();
		}
	}
}
