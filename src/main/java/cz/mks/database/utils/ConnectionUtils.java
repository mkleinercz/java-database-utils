package cz.mks.database.utils;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtils {

// GLOBAL    
    public static void checkDriverClass(String driverClass) throws ClassNotFoundException {
        Class.forName(driverClass);
    }
    
    public static void checkDriverClass_Postgres() throws ClassNotFoundException {
        checkDriverClass("org.postgresql.Driver");
    }
    
    public static void checkDriverClass_Oracle() throws ClassNotFoundException {
        checkDriverClass("oracle.jdbc.OracleDriver");
    }
    
    // jdbc:postgresql://localhost:5432/mydatabase
    // jdbc:oracle:thin:scott/tiger@myhost:1521:orcl
    public static Connection getConnection(String connectionString, String username, String password) throws SQLException  {
        Properties properties = new Properties();
        properties.put("connectTimeout", 2000);
        properties.put("user", username);
        properties.put("password", password);
        //System.out.println("CONNECTION STRING =" + connectionString);
        return DriverManager.getConnection(connectionString,properties);
    }
    
// CONCRETE    
    public static Connection getPostgresConnection(String host, int port, String username, String password, String database, String applicationName) throws SQLException  {
        String DRIVER_NAME = "postgresql";
        String APP_NAME    = (applicationName == null || applicationName.equals("")) ? "" : String.format("?ApplicationName=%s",applicationName);
        //
        String CONNECTION_STRING = String.format("jdbc:%s://%s:%d/%s%s",DRIVER_NAME,host,port,database,APP_NAME);
        return getConnection(CONNECTION_STRING,username,password);        
    }
    
    public static Connection getOracleConnection(String host, int port, String username, String password, String service) throws SQLException  {
        String DRIVER_NAME = "oracle:thin";
        //  If you use a / it is a net service name, if you use a colon it is a SID.
        String CONNECTION_STRING = String.format("jdbc:%s:@%s:%d/%s",DRIVER_NAME,host,port,service);
        return getConnection(CONNECTION_STRING,username,password);        
    }
    
    //
    public static String getConnectionMetadata(Connection connection) {
        StringBuilder sb = new StringBuilder();
        try {
            DatabaseMetaData metadata = connection.getMetaData();
            sb.append("URL").append("=").append(metadata.getURL()).append("\n");
            sb.append("Driver name").append("=").append(metadata.getDriverName()).append("\n");
            sb.append("Driver version").append("=").append(metadata.getDriverVersion()).append("\n");
            sb.append("Driver major version").append("=").append(metadata.getDriverMajorVersion()).append("\n");
            sb.append("Driver minor version").append("=").append(metadata.getDriverMinorVersion()).append("\n");
            sb.append("Database product name").append("=").append(metadata.getDatabaseProductName()).append("\n");
            sb.append("Database product version").append("=").append(metadata.getDatabaseProductVersion());            
            return sb.toString();
        }
        catch (Exception e) {
            return "Metadata Error: "+e;
        }
    }
    
}
