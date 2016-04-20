package objects;

public class Laborslot
{
	public String SlotNr;
	public boolean Belegt;
	public String Termin;
	
	public Laborslot(String nr, boolean belegt, String termin){
		SlotNr = nr;
		Belegt = belegt;
		Termin = termin;
	}
	
	public Laborslot(){
		
	}
	
	public void setValues(String nr, boolean belegt, String termin){
		SlotNr = nr;
		Belegt = belegt;
		Termin = termin;
	}
}
