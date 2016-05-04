package beans;

import java.sql.SQLException;
import java.util.List;

import exceptions.DataBasePathNotFoundException;
import exceptions.NoAccessToDataBaseException;
import logic.ErgebnisLogic;
import management.ErgebnisManagement;
import objects.Ergebnis;

public class ErgebnisBean
{
	public String doAction()
	{
		// management.DeleteErgebnis("1", "1");
		// management.DeleteErgebnis("1", "2");
		// management.DeleteErgebnis("1", "3");
		// management.DeleteErgebnis("1", "4");
		// management.DeleteErgebnis("1", "5");
		// management.DeleteErgebnis("1", "6");
		// management.DeleteErgebnis("1", "7");
		//
		// management.DeleteErgebnis("2", "1");
		// management.DeleteErgebnis("2", "2");
		// management.DeleteErgebnis("2", "3");
		// management.DeleteErgebnis("2", "4");
		//
		// management.DeleteErgebnis("3", "1");
		// management.DeleteErgebnis("3", "2");
		// management.DeleteErgebnis("3", "3");
		//
		// management.DeleteErgebnis("4", "3");
		// management.DeleteErgebnis("4", "4");
		// management.DeleteErgebnis("4", "5");
		// management.DeleteErgebnis("4", "6");
		// management.DeleteErgebnis("4", "7");
		//
		// management.SaveErgebnis("1", "1", "1", "PI", false, (new
		// Date()).toString());
		// management.SaveErgebnis("1", "2", "1", "PI/2", true, (new
		// Date()).toString());
		// management.SaveErgebnis("1", "3", "1", "PI/2", true, (new
		// Date()).toString());
		// management.SaveErgebnis("1", "4", "1", "PI/2", true, (new
		// Date()).toString());
		// management.SaveErgebnis("1", "5", "1", "PI/2", true, (new
		// Date()).toString());
		// management.SaveErgebnis("1", "6", "1", "PI/2", true, (new
		// Date()).toString());
		// management.SaveErgebnis("1", "7", "1", "PI/2", true, (new
		// Date()).toString());
		//
		// management.SaveErgebnis("2", "1", "1", "PI", false, (new
		// Date()).toString());
		// management.SaveErgebnis("2", "2", "1", "PI/2", true, (new
		// Date()).toString());
		// management.SaveErgebnis("2", "3", "1", "PI/2", true, (new
		// Date()).toString());
		// management.SaveErgebnis("2", "4", "1", "PI/2", true, (new
		// Date()).toString());
		//
		// management.SaveErgebnis("3", "1", "1", "PI/2", true, (new
		// Date()).toString());
		// management.SaveErgebnis("3", "2", "1", "PI/2", true, (new
		// Date()).toString());
		// management.SaveErgebnis("3", "3", "1", "PI/2", true, (new
		// Date()).toString());
		//
		// management.SaveErgebnis("4", "3", "1", "PI/2", true, (new
		// Date()).toString());
		// management.SaveErgebnis("4", "4", "1", "PI/2", true, (new
		// Date()).toString());
		// management.SaveErgebnis("4", "5", "1", "PI/2", true, (new
		// Date()).toString());
		// management.SaveErgebnis("4", "6", "1", "PI/2", true, (new
		// Date()).toString());
		// management.SaveErgebnis("4", "7", "1", "PI/2", true, (new
		// Date()).toString());
		List<Ergebnis> ergebnisse;
		ErgebnisManagement management = new ErgebnisManagement();
		ErgebnisLogic logic = new ErgebnisLogic();
		String out = "";
		try
		{
			ergebnisse = management.ReadAllErgebnisse();
			out += logic.HandleErgebnis("2", "1", "2", "192.168.0.1");
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
