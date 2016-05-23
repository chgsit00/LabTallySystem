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
import management.EinteilungManagement;
import management.ErgebnisManagement;
import management.TeamManagement;
import objects.Aufgabe;
import objects.AufgabenDisplayLaborgruppen;
import objects.Einteilung;
import objects.Ergebnis;
import objects.LaborgruppenViewBestandeneAufgaben;
import objects.Team;

public class LaborgruppenViewLogic
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

	public List<LaborgruppenViewBestandeneAufgaben> GetLaborgruppenViewSorted(String slot)
			throws DataBasePathNotFoundException, NoAccessToDataBaseException, SQLException
	{
		List<LaborgruppenViewBestandeneAufgaben> laborgruppenViewBestandeneAufgabenList = new ArrayList<LaborgruppenViewBestandeneAufgaben>();
		AufgabeManagement aufgabeManagement = new AufgabeManagement();
		List<Aufgabe> aufgaben = aufgabeManagement.ReadAllAufgaben();
		TeamManagement teamManagement = new TeamManagement();
		List<Team> teams = teamManagement.ReadAllTeam();
		ErgebnisManagement ergebnisManagement = new ErgebnisManagement();
		List<Ergebnis> ergebnisse = ergebnisManagement.ReadAllErgebnisse();
		EinteilungManagement einteilungManagement = new EinteilungManagement();
		List<Einteilung> einteilungen = einteilungManagement.ReadAllEinteilungen();

		List<Team> slotTeams = GetTeams_for_Slot(slot, teams, einteilungen);

		for (Team team : slotTeams)
		{
			List<Ergebnis> teamErgebnisse = GetErgebnisse_forTeamNr(team.TeamNr, ergebnisse);
			int bestandeneAufgaben = GetSolvedTasks_for_Team(teamErgebnisse);
			LocalDateTime localDateTime = GetNewestTimestamp_for_Team(teamErgebnisse);
			List<AufgabenDisplayLaborgruppen> aufgabentexte = new ArrayList<AufgabenDisplayLaborgruppen>();
			for (Aufgabe aufgabe : aufgaben)
			{
				boolean bestanden = GetBestanden_forAufgabeNr_and_TeamNr(aufgabe.AufgabeNr, team.TeamNr);
				AufgabenDisplayLaborgruppen aufgabenDisplay = new AufgabenDisplayLaborgruppen(aufgabe.AufgabeNr,
						aufgabe.AufgabeText, bestanden);
				aufgabentexte.add(aufgabenDisplay);
			}

			laborgruppenViewBestandeneAufgabenList.add(new LaborgruppenViewBestandeneAufgaben(team.TeamNr,
					"" + bestandeneAufgaben, aufgabentexte, localDateTime));
		}

		Collections.sort(laborgruppenViewBestandeneAufgabenList, new Comparator<LaborgruppenViewBestandeneAufgaben>()
		{
			@Override
			public int compare(LaborgruppenViewBestandeneAufgaben overView2,
					LaborgruppenViewBestandeneAufgaben overView1)
			{

				return overView1.BestandeneAufgabeCount.compareTo(overView2.BestandeneAufgabeCount);
			}
		});
		return laborgruppenViewBestandeneAufgabenList;
	}

	private List<Team> GetTeams_for_Slot(String slot, List<Team> teams, List<Einteilung> einteilungen)
	{
		List<Team> slotTeams = new ArrayList<Team>();
		for (Team team : teams)
		{
			for (Einteilung einteilung : einteilungen)
			{
				if (team.TeamNr.equals(einteilung.TeamNr) && einteilung.Slot.equals(slot))
				{
					slotTeams.add(team);
				}
			}
		}
		return slotTeams;
	}

	private List<Ergebnis> GetErgebnisse_forTeamNr(String teamNr, List<Ergebnis> ergebnisse)
	{
		List<Ergebnis> teamErgebnisse = new ArrayList<Ergebnis>();
		for (Ergebnis ergebnis : ergebnisse)
		{
			if (ergebnis.TeamNr.equals(teamNr))
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
