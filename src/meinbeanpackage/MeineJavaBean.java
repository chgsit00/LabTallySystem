package meinbeanpackage;

import database.*;
import databasesql.InsertOperation;
import databasesql.ReadOperation;
import exceptions.DataBasePathNotFoundException;
import exceptions.NoAccessToDataBaseException;

import java.sql.Connection;
import java.sql.SQLException;

public class MeineJavaBean
{
  public String getDateString()
  {
	  Connection connection = null;
	  try {
		  DataBasePropertyInitializer initializer = new DataBasePropertyInitializer();
		  String databasePath = initializer.GetDataBasePath();
		  DataBaseConnector connector = new DataBaseConnector(databasePath);
		  connection = connector.ConnectToDataBase();
		  InsertOperation insertOperation = new InsertOperation(connection);
		  insertOperation.InsertInto_LaborSlots("1", false, "2016-04-12");
		  ReadOperation readOperation = new ReadOperation(connection);
		  return readOperation.ReadFrom_LaborSlots();
		  //dukfgkdjf
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
	        if(connection != null)
	          connection.close();
	      }
	      catch(SQLException e)
	      {
	        // connection close failed.
	        System.err.println(e);
	      }
	    }
   // return (new SimpleDateFormat("yyyy-MM-dd, HH:mm:ss")).format(new Date()) + " h";
  }
}