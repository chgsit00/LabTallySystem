package objects;

import java.time.LocalDateTime;
import java.util.List;

public class LaborgruppenViewBestandeneAufgaben
{
	public String TeamNr;
	public String BestandeneAufgabeCount;
	public List<AufgabenDisplayLaborgruppen> Aufgaben;
	public LocalDateTime localDateTime;

	public LaborgruppenViewBestandeneAufgaben()
	{

	}

	public LaborgruppenViewBestandeneAufgaben(String teamNr, String bestandeneAufgabeCount,
			List<AufgabenDisplayLaborgruppen> aufgabentexte, LocalDateTime localDateTime)
	{
		TeamNr = teamNr;
		BestandeneAufgabeCount = bestandeneAufgabeCount;
		Aufgaben = aufgabentexte;
		this.localDateTime = localDateTime;
	}
}
