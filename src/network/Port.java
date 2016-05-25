package network;

public class Port
{
	private final int Port;
	private final boolean isOpen;

	public Port(int port, boolean isOpen)
	{
		this.Port = port;
		this.isOpen = isOpen;
	}

	public boolean isOpen()
	{
		return isOpen;
	}

	public int getPort()
	{
		return Port;
	}
}
