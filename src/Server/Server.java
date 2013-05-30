package Server;

import googleShoppingAPI.FindItemGoogleShopping;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	ServerSocket server = null;
	Socket client = null;
	PrintWriter out = null;
	String line = null;
	BufferedReader in = null;
	APIConnector apiconn;
	
	public void listenSocket() throws Exception 
	{
		try 
		{
			server = new ServerSocket(4321);
			System.out.println("Listening on port 4321");
			
		}
		catch (IOException e) 
		{
			System.out.println("Could not listen on port 4321");
			System.out.println(e.getMessage());
			System.exit(-1);
		}
		try {
			client = server.accept();
		}
		catch (IOException e) 
		{
			System.out.println ("Accept failed: 4321");
			System.exit(-1);
		}
		
		try 
		{
			in = new BufferedReader (new InputStreamReader(client.getInputStream()));
			out = new PrintWriter (client.getOutputStream(), true);
		}
		catch (IOException e) 
		{
			System.out.println ("Read failed");
			System.exit(-1);
			}
		while (true)
		{
			try 
			{
				line = in.readLine();
				apiconn = new GoogleShoppingAPIConnector();
				apiconn.getItems(line);
				}
				//System.out.println("Server received: "+ line);
			
			catch (IOException e)
			{
				e.printStackTrace();
				
			}
		}
	}

}
