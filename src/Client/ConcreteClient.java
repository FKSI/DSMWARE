package Client;
import java.awt.Desktop;
import java.io.*;
import java.net.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConcreteClient {

	public static void main (String args[]) throws InterruptedException
	{		      

		Socket socket = null;
		PrintWriter out = null;
		BufferedReader in = null;
		System.out.println("Enter the item you want to search for : ");
		String searchedItem = null;
		String line = null;

		try{
			BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
			searchedItem = bufferRead.readLine();

			System.out.println(searchedItem);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}

		try{
			socket = new Socket("localhost", 4444);
			System.out.println("Client connected on port 4444");
			out = new PrintWriter(socket.getOutputStream(), 
					true);
			in = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
		} catch (UnknownHostException e) {
			System.out.println("Unknown host: kq6py");
			System.exit(1);
		} catch  (IOException e) {
			System.out.println("No I/O");
			System.exit(1);
		}
		out.println(searchedItem);
		try{


			File f = new File("source.htm");
			BufferedWriter bw = new BufferedWriter(new FileWriter(f));
			bw.write("<html>");
			bw.write("<body>");
			bw.write("<h1>ShowGeneratedHtml source</h1>");
			bw.write("<textarea cols=75 rows=30>");
			
			while (!(line = in.readLine()).equals("\u0004")) {
				//System.out.println(line);
				bw.write(line);
				bw.newLine();
			}

		
			
			bw.write("</text" + "area>");
			bw.write("</body>");
			bw.write("</html>");
			bw.close();

			Desktop.getDesktop().browse(f.toURI());
			
			in.close();

			//System.out.println("Text received: " + line);
		} catch (IOException e){
			System.out.println("Read failed");

			// System.exit(1);
		}		      

		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	 

	}
}
