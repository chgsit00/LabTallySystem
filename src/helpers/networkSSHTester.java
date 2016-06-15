package helpers;

import java.io.IOException;

import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

import network.SSHConnector;

public class networkSSHTester
{
	public static void main(final String... args)
	{
		SSHConnector connector = new SSHConnector("192.168.56.101", "chris", "gabawillhaba");

		Thread t = new Thread()
		{
			public void run()
			{
				try
				{
					Session session = connector.EstablishSHHConnection();
					connector.RunScript(session);
				} catch (JSchException e)
				{
					e.printStackTrace();
				} catch (SftpException e)
				{
					e.printStackTrace();
				} catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		};
		t.start();
	}
}
