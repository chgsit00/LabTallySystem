package beans;

import java.sql.SQLException;
import java.util.List;

import exceptions.DataBasePathNotFoundException;
import exceptions.NoAccessToDataBaseException;
import management.TeamManagement;
import objects.Team;

public class TeamBean
{
	public String doAction()
	{
		TeamManagement management = new TeamManagement();
		management.DeleteTeam("1");
		management.DeleteTeam("2");
		management.DeleteTeam("3");
		management.DeleteTeam("4");
		management.SaveTeam("1");
		management.SaveTeam("2");
		management.SaveTeam("3");
		management.SaveTeam("4");
		management.SaveTeam("1", "passwort");
		String out = "";
		List<Team> teams;
		try
		{
			teams = management.ReadAllTeam();
		} catch (DataBasePathNotFoundException e)
		{
			return e.GetMessage();
		} catch (NoAccessToDataBaseException e)
		{
			return e.GetMessage();
		} catch (SQLException e)
		{
			return e.getMessage();
		}
		for (Team team : teams)
		{
			out += "\n Team: ";
			out += "\n TeamNr: " + team.TeamNr;
			out += "\n Passwort: " + team.Passwort;
		}
		return out;
	}
}
