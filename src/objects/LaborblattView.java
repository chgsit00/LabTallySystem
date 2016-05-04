package objects;

import java.util.List;

public class LaborblattView
{
	public String LaborblattNr;
	public List<Aufgabe> Aufgaben;

	public LaborblattView(String laborblattNr, List<Aufgabe> aufgaben)
	{
		LaborblattNr = laborblattNr;
		Aufgaben = aufgaben;
	}

}
