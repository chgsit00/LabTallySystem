package databasesql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import exceptions.NoAccessToDataBaseException;
import objects.Rechner;

public class RechnerSQL
{
	private Connection Connection = null;
	public final String GETALLRECHNER = "SELECT * FROM RECHNER;";
	
	public RechnerSQL(Connection connection){
		Connection = connection;
	}
	
	public Rechner GetRechner_by_RechnerNr(String rechnerNr) throws NoAccessToDataBaseException{
		try
		{			
			if(Connection != null){
				Statement statement = Connection.createStatement();
			    statement.setQueryTimeout(30);
			    ResultSet result = statement.executeQuery("SELECT * FROM Rechner WHERE RechnerNr = '"+rechnerNr+"';");
			    Rechner rechner = new Rechner();
			    while(result.next())
			    {		    	
			        String RechnerNr = result.getString("RechnerNr");
			        rechner.setValues(RechnerNr);
			    }			   
			    return rechner;
			}
			else throw new NoAccessToDataBaseException();
			
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new NoAccessToDataBaseException();
		}
	}
	
	public void InsertInto_Rechner(String rechnerNr) throws NoAccessToDataBaseException
	{
		try
		{			
			if(Connection != null){
				Statement statement = Connection.createStatement();
			    statement.setQueryTimeout(30);
			    statement.executeUpdate("INSERT INTO Rechner (RechnerNr) VALUES('"+rechnerNr+"');");
			}
			else throw new NoAccessToDataBaseException();
			
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String ReadFrom_Rechner() throws NoAccessToDataBaseException
	{
		Statement statement;
		try
		{
			if(Connection != null){
				statement = Connection.createStatement();
			    ResultSet result = statement.executeQuery(GETALLRECHNER);
			    String out = "";
			    while(result.next())
			    {		    	
			        System.out.println("RechnerNr = " + result.getString("RechnerNr"));
			        out += " RechnerNr = " + result.getString("RechnerNr");
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
	
	public void DeleteFrom_Rechner(String rechnerNr) throws NoAccessToDataBaseException{
		try
		{			
			if(Connection != null){
				Statement statement = Connection.createStatement();
			    statement.setQueryTimeout(30);
			    statement.executeUpdate("DELETE FROM RECHNER WHERE RechnerNr = '"+rechnerNr+"';");
			}
			else throw new NoAccessToDataBaseException();
			
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
