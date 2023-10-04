package connection;

import java.sql.*;

public final class Connect {
	private final String USERNAME = "root"; 
	private final String PASSWORD = "";
	private final String DATABASE = "monsterbattle";
	private final String HOST = "localhost:3306";
	private final String CONECTION = String.format("jdbc:mysql://%s/%s", HOST, DATABASE);
	
	private Connection con;
	private Statement st;
	private static Connect connect;
	
	public ResultSet rs;
	public ResultSetMetaData rsm;
	
    private Connect() {
    	try {  
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(CONECTION, USERNAME, PASSWORD);  
            st = con.createStatement(); 
        } catch(Exception e) {
        	e.printStackTrace();
        	System.out.println("Failed to connect the database, the system is terminated!");
        	System.exit(0);
        }  
    }
    

    public static synchronized Connect getInstance() {
		return connect = (connect == null) ? new Connect() : connect;
    }

    public ResultSet execQuery(String query) {
        ResultSet rs = null;
    	try {
            rs = st.executeQuery(query);
        } catch(Exception e) {
        	e.printStackTrace();
        }
        return rs;
    }

    public void execUpdate(String query) {
    	try {
			st.executeUpdate(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public PreparedStatement prepareStatement(String query) {
    	PreparedStatement ps = null;
    	
    	try {
			ps = con.prepareStatement(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return ps;
    }
}
