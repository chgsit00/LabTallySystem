package logic;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import exceptions.DataBasePathNotFoundException;
import exceptions.NoAccessToDataBaseException;
import management.AufgabeManagement;
import management.GlobaleViewManagement;
import objects.Aufgabe;
import objects.GlobaleView;
import objects.GlobaleViewBestandeneAufgaben;

public class GlobaleViewLogic
{

	public List<GlobaleViewBestandeneAufgaben> GetGlobaleViewSorted()
			throws DataBasePathNotFoundException, NoAccessToDataBaseException, SQLException
	{
		List<GlobaleViewBestandeneAufgaben> globaleViewBestandeneAufgabenList = new ArrayList<GlobaleViewBestandeneAufgaben>();
		GlobaleViewManagement globaleViewManagement = new GlobaleViewManagement();
		List<GlobaleView> globaleViews = globaleViewManagement.ReadAllGlobaleViews();
		AufgabeManagement aufgabeManagement = new AufgabeManagement();
		List<Aufgabe> aufgaben = aufgabeManagement.ReadAllAufgaben();

		List<String> aufgabentexte = new ArrayList<String>();

		for (Aufgabe aufgabe : aufgaben)
		{
			String aufgabentext = "Aufgabe: " + aufgabe.AufgabeNr + " Text:" + aufgabe.AufgabeText;

			if (!aufgabentexte.contains(aufgabentext))
			{
				aufgabentexte.add(aufgabentext);
			}
		}

		for (GlobaleView globaleView : globaleViews)
		{
			boolean teamNrAlreadyExists = false;
			for (GlobaleViewBestandeneAufgaben globaleViewBestandeneAufgaben : globaleViewBestandeneAufgabenList)
			{
				if (globaleViewBestandeneAufgaben.TeamNr.equals(globaleView.TeamNr))
				{
					teamNrAlreadyExists = true;
				}
			}
			int bestandeneAufgaben = GetSolvedTasks_for_TeamNr(globaleView.TeamNr, globaleViews);
			if (!teamNrAlreadyExists)
			{
				globaleViewBestandeneAufgabenList.add(
						new GlobaleViewBestandeneAufgaben(globaleView.TeamNr, "" + bestandeneAufgaben, aufgabentexte));
			}
		}

		Collections.sort(globaleViewBestandeneAufgabenList, new Comparator<GlobaleViewBestandeneAufgaben>()
		{
			@Override
			public int compare(GlobaleViewBestandeneAufgaben overView2, GlobaleViewBestandeneAufgaben overView1)
			{

				return overView1.BestandeneAufgabeCount.compareTo(overView2.BestandeneAufgabeCount);
			}
		});
		return globaleViewBestandeneAufgabenList;
	}

	private int GetSolvedTasks_for_TeamNr(String teamNr, List<GlobaleView> globaleViews)
	{
		int solvedTasks = 0;
		for (GlobaleView globaleView : globaleViews)
		{
			if (globaleView.TeamNr.equals(teamNr) && globaleView.Bestanden == true)
			{
				solvedTasks++;
			}
		}
		return solvedTasks;
	}
}