package cz.mks.database.sync;

import cz.mks.database.consts.ECommitInterval;
import cz.mks.database.consts.ELogLevel;
import cz.mks.database.sync.utils.SyncUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Sync {
    
    private Connection source; // source
    private Connection target; // target
    
    private final ELogLevel logLevel;
    private final String    SCRIPT_DIR    = "scripts";
    private final String    SCRIPT_PREFIX = "yyyy-MM-dd-HH-mm-ss-SSS";
    
    public Sync(ELogLevel logLevel) {
        this.logLevel = logLevel != null ? logLevel : ELogLevel.NONE;        
    }

// GET    
    public Connection getSourceConnection() {
        return source;
    }

    public Connection getTargetConnection() {
        return target;
    }

// LOG    
    protected void log(ELogLevel level, String text) {
        if (level.ordinal() <= logLevel.ordinal()) {
            System.out.println(String.format("SYNC: %s: %s",level.name(),text));
        }
    }        
    
// CONNECTION    
    public void connect(Connection source, Connection target) {
        this.source = source;
        this.target = target;
        log(ELogLevel.INFO,"Connect");
        try {
            log(ELogLevel.DEBUG,"Source Url: "+this.source.getMetaData().getURL());    
        }
        catch (Exception e) {
            log(ELogLevel.ERROR,"Source Url: "+e);
        }
        try {
            log(ELogLevel.DEBUG,"Target Url: "+this.target.getMetaData().getURL());
        }
        catch (Exception e) {
            log(ELogLevel.ERROR,"Source Url: "+e);
        }
        
    }
    
    public void disconnect() {
        try { source.close(); } catch (Exception e) {}
        try { target.close(); } catch (Exception e) {}
        source = null;
        target = null;
        log(ELogLevel.INFO,"Disconnect");
    }
    
    public boolean isConnected() {
        try {
            return (! source.isClosed()) && (! target.isClosed());
        }
        catch (Exception e) {
            return false;
        }
    }
       
// SYNC    
    public void synchronizeTable(ECommitInterval commitInterval, boolean onlyScript, String select, String preInsert, String insert, String displayName) throws Exception {
        if (! isConnected()) throw new SQLException("Not connected"); 
        if (onlyScript) {
            new File(SCRIPT_DIR).mkdir();
        }                
        //        
        try {
            log(ELogLevel.DEBUG,"Table: Begin "+commitInterval.getName());                
            target.setAutoCommit(commitInterval.isAutoCommit());            
            internalSynchronizeTable(commitInterval,onlyScript,select,preInsert,insert,displayName);    
            if (commitInterval.isPartialTransaction()) {
                target.commit();
            }
            log(ELogLevel.DEBUG,"Table: End "+commitInterval.getName());            
        }
        catch (Exception e) {
            log(ELogLevel.DEBUG,"Table: Error "+commitInterval.getName()+": "+e);
            if (commitInterval.isPartialTransaction()) {                
                try {                    
                    target.rollback();                    
                }
                catch (Exception e2) {
                   log(ELogLevel.DEBUG,"Table: Rollback error "+commitInterval.getName()+": "+e+"\n"+e2);
                }                
            }    
            throw e;
        }        
    }
    
    private void internalSynchronizeTable(ECommitInterval commitInterval, boolean onlyScript, String select, String preInsert, String insert, String displayName) throws Exception {
        Date d1 = new Date();
        //
        String fileName = String.format("%s/%s-%s.sql", SCRIPT_DIR, new SimpleDateFormat(SCRIPT_PREFIX).format(d1), displayName);
        //PrintWriter printer = onlyScript ? new PrintWriter(fileName) : null;
        OutputStreamWriter printer = null;
        try {
            printer = onlyScript ? new OutputStreamWriter(new FileOutputStream(new File(fileName)), "UTF-8") : null;
            // PRE INSERT
            if ((preInsert != null) && (!"".equals(preInsert))) {
                try ( Statement q = target.createStatement()) {
                    try {
                        if (onlyScript) {
                            //printer.println(preInsert+";");
                            printer.write(preInsert + ";" + "\n");
                        } else {
                            q.execute(preInsert);
                        }
                    } catch (Exception e) {
                        if (!commitInterval.isAutoCommit()) {
                            throw e;
                        }
                    }
                    log(ELogLevel.DEBUG, "SQL: Pre Insert");
                }
            }
            // SELECT
            int rowOk = 0;
            int rowErr = 0;
            try ( Statement statement = source.createStatement()) {
                ResultSet row = statement.executeQuery(select);
                log(ELogLevel.DEBUG, "SQL: Select");
                while (row.next()) {
                    try ( PreparedStatement q = target.prepareStatement(insert)) {
                        // INSERT
                        try {
                            SyncUtils.copyRow(rowOk, row, q);
                            if (onlyScript) {
                                //printer.println(q.toString()+";");
                                printer.write(q.toString() + ";" + "\n");
                            } else {
                                q.execute();
                            }
                            rowOk++;
                        } catch (Exception e) {
                            rowErr++;
                            Date d2 = new Date();
                            log(ELogLevel.INFO, String.format("%s: %d/%d rows, %d ms, Error: %s", displayName, rowOk, rowErr, (d2.getTime() - d1.getTime()), e.toString()));
                            if (!commitInterval.isAutoCommit()) {
                                throw e;
                            }
                        }
                    }
                }
            } finally {
                Date d2 = new Date();
                log(ELogLevel.INFO, String.format("%s: %d rows, %d ms", displayName, rowOk, (d2.getTime() - d1.getTime())));
            }
        } finally {
            if (onlyScript && printer != null) {
                printer.flush();
                printer.close();
            }
        }
    }
    
    
}
