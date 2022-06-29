import java.security.PublicKey;

public class Vout {
    
    private int valueWei;
    private PublicKey destination;


    public Vout(int _valueWei,PublicKey _destination){
        this.destination = _destination;
        this.valueWei = _valueWei;
    }
    
    public int getvalueInWei(){
        return this.valueWei;
    }

    public PublicKey getDestination(){
        return this.destination;
    }
}
