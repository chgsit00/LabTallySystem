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
import objects.AufgabenDisplay;
import objects.Ergebnis;
import objects.Team;
import objects.TeamOverview;

public class TeamOverviewLogic
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

	public List<TeamOverview> GetTeamOverview()
			throws DataBasePathNotFoundException, NoAccessToDataBaseException, SQLException
	{
		List<TeamOverview> teamOverviewList = new ArrayList<TeamOverview>();
		AufgabeManagement aufgabeManagement = new AufgabeManagement();
		List<Aufgabe> aufgaben = aufgabeManagement.ReadAllAufgaben();
		TeamManagement teamManagement = new TeamManagement();
		List<Team> teams = teamManagement.ReadAllTeam();
		ErgebnisManagement ergebnisManagement = new ErgebnisManagement();
		List<Ergebnis> ergebnisse = ergebnisManagement.ReadAllErgebnisse();
		for (Team team : teams)
		{
			List<Ergebnis> teamErgebnisse = GetErgebnisse_forTeamNr(team.TeamNr, ergebnisse);
			int bestandeneAufgaben = GetSolvedTasks_for_Team(teamErgebnisse);
			LocalDateTime localDateTime = GetNewestTimestamp_for_Team(teamErgebnisse);
			List<AufgabenDisplay> aufgabentexte = new ArrayList<AufgabenDisplay>();
			for (Aufgabe aufgabe : aufgaben)
			{
				boolean bestanden = GetBestanden_forAufgabeNr_and_TeamNr(aufgabe.AufgabeNr, team.TeamNr);
				AufgabenDisplay aufgabenDisplay = new AufgabenDisplay(aufgabe.AufgabeNr, aufgabe.AufgabeText,
						bestanden);
				aufgabentexte.add(aufgabenDisplay);
			}
			teamOverviewList.add(new TeamOverview(team.TeamNr, "" + bestandeneAufgaben, aufgabentexte, localDateTime));
		}
		Collections.sort(teamOverviewList, new Comparator<TeamOverview>()
		{
			@Override
			public int compare(TeamOverview overView2, TeamOverview overView1)
			{
				int c;
				c = overView1.BestandeneAufgabeCount.compareTo(overView2.BestandeneAufgabeCount);
				if (c == 0)
					if (overView1.localDateTime.isAfter(overView2.localDateTime))
					{
						c = -1;
					} else if (overView1.localDateTime.isEqual(overView2.localDateTime))
					{
						c = 0;
					} else if (overView1.localDateTime.isBefore(overView2.localDateTime))
					{
						c = 1;
					}
				return c;
			}
		});
		return teamOverviewList;
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