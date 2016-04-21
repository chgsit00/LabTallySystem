package beans;

import management.AufgabeManagement;

public class AufgabeBean
{
	public String doAction(){
		AufgabeManagement management = new AufgabeManagement();
		management.DeleteAufgabe("1");
		management.SaveAufgabe("1", "Hans Peter ist da !");
		management.SaveAufgabe("1", "2", "Sei lieb zu Philip");
		return management.ReadAllAufgaben();
	}
}
