package org.maxhoffmann.dev.Chain;

import java.util.ArrayList;

public class ProcessChainCounter {
	
	public ProcessChainCounter() {
	}
	
	public void ProcessChainOperations(ArrayList<String> generatedChains) {
		
		ArrayList<Integer> countProcessChains = new ArrayList<Integer>();
		ArrayList<Integer> countLengthProcessChains = new ArrayList<Integer>();
		
		ArrayList<Integer> countContainProcessChains = new ArrayList<Integer>();
		ArrayList<Integer> countSubProcessChains = new ArrayList<Integer>();
		ArrayList<Integer> countSubChainsDiff = new ArrayList<Integer>();
		
		ArrayList<String> listedChains = new ArrayList<String>();
		ArrayList<String> processChains = new ArrayList<String>();
		
		listedChains = generatedChains;
		int n = 0;
		
		int maxChainLength = 0;
		for ( int m = 0; m < listedChains.size(); m++ ) {
			int currentChainLength = listedChains.get(m).length();
			if ( currentChainLength > maxChainLength ) {
				maxChainLength = currentChainLength;
			}
		}
		
		int maxProcessChainLength = ( maxChainLength + 1 ) / 5;
		System.out.println("Maximum Chain Length: " + maxProcessChainLength + "\n");
		
		for ( int c = 0; c < maxProcessChainLength; c++ ) {
			countLengthProcessChains.add(c, 0);
		}
		
		for ( int m = 0; m < listedChains.size(); m++ ) {
			if ( processChains.contains(listedChains.get(m)) == false ) {
				processChains.add(n, listedChains.get(m));
				n++;
			}
		}
		
			
		for ( n = 0; n < processChains.size(); n++ ) {
			int counter = 0;
			for ( int m = 0; m < listedChains.size(); m++ ) {
				if ( processChains.get(n).equals(listedChains.get(m)) == true ) {
					counter++;
				}
			}
			countProcessChains.add(n, counter);
		}
		
		
		for ( int m = 0; m < listedChains.size(); m++ ) {
			int index = ( ( listedChains.get(m).length() + 1 ) / 5 ) - 1;
			countLengthProcessChains.set(index, countLengthProcessChains.get(index) + 1);
		}
		
		
		System.out.println("Anzahl der Prozessketten:\n");
		for (int c = 0; c < maxProcessChainLength; c++) {
			System.out.println((c+1) + "er-Kette:\t" + countLengthProcessChains.get(c));
		}
		System.out.println("\n");		
		
		for ( n = 0; n < processChains.size(); n++ ) {
			int counterContain = 0;
			int counterSub = 0;
			for ( int m = 0; m < listedChains.size(); m++ ) {
				if ( listedChains.get(m).contains(processChains.get(n)) == true ) {
					counterContain++;
				}
				if ( processChains.get(n).contains(listedChains.get(m)) == true ) {
					counterSub++;
				}
			}
			countContainProcessChains.add(n,counterContain);
			countSubProcessChains.add(n,counterSub);
			countSubChainsDiff.add(n,counterContain-countProcessChains.get(n));
		}
		
		/*
		for ( int m = 0; m < listedChains.size(); m++ ) {
			int counterContainBreak = 0;
			for ( n = 0; n < processChains.size(); n++ ) {
				if ( listedChains.get(m).contains(processChains.get(n)) == true ) {
					counterContainBreak++;
				}
				countContainBreakProcessChains.add(n,counterContainBreak);
			}
		}
		*/
		
		for ( n = 0; n < processChains.size(); n++ ) {
			System.out.println((n+1) + ".\tProzesskette:\t" 
					+ countProcessChains.get(n) + "\t" 
					+ countContainProcessChains.get(n) + "\t" 
					+ countSubProcessChains.get(n) + "\t"
					+ countSubChainsDiff.get(n) + "\t\t"
					+ processChains.get(n) );
		}
	}	
}