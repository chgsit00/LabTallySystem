package logic;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import exceptions.DataBasePathNotFoundException;
import exceptions.NoAccessToDataBaseException;
import management.AufgabeManagement;
import management.LaborblattManagement;
import objects.Aufgabe;
import objects.Laborblatt;
import objects.LaborblattView;

public class LaborblattLogic
{
	public List<LaborblattView> GetLaborblattViews()
			throws SQLException, DataBasePathNotFoundException, NoAccessToDataBaseException
	{
		List<LaborblattView> laborblattViews = new ArrayList<LaborblattView>();
		LaborblattManagement laborblattManagement = new LaborblattManagement();
		AufgabeManagement aufgabeManagement = new AufgabeManagement();

		List<Laborblatt> laborblattList = laborblattManagement.ReadAllLaborBlaetter();
		List<Aufgabe> aufgabenList = aufgabeManagement.ReadAllAufgaben();
		for (Laborblatt laborblatt : laborblattList)
		{
			List<Aufgabe> laborblattAufgaben = new ArrayList<Aufgabe>();
			for (Aufgabe aufgabe : aufgabenList)
			{
				if (aufgabe.LaborBlattNr.equals(laborblatt.LaborblattNr))
				{
					laborblattAufgaben.add(aufgabe);
				}
			}
			LaborblattView view = new LaborblattView(laborblatt.LaborblattNr, laborblattAufgaben);
			laborblattViews.add(view);
		}
		return laborblattViews;
	}
}
