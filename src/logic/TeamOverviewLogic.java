package logic;

import java.util.ArrayList;
import java.util.List;

import objects.Ergebnis;
import objects.TeamOverview;

public class TeamOverviewLogic
{
	private int GetSolvedTasks_for_Team(List<Ergebnis> ergebnisList)
	{
		int iterator = 0;
		for (Ergebnis ergebnis : ergebnisList)
		{
			if (ergebnis.Bestanden = true)
			{
				iterator++;
			}
		}
		return iterator;
	}

	public List<TeamOverview> GetTeamOverview()
	{
		List<TeamOverview> teamOverviewList = new ArrayList<TeamOverview>();
		List<String> aufgaben = new ArrayList<String>();
		aufgaben.add("Aufgabe1");
		aufgaben.add("Aufgabe2");
		aufgaben.add("Aufgabe3");
		teamOverviewList.add(new TeamOverview("1", "3", aufgaben));
		teamOverviewList.add(new TeamOverview("2", "5", aufgaben));
		teamOverviewList.add(new TeamOverview("3", "7", aufgaben));
		teamOverviewList.add(new TeamOverview("4", "1", aufgaben));
		return teamOverviewList;
	}
}
