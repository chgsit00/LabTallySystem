package objects;

public class Loesung
{
	public String Loesung;
	public String AufgabeNr;
	public String RechnerNr;
	
	public Loesung(){
		
	}

	public Loesung(String loesung, String aufgabeNr, String rechnerNr)
	{
		Loesung = loesung;
		AufgabeNr = aufgabeNr;
		RechnerNr = rechnerNr;
	}
	
	public void setValues(String loesung, String aufgabeNr, String rechnerNr)
	{
		Loesung = loesung;
		AufgabeNr = aufgabeNr;
		RechnerNr = rechnerNr;
	}	
}
