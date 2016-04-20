package meinbeanpackage;

import management.LaborblattManagement;

public class LaborblattBean
{
	public String doAction(){
		LaborblattManagement management = new LaborblattManagement();
		management.DeleteLaborBlatt("1");
		management.InsertLaborBlatt("1");
		management.DeleteLaborBlatt("2");
		management.InsertLaborBlatt("2");
		return management.ReadAllLaborBlaetter();
	}
}
