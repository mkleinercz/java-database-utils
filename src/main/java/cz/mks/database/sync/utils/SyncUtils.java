package cz.mks.database.sync.utils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;


public class SyncUtils {
    
    public static void copyRow(int rowId, ResultSet source, PreparedStatement target) throws Exception {        
        for (int col = 1; col <= source.getMetaData().getColumnCount(); col++) {       
            
            // DEBUG
            /*if (rowId == 0) {       
                System.out.println(String.format("    %s,%d,%s,%s,%d,%d",
                    source.getMetaData().getColumnName(col),
                    source.getMetaData().getColumnType(col),
                    source.getMetaData().getColumnTypeName(col),
                    source.getMetaData().getColumnClassName(col),
                    source.getMetaData().getPrecision(col),
                    source.getMetaData().getScale(col)
                ));
            }*/
            //
            
            // column is null
            if (source.getObject(col) == null) {
                target.setNull(col,source.getMetaData().getColumnType(col));
            }
            // column is not null
            else {
                int scale = source.getMetaData().getScale(col);
                switch (source.getMetaData().getColumnType(col)) {                                
                    case Types.CHAR:
                    case Types.VARCHAR:
                        target.setString(col,source.getString(col));
                        break;
                    case Types.CLOB:
                        target.setClob(col,source.getClob(col));
                        break;
                    case Types.INTEGER:
                        target.setInt(col,source.getInt(col));
                        break;
                    case Types.NUMERIC:
                        if (scale > 0) {
                            target.setDouble(col,source.getDouble(col));                            
                        }
                        else {
                            target.setInt(col,source.getInt(col));
                        }
                        break;
                    case Types.BOOLEAN:
                        target.setBoolean(col,source.getBoolean(col));
                        break;
                    case Types.TIMESTAMP: 
                        target.setTimestamp(col,source.getTimestamp(col));
                        break;
                    
                    default: System.out.println("Unknown ColumnType: "+source.getMetaData().getColumnType(col));
                }
            }                            
        }        
    }
    
}
