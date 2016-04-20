package objects;

public class Laborslot
{
	public String SlotNr;
	public boolean Belegt;
	public String Termin;
	public String LaborblattNr;
	
	public Laborslot(String nr, boolean belegt, String termin, String laborblattNr){
		SlotNr = nr;
		Belegt = belegt;
		Termin = termin;
		LaborblattNr = laborblattNr;
	}
	
	public Laborslot(){
		
	}
	
	public void setValues(String nr, boolean belegt, String termin, String laborblattNr){
		SlotNr = nr;
		Belegt = belegt;
		Termin = termin;
		LaborblattNr = laborblattNr;		
	}
}
