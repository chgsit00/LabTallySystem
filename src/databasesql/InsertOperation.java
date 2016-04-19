package databasesql;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import exceptions.NoAccessToDataBaseException;

public class InsertOperation
{
	private Connection Connection = null;
	
	public InsertOperation(Connection connection)
	{
		Connection = connection;
	}
	
	public void InsertInto_LaborSlots(String nr, boolean belegt, String termin) throws NoAccessToDataBaseException
	{
		int belegt_int = 0;
		if(belegt == true)
		{
			belegt_int = 1;
		}
		try
		{			
			if(Connection != null){
				Statement statement = Connection.createStatement();
			    statement.setQueryTimeout(30);
			    statement.executeUpdate("DELETE FROM LABORSLOTS;");
			    statement.executeUpdate("INSERT INTO LABORSLOTS (Slot, Belegt, Termin) VALUES('"+nr+"' ,"+belegt_int+", '"+termin+"');");
			}
			else throw new NoAccessToDataBaseException();
			
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
