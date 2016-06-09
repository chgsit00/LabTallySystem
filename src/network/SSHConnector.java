package network;

import java.io.IOException;
import java.io.InputStream;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

public class SSHConnector
{
	private String User = "chris";
	private String Password = "";
	private String Host = "192.168.56.101";
	private int Port = 22;
	private String command1 = "ssh -o StrictHostKeyChecking=no 192.168.56.100 uptime ; ssh 192.168.56.101 'bash -s' < ./Desktop/script";

	public SSHConnector(String ipAddress, String userName, String password)
	{
		User = userName;
		Password = password;
		Host = ipAddress;
	}

	public Session EstablishSHHConnection() throws JSchException
	{

		JSch jsch = new JSch();
		Session session = jsch.getSession(User, Host, Port);
		session.setPassword(Password);
		session.setConfig("StrictHostKeyChecking", "no");
		System.out.println("Establishing Connection...");
		session.connect();
		System.out.println("Connection established.");
		return session;
	}

	public String RunScript(Session session) throws JSchException, SftpException, IOException
	{
		// String remoteFile = "/home/john/test.txt";
		// System.out.println("Crating SFTP Channel.");
		// ChannelSftp sftpChannel = (ChannelSftp) session.openChannel("sftp");
		// sftpChannel.connect();
		// System.out.println("SFTP Channel created.");
		//
		// InputStream out = null;
		// out = sftpChannel.get(remoteFile);
		// BufferedReader br = new BufferedReader(new InputStreamReader(out));
		// String line;
		// while ((line = br.readLine()) != null)
		// System.out.println(line);
		// br.close();
		// return br.lines().toString();
		Channel channel = session.openChannel("exec");
		((ChannelExec) channel).setCommand(command1);
		channel.setInputStream(null);
		((ChannelExec) channel).setErrStream(System.err);

		InputStream in = channel.getInputStream();
		channel.connect();
		byte[] tmp = new byte[1024];
		while (true)
		{
			while (in.available() > 0)
			{
				int i = in.read(tmp, 0, 1024);
				if (i < 0)
					break;
				System.out.print(new String(tmp, 0, i));
			}
			if (channel.isClosed())
			{
				System.out.println("exit-status: " + channel.getExitStatus());
				break;
			}
			try
			{
				Thread.sleep(1000);
			} catch (Exception ee)
			{
			}
		}
		channel.disconnect();
		session.disconnect();
		System.out.println("DONE");
		return "Done";
	}
}
