package beans;

import java.sql.SQLException;
import java.util.List;

import exceptions.DataBasePathNotFoundException;
import exceptions.NoAccessToDataBaseException;
import management.AufgabeManagement;
import objects.Aufgabe;

public class AufgabeBean
{
	public String doAction()
	{
		AufgabeManagement management = new AufgabeManagement();
		management.DeleteAufgabe("1");
		management.SaveAufgabe("1", "Hans Peter ist da !");
		management.SaveAufgabe("1", "2", "Sei lieb zu Philip");
		List<Aufgabe> aufgaben;
		try
		{
			aufgaben = management.ReadAllAufgaben();
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
		for (Aufgabe aufgabe : aufgaben)
		{
			out += "Aufgabe: ";
			out += "\n AufgabeNr: " + aufgabe.AufgabeNr;
			out += "\n LaborblattNr: " + aufgabe.LaborBlattNr;
			out += "\n Text: " + aufgabe.AufgabeText;
		}
		return out;
	}
}
