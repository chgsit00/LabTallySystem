package objects;

public class Team
{
	public String TeamNr;
	public String Passwort;
	
	public Team(){
		
	}

	public Team(String teamNr, String passwort)
	{
		TeamNr = teamNr;
		Passwort = passwort;
	}
	
	public void setValues(String teamNr, String passwort){
		TeamNr = teamNr;
		Passwort = passwort;
	}
}
