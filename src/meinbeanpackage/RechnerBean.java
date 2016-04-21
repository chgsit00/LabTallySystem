package meinbeanpackage;

import management.RechnerManagement;

public class RechnerBean
{
	public String doAction(){
		RechnerManagement management = new RechnerManagement();
		management.DeleteRechner("1");
		management.InsertRechner("1");
	//	management.DeleteRechner("2");
	//	management.InsertRechner("2");
		return management.ReadAllRechner();
	}
}
