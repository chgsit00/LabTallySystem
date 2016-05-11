package objects;

import java.util.List;

public class LaborgruppenViewBestandeneAufgaben
{
	public String TeamNr;
	public String BestandeneAufgabeCount;
	public List<String> Aufgaben;

	public LaborgruppenViewBestandeneAufgaben()
	{

	}

	public LaborgruppenViewBestandeneAufgaben(String teamNr, String bestandeneAufgabeCount, List<String> aufgabentexte)
	{
		TeamNr = teamNr;
		BestandeneAufgabeCount = bestandeneAufgabeCount;
		Aufgaben = aufgabentexte;
	}
}
