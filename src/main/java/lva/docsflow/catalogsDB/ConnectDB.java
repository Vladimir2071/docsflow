package lva.docsflow.catalogsDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {

	private String connectionUrl;
	private String server;
	private String port;
	private String dbname;
	private String user;
	private String password;
	
	
	public ConnectDB(String server, String port, String dbname, String user, String password) {
		super();
		this.server = server;
		this.port = port;
		this.dbname = dbname;
		this.user = user;
		this.password = password;
		setConnectionUrl();
	}
	
	public Connection connectDB() {
		
		Connection conn = null;
		
        try {
            conn = DriverManager.getConnection(connectionUrl);
        // Handle any errors that may have occurred.
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
        
        return conn;
	}

	public String getConnectionUrl() {
		return connectionUrl;
	}

	private void setConnectionUrl() {
		this.connectionUrl = "jdbc:sqlserver://" + this.server + ";databaseName=" + this.dbname + ";user=" + this.user + ";password=" + this.password;
	}	
}
