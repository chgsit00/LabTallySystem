package logic;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import exceptions.DataBasePathNotFoundException;
import exceptions.NoAccessToDataBaseException;
import management.AufgabeManagement;
import management.ErgebnisManagement;
import management.TeamManagement;
import objects.Aufgabe;
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

	public List<LaborblattViewBestandeneAufgaben> GetLaborblattViewSorted(String laborblattNr)
			throws DataBasePathNotFoundException, NoAccessToDataBaseException, SQLException
	{
		List<LaborblattViewBestandeneAufgaben> laborblattViewBestandeneAufgabenList = new ArrayList<LaborblattViewBestandeneAufgaben>();
		List<String> aufgabentexte = new ArrayList<String>();
		AufgabeManagement aufgabeManagement = new AufgabeManagement();
		List<Aufgabe> aufgaben = aufgabeManagement.ReadAllAufgaben();
		TeamManagement teamManagement = new TeamManagement();
		List<Team> teams = teamManagement.ReadAllTeam();
		ErgebnisManagement ergebnisManagement = new ErgebnisManagement();
		List<Ergebnis> ergebnisse = ergebnisManagement.ReadAllErgebnisse();
		for (Aufgabe aufgabe : aufgaben)
		{
			if (aufgabe.LaborBlattNr.equals(laborblattNr))
			{
				aufgabentexte.add("Aufgabe: " + aufgabe.AufgabeNr + "  Text:" + aufgabe.AufgabeText);
			}
		}

		for (Team team : teams)
		{
			List<Ergebnis> teamErgebnisse = GetErgebnisse_forTeamNr_and_LaborblattNr(team.TeamNr, ergebnisse, aufgaben,
					laborblattNr);
			int bestandeneAufgaben = GetSolvedTasks_for_Team(teamErgebnisse);
			laborblattViewBestandeneAufgabenList
					.add(new LaborblattViewBestandeneAufgaben(team.TeamNr, "" + bestandeneAufgaben, aufgabentexte));
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

	// public List<LaborblattViewBestandeneAufgaben>
	// GetLaborblattViewSorted(String laborblattNr)
	// throws DataBasePathNotFoundException, NoAccessToDataBaseException,
	// SQLException
	// {
	// List<LaborblattViewBestandeneAufgaben>
	// laborblattViewBestandeneAufgabenList = new
	// ArrayList<LaborblattViewBestandeneAufgaben>();
	// LaborblattViewManagement laborblattViewManagement = new
	// LaborblattViewManagement();
	// List<LaborblattViewOutput> laborblattViews =
	// laborblattViewManagement.ReadAllLaborblattViewOutputs();
	// AufgabeManagement aufgabeManagement = new AufgabeManagement();
	// List<Aufgabe> aufgaben = aufgabeManagement.ReadAllAufgaben();
	//
	// List<String> aufgabentexte = new ArrayList<String>();
	//
	// for (Aufgabe aufgabe : aufgaben)
	// {
	// if (aufgabe.LaborBlattNr.equals(laborblattNr))
	// {
	// String aufgabentext = "Aufgabe: " + aufgabe.AufgabeNr + " Text:" +
	// aufgabe.AufgabeText;
	//
	// if (!aufgabentexte.contains(aufgabentext))
	// {
	// aufgabentexte.add(aufgabentext);
	// }
	// }
	// }
	//
	// for (LaborblattViewOutput laborblattView : laborblattViews)
	// {
	// boolean teamNrAlreadyExists = false;
	// for (LaborblattViewBestandeneAufgaben laborblattViewBestandeneAufgaben :
	// laborblattViewBestandeneAufgabenList)
	// {
	// if
	// (laborblattViewBestandeneAufgaben.TeamNr.equals(laborblattView.TeamNr))
	// {
	// teamNrAlreadyExists = true;
	// }
	// }
	// int bestandeneAufgaben =
	// GetSolvedTasks_for_TeamNr_and_LaborblattNr(laborblattView.TeamNr,
	// laborblattViews,
	// laborblattNr);
	// if (!teamNrAlreadyExists)
	// {
	// laborblattViewBestandeneAufgabenList.add(new
	// LaborblattViewBestandeneAufgaben(laborblattView.TeamNr,
	// "" + bestandeneAufgaben, aufgabentexte));
	// }
	// }
	//
	// Collections.sort(laborblattViewBestandeneAufgabenList, new
	// Comparator<LaborblattViewBestandeneAufgaben>()
	// {
	// @Override
	// public int compare(LaborblattViewBestandeneAufgaben overView2,
	// LaborblattViewBestandeneAufgaben overView1)
	// {
	//
	// return
	// overView1.BestandeneAufgabeCount.compareTo(overView2.BestandeneAufgabeCount);
	// }
	// });
	// return laborblattViewBestandeneAufgabenList;
	// }
	//
	// private int GetSolvedTasks_for_TeamNr_and_LaborblattNr(String teamNr,
	// List<LaborblattViewOutput> laborblattViews,
	// String laborblattNr)
	// {
	// int solvedTasks = 0;
	// for (LaborblattViewOutput laborblattView : laborblattViews)
	// {
	// if (laborblattView.TeamNr.equals(teamNr) && laborblattView.Bestanden ==
	// true
	// && laborblattView.LaborblattNr.equals(laborblattNr))
	// {
	// solvedTasks++;
	// }
	// }
	// return solvedTasks;
	// }
}