package objects;

public class LaborgruppenView
{
	public String TeamNr;
	public boolean Bestanden;
	public String Zeitstempel;
	public String AufgabeNr;
	public String AufgabeText;
	public String Slot;

	public LaborgruppenView()
	{

	}

	public LaborgruppenView(String teamNr, boolean bestanden, String zeitstempel, String aufgabeNr, String aufgabeText,
			String slot)
	{
		TeamNr = teamNr;
		Bestanden = bestanden;
		Zeitstempel = zeitstempel;
		AufgabeNr = aufgabeNr;
		AufgabeText = aufgabeText;
		Slot = slot;
	}

	public void setValues(String teamNr, boolean bestanden, String zeitstempel, String aufgabeNr, String aufgabeText,
			String slot)
	{
		TeamNr = teamNr;
		Bestanden = bestanden;
		Zeitstempel = zeitstempel;
		AufgabeNr = aufgabeNr;
		AufgabeText = aufgabeText;
		Slot = slot;
	}
}
