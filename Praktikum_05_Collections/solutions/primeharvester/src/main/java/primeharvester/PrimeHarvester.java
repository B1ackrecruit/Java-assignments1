package primeharvester;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class PrimeHarvester implements Iterable<BigInteger> {

    public final BigInteger startValue;

    public final BigInteger endValue;

    public PrimeHarvester(BigInteger startValue, BigInteger endValue) {
      

        //Prüfung auf null
        if(startValue == null || endValue == null){
            throw new IllegalArgumentException("null is not a valid value");
        }

        // Prüfung ob startValue größer als 1 ist
        if (new BigInteger("1").compareTo(startValue) >= 0) {
            throw new IllegalArgumentException("startValue must be greater than 1");
        }

        // Prüfung ob endValue größer als startValue ist
        if (startValue.compareTo(endValue) > 0) {
            throw new IllegalArgumentException("endValue must be greater than startValue");
        }

        this.startValue = startValue;
        this.endValue = endValue;
    }

    @Override
    public Iterator<BigInteger> iterator() {
        return new Iterator<BigInteger>(){

            BigInteger current = startValue.subtract(BigInteger.ONE);

            @Override
            public boolean hasNext() {
                //Wenn die nächste Primzahl größer ist als der endValue
                if(current.nextProbablePrime().compareTo(endValue)>0){
                    return false;
                }
                
                return true;
            }

            @Override
            public BigInteger next() {
                if (!hasNext())
                    throw new NoSuchElementException("All prime values are returned");

                current = current.nextProbablePrime();
                return current;
            }

        };
    }

    public long getPrimeCount(){
        Iterator<BigInteger> iterator = this.iterator();
        long result = 0;
        while(iterator.hasNext()){
            result++;
            iterator.next();
        }
        return result;
    }
}
