package testers;

import antiprimes.AntiPrimesSequence;

public class Tester {

	public static void main(String[] args) {
		
		AntiPrimesSequence seq = new AntiPrimesSequence();
		
		for(int i =0; i<100; i++) {
			seq.computeNext();
			System.out.println(seq.getLast().getValue());
			
		}
		
		
		
	}

}
