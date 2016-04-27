package csv;

import java.sql.Connection;
import java.sql.SQLException;

import database.DataBaseConnector;
import database.DataBasePropertyInitializer;
import exceptions.DataBasePathNotFoundException;
import exceptions.NoAccessToDataBaseException;

public class CSVManagement
{
	private Connection Connection = null;

	public String DeleteAndImportAufgabeFromCSV()
	{

		try
		{
			// String message = "Failure - DeleteAndImport-Operation did not
			// work correctly";
			DataBasePropertyInitializer initializer = new DataBasePropertyInitializer();
			String databasePath = initializer.GetDataBasePath();
			DataBaseConnector connector = new DataBaseConnector(databasePath);
			Connection = connector.ConnectToDataBase();
			AufgabeCSV operation = new AufgabeCSV(Connection);
			operation.DeleteAllFrom_Aufgabe();
			operation.ImportFromCSV_InsertInto_Aufgabe();

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

	public String DeleteAndImportEinteilungFromCSV()
	{

		try
		{
			// String message = "Failure - DeleteAndImport-Operation did not
			// work correctly";
			DataBasePropertyInitializer initializer = new DataBasePropertyInitializer();
			String databasePath = initializer.GetDataBasePath();
			DataBaseConnector connector = new DataBaseConnector(databasePath);
			Connection = connector.ConnectToDataBase();
			EinteilungCSV operation = new EinteilungCSV(Connection);
			operation.DeleteAllFrom_Einteilung();
			operation.ImportFromCSV_InsertInto_Einteilung();

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

	public String DeleteAndImportErgebnisFromCSV()
	{

		try
		{
			// String message = "Failure - DeleteAndImport-Operation did not
			// work correctly";
			DataBasePropertyInitializer initializer = new DataBasePropertyInitializer();
			String databasePath = initializer.GetDataBasePath();
			DataBaseConnector connector = new DataBaseConnector(databasePath);
			Connection = connector.ConnectToDataBase();
			ErgebnisCSV operation = new ErgebnisCSV(Connection);
			operation.DeleteAllFrom_Ergebnis();
			operation.ImportFromCSV_InsertInto_Ergebnis();

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

	public String DeleteAndImportLaborblattFromCSV()
	{

		try
		{
			// String message = "Failure - DeleteAndImport-Operation did not
			// work correctly";
			DataBasePropertyInitializer initializer = new DataBasePropertyInitializer();
			String databasePath = initializer.GetDataBasePath();
			DataBaseConnector connector = new DataBaseConnector(databasePath);
			Connection = connector.ConnectToDataBase();
			LaborlattCSV operation = new LaborlattCSV(Connection);
			operation.DeleteAllFrom_Laborlatt();
			operation.ImportFromCSV_InsertInto_Laborlatt();

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

	public String DeleteAndImportLaborslotFromCSV()
	{

		try
		{
			// String message = "Failure - DeleteAndImport-Operation did not
			// work correctly";
			DataBasePropertyInitializer initializer = new DataBasePropertyInitializer();
			String databasePath = initializer.GetDataBasePath();
			DataBaseConnector connector = new DataBaseConnector(databasePath);
			Connection = connector.ConnectToDataBase();
			LaborslotCSV operation = new LaborslotCSV(Connection);
			operation.DeleteAllFrom_Laborslot();
			operation.ImportFromCSV_InsertInto_Laborslot();

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

	public String DeleteAndImportLoesungFromCSV()
	{

		try
		{
			// String message = "Failure - DeleteAndImport-Operation did not
			// work correctly";
			DataBasePropertyInitializer initializer = new DataBasePropertyInitializer();
			String databasePath = initializer.GetDataBasePath();
			DataBaseConnector connector = new DataBaseConnector(databasePath);
			Connection = connector.ConnectToDataBase();
			LoesungCSV operation = new LoesungCSV(Connection);
			operation.DeleteAllFrom_Loesung();
			operation.ImportFromCSV_InsertInto_Loesung();

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

	public String DeleteAndImportRechnerFromCSV()
	{

		try
		{
			// String message = "Failure - DeleteAndImport-Operation did not
			// work correctly";
			DataBasePropertyInitializer initializer = new DataBasePropertyInitializer();
			String databasePath = initializer.GetDataBasePath();
			DataBaseConnector connector = new DataBaseConnector(databasePath);
			Connection = connector.ConnectToDataBase();
			RechnerCSV operation = new RechnerCSV(Connection);
			operation.DeleteAllFrom_Rechner();
			operation.ImportFromCSV_InsertInto_Rechner();

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

	public String DeleteAndImportTeamFromCSV()
	{

		try
		{
			// String message = "Failure - DeleteAndImport-Operation did not
			// work correctly";
			DataBasePropertyInitializer initializer = new DataBasePropertyInitializer();
			String databasePath = initializer.GetDataBasePath();
			DataBaseConnector connector = new DataBaseConnector(databasePath);
			Connection = connector.ConnectToDataBase();
			TeamCSV operation = new TeamCSV(Connection);
			operation.DeleteAllFrom_Team();
			operation.ImportFromCSV_InsertInto_Team();

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
