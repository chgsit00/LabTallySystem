package objects;

import java.time.LocalDateTime;
import java.util.List;

public class LaborblattViewBestandeneAufgaben
{
	public String TeamNr;
	public String BestandeneAufgabeCount;
	public List<AufgabenDisplayLaborblatt> Aufgaben;
	public LocalDateTime localDateTime;

	public LaborblattViewBestandeneAufgaben()
	{

	}

	public LaborblattViewBestandeneAufgaben(String teamNr, String bestandeneAufgabeCount,
			List<AufgabenDisplayLaborblatt> aufgabentexte, LocalDateTime localDateTime)
	{
		TeamNr = teamNr;
		BestandeneAufgabeCount = bestandeneAufgabeCount;
		Aufgaben = aufgabentexte;
		this.localDateTime = localDateTime;
	}
}
