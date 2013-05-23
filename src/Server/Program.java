package Server;

public class Program {
	public static void main (String[] arguments){
		Server s = new Server();
		try {
			s.listenSocket();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
