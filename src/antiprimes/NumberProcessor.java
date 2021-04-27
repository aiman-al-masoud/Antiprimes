package antiprimes;

public class NumberProcessor extends Thread {

	//number processor gets called by, and calls-back an AntiPrimesSequence
	AntiPrimesSequence antiPrimesSequence;
	
	//store last antiprime from antiPrimeSequence.
	//Since request is always accessed through synchronized methods,
	//there are no issues with multiple threads modifying it.
	private Number request = null;
	
	public NumberProcessor(AntiPrimesSequence antiPrimesSequence) {
		this.antiPrimesSequence = antiPrimesSequence;
	}
	
	
	//called by AntiPrimesSequence, tells number processor to find a new antiprime
	public synchronized void computeNextAntiprime(Number newRequest) throws InterruptedException{
		
		//while there's already a request that is not null...
		while (this.request != null) {
			
			//if the new request concerns the same num, reject it
            if (this.request.getValue() == newRequest.getValue()) {
            	return;
            }
                
            //else put the CALLLER on the waiting line
            wait();
        }
		
		//when current request becomes null, accept newRequest
        this.request = newRequest;
        //notify CALLER
        notify();
		
		
	}


	
	//overriding the run method
	@Override
	public void run() {
		super.run();
		
		//infinite loop
		while(true) {
			
			try {
				
				//wait until request is not null anymore
	            Number n = waitToGetNewRequest();
	            
	            //when request is not null, compute result
	            Number m = AntiPrimes.nextAntiPrimeAfter(n);
	            
	            //pass result to the caller sequence
	            antiPrimesSequence.addAntiPrime(m);
	            
	            //set request back to null
	            readyForNewRequests();
	        } catch (InterruptedException e) {
	            break;
	        }
			
				
			}
			
			
	}
		
	
	

	
// Retrieve the next request.  Wait for a new request if there are none.
synchronized private Number waitToGetNewRequest() throws InterruptedException {
    
	//while request is null, wait
	while (request == null) {
    	wait();
    }
    
	//when request is not null, return it
    return request;
}


//Make the processor ready for new computations.
synchronized private void readyForNewRequests() {
    request = null;
    notify();
}
	
	
	
	
	
	
	
	
	
	
	
}
