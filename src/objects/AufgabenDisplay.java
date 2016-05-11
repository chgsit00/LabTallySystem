package objects;

public class AufgabenDisplay
{
	public String AufgabeNr;
	public String AufgabeText;
	public Boolean Bestanden;

	public AufgabenDisplay(String aufgabeNr, String aufgabeText, Boolean bestanden)
	{
		AufgabeNr = aufgabeNr;
		AufgabeText = aufgabeText;
		Bestanden = bestanden;
	}
}
