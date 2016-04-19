package databasesql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import exceptions.NoAccessToDataBaseException;

public class ReadOperation
{
	private Connection Connection = null;
	public final String GETALLSLOTS = "SELECT * FROM LABORSLOTS;";
	
	public ReadOperation(Connection connection)
	{
		Connection = connection;
	}
	
	public String ReadFrom_LaborSlots() throws NoAccessToDataBaseException
	{
		Statement statement;
		try
		{
			if(Connection != null){
				statement = Connection.createStatement();
			    ResultSet result = statement.executeQuery(GETALLSLOTS);
			    String out = "";
			    while(result.next())
			    {		    	
			        // read the result set
			    	System.out.println("Slot = " + result.getString("Slot"));
			    	out += " Slot = " + result.getString("Slot");
			        System.out.println("Belegt = " + result.getBoolean("Belegt"));
			        out += " Belegt = " + result.getBoolean("Belegt");
			        System.out.println("Termin = " + result.getString("Termin"));
			        out += " Termin = " + result.getString("Termin");
			    }
			    return out;
			}
			else throw new NoAccessToDataBaseException();
		} catch (SQLException s)
		{
			// TODO Auto-generated catch block
			System.out.println(s.getMessage());
			return s.getMessage();
		}

	}
}
