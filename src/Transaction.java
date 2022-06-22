import java.util.Random;

public class Transaction {

    private UTXO[] inputs;
    private UTXO[] outputs;
    private Account from;
    private Account to;
    private int inputBalanceWei;
    private int outputBalanceWei;
    private int tipWei;
    private byte[] data;
    private byte[] signature;


    public Transaction(UTXO[] _inputs,UTXO[] _outputs,Account _from, Account _to){
        this.from = _from;
        this.to = _to;
        this.inputs = _inputs;
        this.outputs = _outputs;
        this.inputBalanceWei = Transaction.calculateSum(_inputs);
        this.outputBalanceWei = Transaction.calculateSum(_outputs);
        this.tipWei = this.inputBalanceWei - this.outputBalanceWei;
        this.data = this.makeData().getBytes();
    }

    private static int calculateSum(UTXO[] _transactions){
        int _sum = 0;
        for (UTXO utxo : _transactions) {
            _sum += utxo.getvalueInWei();
        }
        return _sum;
    }
    
    private String makeData(){
        return "{ " + this.from.getKeyPair().getPublic() + ":" + this.inputBalanceWei + " ," + this.from.getKeyPair().getPublic() + ":" + this.outputBalanceWei + " }" + " tip:" + this.tipWei ;
    }

    public Account getFrom(){
        return this.from;
    }

    public byte[] getSignature(){
        return this.signature;
    }

    public byte[] getData(){
        return this.data;
    }

    public int getInputBalanceWei(){
       return this.inputBalanceWei;
    }

    public int getOutputBalanceWei(){
        return this.outputBalanceWei;
     }


}
