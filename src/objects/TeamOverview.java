package objects;

import java.util.List;

public class TeamOverview
{
	public String TeamNr;
	public String BestandeneAufgabeCount;
	public List<AufgabenDisplay> Aufgaben;

	public TeamOverview(String teamNr, String bestandeneAufgabeCount, List<AufgabenDisplay> aufgaben)
	{
		TeamNr = teamNr;
		BestandeneAufgabeCount = bestandeneAufgabeCount;
		Aufgaben = aufgaben;
	}
}
