package beans;

import java.sql.SQLException;
import java.util.List;

import exceptions.DataBasePathNotFoundException;
import exceptions.NoAccessToDataBaseException;
import management.EinteilungManagement;
import objects.Einteilung;

public class EinteilungBean
{
	public String doAction()
	{
		EinteilungManagement management = new EinteilungManagement();
		management.DeleteEinteilung("1", "1");
		management.SaveEinteilung("1", "1");
		List<Einteilung> einteilungen;
		try
		{
			einteilungen = management.ReadAllEinteilungen();
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
		for (Einteilung einteilung : einteilungen)
		{
			out += "Einteilung: ";
			out += "\n TeamNr: " + einteilung.TeamNr;
			out += "\n Slot: " + einteilung.Slot;
		}
		return out;
	}
}
