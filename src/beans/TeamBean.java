package beans;

import management.TeamManagement;

public class TeamBean
{
	public String doAction(){
		TeamManagement management = new TeamManagement();
		management.DeleteTeam("1");
		management.SaveTeam("1");
		management.SaveTeam("1", "passwort");
		return management.ReadAllTeam();
	}
}
