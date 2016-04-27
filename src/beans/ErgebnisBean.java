package beans;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import exceptions.DataBasePathNotFoundException;
import exceptions.NoAccessToDataBaseException;
import management.ErgebnisManagement;
import objects.Ergebnis;

public class ErgebnisBean
{
	public String doAction()
	{
		ErgebnisManagement management = new ErgebnisManagement();
		management.DeleteErgebnis("1", "1");
		management.SaveErgebnis("1", "1", "1", "PI", false, (new Date()).toString());
		management.SaveErgebnis("1", "1", "1", "PI/2", true, (new Date()).toString());
		List<Ergebnis> ergebnisse;
		try
		{
			ergebnisse = management.ReadAllErgebnisse();
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
		for (Ergebnis ergebnis : ergebnisse)
		{
			out += "Ergebnis: ";
			out += "\n TeamNr: " + ergebnis.TeamNr;
			out += "\n AufgabenNr: " + ergebnis.AufgabeNr;
			out += "\n RechnerNr: " + ergebnis.RechnerNr;
			out += "\n Eingabe: " + ergebnis.Eingabe;
			out += "\n Zeitstempel: " + ergebnis.Zeitstempel;
			out += "\n Bestanden: " + ergebnis.Bestanden;
		}
		return out;
	}
}
