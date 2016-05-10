package objects;

import java.util.List;

public class LaborblattViewBestandeneAufgaben
{
	public String TeamNr;
	public String BestandeneAufgabeCount;
	public List<String> Aufgaben;

	public LaborblattViewBestandeneAufgaben()
	{

	}

	public LaborblattViewBestandeneAufgaben(String teamNr, String bestandeneAufgabeCount, List<String> aufgabentexte)
	{
		TeamNr = teamNr;
		BestandeneAufgabeCount = bestandeneAufgabeCount;
		Aufgaben = aufgabentexte;
	}
}
