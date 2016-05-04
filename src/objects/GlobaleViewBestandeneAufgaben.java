package objects;

import java.util.List;

public class GlobaleViewBestandeneAufgaben
{
	public String TeamNr;
	public String BestandeneAufgabeCount;
	public List<String> Aufgaben;

	public GlobaleViewBestandeneAufgaben()
	{

	}

	public GlobaleViewBestandeneAufgaben(String teamNr, String bestandeneAufgabeCount, List<String> aufgabentexte)
	{
		TeamNr = teamNr;
		BestandeneAufgabeCount = bestandeneAufgabeCount;
		Aufgaben = aufgabentexte;
	}
}