package databasesql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import exceptions.NoAccessToDataBaseException;
import objects.Loesung;

public class LoesungSQL
{
	private Connection Connection = null;
	public final String GETALLLOESUNGEN = "SELECT * FROM LOESUNG;";
	
	public LoesungSQL(Connection connection){
		Connection = connection;
	}
	
	public Loesung GetLoesung_by_AufgabeNr_and_RechnerNr(String aufgabeNr, String rechnerNr) throws NoAccessToDataBaseException{
		try
		{			
			if(Connection != null){
				Statement statement = Connection.createStatement();
			    statement.setQueryTimeout(30);
			    ResultSet result = statement.executeQuery("SELECT * FROM LOESUNG WHERE AufgabeNr = '"+aufgabeNr+"' AND RechnerNr = '"+rechnerNr+"';");
			    Loesung loesung = new Loesung();
			    while(result.next())
			    {		    	
			        // read the result set

			    	String AufgabeNr = result.getString("AufgabeNr");
			        String RechnerNr = result.getString("RechnerNr");	
			        String Loesung = result.getString("Loesung");	
			        loesung.setValues(Loesung, AufgabeNr, RechnerNr);
			    }			   
			    return loesung;
			}
			else throw new NoAccessToDataBaseException();
			
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new NoAccessToDataBaseException();
		}
	}
	
	public void InsertInto_Loesung(String aufgabeNr, String rechnerNr, String loesung) throws NoAccessToDataBaseException
	{
		try
		{			
			if(Connection != null){
				Statement statement = Connection.createStatement();
			    statement.setQueryTimeout(30);
			    statement.executeUpdate("INSERT INTO LOESUNG (AufgabeNr, RechnerNr, Loesung) VALUES('"+aufgabeNr+"', '"+rechnerNr+"', '"+loesung+"');");
			}
			else throw new NoAccessToDataBaseException();
			
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void Update_Loesung(String aufgabeNr, String rechnerNr, String loesung) throws NoAccessToDataBaseException{
		try
		{			
			if(Connection != null){
				Statement statement = Connection.createStatement();
			    statement.setQueryTimeout(30);
			    statement.executeUpdate("UPDATE LOESUNG SET AufgabeNr = '"+aufgabeNr+"',  RechnerNr = '"+rechnerNr+"', Loesung = '"+loesung+"' WHERE AufgabeNr = '"+aufgabeNr+"' AND RechnerNr = '"+rechnerNr+"';");
			}
			else throw new NoAccessToDataBaseException();
			
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String ReadFrom_Loesung() throws NoAccessToDataBaseException
	{
		Statement statement;
		try
		{
			if(Connection != null){
				statement = Connection.createStatement();
			    ResultSet result = statement.executeQuery(GETALLLOESUNGEN);
			    String out = "";
			    while(result.next())
			    {		    	
			    	out += " AufgabeNr = " + result.getString("AufgabeNr");
			        out += " RechnerNr = " + result.getString("RechnerNr");
			        out += " Loesung = " +result.getString("Loesung");
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
	
	public void DeleteFrom_Loesung(String aufgabeNr, String rechnerNr) throws NoAccessToDataBaseException{
		try
		{			
			if(Connection != null){
				Statement statement = Connection.createStatement();
			    statement.setQueryTimeout(30);
			    statement.executeUpdate("DELETE FROM LOESUNG WHERE AufgabeNr = '"+aufgabeNr+"' AND RechnerNr = '"+rechnerNr+"';");
			}
			else throw new NoAccessToDataBaseException();
			
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
