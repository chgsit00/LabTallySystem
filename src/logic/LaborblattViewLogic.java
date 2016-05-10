package logic;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import exceptions.DataBasePathNotFoundException;
import exceptions.NoAccessToDataBaseException;
import management.AufgabeManagement;
import management.LaborblattViewManagement;
import objects.Aufgabe;
import objects.LaborblattViewBestandeneAufgaben;
import objects.LaborblattViewOutput;

public class LaborblattViewLogic
{
	public List<LaborblattViewBestandeneAufgaben> GetLaborblattViewSorted(String laborblattNr)
			throws DataBasePathNotFoundException, NoAccessToDataBaseException, SQLException
	{
		List<LaborblattViewBestandeneAufgaben> laborblattViewBestandeneAufgabenList = new ArrayList<LaborblattViewBestandeneAufgaben>();
		LaborblattViewManagement laborblattViewManagement = new LaborblattViewManagement();
		List<LaborblattViewOutput> laborblattViews = laborblattViewManagement.ReadAllLaborblattViewOutputs();
		AufgabeManagement aufgabeManagement = new AufgabeManagement();
		List<Aufgabe> aufgaben = aufgabeManagement.ReadAllAufgaben();

		List<String> aufgabentexte = new ArrayList<String>();

		for (Aufgabe aufgabe : aufgaben)
		{
			if (aufgabe.LaborBlattNr.equals(laborblattNr))
			{
				String aufgabentext = "Aufgabe: " + aufgabe.AufgabeNr + " Text:" + aufgabe.AufgabeText;

				if (!aufgabentexte.contains(aufgabentext))
				{
					aufgabentexte.add(aufgabentext);
				}
			}
		}

		for (LaborblattViewOutput laborblattView : laborblattViews)
		{
			boolean teamNrAlreadyExists = false;
			for (LaborblattViewBestandeneAufgaben laborblattViewBestandeneAufgaben : laborblattViewBestandeneAufgabenList)
			{
				if (laborblattViewBestandeneAufgaben.TeamNr.equals(laborblattView.TeamNr))
				{
					teamNrAlreadyExists = true;
				}
			}
			int bestandeneAufgaben = GetSolvedTasks_for_TeamNr_and_LaborblattNr(laborblattView.TeamNr, laborblattViews,
					laborblattNr);
			if (!teamNrAlreadyExists)
			{
				laborblattViewBestandeneAufgabenList.add(new LaborblattViewBestandeneAufgaben(laborblattView.TeamNr,
						"" + bestandeneAufgaben, aufgabentexte));
			}
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

	private int GetSolvedTasks_for_TeamNr_and_LaborblattNr(String teamNr, List<LaborblattViewOutput> laborblattViews,
			String laborblattNr)
	{
		int solvedTasks = 0;
		for (LaborblattViewOutput laborblattView : laborblattViews)
		{
			if (laborblattView.TeamNr.equals(teamNr) && laborblattView.Bestanden == true
					&& laborblattView.LaborblattNr.equals(laborblattNr))
			{
				solvedTasks++;
			}
		}
		return solvedTasks;
	}
}