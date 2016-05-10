package objects;

public class LaborblattViewOutput
{
	public String TeamNr;
	public boolean Bestanden;
	public String Zeitstempel;
	public String AufgabeNr;
	public String AufgabeText;
	public String LaborblattNr;

	public LaborblattViewOutput()
	{

	}

	public LaborblattViewOutput(String teamNr, boolean bestanden, String zeitstempel, String aufgabeNr,
			String aufgabeText, String laborblattNr)
	{
		TeamNr = teamNr;
		Bestanden = bestanden;
		Zeitstempel = zeitstempel;
		AufgabeNr = aufgabeNr;
		AufgabeText = aufgabeText;
		LaborblattNr = laborblattNr;
	}

	public void setValues(String teamNr, boolean bestanden, String zeitstempel, String aufgabeNr, String aufgabeText,
			String laborblattNr)
	{
		TeamNr = teamNr;
		Bestanden = bestanden;
		Zeitstempel = zeitstempel;
		AufgabeNr = aufgabeNr;
		AufgabeText = aufgabeText;
		LaborblattNr = laborblattNr;
	}
}
