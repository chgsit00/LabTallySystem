package objects;

public class Aufgabe
{
	public String AufgabeNr;
	public String LaborBlattNr;
	public String AufgabeText;
	
	public Aufgabe(){
		
	}

	public Aufgabe(String aufgabeNr, String laborBlattNr, String aufgabeText)
	{
		AufgabeNr = aufgabeNr;
		LaborBlattNr = laborBlattNr;
		AufgabeText = aufgabeText;
	}
	
	public void setValues(String aufgabeNr, String laborBlattNr, String aufgabeText)
	{
		AufgabeNr = aufgabeNr;
		LaborBlattNr = laborBlattNr;
		AufgabeText = aufgabeText;
	}
}
