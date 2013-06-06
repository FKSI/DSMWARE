package Client;
import java.awt.Desktop;
import java.io.*;
import java.net.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConcreteClient {

	/**
	 * @param args
	 * @throws InterruptedException
	 */
	/**
	 * @param args
	 * @throws InterruptedException
	 */
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
			//e.printStackTrace();
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


			File f = new File("index.html");
			BufferedWriter bw = new BufferedWriter(new FileWriter(f));
			bw.write("<html>");
			bw.write("<head>");
			bw.write("<meta charset='utf-8'>");
			bw.write("<title>* DSMWare *</title>");
			bw.write("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
			bw.write("<link href='bootstrap/css/bootstrap.css' rel='stylesheet'>");
			bw.write("<link href='css/flat-ui.css' rel='stylesheet'>");
			bw.write("</head>");
			bw.write("<body>");
			bw.write("<div class='demo-headline'><h1 class='demo-logo'>DSMWare Project - Spring 2013<small>SMETKO Andrea - KY Francois</small></h1></div>");
			bw.write("<div class='container'>");
			bw.write("<div class='span11'>");
			bw.write("<h3 class='demo-panel-title'>You search is: </h3>");
			bw.write("<input name='tagsinput' id='tagsinput' class='tagsinput' value='" + searchedItem.replace(' ', ',') + "' />");
			bw.write("</div>");
			bw.write("<center><div class='span11'><a href='#Bot' class='btn btn-large btn-block btn-info'>Go to the bottom of the list </a></div></center>");
			bw.write("<a name='Top'></a>");
			bw.write("</br>");
			bw.write("</br>");
			bw.write("<div class='span11'>");
			bw.write("<div class='demo-text-box prl'>");

			while (!(line = in.readLine()).equals("\u0004")) {

				bw.write("<div class='fui-radio-unchecked'></div>");
				bw.write(" "+line);
				bw.write("</br>");
				bw.write("</br>");
				//bw.newLine();
			}

			bw.write("</div>");

			
			bw.write("</div>");
			bw.write("<center><div class='span11'><a href='#Top' class='btn btn-large btn-block btn-primary'>Go to the top of the list </a></div></center>");
			bw.write("</div>");


			bw.write("<a name='Bot'></a>");
			bw.write("<script src='js/jquery-1.8.3.min.js'></script><script src='js/jquery-ui-1.10.3.custom.min.js'></script><script src='js/bootstrap.min.js'></script><script src='js/bootstrap-select.js'></script><script src='js/bootstrap-switch.js'></script><script src='js/jquery.tagsinput.js'></script><script src='js/application.js'></script>");
			
			bw.write("</body>");
			bw.write("</html>");
			bw.close();

			Desktop.getDesktop().browse(f.toURI());

			in.close();
			//socket.close();
			//System.out.println("Text received: " + line);
		} catch (IOException e){
			System.out.println("Read failed");

			// System.exit(1);
		}		      



	}
}
