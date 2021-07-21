import java.io.IOException;
import java.net.InetSocketAddress;
import com.sun.net.httpserver.HttpServer;


public class mainPOs {

	HttpServer server;

	public static void main(String[] args) {
		System.out.println("POS Web Server is starting .... ");

		mainPOs serverStart = new mainPOs();
		serverStart.init();
	}

	public void init() {
		try {
			server = HttpServer.create(new InetSocketAddress("localhost", 8001), 0);
			server.createContext("/posapi", new POSapi());
			server.start();

			System.out.println("Server started on port 8001");
		} catch(IOException ex) {
			System.out.println("IO Exception on web server " + ex);
		}
	}

}