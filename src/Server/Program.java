package Server;

public class Program {
	public static void main (String[] arguments){
		Server s = new Server();
		try {
			s.listenSocket();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
