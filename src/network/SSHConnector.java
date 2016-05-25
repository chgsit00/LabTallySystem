package network;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class SSHConnector
{
	public static void main(String args[])
	{
		String user = "user"; // TODO: Richtigen User eintragen
		String password = "passwort"; // TODO: Richtiges Passwort angeben
		String host = "localhost";
		int port = 22;

		String remoteFile = "/home/bob/secret1.txt";

		try
		{
			JSch jsch = new JSch();
			Session session = jsch.getSession(user, host, port);
			session.setPassword(password);
			session.setConfig("StrictHostKeyChecking", "no");
			System.out.println("Establishing Connection...");
			session.connect();
			System.out.println("Connection established.");
			System.out.println("Crating SFTP Channel.");
			ChannelSftp sftpChannel = (ChannelSftp) session.openChannel("sftp");
			sftpChannel.connect();
			System.out.println("SFTP Channel created.");
		} catch (Exception e)
		{
			System.err.print(e);
		}
	}
}
