package beans;

import java.sql.SQLException;
import java.util.List;

import exceptions.DataBasePathNotFoundException;
import exceptions.NoAccessToDataBaseException;
import management.GlobaleViewManagement;
import objects.GlobaleView;

public class GlobaleViewBean
{
	public String doAction()
	{
		GlobaleViewManagement management = new GlobaleViewManagement();
		List<GlobaleView> globaleViews;
		try
		{
			globaleViews = management.ReadAllGlobaleViews();
		} catch (DataBasePathNotFoundException e)
		{
			// TODO Auto-generated catch block
			return e.GetMessage();
		} catch (NoAccessToDataBaseException e)
		{
			// TODO Auto-generated catch block
			return e.GetMessage();
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			return e.getMessage();
		}
		String out = "";
		for (GlobaleView globaleView : globaleViews)
		{
			out += "GlobaleView: ";
			out += "\n TeamNr: " + globaleView.TeamNr;
			out += "\n Bestanden: " + globaleView.Bestanden;
			out += "\n Zeitstempel: " + globaleView.Zeitstempel;
			out += "\n AufgabenNr: " + globaleView.AufgabeNr;
			out += "\n AufgabeText: " + globaleView.AufgabeText;
		}
		return out;
	}
}
