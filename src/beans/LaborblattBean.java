package beans;

import java.sql.SQLException;
import java.util.List;

import exceptions.DataBasePathNotFoundException;
import exceptions.NoAccessToDataBaseException;
import management.LaborblattManagement;
import objects.Laborblatt;

public class LaborblattBean
{
	public String doAction()
	{
		LaborblattManagement management = new LaborblattManagement();
		management.DeleteLaborBlatt("1");
		management.InsertLaborBlatt("1");
		management.DeleteLaborBlatt("2");
		management.InsertLaborBlatt("2");
		List<Laborblatt> laborblaetter;
		try
		{
			laborblaetter = management.ReadAllLaborBlaetter();
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			return e.getMessage();
		} catch (DataBasePathNotFoundException e)
		{
			// TODO Auto-generated catch block
			return e.GetMessage();
		} catch (NoAccessToDataBaseException e)
		{
			// TODO Auto-generated catch block
			return e.GetMessage();
		}
		String out = "";
		for (Laborblatt laborblatt : laborblaetter)
		{
			out += " Laborblatt: ";
			out += "\n LaborblattNr: " + laborblatt.LaborblattNr;
		}
		return out;
	}
}
