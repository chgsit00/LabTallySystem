package management;
import java.sql.Connection;
import java.sql.SQLException;
import database.DataBaseConnector;
import database.DataBasePropertyInitializer;
import databasesql.LaborslotSQL;
import exceptions.DataBasePathNotFoundException;
import exceptions.NoAccessToDataBaseException;
import objects.Laborslot;

public class LaborslotManagement
{
	private Connection Connection = null;
	
	public String InsertLaborSlot(String nr, boolean belegt, String termin, String laborBlattNr){
		  
		  try {
			  DataBasePropertyInitializer initializer = new DataBasePropertyInitializer();
			  String databasePath = initializer.GetDataBasePath();
			  DataBaseConnector connector = new DataBaseConnector(databasePath);
			  Connection = connector.ConnectToDataBase();			  
			  LaborslotSQL operation = new LaborslotSQL(Connection);
			  Laborslot slot = operation.GetLaborslot_by_SlotNr(nr);
			  if(slot.SlotNr != null){
				  operation.InsertInto_LaborSlots(nr, belegt, termin, laborBlattNr);
				  return "Success";
			  }
			  else return "Failure - Entry already exists";
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
	
	public String SaveLaborSlot(String nr, boolean belegt, String termin){
		  
		  try {
			  DataBasePropertyInitializer initializer = new DataBasePropertyInitializer();
			  String databasePath = initializer.GetDataBasePath();
			  DataBaseConnector connector = new DataBaseConnector(databasePath);
			  Connection = connector.ConnectToDataBase();			  
			  LaborslotSQL operation = new LaborslotSQL(Connection);
			  Laborslot slot = operation.GetLaborslot_by_SlotNr(nr);
			  String message = "Failure - Save-Operation did not work correctly";
			  if(slot.SlotNr == null || slot.SlotNr == ""){
				  operation.InsertInto_LaborSlots(nr, belegt, termin);
				  message = "Insert - Success";
			  }
			  else {
				  operation.Update_Laborslot(nr, belegt, termin);
				  message = "Update - Success";
			  }
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
	
	public String ReadAllLaborSlots(){
		  try {
			  DataBasePropertyInitializer initializer = new DataBasePropertyInitializer();
			  String databasePath = initializer.GetDataBasePath();
			  DataBaseConnector connector = new DataBaseConnector(databasePath);
			  Connection = connector.ConnectToDataBase();			  
			  LaborslotSQL operation = new LaborslotSQL(Connection);
			  return operation.ReadFrom_LaborSlots();
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
	
	public String DeleteLaborSlot(String slotNr){
		  try {
			  DataBasePropertyInitializer initializer = new DataBasePropertyInitializer();
			  String databasePath = initializer.GetDataBasePath();
			  DataBaseConnector connector = new DataBaseConnector(databasePath);
			  Connection = connector.ConnectToDataBase();			  
			  LaborslotSQL operation = new LaborslotSQL(Connection);
			  operation.DeleteFrom_LaborSlots(slotNr);
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
