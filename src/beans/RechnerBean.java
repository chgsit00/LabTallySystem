package beans;

import java.sql.SQLException;
import java.util.List;

import exceptions.DataBasePathNotFoundException;
import exceptions.NoAccessToDataBaseException;
import management.RechnerManagement;
import objects.Rechner;

public class RechnerBean
{
	public String doAction()
	{
		RechnerManagement management = new RechnerManagement();
		management.DeleteRechner("1");
		management.InsertRechner("1");
		management.DeleteRechner("2");
		management.InsertRechner("2");
		String out = "";
		List<Rechner> rechner;
		try
		{
			rechner = management.ReadAllRechner();
		} catch (DataBasePathNotFoundException e)
		{
			return e.GetMessage();
		} catch (SQLException e)
		{
			return e.getMessage();
		} catch (NoAccessToDataBaseException e)
		{
			return e.GetMessage();
		}
		for (Rechner Rechner : rechner)
		{
			out += "\n Rechner: ";
			out += "\n RechnerNr: " + Rechner.RechnerNr;
		}
		return out;
	}
}
