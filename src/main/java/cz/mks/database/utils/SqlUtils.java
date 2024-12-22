package cz.mks.database.utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SqlUtils {
    
    public static void executeSql(Connection connection, String sql) throws Exception {
        try (Statement q = connection.createStatement()) {
            q.execute(sql);
        }
    }
      
    public static List<Object> selectOneRecord(Connection connection, String sqlSelect) throws Exception {
        List<Object> list = new ArrayList();
        try (Statement q = connection.createStatement()) {
            ResultSet row = q.executeQuery(sqlSelect);
            int columnCount = row.getMetaData().getColumnCount();
            row.next();            
            for (int i = 0; i < columnCount; i++) {
                list.add(row.getObject(i+1));                
            }
        }        
        return list;
    }
    
    private static void writeLines(Connection connection, StringBuilder sb) throws Exception {
        if (sb.length() > 0 ) {
            SqlUtils.executeSql(connection,sb.toString());
            sb.setLength(0);
        }
    }
    
    public static void executeSqlFile(Connection connection, String fileName, int lineBuffer, int offset) throws Exception {
        StringBuilder sb = new StringBuilder();        
        try (BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), StandardCharsets.UTF_8))) {
            int i = 0;
            String line;
            while ((line = in.readLine()) != null) {
                i++;
                // offset
                if (i <= offset) {
                    continue;
                }
                // ukladani po bufferu
                if (lineBuffer > 1) {
                    sb.append(line).append("\n");
                    if ((i % lineBuffer == 0) && (line.endsWith(";"))) {
                        //System.out.println("Count: "+i);
                        writeLines(connection, sb);
                    }
                } // ukladani po radcich
                else {
                    SqlUtils.executeSql(connection, line); // TODO muze byt kolize pri scriptu na vice radku                
                }
            }
            // pripadny zbytek
            writeLines(connection, sb);
        }
    }
}
