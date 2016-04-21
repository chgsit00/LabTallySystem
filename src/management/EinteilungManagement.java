package management;

import java.sql.Connection;
import java.sql.SQLException;

import database.DataBaseConnector;
import database.DataBasePropertyInitializer;
import databasesql.EinteilungSQL;
import databasesql.LaborslotSQL;
import databasesql.TeamSQL;
import exceptions.DataBasePathNotFoundException;
import exceptions.NoAccessToDataBaseException;
import objects.Einteilung;
import objects.Laborslot;
import objects.Team;

public class EinteilungManagement
{
	private Connection Connection = null;
	
	public String SaveEinteilung(String teamNr, String slot){
		  
		  try {
			  String message = "Failure - Save-Operation did not work correctly";
			  DataBasePropertyInitializer initializer = new DataBasePropertyInitializer();
			  String databasePath = initializer.GetDataBasePath();
			  DataBaseConnector connector = new DataBaseConnector(databasePath);
			  Connection = connector.ConnectToDataBase();			  
			  TeamSQL operation = new TeamSQL(Connection);
			  LaborslotSQL operation2 = new LaborslotSQL(Connection);
			  EinteilungSQL operation3 = new EinteilungSQL(Connection);
			  Team team = operation.GetTeam_by_TeamNr(teamNr);
			  Laborslot laborslot = operation2.GetLaborslot_by_SlotNr(slot);
			  
			  if(team.TeamNr == null){
				  message = "Failure - Team with TeamNr = "+teamNr+" does not exist";
			  }
			  else if(laborslot.SlotNr == null){
				  message = "Failure - Laborslot with SlotNr = "+slot+" does not exist";
			  }
			  else{
				  Einteilung einteilung = operation3.GetEinteilung_by_TeamNr_and_Slot(teamNr, slot);
				  if(einteilung.TeamNr == null && einteilung.Slot == null){
					  operation3.InsertInto_Einteilung(teamNr, slot);
					  message = "Insert - Success";
				  }
				  else {
					  message = "Failure - Einteilung with SlotNr = "+slot+" and TeamNr"+teamNr+" already exists";
				  }
			  }			  
			  System.out.println(message);
			  return message;
		  }
		  catch (DataBasePathNotFoundException ex){
			  return ex.getMessage();
		  }
		  catch (NoAccessToDataBaseException ac){
			  return ac.getMessage();
		  }
		    finally
		    {
		      try
		      {
		        if(Connection != null)
		          Connection.close();
		      }
		      catch(SQLException e)
		      {
		        // connection close failed.
		        System.err.println(e);
		      }
		    }
	}
	
	public String ReadAllEinteilungen(){
		  try {
			  DataBasePropertyInitializer initializer = new DataBasePropertyInitializer();
			  String databasePath = initializer.GetDataBasePath();
			  DataBaseConnector connector = new DataBaseConnector(databasePath);
			  Connection = connector.ConnectToDataBase();			  
			  EinteilungSQL operation = new EinteilungSQL(Connection);
			  return operation.ReadFrom_Einteilung();
		  }
		  catch (DataBasePathNotFoundException ex){
			  return ex.getMessage();
		  }
		  catch (NoAccessToDataBaseException ac){
			  return ac.getMessage();
		  }
		    finally
		    {
		      try
		      {
		        if(Connection != null)
		          Connection.close();
		      }
		      catch(SQLException e)
		      {
		        // connection close failed.
		        System.err.println(e);
		      }
		    }
	}
	
	public String DeleteEinteilung(String teamNr, String slot){
		  try {
			  DataBasePropertyInitializer initializer = new DataBasePropertyInitializer();
			  String databasePath = initializer.GetDataBasePath();
			  DataBaseConnector connector = new DataBaseConnector(databasePath);
			  Connection = connector.ConnectToDataBase();			  
			  EinteilungSQL operation = new EinteilungSQL(Connection);
			  operation.DeleteFrom_Einteilung(teamNr, slot);
			  return "Success";
		  }
		  catch (DataBasePathNotFoundException ex){
			  return ex.getMessage();
		  }
		  catch (NoAccessToDataBaseException ac){
			  return ac.getMessage();
		  }
		    finally
		    {
		      try
		      {
		        if(Connection != null)
		          Connection.close();
		      }
		      catch(SQLException e)
		      {
		        // connection close failed.
		        System.err.println(e);
		      }
		    }
	}
}
