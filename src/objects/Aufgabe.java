package objects;

public class Aufgabe
{
	public String AufgabeNr;
	public String LaborBlattNr;
	public String AufgabeText;
	public String EingabeArt;

	public Aufgabe()
	{

	}

	public Aufgabe(String aufgabeNr, String laborBlattNr, String aufgabeText, String eingabeArt)
	{
		AufgabeNr = aufgabeNr;
		LaborBlattNr = laborBlattNr;
		AufgabeText = aufgabeText;
		EingabeArt = eingabeArt;
	}

	public void setValues(String aufgabeNr, String laborBlattNr, String aufgabeText, String eingabeArt)
	{
		AufgabeNr = aufgabeNr;
		LaborBlattNr = laborBlattNr;
		AufgabeText = aufgabeText;
		EingabeArt = eingabeArt;
	}
}
