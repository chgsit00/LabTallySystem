package beans;

import csv.CSVManagement;

public class CSVBean
{
	public String doAction()
	{
		CSVManagement management = new CSVManagement();
		return management.DeleteAndImportEinteilungFromCSV();
	}
}
