package objects;

public class Ergebnis
{
	public String TeamNr;
	public String AufgabeNr;
	public String RechnerNr;
	public String Eingabe;
	public boolean Bestanden;
	public String Zeitstempel;
	
	public Ergebnis(){
		
	}

	public Ergebnis(String teamNr, String aufgabeNr, String rechnerNr, String eingabe, boolean bestanden, String zeitstempel)
	{
		TeamNr = teamNr;
		AufgabeNr = aufgabeNr;
		RechnerNr = rechnerNr;
		Eingabe = eingabe;
		Bestanden = bestanden;
		Zeitstempel = zeitstempel;
	}
	
	public void setValues(String teamNr, String aufgabeNr, String rechnerNr, String eingabe, boolean bestanden, String zeitstempel)
	{
		TeamNr = teamNr;
		AufgabeNr = aufgabeNr;
		RechnerNr = rechnerNr;
		Eingabe = eingabe;
		Bestanden = bestanden;
		Zeitstempel = zeitstempel;
	}
}
