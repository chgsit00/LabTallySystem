package databasesql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import exceptions.NoAccessToDataBaseException;
import objects.Laborslot;

public class LaborslotSQL
{
	private Connection Connection = null;
	public final String GETALLSLOTS = "SELECT * FROM LABORSLOTS;";

	public LaborslotSQL(Connection connection)
	{
		Connection = connection;
	}

	public Laborslot GetLaborslot_by_SlotNr(String nr) throws NoAccessToDataBaseException
	{
		try
		{
			if (Connection != null)
			{
				Statement statement = Connection.createStatement();
				statement.setQueryTimeout(30);
				ResultSet result = statement.executeQuery("SELECT * FROM LABORSLOTS WHERE Slot = '" + nr + "';");
				Laborslot laborslot = new Laborslot();
				while (result.next())
				{
					// read the result set

					String slot = result.getString("Slot");
					boolean belegt = result.getBoolean("Belegt");
					String termin = result.getString("Termin");
					String laborblattNr = result.getString("LaborblattNr");
					laborslot.setValues(slot, belegt, termin, laborblattNr);
				}
				return laborslot;
			} else
				throw new NoAccessToDataBaseException();

		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new NoAccessToDataBaseException();
		}
	}

	public void InsertInto_LaborSlots(String nr, boolean belegt, String termin, String laborBlattNr)
			throws NoAccessToDataBaseException
	{
		int belegt_int = 0;
		if (belegt == true)
		{
			belegt_int = 1;
		}
		try
		{
			if (Connection != null)
			{
				Statement statement = Connection.createStatement();
				statement.setQueryTimeout(30);
				statement.executeUpdate("INSERT INTO LABORSLOTS (Slot, Belegt, Termin, LaborblattNr) VALUES('" + nr
						+ "' ," + belegt_int + ", '" + termin + "', '" + laborBlattNr + "');");
			} else
				throw new NoAccessToDataBaseException();

		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void InsertInto_LaborSlots(String nr, boolean belegt, String termin) throws NoAccessToDataBaseException
	{
		int belegt_int = 0;
		if (belegt == true)
		{
			belegt_int = 1;
		}
		try
		{
			if (Connection != null)
			{
				Statement statement = Connection.createStatement();
				statement.setQueryTimeout(30);
				statement.executeUpdate("INSERT INTO LABORSLOTS (Slot, Belegt, Termin) VALUES('" + nr + "' ,"
						+ belegt_int + ", '" + termin + "');");
			} else
				throw new NoAccessToDataBaseException();

		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void Update_Laborslot(String nr, boolean belegt, String termin, String laborBlattNr)
			throws NoAccessToDataBaseException
	{
		int belegt_int = 0;
		if (belegt == true)
		{
			belegt_int = 1;
		}
		try
		{
			if (Connection != null)
			{
				Statement statement = Connection.createStatement();
				statement.setQueryTimeout(30);
				statement.executeUpdate(
						"UPDATE LABORSLOTS SET Slot = '" + nr + "' , Belegt = " + belegt_int + ", Termin = '" + termin
								+ "',  LaborBlattNr = '" + laborBlattNr + "' WHERE Slot = '" + nr + "';");
			} else
				throw new NoAccessToDataBaseException();

		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void Update_Laborslot(String nr, boolean belegt, String termin) throws NoAccessToDataBaseException
	{
		int belegt_int = 0;
		if (belegt == true)
		{
			belegt_int = 1;
		}
		try
		{
			if (Connection != null)
			{
				Statement statement = Connection.createStatement();
				statement.setQueryTimeout(30);
				statement.executeUpdate("UPDATE LABORSLOTS SET Slot = '" + nr + "' , Belegt = " + belegt_int
						+ ", Termin = '" + termin + "' WHERE Slot = '" + nr + "';");
			} else
				throw new NoAccessToDataBaseException();

		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<Laborslot> ReadFrom_LaborSlots() throws NoAccessToDataBaseException, SQLException
	{
		Statement statement;
		if (Connection != null)
		{
			statement = Connection.createStatement();
			ResultSet result = statement.executeQuery(GETALLSLOTS);
			List<Laborslot> laborslots = new ArrayList<Laborslot>();
			while (result.next())
			{
				String slot = result.getString("Slot");
				boolean belegt = result.getBoolean("Belegt");
				String termin = result.getString("Termin");
				String laborblattNr = result.getString("LaborblattNr");
				Laborslot laborslot = new Laborslot(slot, belegt, termin, laborblattNr);
				laborslots.add(laborslot);
			}
			return laborslots;
		} else
			throw new NoAccessToDataBaseException();
	}

	public void DeleteFrom_LaborSlots(String slotNr) throws NoAccessToDataBaseException
	{
		try
		{
			if (Connection != null)
			{
				Statement statement = Connection.createStatement();
				statement.setQueryTimeout(30);
				statement.executeUpdate("DELETE FROM LABORSLOTS WHERE Slot = '" + slotNr + "';");
			} else
				throw new NoAccessToDataBaseException();

		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
