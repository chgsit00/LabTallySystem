package management;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import database.DataBaseConnector;
import database.DataBasePropertyInitializer;
import databasesql.LaborblattSQL;
import databasesql.LaborslotSQL;
import exceptions.DataBasePathNotFoundException;
import exceptions.NoAccessToDataBaseException;
import objects.Laborblatt;
import objects.Laborslot;

public class LaborslotManagement
{
	private Connection Connection = null;

	public String SaveLaborSlot(String nr, boolean belegt, String termin, String laborBlattNr)
	{

		try
		{
			String message = "Failure - Save-Operation did not work correctly";
			DataBasePropertyInitializer initializer = new DataBasePropertyInitializer();
			String databasePath = initializer.GetDataBasePath();
			DataBaseConnector connector = new DataBaseConnector(databasePath);
			Connection = connector.ConnectToDataBase();
			LaborslotSQL operation = new LaborslotSQL(Connection);
			LaborblattSQL operation2 = new LaborblattSQL(Connection);
			Laborblatt laborblatt = operation2.GetLaborblatt_by_LaborblattNr(laborBlattNr);
			if (laborblatt.LaborblattNr != null)
			{
				Laborslot slot = operation.GetLaborslot_by_SlotNr(nr);
				if (slot.SlotNr == null || slot.SlotNr == "")
				{
					operation.InsertInto_LaborSlots(nr, belegt, termin, laborBlattNr);
					message = "Insert - Success";
				} else
				{
					operation.Update_Laborslot(nr, belegt, termin, laborBlattNr);
					message = "Update - Success";
				}
			} else
				message = "Save not possible - Laborblatt with LaborblattNr = " + laborBlattNr + " doesn't exist";
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

	public String SaveLaborSlot(String nr, boolean belegt, String termin)
	{

		try
		{
			DataBasePropertyInitializer initializer = new DataBasePropertyInitializer();
			String databasePath = initializer.GetDataBasePath();
			DataBaseConnector connector = new DataBaseConnector(databasePath);
			Connection = connector.ConnectToDataBase();
			LaborslotSQL operation = new LaborslotSQL(Connection);
			Laborslot slot = operation.GetLaborslot_by_SlotNr(nr);
			String message = "Failure - Save-Operation did not work correctly";
			if (slot.SlotNr == null || slot.SlotNr == "")
			{
				operation.InsertInto_LaborSlots(nr, belegt, termin);
				message = "Insert - Success";
			} else
			{
				operation.Update_Laborslot(nr, belegt, termin);
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

	public List<Laborslot> ReadAllLaborSlots()
			throws DataBasePathNotFoundException, NoAccessToDataBaseException, SQLException
	{
		try
		{
			DataBasePropertyInitializer initializer = new DataBasePropertyInitializer();
			String databasePath = initializer.GetDataBasePath();
			DataBaseConnector connector = new DataBaseConnector(databasePath);
			Connection = connector.ConnectToDataBase();
			LaborslotSQL operation = new LaborslotSQL(Connection);
			return operation.ReadFrom_LaborSlots();
		} catch (DataBasePathNotFoundException ex)
		{
			throw ex;
		} catch (NoAccessToDataBaseException ac)
		{
			throw ac;
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

	public String DeleteLaborSlot(String slotNr)
	{
		try
		{
			DataBasePropertyInitializer initializer = new DataBasePropertyInitializer();
			String databasePath = initializer.GetDataBasePath();
			DataBaseConnector connector = new DataBaseConnector(databasePath);
			Connection = connector.ConnectToDataBase();
			LaborslotSQL operation = new LaborslotSQL(Connection);
			operation.DeleteFrom_LaborSlots(slotNr);
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
