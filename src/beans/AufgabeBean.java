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
		management.DeleteAufgabe("2");
		management.DeleteAufgabe("3");
		management.DeleteAufgabe("4");
		management.DeleteAufgabe("5");
		management.DeleteAufgabe("6");
		management.SaveAufgabe("1", "Finde die IP von Troll");
		management.SaveAufgabe("2", "2", "Bobs Betriebssystem ?", "ManualInput");
		management.SaveAufgabe("3", "2", "offene TCP Ports ?", "ManualInput");
		management.SaveAufgabe("4", "2", "Services ?", "ManualInput");
		management.SaveAufgabe("5", "2", "offene UDP Ports ?", "ManualInput");
		management.SaveAufgabe("6", "2", "Ist Bob erreichbar ?", "ManualInput");
		management.SaveAufgabe("1", "2", "Finde die IP von Bob", "ManualInput");
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
