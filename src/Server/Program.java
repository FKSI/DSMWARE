package Server;


/**
 * 
 * @author Andrea SMETKO
 * Eurecom DSMWare Project 2013 - Andrea SMETKO - Francois KY
 * Professor Y.ROUDIER
 *
 */

public class Program {
	public static void main (String[] arguments){
		Server s = new Server();
		try {
			s.listenSocket();
		} catch (Exception e) {
			System.out.println("Couldn't launch the server ");
		}
	}

}
