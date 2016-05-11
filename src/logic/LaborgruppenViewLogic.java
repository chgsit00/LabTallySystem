package logic;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import exceptions.DataBasePathNotFoundException;
import exceptions.NoAccessToDataBaseException;
import management.AufgabeManagement;
import management.EinteilungManagement;
import management.ErgebnisManagement;
import management.TeamManagement;
import objects.Aufgabe;
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

	public List<LaborgruppenViewBestandeneAufgaben> GetLaborgruppenViewSorted(String slot)
			throws DataBasePathNotFoundException, NoAccessToDataBaseException, SQLException
	{
		List<LaborgruppenViewBestandeneAufgaben> laborgruppenViewBestandeneAufgabenList = new ArrayList<LaborgruppenViewBestandeneAufgaben>();
		List<String> aufgabentexte = new ArrayList<String>();
		AufgabeManagement aufgabeManagement = new AufgabeManagement();
		List<Aufgabe> aufgaben = aufgabeManagement.ReadAllAufgaben();
		TeamManagement teamManagement = new TeamManagement();
		List<Team> teams = teamManagement.ReadAllTeam();
		ErgebnisManagement ergebnisManagement = new ErgebnisManagement();
		List<Ergebnis> ergebnisse = ergebnisManagement.ReadAllErgebnisse();
		EinteilungManagement einteilungManagement = new EinteilungManagement();
		List<Einteilung> einteilungen = einteilungManagement.ReadAllEinteilungen();

		for (Aufgabe aufgabe : aufgaben)
		{
			aufgabentexte.add("Aufgabe: " + aufgabe.AufgabeNr + "  Text:" + aufgabe.AufgabeText);
		}

		List<Team> slotTeams = GetTeams_for_Slot(slot, teams, einteilungen);

		for (Team team : slotTeams)
		{
			List<Ergebnis> teamErgebnisse = GetErgebnisse_forTeamNr(team.TeamNr, ergebnisse);
			int bestandeneAufgaben = GetSolvedTasks_for_Team(teamErgebnisse);
			laborgruppenViewBestandeneAufgabenList
					.add(new LaborgruppenViewBestandeneAufgaben(team.TeamNr, "" + bestandeneAufgaben, aufgabentexte));
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

	// public List<LaborgruppenViewBestandeneAufgaben>
	// GetLaborgruppenViewSorted(String slot)
	// throws DataBasePathNotFoundException, NoAccessToDataBaseException,
	// SQLException
	// {
	// List<LaborgruppenViewBestandeneAufgaben>
	// laborgruppenViewBestandeneAufgabenList = new
	// ArrayList<LaborgruppenViewBestandeneAufgaben>();
	// LaborgruppenViewManagement laborgruppenViewManagement = new
	// LaborgruppenViewManagement();
	// List<LaborgruppenView> laborgruppenViews =
	// laborgruppenViewManagement.ReadAllLaborgruppenViews();
	// AufgabeManagement aufgabeManagement = new AufgabeManagement();
	// List<Aufgabe> aufgaben = aufgabeManagement.ReadAllAufgaben();
	//
	// List<String> aufgabentexte = new ArrayList<String>();
	//
	// for (Aufgabe aufgabe : aufgaben)
	// {
	// String aufgabentext = "Aufgabe: " + aufgabe.AufgabeNr + " Text:" +
	// aufgabe.AufgabeText;
	//
	// if (!aufgabentexte.contains(aufgabentext))
	// {
	// aufgabentexte.add(aufgabentext);
	// }
	// }
	//
	// for (LaborgruppenView laborgruppenView : laborgruppenViews)
	// {
	// boolean teamNrAlreadyExists = false;
	// for (LaborgruppenViewBestandeneAufgaben
	// laborgruppenViewBestandeneAufgaben :
	// laborgruppenViewBestandeneAufgabenList)
	// {
	// if
	// (laborgruppenViewBestandeneAufgaben.TeamNr.equals(laborgruppenView.TeamNr))
	// {
	// teamNrAlreadyExists = true;
	// }
	// }
	// int bestandeneAufgaben =
	// GetSolvedTasks_for_TeamNr_and_Slot(laborgruppenView.TeamNr,
	// laborgruppenViews, slot);
	// if (!teamNrAlreadyExists)
	// {
	// laborgruppenViewBestandeneAufgabenList.add(new
	// LaborgruppenViewBestandeneAufgaben(
	// laborgruppenView.TeamNr, "" + bestandeneAufgaben, aufgabentexte));
	// }
	// }
	//
	// Collections.sort(laborgruppenViewBestandeneAufgabenList, new
	// Comparator<LaborgruppenViewBestandeneAufgaben>()
	// {
	// @Override
	// public int compare(LaborgruppenViewBestandeneAufgaben overView2,
	// LaborgruppenViewBestandeneAufgaben overView1)
	// {
	//
	// return
	// overView1.BestandeneAufgabeCount.compareTo(overView2.BestandeneAufgabeCount);
	// }
	// });
	// return laborgruppenViewBestandeneAufgabenList;
	// }
	//
	// private int GetSolvedTasks_for_TeamNr_and_Slot(String teamNr,
	// List<LaborgruppenView> laborgruppenViews,
	// String slot)
	// {
	// int solvedTasks = 0;
	// for (LaborgruppenView laborblattView : laborgruppenViews)
	// {
	// if (laborblattView.TeamNr.equals(teamNr) && laborblattView.Bestanden ==
	// true
	// && laborblattView.Slot.equals(slot))
	// {
	// solvedTasks++;
	// }
	// }
	// return solvedTasks;
	// }
}
