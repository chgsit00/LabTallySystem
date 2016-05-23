package logic;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

import exceptions.DataBasePathNotFoundException;
import exceptions.NoAccessToDataBaseException;
import management.AufgabeManagement;
import management.ErgebnisManagement;
import management.TeamManagement;
import objects.Aufgabe;
import objects.AufgabenDisplayLaborblatt;
import objects.Ergebnis;
import objects.LaborblattViewBestandeneAufgaben;
import objects.Team;

public class LaborblattViewLogic
{
	private int GetSolvedTasks_for_Team(List<Ergebnis> ergebnisList)
	{
		int iterator = 0;
		for (Ergebnis ergebnis : ergebnisList)
		{
			if (ergebnis.Bestanden == true)
			{
				iterator++;
			}
		}
		return iterator;
	}

	private LocalDateTime GetNewestTimestamp_for_Team(List<Ergebnis> ergebnisList)
	{
		LocalDateTime timestamp = LocalDateTime.MIN;
		for (Ergebnis ergebnis : ergebnisList)
		{
			if (ergebnis.Bestanden == true)
			{
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
				LocalDateTime date = LocalDateTime.parse(ergebnis.Zeitstempel, formatter);

				if (date.isAfter(timestamp))
				{
					timestamp = date;
				}
			}
		}
		return timestamp;
	}

	public List<LaborblattViewBestandeneAufgaben> GetLaborblattViewSorted(String laborblattNr)
			throws DataBasePathNotFoundException, NoAccessToDataBaseException, SQLException
	{
		List<LaborblattViewBestandeneAufgaben> laborblattViewBestandeneAufgabenList = new ArrayList<LaborblattViewBestandeneAufgaben>();
		AufgabeManagement aufgabeManagement = new AufgabeManagement();
		List<Aufgabe> aufgaben = aufgabeManagement.ReadAllAufgaben();
		TeamManagement teamManagement = new TeamManagement();
		List<Team> teams = teamManagement.ReadAllTeam();
		ErgebnisManagement ergebnisManagement = new ErgebnisManagement();
		List<Ergebnis> ergebnisse = ergebnisManagement.ReadAllErgebnisse();

		for (Team team : teams)
		{
			List<Ergebnis> teamErgebnisse = GetErgebnisse_forTeamNr_and_LaborblattNr(team.TeamNr, ergebnisse, aufgaben,
					laborblattNr);
			int bestandeneAufgaben = GetSolvedTasks_for_Team(teamErgebnisse);
			LocalDateTime localDateTime = GetNewestTimestamp_for_Team(teamErgebnisse);
			List<AufgabenDisplayLaborblatt> aufgabentexte = new ArrayList<AufgabenDisplayLaborblatt>();
			for (Aufgabe aufgabe : aufgaben)
			{
				if (aufgabe.LaborBlattNr.equals(laborblattNr))
				{
					boolean bestanden = GetBestanden_forAufgabeNr_and_TeamNr(aufgabe.AufgabeNr, team.TeamNr);
					AufgabenDisplayLaborblatt aufgabenDisplay = new AufgabenDisplayLaborblatt(aufgabe.AufgabeNr,
							aufgabe.AufgabeText, bestanden);
					aufgabentexte.add(aufgabenDisplay);
				}
			}

			laborblattViewBestandeneAufgabenList.add(new LaborblattViewBestandeneAufgaben(team.TeamNr,
					"" + bestandeneAufgaben, aufgabentexte, localDateTime));
		}
		Collections.sort(laborblattViewBestandeneAufgabenList, new Comparator<LaborblattViewBestandeneAufgaben>()
		{
			@Override
			public int compare(LaborblattViewBestandeneAufgaben overView2, LaborblattViewBestandeneAufgaben overView1)
			{

				return overView1.BestandeneAufgabeCount.compareTo(overView2.BestandeneAufgabeCount);
			}
		});
		return laborblattViewBestandeneAufgabenList;
	}

	private List<Ergebnis> GetErgebnisse_forTeamNr_and_LaborblattNr(String teamNr, List<Ergebnis> ergebnisse,
			List<Aufgabe> aufgaben, String laborblattNr)
	{
		List<Ergebnis> teamErgebnisse = new ArrayList<Ergebnis>();
		for (Ergebnis ergebnis : ergebnisse)
		{
			String ergebnisLaborblattNr = "";
			for (Aufgabe aufgabe : aufgaben)
			{
				if (ergebnis.AufgabeNr.equals(aufgabe.AufgabeNr))
				{
					ergebnisLaborblattNr = aufgabe.LaborBlattNr;
				}
			}
			if (ergebnis.TeamNr.equals(teamNr) && ergebnisLaborblattNr.equals(laborblattNr))
			{
				teamErgebnisse.add(ergebnis);
			}
		}
		return teamErgebnisse;
	}

	private Boolean GetBestanden_forAufgabeNr_and_TeamNr(String aufgabeNr, String teamNr)
			throws DataBasePathNotFoundException, NoAccessToDataBaseException, SQLException
	{
		ErgebnisManagement ergebnisManagement = new ErgebnisManagement();
		List<Ergebnis> ergebnisse = ergebnisManagement.ReadAllErgebnisse();

		boolean bestanden = false;
		for (Ergebnis ergebnis : ergebnisse)
		{
			if (ergebnis.TeamNr.equals(teamNr) && ergebnis.AufgabeNr.equals(aufgabeNr))
			{
				bestanden = ergebnis.Bestanden;
			}
		}
		return bestanden;
	}
}