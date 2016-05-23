package objects;

public class AufgabenDisplayLaborblatt
{
	public String AufgabeNr;
	public String AufgabeText;
	public Boolean Bestanden;

	public AufgabenDisplayLaborblatt(String aufgabeNr, String aufgabeText, Boolean bestanden)
	{
		AufgabeNr = aufgabeNr;
		AufgabeText = aufgabeText;
		Bestanden = bestanden;
	}
}
