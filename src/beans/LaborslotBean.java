package beans;

import java.sql.SQLException;
import java.util.List;

import exceptions.DataBasePathNotFoundException;
import exceptions.NoAccessToDataBaseException;
import management.LaborslotManagement;
import objects.Laborslot;

public class LaborslotBean
{
	public String getDateString()
	{
		LaborslotManagement management = new LaborslotManagement();
		management.DeleteLaborSlot("1");
		management.SaveLaborSlot("1", false, "2016-04-12");
		management.SaveLaborSlot("1", true, "2016-04-19", "1");
		management.DeleteLaborSlot("3");
		management.SaveLaborSlot("3", false, "2016-04-12");
		management.SaveLaborSlot("3", true, "2016-04-19", "3");
		List<Laborslot> laborslots;
		try
		{
			laborslots = management.ReadAllLaborSlots();
		} catch (DataBasePathNotFoundException e)
		{
			// TODO Auto-generated catch block
			return e.GetMessage();
		} catch (NoAccessToDataBaseException e)
		{
			// TODO Auto-generated catch block
			return e.GetMessage();
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			return e.getMessage();
		}
		String out = "";
		for (Laborslot laborslot : laborslots)
		{
			out += " Laborslot: ";
			out += "\n LaborslotNr: " + laborslot.SlotNr;
			out += "\n LaborblattNr: " + laborslot.LaborblattNr;
			out += "\n Belegt: " + laborslot.Belegt;
			out += "\n Termin: " + laborslot.Termin;
		}
		return out;
	}
}