import java.util.Random;

public class Transaction {

    private UTXO inputs[];
    private Account from;
    private Account to;
    private int outputWei;
    private int inputBalanceWei;
    private int tipWei;
    private byte[] id;
    private byte[] signature;
    private byte[] data;


    public Transaction(UTXO _inputs[],int _outputWei,Account _from,byte[] _signature, Account _to) throws Exception{
        this.from = _from;
        this.to = _to;
        this.inputs = _inputs;
        this.outputWei = _outputWei;
        calculateInputBalance();
        calculateTip();
        if(this.inputBalanceWei < this.outputWei){
            throw new Error("Transaction Input < Transaction Output");
        }
        this.id = Transaction.getRandomNumberInts().getBytes();
        this.signature = _signature;
    }

    private void calculateInputBalance(){
        int _inputBalance = 0;
        for (UTXO utxo : inputs) {
            _inputBalance += utxo.getvalueInWei();
        }
        this.inputBalanceWei = _inputBalance;
    }

    private void calculateTip(){
        this.tipWei = this.inputBalanceWei - this.outputWei;
    }
    
    private static String getRandomNumberInts(){
        int _min = 1000000;
        int _max = 1000000000;
        Random _random = new Random();
        return String.valueOf(_random.ints(_min,(_max+1)).findFirst().getAsInt());
        
    }

    private String makeData(){
        return "{ " + this.from.getKeyPair().getPublic() + ":" + this.inputBalanceWei + " ," + this.from.getKeyPair().getPublic() + ":" + this.outputWei + " }" + " tip: " + this.tipWei + " .";
    }

    public Account getFrom(){
        return this.from;
    }

    public byte[] getId(){
        return this.id;
    }

    public byte[] getSignature(){
        return this.signature;
    }

    
}
