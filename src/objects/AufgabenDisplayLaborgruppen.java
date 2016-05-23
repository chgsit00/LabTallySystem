package objects;

public class AufgabenDisplayLaborgruppen
{
	public String AufgabeNr;
	public String AufgabeText;
	public Boolean Bestanden;

	public AufgabenDisplayLaborgruppen(String aufgabeNr, String aufgabeText, Boolean bestanden)
	{
		AufgabeNr = aufgabeNr;
		AufgabeText = aufgabeText;
		Bestanden = bestanden;
	}
}
