package cz.mks.database.consts;

public enum ECommitInterval {
    
    NEVER     ("Transaction 'none'"      ,true ,false,false),
    PER_PARTES("Transaction 'per partes'",false,true ,false),
    AT_ONCE   ("Transaction 'at once'"   ,false,false,true),
    ;
    
    private final boolean autoCommit;
    private final boolean partialTransaction;
    private final boolean completeTransaction;
    private final String  name;
    
    private ECommitInterval(String name, boolean autoCommit, boolean partialTransaction, boolean completeTransaction) {
        this.name                = name;
        this.autoCommit          = autoCommit;
        this.partialTransaction  = partialTransaction;
        this.completeTransaction = completeTransaction;
    }

    public String getName() {
        return name;
    }

    public boolean isAutoCommit() {
        return autoCommit;
    }

    public boolean isPartialTransaction() {
        return partialTransaction;
    }

    public boolean isCompleteTransaction() {
        return completeTransaction;
    }
    
    
    
}
