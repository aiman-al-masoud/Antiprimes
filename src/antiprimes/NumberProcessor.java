package antiprimes;

public class NumberProcessor extends Thread {

	//number processor gets called by, and calls-back an AntiPrimesSequence
	AntiPrimesSequence antiPrimesSequence;
	
	public NumberProcessor(AntiPrimesSequence antiPrimesSequence) {
		this.antiPrimesSequence = antiPrimesSequence;
	}
	
	
	//called by AntiPrimesSequence, tells number processor to find a new antiprime
	public void computeNextAntiprime(Number lastAntiprime) {
		
		//compute next antiprime
		Number nextAntiprime = AntiPrimes.nextAntiPrimeAfter(lastAntiprime);
		
		//pass it to the antiprime sequence
		antiPrimesSequence.addAntiPrime(nextAntiprime);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
