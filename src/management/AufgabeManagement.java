package management;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import database.DataBaseConnector;
import database.DataBasePropertyInitializer;
import databasesql.AufgabeSQL;
import databasesql.LaborblattSQL;
import exceptions.DataBasePathNotFoundException;
import exceptions.EingabeArtInputWrongException;
import exceptions.NoAccessToDataBaseException;
import objects.Aufgabe;
import objects.Laborblatt;

public class AufgabeManagement
{
	private Connection Connection = null;

	public String SaveAufgabe(String aufgabeNr, String laborblattNr, String aufgabeText, String eingabeArt)
	{

		try
		{
			String message = "Failure - Save-Operation did not work correctly";
			DataBasePropertyInitializer initializer = new DataBasePropertyInitializer();
			String databasePath = initializer.GetDataBasePath();
			DataBaseConnector connector = new DataBaseConnector(databasePath);
			Connection = connector.ConnectToDataBase();
			AufgabeSQL operation = new AufgabeSQL(Connection);
			LaborblattSQL operation2 = new LaborblattSQL(Connection);
			Laborblatt laborblatt = operation2.GetLaborblatt_by_LaborblattNr(laborblattNr);
			if (laborblatt.LaborblattNr != null)
			{
				if (eingabeArt.equals("Automatic") || eingabeArt.equals("ManualInput") || eingabeArt.equals("Upload"))
				{
					Aufgabe aufgabe = operation.GetAufgabe_by_AufgabeNr(aufgabeNr);
					if (aufgabe.AufgabeNr == null)
					{
						operation.InsertInto_Aufgabe(aufgabeNr, laborblattNr, aufgabeText, eingabeArt);
						message = "Insert - Success";
					} else
					{
						operation.Update_Aufgabe(aufgabeNr, laborblattNr, aufgabeText, eingabeArt);
						message = "Update - Success";
					}
				} else
				{
					throw new EingabeArtInputWrongException();
				}
			} else
				message = "Save not possible - Laborblatt with LaborblattNr = " + laborblattNr + " doesn't exist";
			System.out.println(message);
			return message;
		} catch (EingabeArtInputWrongException ex)
		{
			return ex.getMessage();
		} catch (DataBasePathNotFoundException ex)
		{
			return ex.getMessage();
		} catch (NoAccessToDataBaseException ac)
		{
			return ac.getMessage();
		} finally
		{
			try
			{
				if (Connection != null)
					Connection.close();
			} catch (SQLException e)
			{
				// connection close failed.
				System.err.println(e);
			}
		}
	}

	public String SaveAufgabe(String aufgabeNr, String aufgabeText)
	{

		try
		{
			DataBasePropertyInitializer initializer = new DataBasePropertyInitializer();
			String databasePath = initializer.GetDataBasePath();
			DataBaseConnector connector = new DataBaseConnector(databasePath);
			Connection = connector.ConnectToDataBase();
			AufgabeSQL operation = new AufgabeSQL(Connection);
			Aufgabe aufgabe = operation.GetAufgabe_by_AufgabeNr(aufgabeNr);
			String message = "Failure - Save-Operation did not work correctly";
			if (aufgabe.AufgabeNr == null)
			{
				operation.InsertInto_Aufgabe(aufgabeNr, aufgabeText);
				message = "Insert - Success";
			} else
			{
				operation.Update_Aufgabe(aufgabeNr, aufgabeText);
				message = "Update - Success";
			}
			System.out.println(message);
			return message;
		} catch (DataBasePathNotFoundException ex)
		{
			return ex.getMessage();
		} catch (NoAccessToDataBaseException ac)
		{
			return ac.getMessage();
		} finally
		{
			try
			{
				if (Connection != null)
					Connection.close();
			} catch (SQLException e)
			{
				// connection close failed.
				System.err.println(e);
			}
		}
	}

	public List<Aufgabe> ReadAllAufgaben()
			throws DataBasePathNotFoundException, NoAccessToDataBaseException, SQLException
	{
		try
		{
			DataBasePropertyInitializer initializer = new DataBasePropertyInitializer();
			String databasePath = initializer.GetDataBasePath();
			DataBaseConnector connector = new DataBaseConnector(databasePath);
			Connection = connector.ConnectToDataBase();
			AufgabeSQL operation = new AufgabeSQL(Connection);
			return operation.ReadFrom_Aufgabe();
		} catch (DataBasePathNotFoundException ex)
		{
			throw ex;
		} catch (NoAccessToDataBaseException ac)
		{
			throw ac;
		} catch (SQLException s)
		{
			throw s;
		} finally
		{
			try
			{
				if (Connection != null)
					Connection.close();
			} catch (SQLException e)
			{
				// connection close failed.
				System.err.println(e);
			}
		}
	}

	public String DeleteAufgabe(String aufgabeNr)
	{
		try
		{
			DataBasePropertyInitializer initializer = new DataBasePropertyInitializer();
			String databasePath = initializer.GetDataBasePath();
			DataBaseConnector connector = new DataBaseConnector(databasePath);
			Connection = connector.ConnectToDataBase();
			AufgabeSQL operation = new AufgabeSQL(Connection);
			operation.DeleteFrom_Aufgabe(aufgabeNr);
			return "Success";
		} catch (DataBasePathNotFoundException ex)
		{
			return ex.getMessage();
		} catch (NoAccessToDataBaseException ac)
		{
			return ac.getMessage();
		} finally
		{
			try
			{
				if (Connection != null)
					Connection.close();
			} catch (SQLException e)
			{
				// connection close failed.
				System.err.println(e);
			}
		}
	}

}
