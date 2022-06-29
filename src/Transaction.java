import java.security.MessageDigest;

public class Transaction {

    private static int NONCE = 0;

    private Vin[] vin;
    private Vout[] vout;
    private String txid;
    private byte[] data;
    private byte[] signature;
 

    public Transaction(Vin[] _inputs,Vout[] _outputs,byte[] _signature,byte[] _data) throws Exception {
        this.vin = _inputs;
        this.vout = _outputs;
        this.txid = Transaction.SHA256();
        this.data = _data;
        this.signature = _signature;
        Transaction.NONCE++;
    }

    private static String SHA256() throws Exception {
      MessageDigest md = MessageDigest.getInstance("SHA-256");
      md.update((byte) Transaction.NONCE);
      byte[] digest = md.digest();       
      StringBuffer hexString = new StringBuffer();
      
      for (int i = 0;i<digest.length;i++) {
         hexString.append(Integer.toHexString(0xFF & digest[i]));
      }
      return hexString.toString();  
    }

    public Vin[] getVin(){
        return this.vin;
    }

    public Vout[] getVout(){
        return this.vout;
    }

    public byte[] getData(){
        return this.data;
    }

    public byte[] getSignature(){
        return this.signature;
    }

}
