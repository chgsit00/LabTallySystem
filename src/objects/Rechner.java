package objects;

public class Rechner
{
	public String RechnerNr;
	public String IPAdresse;
	public String Account;
	public String Passwort;

	public Rechner()
	{

	}

	public Rechner(String rechnerNr, String ipadresse, String account, String passwort)
	{
		RechnerNr = rechnerNr;
		IPAdresse = ipadresse;
		Account = account;
		Passwort = passwort;
	}

	public void setValues(String rechnerNr, String ipadresse, String account, String passwort)
	{
		RechnerNr = rechnerNr;
	}
}
