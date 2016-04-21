package beans;

import java.util.Date;

import management.ErgebnisManagement;

public class ErgebnisBean
{
	public String doAction(){
		ErgebnisManagement management = new ErgebnisManagement();
		management.DeleteErgebnis("1", "1");
		management.SaveErgebnis("1", "1", "1", "PI", false, (new Date()).toString());
		management.SaveErgebnis("1", "1", "1", "PI/2", true, (new Date()).toString());
		return management.ReadAllErgebnisse();
	}
}
