package beans;

import java.sql.SQLException;
import java.util.List;

import exceptions.DataBasePathNotFoundException;
import exceptions.NoAccessToDataBaseException;
import management.LoesungManagement;
import objects.Loesung;

public class LoesungBean
{
	public String doAction()
	{
		LoesungManagement management = new LoesungManagement();
		management.DeleteLoesung("1", "1");
		management.SaveLoesung("1", "1", "PI");
		management.SaveLoesung("1", "1", "PI/2");
		List<Loesung> loesungen;
		try
		{
			loesungen = management.ReadAllLoesungen();
		} catch (DataBasePathNotFoundException e)
		{
			return e.GetMessage();
		} catch (NoAccessToDataBaseException e)
		{
			return e.GetMessage();
		} catch (SQLException e)
		{
			return e.getMessage();
		}
		String out = "";
		for (Loesung loesung : loesungen)
		{
			out += " Lösung: ";
			out += "\n AufgabeNr: " + loesung.AufgabeNr;
			out += "\n RechnerNr: " + loesung.RechnerNr;
			out += "\n Lösung: " + loesung.Loesung;
		}
		return out;
	}
}
