import java.util.HashMap;
import java.security.PublicKey;
import java.util.ArrayList;

public class State {

    private static HashMap<String,Transaction> ALL_TRANSACTIONS = new HashMap<String,Transaction>();

    public static void StateTransition(Transaction _transaction,PublicKey _from) throws Exception {
        if(!State.allVinsInSet(_transaction)){
            throw new Error("Vin not in S");
        }
        if(!vinsFromVoutsFromSigner(_transaction.getVin())){
            throw new Error("Valid vins");
        }
        if(sumVins(_transaction) < sumVouts(_transaction)){
            throw new Error("input value < output value");
        }
        if(senderOwnerOfAllVins(_transaction.getVin(), _from)){
            throw new Error("not owner of vins");
        }
        if(!Cryptography.verifySignature(_transaction.getData(),_transaction.getSignature(),_from)){
            throw new Error("invalid signature");
        }


    }

    private static boolean vinsFromVoutsFromSigner(Vin[] _vins){
        for (Vin vin : _vins) {
            Vout[] _vout = ALL_TRANSACTIONS.get(vin.getTxid()).getVout();
            if(_vout.length <= vin.getVoutIndex()){
                return false;
            }
            
        }
        return true;
    }

    private static boolean allVinsInSet(Transaction _transaction){
        for (Vin vin : _transaction.getVin()) {
            if(!State.ALL_TRANSACTIONS.containsKey(vin.getTxid())){
                return false;
            }
        }
        return true;
    }

    private static int sumVins(Transaction _transaction){
        int _sum = 0;
        for (Vin _vin : _transaction.getVin()) {
            Vout _vout = ALL_TRANSACTIONS.get(_vin.getTxid()).getVout()[_vin.getVoutIndex()];
            _sum += _vout.getvalueInWei();
        }
        return _sum;
    }

    private static int sumVouts(Transaction _transaction){
        int _sum = 0;
        for (Vout _vout : _transaction.getVout()) {
            _sum += _vout.getvalueInWei();
        }
        return _sum;
    }

    private static boolean senderOwnerOfAllVins(Vin[] _vins,PublicKey _from){
        for (Vin _vin : _vins) {
            Vout[] _vout = ALL_TRANSACTIONS.get(_vin.getTxid()).getVout();
            if(_vout[_vin.getVoutIndex()] != _from){
                return false;
            }
        }
        return true;
    }



    
    
}
