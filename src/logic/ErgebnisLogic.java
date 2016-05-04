package logic;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
	public List<String> HandleErgebnis(String teamNr, String aufgabeNr, String rechnerNr, String eingabe)
			throws DataBasePathNotFoundException, NoAccessToDataBaseException
	{
		List<String> message = new ArrayList<String>();
		RechnerManagement rechnerManagement = new RechnerManagement();
		Rechner rechner = rechnerManagement.GetRechner_by_RechnerNr(rechnerNr);
		if (rechner.RechnerNr == null)
		{
			message.add("Rechner with RechnerNr " + rechnerNr + " does not exist. Please check your Login");
		} else
		{
			LoesungManagement loesungManagement = new LoesungManagement();
			Loesung loesung = loesungManagement.GetLoesung_by_RechnerNr_and_AufgabeNr(aufgabeNr, rechnerNr);
			if (loesung.AufgabeNr == null)
			{
				message.add("Loesung with RechnerNr " + rechnerNr + " and AufgabeNr " + aufgabeNr + " does not exist");
			} else
			{
				boolean bestanden = Compare_Ergebnis_to_Loesung(loesung.Loesung, eingabe);
				ErgebnisManagement ergebnisManagement = new ErgebnisManagement();
				Ergebnis ergebnis = ergebnisManagement.GetErgebnis_by_AufgabeNr_and_TeamNr(aufgabeNr, teamNr);
				if (ergebnis.AufgabeNr == null || ergebnis.TeamNr == null)
				{
					message.add(ergebnisManagement.SaveErgebnis(teamNr, aufgabeNr, rechnerNr, eingabe, bestanden,
							(new Date()).toString()));
				} else
				{
					if (ergebnis.Bestanden == false)
					{
						ergebnisManagement.DeleteErgebnis(teamNr, aufgabeNr);
						message.add(ergebnisManagement.SaveErgebnis(teamNr, aufgabeNr, rechnerNr, eingabe, bestanden,
								(new Date()).toString()));
					} else
					{
						bestanden = true;
						message.add("You already passed that Labtask - There's no Need to change the result");
					}
				}
				message.add("Passed = " + bestanden);
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
