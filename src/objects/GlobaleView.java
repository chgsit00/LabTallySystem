package objects;

public class GlobaleView
{
	public String TeamNr;
	public boolean Bestanden;
	public String Zeitstempel;
	public String AufgabeNr;
	public String AufgabeText;

	public GlobaleView()
	{

	}

	public GlobaleView(String teamNr, boolean bestanden, String zeitstempel, String aufgabeNr, String aufgabeText)
	{
		TeamNr = teamNr;
		Bestanden = bestanden;
		Zeitstempel = zeitstempel;
		AufgabeNr = aufgabeNr;
		AufgabeText = aufgabeText;
	}

	public void setValues(String teamNr, boolean bestanden, String zeitstempel, String aufgabeNr, String aufgabeText)
	{
		TeamNr = teamNr;
		Bestanden = bestanden;
		Zeitstempel = zeitstempel;
		AufgabeNr = aufgabeNr;
		AufgabeText = aufgabeText;
	}
}