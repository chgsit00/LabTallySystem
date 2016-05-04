package passwordgenerator;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import exceptions.DataBasePathNotFoundException;
import exceptions.NoAccessToDataBaseException;
import management.TeamManagement;
import objects.Team;

public class PasswordGenerator
{

	private static String GenerateRandomString()
	{
		String random = UUID.randomUUID().toString();
		String[] parts = random.split("-");
		return parts[0];
	}

	private static List<String> GeneratePasswords_for_DataBase()
			throws DataBasePathNotFoundException, NoAccessToDataBaseException, SQLException
	{
		TeamManagement teamManagement = new TeamManagement();
		List<Team> teams = teamManagement.ReadAllTeam();
		List<String> teamsandPasswords = new ArrayList<String>();
		for (Team team : teams)
		{
			String password = GenerateRandomString();
			teamManagement.SaveTeam(team.TeamNr, password);
			teamsandPasswords.add(team.TeamNr + ";" + password);
		}
		return teamsandPasswords;
	}

	public static void GenerateTextFile(List<String> teamsandPasswords) throws IOException
	{
		Path file = Paths.get("teams-and-passwords.txt");
		Files.write(file, teamsandPasswords, Charset.forName("UTF-8"));
	}

	public static void main(String[] args)
	{
		List<String> teamsandPasswords;
		try
		{
			teamsandPasswords = GeneratePasswords_for_DataBase();
			GenerateTextFile(teamsandPasswords);
		} catch (DataBasePathNotFoundException | NoAccessToDataBaseException | SQLException | IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
