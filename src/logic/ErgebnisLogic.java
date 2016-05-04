package logic;

import java.util.Date;

import exceptions.DataBasePathNotFoundException;
import exceptions.NoAccessToDataBaseException;
import management.ErgebnisManagement;
import management.LoesungManagement;
import management.RechnerManagement;
import objects.Ergebnis;
import objects.Loesung;
import objects.Rechner;

public class ErgebnisLogic
{
	public String HandleErgebnis(String teamNr, String aufgabeNr, String rechnerNr, String eingabe)
			throws DataBasePathNotFoundException, NoAccessToDataBaseException
	{
		String message = "";
		RechnerManagement rechnerManagement = new RechnerManagement();
		Rechner rechner = rechnerManagement.GetRechner_by_RechnerNr(rechnerNr);
		if (rechner.RechnerNr == null)
		{
			message = "Rechner with RechnerNr " + rechnerNr + " does not exist. Please check your Login";
		} else
		{
			LoesungManagement loesungManagement = new LoesungManagement();
			Loesung loesung = loesungManagement.GetLoesung_by_RechnerNr_and_AufgabeNr(aufgabeNr, rechnerNr);
			if (loesung.AufgabeNr == null)
			{
				message = "Loesung with RechnerNr " + rechnerNr + " and AufgabeNr " + aufgabeNr + " does not exist";
			} else
			{
				boolean bestanden = Compare_Ergebnis_to_Loesung(loesung.Loesung, eingabe);
				ErgebnisManagement ergebnisManagement = new ErgebnisManagement();
				Ergebnis ergebnis = ergebnisManagement.GetErgebnis_by_AufgabeNr_and_TeamNr(aufgabeNr, teamNr);
				if (ergebnis.AufgabeNr == null || ergebnis.TeamNr == null)
				{
					message = ergebnisManagement.SaveErgebnis(teamNr, aufgabeNr, rechnerNr, eingabe, bestanden,
							(new Date()).toString());
				} else
				{
					if (ergebnis.Bestanden == false)
					{
						ergebnisManagement.DeleteErgebnis(teamNr, aufgabeNr);
						message = ergebnisManagement.SaveErgebnis(teamNr, aufgabeNr, rechnerNr, eingabe, bestanden,
								(new Date()).toString());
					} else
					{
						message = "You already passed that Labtask - There's no Need to change the result";
					}
				}
				message += "Passed = " + bestanden;
			}
		}
		return message;
	}

	private boolean Compare_Ergebnis_to_Loesung(String loesung, String eingabe)
	{
		if (loesung.equals(eingabe))
		{
			return true;
		} else
			return false;
	}
}
