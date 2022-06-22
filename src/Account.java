import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.Signature;

import java.util.Scanner;

public class Account {

    public int nonce;
    private KeyPair keyPair;


    public Account() throws Exception {
        KeyPairGenerator _keyPairGen = KeyPairGenerator.getInstance("DSA");
        _keyPairGen.initialize(2048);
        this.keyPair = _keyPairGen.generateKeyPair();
    }

    public byte[] getSignature(byte[] _bytes) throws Exception{
        PrivateKey _privKey = this.keyPair.getPrivate();
        Signature _sign = Signature.getInstance("SHA256withDSA");
        _sign.initSign(_privKey);
        _sign.update(_bytes);

        return _sign.sign();
    }

    public boolean verifySignature(byte[] _bytes,byte[] _signature) throws Exception {
        PrivateKey _privKey = this.keyPair.getPrivate();
        Signature _sign = Signature.getInstance("SHA256withDSA");
        _sign.initSign(_privKey);
        _sign.update(_bytes);

        _sign.initVerify(this.keyPair.getPublic());
        _sign.update(_bytes);

        return _sign.verify(_signature);
    }

  

    
}
