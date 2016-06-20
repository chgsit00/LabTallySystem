package logic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

import exceptions.DataBasePathNotFoundException;
import exceptions.NoAccessToDataBaseException;
import management.RechnerManagement;
import network.SSHConnector;
import objects.Rechner;

public class NetworkLogic
{
	public List<String> RunScriptonRechner(String aufgabeNr, String rechnerNr, String TeamNr)
	{
		List<String> messages = new ArrayList<String>();
		Thread t = new Thread()
		{
			public void run()
			{
				// AufgabeManagement aufgabeManagement = new
				// AufgabeManagement();
				RechnerManagement rechnerManagement = new RechnerManagement();
				try
				{
					Rechner rechner = rechnerManagement.GetRechner_by_RechnerNr(rechnerNr);
					if (rechner.RechnerNr != null)
					{
						SSHConnector connector = new SSHConnector(rechner.IPAdresse, rechner.Account, rechner.Passwort);
						Session session = connector.EstablishSSHConnection();
						connector.RunScript(session,
								"ssh-keygen -f '/home/chris/.ssh/known_hosts' -R HOSTNAME; ssh 192.168.56.101 'bash -s' < ./Desktop/script");
					}
				} catch (JSchException e)
				{
					e.printStackTrace();
				} catch (SftpException e)
				{
					e.printStackTrace();
				} catch (IOException e)
				{
					e.printStackTrace();
				} catch (DataBasePathNotFoundException e)
				{
					e.printStackTrace();
				} catch (NoAccessToDataBaseException e)
				{
					e.printStackTrace();
				}
			}
		};
		t.start();
		return messages;
	}
}
