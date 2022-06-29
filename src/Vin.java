import java.security.Signature;

public class Vin {

    private String txid;
    private int voutIndex;


    public Vin(String _txid,int _vout){
        this.txid = _txid;
        this.voutIndex = _vout;
    }

    public String getTxid(){
        return this.txid;
    }

    public int getVoutIndex(){
        return this.voutIndex;
    }


}
