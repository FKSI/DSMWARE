package Server;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.io.IOException;

import com.ebay.services.finding.SearchItem;

import eBayAPI.FindItem;
import googleShoppingAPI.FindItemGoogleShopping;
public class Server {
	ServerSocket server = null;
	Socket client = null;
	PrintWriter out = null;
	String line = null;
	BufferedReader in = null;
	APIConnector apiconn;

	public void listenSocket() throws IOException 
	{
		List<String> res = new ArrayList<String>();
		try 
		{
			server = new ServerSocket(4444);
			System.out.println("Listening on port 4444");

		}
		catch (IOException e) 
		{
			System.out.println("Could not listen on port 4444");
			System.out.println(e.getMessage());
			System.exit(-1);
		}
		try {
			client = server.accept();
		}
		catch (IOException e) 
		{
			System.out.println ("Accept failed: 4444");
			System.exit(-1);
		}

		try 
		{
			this.in = new BufferedReader (new InputStreamReader(client.getInputStream()));
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
					line = this.in.readLine();
					
					apiconn = new GoogleShoppingAPIConnector();
					res = apiconn.getItems(line);
					apiconn = new BestBuyAPIConnector();
					res.addAll(apiconn.getItems(line));
					Collections.sort(res);
					
					for (int i = 0; i < res.size(); i++) 
					{
						
						out.println(res.get(i));
					}
					
					out.println("\u0004");
					out.flush();

			}


			catch (Exception e)
			{
				//e.printStackTrace();
				server.close();
				listenSocket();
				//System.out.println(line);

			}
		}
	}

}
