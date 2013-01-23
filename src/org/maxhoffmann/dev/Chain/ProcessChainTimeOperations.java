package org.maxhoffmann.dev.Chain;

import java.util.ArrayList;

public class ProcessChainTimeOperations {
	
	public ProcessChainTimeOperations() {
	}
	
	public void chainTimeOperations(ArrayList<String> generatedChains, ArrayList<String> generatedChainTimes) {
		
		ArrayList<String> processChains = new ArrayList<String>();
		ArrayList<String> chainTimes = new ArrayList<String>();
		processChains = generatedChains;
		chainTimes = generatedChainTimes;
		
		for ( int i = 0; i < 100 /*chainTimes.size()*/; i++ ) {
			System.out.println((i+1) + ".\tProzesskette:\t" + processChains.get(i) + "\n\tProzesszeiten:\t" + chainTimes.get(i) + "\n");
		}
	
	}	
}