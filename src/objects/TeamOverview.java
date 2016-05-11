package objects;

import java.util.List;

public class TeamOverview
{
	public String TeamNr;
	public String BestandeneAufgabeCount;
	public List<String> Aufgaben;

	public TeamOverview(String teamNr, String bestandeneAufgabeCount, List<String> aufgaben)
	{
		TeamNr = teamNr;
		BestandeneAufgabeCount = bestandeneAufgabeCount;
		Aufgaben = aufgaben;
	}
}
