package objects;

import java.time.LocalDateTime;
import java.util.List;

public class TeamOverview
{
	public String TeamNr;
	public String BestandeneAufgabeCount;
	public List<AufgabenDisplay> Aufgaben;
	public LocalDateTime localDateTime;

	public TeamOverview(String teamNr, String bestandeneAufgabeCount, List<AufgabenDisplay> aufgaben,
			LocalDateTime localDateTime)
	{
		TeamNr = teamNr;
		BestandeneAufgabeCount = bestandeneAufgabeCount;
		Aufgaben = aufgaben;
		this.localDateTime = localDateTime;
	}
}
