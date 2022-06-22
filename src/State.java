import java.util.HashMap; 
import java.util.ArrayList;

public class State {

    private static HashMap<Account,ArrayList<UTXO>> UTXOperAccount = new HashMap<Account,ArrayList<UTXO>>();
    private static HashMap<UTXO,Boolean> UTXOinSystem = new HashMap<UTXO,Boolean>();

    public static void StateTransition(Transaction _transaction) throws Exception {
        if(!UTXOinSystem.get(_transaction)){
            throw new Error("UTXO is not in S");
        }
        Account _from = _transaction.getFrom();
        if(!_transaction.getFrom().verifySignature(_transaction.getId(),_transaction.getSignature())){
            throw new Error("from not signer");
        }

    }
    
    
}
