package antiprimes;

import java.util.ArrayList;
import java.util.List;


//the MainWindow calls this, then this calls the numberProcessor, 
//the number processor is a Thread, and once it's done, it passes back
//the results to this, which passes them back to the MainWindow



//Represents the sequence of antiprimes found so far.
public class AntiPrimesSequence {

    
    //The numbers in the sequence.
    private List<Number> antiPrimes = new ArrayList<>();
    
    //the number processor, that does the actual heavy lifting 
    NumberProcessor numProcessor;
    

    
    //Create a new sequence containing only the first antiprime (the number '1').
    public AntiPrimesSequence() {
        this.reset();
        
        
        //create and start the num-calculating thread
        this.numProcessor = new NumberProcessor(this);
        numProcessor.start();
    }

    
     //Clear the sequence so that it contains only the first antiprime (the number '1').
    public void reset() {
        antiPrimes.clear();
        antiPrimes.add(new Number(1, 1));
    }


    
    
    //Tell number processor to find a new antiprime and let it add it to the sequence
    //called by MainWindow
    public void computeNext() {
    	numProcessor.computeNextAntiprime(getLast());
    }
    
     //add an antiprime (calculated elsewhere) to the list.
    //called nu NumberProcessor
    public void addAntiPrime(Number number) {
    	antiPrimes.add(number);
    }

    
    
    
    //Return the last antiprime found.
    public Number getLast() {
        int n = antiPrimes.size();
        return antiPrimes.get(n - 1);
    }

    
    //Return the last k antiprimes found.
    public List<Number> getLastK(int k) {
        int n = antiPrimes.size();
        if (k > n)
            k = n;
        return antiPrimes.subList(n - k, n);
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
}
