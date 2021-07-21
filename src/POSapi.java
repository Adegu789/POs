import java.io.IOException;
import java.io.OutputStream;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

public class POSapi implements HttpHandler {

	@Override    
	public void handle(HttpExchange httpExchange) {
		System.out.println("Reached handle");

		String sInput = httpExchange.getRequestURI().toString();
		System.out.println(sInput);

		handleResponse(httpExchange);
	}


	private void handleResponse(HttpExchange httpExchange) {
		POSdatabase dbConnection = new POSdatabase();
		dbConnection.connect();
		String resp = dbConnection.readItems();
		dbConnection.close();


		try {
			OutputStream outputStream = httpExchange.getResponseBody();
			StringBuilder htmlBuilder = new StringBuilder();
			htmlBuilder.append(resp);

			
			String htmlResponse = htmlBuilder.toString();

			
			httpExchange.sendResponseHeaders(300, htmlResponse.length());

			outputStream.write(htmlResponse.getBytes());
			outputStream.flush();
			outputStream.close();
		} catch(IOException rex) {System.out.println("IO Error on server response " + rex);
		}
	}

}