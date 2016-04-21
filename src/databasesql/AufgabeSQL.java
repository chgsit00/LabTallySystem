package databasesql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import exceptions.NoAccessToDataBaseException;
import objects.Aufgabe;

public class AufgabeSQL
{
	private Connection Connection = null;
	public final String GETALLAUFGABEN = "SELECT * FROM AUFGABE;";
	
	public AufgabeSQL(Connection connection){
		Connection = connection;
	}
	
	public Aufgabe GetAufgabe_by_AufgabeNr(String aufgabeNr) throws NoAccessToDataBaseException{
		try
		{			
			if(Connection != null){
				Statement statement = Connection.createStatement();
			    statement.setQueryTimeout(30);
			    ResultSet result = statement.executeQuery("SELECT * FROM AUFGABE WHERE AufgabeNr = '"+aufgabeNr+"';");
			    Aufgabe aufgabe = new Aufgabe();
			    while(result.next())
			    {		    	
			        // read the result set

			    	String AufgabeNr = result.getString("AufgabeNr");
			        String LaborblattNr = result.getString("LaborblattNr");	
			        String AufgabeText = result.getString("AufgabeText");	
			        aufgabe.setValues(AufgabeNr, LaborblattNr, AufgabeText);
			    }			   
			    return aufgabe;
			}
			else throw new NoAccessToDataBaseException();
			
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new NoAccessToDataBaseException();
		}
	}
	
	public void InsertInto_Aufgabe(String aufgabeNr, String laborblattNr, String aufgabeText) throws NoAccessToDataBaseException
	{
		try
		{			
			if(Connection != null){
				Statement statement = Connection.createStatement();
			    statement.setQueryTimeout(30);
			    statement.executeUpdate("INSERT INTO AUFGABE (AufgabeNr, LaborblattNr, AufgabeText) VALUES('"+aufgabeNr+"', '"+laborblattNr+"', '"+aufgabeText+"');");
			}
			else throw new NoAccessToDataBaseException();
			
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void InsertInto_Aufgabe(String aufgabeNr, String aufgabeText) throws NoAccessToDataBaseException
	{
		try
		{			
			if(Connection != null){
				Statement statement = Connection.createStatement();
			    statement.setQueryTimeout(30);
			    statement.executeUpdate("INSERT INTO AUFGABE (AufgabeNr, AufgabeText) VALUES('"+aufgabeNr+"', '"+aufgabeText+"');");
			}
			else throw new NoAccessToDataBaseException();
			
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void Update_Aufgabe(String aufgabeNr, String laborblattNr, String aufgabeText) throws NoAccessToDataBaseException{
		try
		{			
			if(Connection != null){
				Statement statement = Connection.createStatement();
			    statement.setQueryTimeout(30);
			    statement.executeUpdate("UPDATE AUFGABE SET AufgabeNr = '"+aufgabeNr+"',  LaborBlattNr = '"+laborblattNr+"', AufgabeText = '"+aufgabeText+"' WHERE AufgabeNr = '"+aufgabeNr+"';");
			}
			else throw new NoAccessToDataBaseException();
			
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	public void Update_Aufgabe(String aufgabeNr, String aufgabeText) throws NoAccessToDataBaseException{
		try
		{			
			if(Connection != null){
				Statement statement = Connection.createStatement();
			    statement.setQueryTimeout(30);
			    statement.executeUpdate("UPDATE AUFGABE SET AufgabeNr = '"+aufgabeNr+"', AufgabeText = '"+aufgabeText+"' WHERE AufgabeNr = '"+aufgabeNr+"';");
			}
			else throw new NoAccessToDataBaseException();
			
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
			
	public String ReadFrom_Aufgabe() throws NoAccessToDataBaseException
	{
		Statement statement;
		try
		{
			if(Connection != null){
				statement = Connection.createStatement();
			    ResultSet result = statement.executeQuery(GETALLAUFGABEN);
			    String out = "";
			    while(result.next())
			    {		    	
			    	out += " AufgabeNr = " + result.getString("AufgabeNr");
			        out += " LaborblattNr = " + result.getString("LaborblattNr");
			        out += " AufgabeText = " +result.getString("AufgabeText");
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
	
	public void DeleteFrom_Aufgabe(String aufgabeNr) throws NoAccessToDataBaseException{
		try
		{			
			if(Connection != null){
				Statement statement = Connection.createStatement();
			    statement.setQueryTimeout(30);
			    statement.executeUpdate("DELETE FROM AUFGABE WHERE AufgabeNr = '"+aufgabeNr+"';");
			}
			else throw new NoAccessToDataBaseException();
			
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
