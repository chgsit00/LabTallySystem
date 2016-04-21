package databasesql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import exceptions.NoAccessToDataBaseException;
import objects.Einteilung;

public class EinteilungSQL
{
	private Connection Connection = null;
	public final String GETALLEINTEILUNGEN = "SELECT * FROM EINTEILUNG;";
	
	public EinteilungSQL(Connection connection){
		Connection = connection;
	}
	
	public Einteilung GetEinteilung_by_TeamNr_and_Slot(String teamNr, String slot) throws NoAccessToDataBaseException{
		try
		{			
			if(Connection != null){
				Statement statement = Connection.createStatement();
			    statement.setQueryTimeout(30);
			    ResultSet result = statement.executeQuery("SELECT * FROM EINTEILUNG WHERE TeamNr = '"+teamNr+"' AND Slot = '"+slot+"';");
			    Einteilung einteilung = new Einteilung();
			    while(result.next())
			    {		    	
			        // read the result set

			    	String TeamNr = result.getString("TeamNr");
			        String Slot = result.getString("Slot");	
			        einteilung.setValues(TeamNr, Slot);
			    }			   
			    return einteilung;
			}
			else throw new NoAccessToDataBaseException();
			
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new NoAccessToDataBaseException();
		}
	}
	
	public void InsertInto_Einteilung(String teamNr, String slot) throws NoAccessToDataBaseException
	{
		try
		{			
			if(Connection != null){
				Statement statement = Connection.createStatement();
			    statement.setQueryTimeout(30);
			    statement.executeUpdate("INSERT INTO EINTEILUNG (TeamNr, Slot) VALUES('"+teamNr+"', '"+slot+"');");
			}
			else throw new NoAccessToDataBaseException();
			
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String ReadFrom_Einteilung() throws NoAccessToDataBaseException
	{
		Statement statement;
		try
		{
			if(Connection != null){
				statement = Connection.createStatement();
			    ResultSet result = statement.executeQuery(GETALLEINTEILUNGEN);
			    String out = "";
			    while(result.next())
			    {		    	
			    	out += " TeamNr = " + result.getString("TeamNr");
			        out += " Slot = " + result.getString("Slot");
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
	
	public void DeleteFrom_Einteilung(String teamNr, String slot) throws NoAccessToDataBaseException{
		try
		{			
			if(Connection != null){
				Statement statement = Connection.createStatement();
			    statement.setQueryTimeout(30);
			    statement.executeUpdate("DELETE FROM EINTEILUNG WHERE TeamNr = '"+teamNr+"' AND Slot = '"+slot+"';");
			}
			else throw new NoAccessToDataBaseException();
			
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
