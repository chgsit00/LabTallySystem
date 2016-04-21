package objects;

public class Einteilung
{
	public String TeamNr;
	public String Slot;
	
	public Einteilung(){
		
	}

	public Einteilung(String teamNr, String slot)
	{
		TeamNr = teamNr;
		Slot = slot;
	}
	
	public void setValues(String teamNr, String slot)
	{
		TeamNr = teamNr;
		Slot = slot;
	}
}
