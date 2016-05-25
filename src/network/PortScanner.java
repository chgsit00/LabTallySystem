package network;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.nmap4j.core.flags.ArgumentProperties;
import org.nmap4j.core.scans.BaseScan;

public class PortScanner
{
	public static Future<Port> portIsOpen(final ExecutorService es, final String ip, final int port, final int timeout)
	{
		return es.submit(new Callable<Port>()
		{
			@Override
			public Port call()
			{
				try
				{
					Socket socket = new Socket();
					socket.connect(new InetSocketAddress(ip, port), timeout);
					socket.close();
					return new Port(port, true);
				} catch (Exception ex)
				{
					return new Port(port, false);
				}
			}
		});
	}

	public static List<Future<Port>> ScanPorts(final String ip)
	{
		final ExecutorService es = Executors.newFixedThreadPool(20);
		final int timeout = 1000;
		final List<Future<Port>> futures = new ArrayList<>();
		for (int port = 1; port <= 65535; port++)
		{
			System.out.println("Scanning Port: " + port);
			futures.add(portIsOpen(es, ip, port, timeout));
		}
		es.shutdown();
		return futures;
	}

	private static void PrintPorts(List<Future<Port>> ports)
	{
		int openPorts = 0;
		for (final Future<Port> f : ports)
		{
			try
			{
				if (f.get() != null)
				{
					if (f.get().isOpen())
					{
						openPorts++;
					}
				}
			} catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("There are " + openPorts + " open ports on host ");
	}

	public static void main(final String... args)
	{
		// List<Future<Port>> ports = ScanPorts("192.168.0.1");
		// PrintPorts(ports);
		// SimplePortScan("localhost");
		NMapScan();
	}

	public static void SimplePortScan(final String ip)
	{
		for (int port = 1; port <= 65535; port++)
		{
			try
			{
				Socket socket = new Socket();
				socket.connect(new InetSocketAddress(ip, port), 10000);
				socket.close();
				System.out.println("Port " + port + " is open");
			} catch (Exception ex)
			{
				System.out.println("Port " + port + " is closed");
				System.err.println(ex);
			}
		}
	}

	public static void NMapScan()
	{
		BaseScan scan = new BaseScan();
		scan.addPorts(new int[65535]);
		ArgumentProperties properties = scan.getArgumentProperties();
		String hosts = properties.getIncludedHostsAsString();
		System.out.println(hosts);
	}
}
