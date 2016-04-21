package beans;

import management.EinteilungManagement;

public class EinteilungBean
{
	public String doAction(){
		EinteilungManagement management = new EinteilungManagement();
		management.DeleteEinteilung("1", "1");
		management.SaveEinteilung("1", "1");
		return management.ReadAllEinteilungen();
	}
}
