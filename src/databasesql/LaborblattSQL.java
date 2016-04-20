package databasesql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import exceptions.NoAccessToDataBaseException;
import objects.Laborblatt;

public class LaborblattSQL
{
	private Connection Connection = null;
	public final String GETALLBLAETTER = "SELECT * FROM LABORBLATT;";
	
	public LaborblattSQL(Connection connection){
		Connection = connection;
	}
	
	public Laborblatt GetLaborblatt_by_LaborblattNr(String laborblattNr) throws NoAccessToDataBaseException{
		try
		{			
			if(Connection != null){
				Statement statement = Connection.createStatement();
			    statement.setQueryTimeout(30);
			    ResultSet result = statement.executeQuery("SELECT * FROM LABORBLATT WHERE LaborblattNr ="+laborblattNr+";");
			    Laborblatt laborblatt = new Laborblatt();
			    while(result.next())
			    {		    	
			        String LaborblattNr = result.getString("LaborblattNr");
			        laborblatt.setValues(LaborblattNr);
			    }			   
			    return laborblatt;
			}
			else throw new NoAccessToDataBaseException();
			
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new NoAccessToDataBaseException();
		}
	}
	
	public void InsertInto_Laborblatt(String laborblattNr) throws NoAccessToDataBaseException
	{
		try
		{			
			if(Connection != null){
				Statement statement = Connection.createStatement();
			    statement.setQueryTimeout(30);
			    statement.executeUpdate("INSERT INTO LABORBLATT (LaborblattNr) VALUES('"+laborblattNr+"');");
			}
			else throw new NoAccessToDataBaseException();
			
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String ReadFrom_Laborblatt() throws NoAccessToDataBaseException
	{
		Statement statement;
		try
		{
			if(Connection != null){
				statement = Connection.createStatement();
			    ResultSet result = statement.executeQuery(GETALLBLAETTER);
			    String out = "";
			    while(result.next())
			    {		    	
			        System.out.println("LaborblattNr = " + result.getString("LaborblattNr"));
			        out += " LaborblattNr = " + result.getString("LaborblattNr");
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
	
	public void DeleteFrom_Laborblatt(String laborblattNr) throws NoAccessToDataBaseException{
		try
		{			
			if(Connection != null){
				Statement statement = Connection.createStatement();
			    statement.setQueryTimeout(30);
			    statement.executeUpdate("DELETE FROM LABORBLATT WHERE LaborblattNr ="+laborblattNr+";");
			}
			else throw new NoAccessToDataBaseException();
			
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
