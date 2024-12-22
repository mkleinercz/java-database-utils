package runners.database;

import cz.mks.database.utils.ConnectionUtils;
import cz.mks.database.utils.SqlUtils;
import java.sql.Connection;

public class TestConnection extends javax.swing.JFrame {

    private Connection connection;
    
    public TestConnection() {
        initComponents();
        //
        onProfilePostgres(null);
        //
        //editFileName.setText("m:/_Backup/database/2017-movo/2017-01-13 movo/2017-02-13-21-45-44-414-ext_usys.t_employee.sql");
        //editFileName.setText("m:/_Backup/database/2017-movo/2017-01-13 movo/2017-02-13-21-45-44-679-ext_usys.t_address.sql");
        editFileName.setText("m:/_Backup/database/2017-03-15-movo/scripts/2017-03-15-15-21-59-735-ext_usys.t_installation.sql");
        editLineBuffer.setText("10000");
    }

    
    private void log(String text) {
        console.setText(console.getText()+"\n"+text);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel10 = new javax.swing.JLabel();
        panelTop = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        editDriverClass = new javax.swing.JTextField();
        buttonCheckDriver = new javax.swing.JButton();
        buttonDisconnect = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        editHost = new javax.swing.JTextField();
        buttonProfilePostgres = new javax.swing.JButton();
        buttonConnect = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        editPort = new javax.swing.JTextField();
        buttonProfileOracle = new javax.swing.JButton();
        buttonConnectOracle = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        editUsername = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        editPassword = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        editFileName = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        EditDatabase = new javax.swing.JTextField();
        buttonLoad = new javax.swing.JButton();
        editLineBuffer = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        editApplication = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        buttonClearConsole = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        console = new javax.swing.JTextArea();

        jLabel10.setText("...");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("JDBC Connection");

        panelTop.setLayout(new java.awt.GridLayout(8, 4, 5, 5));

        jLabel1.setText("Driver class");
        panelTop.add(jLabel1);
        panelTop.add(editDriverClass);

        buttonCheckDriver.setText("Check Driver");
        buttonCheckDriver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onCheckDriver(evt);
            }
        });
        panelTop.add(buttonCheckDriver);

        buttonDisconnect.setText("Disconnect");
        buttonDisconnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onDisconnect(evt);
            }
        });
        panelTop.add(buttonDisconnect);

        jLabel3.setText("Host");
        panelTop.add(jLabel3);
        panelTop.add(editHost);

        buttonProfilePostgres.setText("Profile PostgreSQL");
        buttonProfilePostgres.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onProfilePostgres(evt);
            }
        });
        panelTop.add(buttonProfilePostgres);

        buttonConnect.setText("Connect PostgreSQL");
        buttonConnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onConnectPostgres(evt);
            }
        });
        panelTop.add(buttonConnect);

        jLabel5.setText("Port");
        panelTop.add(jLabel5);
        panelTop.add(editPort);

        buttonProfileOracle.setText("Profile Oracle");
        buttonProfileOracle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onprofileOracle(evt);
            }
        });
        panelTop.add(buttonProfileOracle);

        buttonConnectOracle.setText("Connect Oracle");
        buttonConnectOracle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onConnectOracle(evt);
            }
        });
        panelTop.add(buttonConnectOracle);

        jLabel7.setText("Username");
        panelTop.add(jLabel7);
        panelTop.add(editUsername);

        jLabel23.setText("...");
        panelTop.add(jLabel23);

        jLabel12.setText("...");
        panelTop.add(jLabel12);

        jLabel8.setText("Password");
        panelTop.add(jLabel8);
        panelTop.add(editPassword);

        jLabel13.setText("File name");
        panelTop.add(jLabel13);
        panelTop.add(editFileName);

        jLabel4.setText("Database");
        panelTop.add(jLabel4);
        panelTop.add(EditDatabase);

        buttonLoad.setText("Execute file");
        buttonLoad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onExecuteFile(evt);
            }
        });
        panelTop.add(buttonLoad);

        editLineBuffer.setText("1000");
        panelTop.add(editLineBuffer);

        jLabel6.setText("Application name");
        panelTop.add(jLabel6);
        panelTop.add(editApplication);

        jLabel31.setText("...");
        panelTop.add(jLabel31);

        jLabel9.setText("...");
        panelTop.add(jLabel9);

        buttonClearConsole.setText("Clear Console");
        buttonClearConsole.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onClearConsole(evt);
            }
        });
        panelTop.add(buttonClearConsole);

        getContentPane().add(panelTop, java.awt.BorderLayout.PAGE_START);

        console.setEditable(false);
        console.setColumns(20);
        console.setRows(5);
        jScrollPane1.setViewportView(console);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void onProfilePostgres(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onProfilePostgres
        editDriverClass.setText("org.postgresql.Driver");
        editHost.setText("192.168.3.17");
        //editHost.setText("localhost");
        editPort.setText("5432");
        editUsername.setText("postgres");
        editPassword.setText("postgres");
        //editPassword.setText("p");
        EditDatabase.setText("smg");
        editApplication.setText("App-PostgreSQL");
        
    }//GEN-LAST:event_onProfilePostgres

    private void onprofileOracle(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onprofileOracle
        editDriverClass.setText("oracle.jdbc.OracleDriver");        
        editHost.setText("vvcora-scan.veoliavoda.cz");
        editPort.setText("1521");
        editUsername.setText("USYSIREADING2");
        editPassword.setText("epd804pab");
        EditDatabase.setText("DCMOVO.VEOLIAVODA.CZ");
        editApplication.setText("");
    }//GEN-LAST:event_onprofileOracle

    private void onConnectPostgres(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onConnectPostgres
        try {
//            ConnectionUtils.checkDriverClass(editDriverClass.getText());
            log("Driver OK");
            connection = ConnectionUtils.getPostgresConnection(
                    editHost.getText(),
                    Integer.parseInt(editPort.getText()),                    
                    editUsername.getText(),
                    editPassword.getText(),
                    EditDatabase.getText(),
                    editApplication.getText()
            );            
            log("Connection OK");
            String meta = ConnectionUtils.getConnectionMetadata(connection);
            log(meta);
        }
        catch (Exception e) {
            log("Error: "+e);
        }
    }//GEN-LAST:event_onConnectPostgres

    private void onDisconnect(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onDisconnect
        try {
            connection.close();
            log("Disconnected");                                }
        catch (Exception e) {
            log("Error: "+e);
        }
    }//GEN-LAST:event_onDisconnect

    private void onCheckDriver(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onCheckDriver
        try {
            ConnectionUtils.checkDriverClass(editDriverClass.getText());
            log("Driver OK");
        }
        catch (Exception e) {
            log("Error: "+e);
        }
    }//GEN-LAST:event_onCheckDriver

    private void onClearConsole(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onClearConsole
        console.setText("");
    }//GEN-LAST:event_onClearConsole

    private void onConnectOracle(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onConnectOracle
         try {
            ConnectionUtils.checkDriverClass(editDriverClass.getText());
            log("Driver OK");
            connection = ConnectionUtils.getOracleConnection(
                    editHost.getText(),
                    Integer.parseInt(editPort.getText()),                    
                    editUsername.getText(),
                    editPassword.getText(),
                    EditDatabase.getText()
            );            
            log("Connection OK");
            String meta = ConnectionUtils.getConnectionMetadata(connection);
            log(meta);
        }
        catch (Exception e) {
            log("Error: "+e);
        }
    }//GEN-LAST:event_onConnectOracle

    private void onExecuteFile(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onExecuteFile
        try {
            String filename   = editFileName.getText();
            int    lineBuffer = Integer.parseInt(editLineBuffer.getText());
            //
            SqlUtils.executeSqlFile(connection,filename,lineBuffer,0);
            System.out.println("ExecuteFile: Done");
        }
        catch (Exception e) {
            System.out.println("ExecuteFile Error: "+e);
        }
    }//GEN-LAST:event_onExecuteFile

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TestConnection().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField EditDatabase;
    private javax.swing.JButton buttonCheckDriver;
    private javax.swing.JButton buttonClearConsole;
    private javax.swing.JButton buttonConnect;
    private javax.swing.JButton buttonConnectOracle;
    private javax.swing.JButton buttonDisconnect;
    private javax.swing.JButton buttonLoad;
    private javax.swing.JButton buttonProfileOracle;
    private javax.swing.JButton buttonProfilePostgres;
    private javax.swing.JTextArea console;
    private javax.swing.JTextField editApplication;
    private javax.swing.JTextField editDriverClass;
    private javax.swing.JTextField editFileName;
    private javax.swing.JTextField editHost;
    private javax.swing.JTextField editLineBuffer;
    private javax.swing.JTextField editPassword;
    private javax.swing.JTextField editPort;
    private javax.swing.JTextField editUsername;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelTop;
    // End of variables declaration//GEN-END:variables
}
