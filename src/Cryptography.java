import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;


public class Cryptography {

    public static KeyPair getKeyPair() throws Exception {
        KeyPairGenerator _keyPairGen = KeyPairGenerator.getInstance("DSA");
        _keyPairGen.initialize(2048);
        return _keyPairGen.generateKeyPair();
    }

    private byte[] getSignature(byte[] _bytes,PrivateKey _privKey) throws Exception{
        Signature _sign = Signature.getInstance("SHA256withDSA");
        _sign.initSign(_privKey);
        _sign.update(_bytes);

        return _sign.sign();
    }

    public static boolean verifySignature(byte[] _bytes,byte[] _signature,PublicKey _publicvKey) throws Exception {
        Signature _sign = Signature.getInstance("SHA256withDSA");
        _sign.initVerify(_publicvKey);
        _sign.update(_bytes);

        return _sign.verify(_signature);
    }



    // private String makeData(){
    //     return "{ " + this.from.getKeyPair().getPublic() + ":" + this.inputBalanceWei + " ," + this.from.getKeyPair().getPublic() + ":" + this.outputBalanceWei + " }" + " tip:" + this.tipWei ;
    // }

    
    


    
}
