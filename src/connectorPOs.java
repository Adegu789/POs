import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.util.HashMap;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


public class POSdatabase{
	Connection conn = null;
	private HashMap jItem;

	public void connect() {
		try {String url = "jdbc:postgresql://localhost/posdtabase";
			conn =  DriverManager.getConnection(url, "postgres", "12345");
		} catch(SQLException ex) {
			System.out.println("SQL DB Connection error : " + ex);
		}
    }

	public String readItems() {JSONArray jaItems = new JSONArray();
		String query = "SELECT product_id, product_name, price,  "
			+ "FROM vw_items";
   		try {
			Statement stmt = conn.createStatement();
      		ResultSet rs = stmt.executeQuery(query);
      		while (rs.next()) {
				JSONObject jItem = new JSONObject();
			jItem.put("id", rs.getString("product_id"));
				jItem.put("name", rs.getString("product_name"));
				jItem.put("price", rs.getString("price"));
				

				jaItems.add(jItem);
        	}
    	} catch (SQLException ex) {
      		System.out.println("SQL DB Connection error : " + ex);
    	}
               jItem.put("products", jaItems); 
		return jaItems.toString();
	}

	public void close() {
		try {if(conn != null) conn.close();
		} catch(SQLException ex) {System.out.println("SQL DB Connection error : " + ex);
		}
    }
}