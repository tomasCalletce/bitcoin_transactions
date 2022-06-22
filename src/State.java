import java.util.HashMap; 
import java.util.ArrayList;

public class State {

    private static HashMap<UTXO,Boolean> UTXOinSystem = new HashMap<UTXO,Boolean>();

    public static void StateTransition(Transaction _transaction) throws Exception {
        if(!UTXOinSystem.get(_transaction)){
            throw new Error("UTXO is not in S");
        }
        if(!_transaction.getFrom().verifySignature(_transaction.getData(),_transaction.getSignature())){
            throw new Error("from not signer");
        }
        if(_transaction.getInputBalanceWei() < _transaction.getOutputBalanceWei()){
            throw new Error("input value < output value");
        }

    }

    private static void RemovefromS(UTXO[] _transactions){
        for (UTXO utxo : _transactions) {
            State.UTXOinSystem.remove(utxo);
        }
    }

    private static void AddtoS(UTXO[] _transactions){
        for (UTXO utxo : _transactions) {
            State.UTXOinSystem.put(utxo,true);
        }
    }
    
    
}
