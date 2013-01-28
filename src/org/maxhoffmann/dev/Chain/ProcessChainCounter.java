package org.maxhoffmann.dev.Chain;

import java.util.ArrayList;
import java.util.Collections;

public class ProcessChainCounter {
	
	public ProcessChainCounter() {
	}
	
	public void ProcessChainOperations(ArrayList<String> generatedChains) {
		
		ArrayList<Integer> countProcessChains = new ArrayList<Integer>();
		ArrayList<Integer> countLengthProcessChains = new ArrayList<Integer>();
		
		ArrayList<Integer> countProcessChainsLength = new ArrayList<Integer>();
		
		ArrayList<Integer> countContainProcessChains = new ArrayList<Integer>();
		ArrayList<Integer> countSubProcessChains = new ArrayList<Integer>();
		ArrayList<Integer> countSubChainsDiff = new ArrayList<Integer>();
		
		ArrayList<Integer> countContainProcessChainsLength = new ArrayList<Integer>();
		ArrayList<Integer> countSubProcessChainsLength = new ArrayList<Integer>();
		ArrayList<Integer> countSubChainsDiffLength = new ArrayList<Integer>();
		
		ArrayList<String> processChainsLength = new ArrayList<String>();
		
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
		
		ArrayList<Float> percentChainCount = new ArrayList<Float>();
		
		System.out.println("Anzahl der Prozessketten:\n");
		for (int c = 0; c < maxProcessChainLength; c++) {
			percentChainCount.add((float) countLengthProcessChains.get(c) / listedChains.size());
			System.out.println((c+1) + "er-Kette:\t" + countLengthProcessChains.get(c) + 
					"\t Anteil an Gesamtzahl:  " + percentChainCount.get(c)  * 100 + " %");
		}
		System.out.println("\nTotal number of chains: " + listedChains.size() + "\n");
		
		
		double cumulatedLengthPercentage = 0;
		ArrayList<Float> percentChain = new ArrayList<Float>();
		int chainCounter = 1;
		
		/*
		 * The percentage of each chain type (length) is cumulated until they make up more than 90 % of all chains.
		 * For example, if the 1-, 2-, 3-, 4- and 5-link chains together make up more than 90 % percent, break up.
		 */
		
		while (cumulatedLengthPercentage < 0.9) {	
			percentChain.add((float) countLengthProcessChains.get(chainCounter - 1) / listedChains.size());
			cumulatedLengthPercentage = cumulatedLengthPercentage + percentChain.get(chainCounter - 1);
			chainCounter++;
		}
		
		System.out.println("Nach der " + (chainCounter - 1)  + ". Kette: Kumulierte " + cumulatedLengthPercentage * 100 + " %");
		System.out.println("Anteil der " + (chainCounter - 1)  + ". Kette: " + percentChain.get(chainCounter - 2) * 100 + " %\n");
		
		/*
		 * The longest chain type of the cumulated chains is taken in order generate the main process chains.
		 * Condition: The longest chain type make up more than 10 % of all chains, because it has to be representative.
		 * If for exmaple, the 5-link chain is the last chain of the cumulated chains until 90 %, but makes up less than 10 %,
		 * than the 4-link chain is taken in order to generate the main process chains, and so on...
		 */
		
		int actualPercentageIndex = 0;
		float actualPercentage = 0;
		int arrayNumber = 1;
		System.out.println("Start-Kette:\t" + percentChain.get(chainCounter - 1 - arrayNumber));
		actualPercentage = percentChain.get(chainCounter - 1 - arrayNumber);
		actualPercentageIndex = chainCounter - arrayNumber - 1;
		while ( percentChain.get(chainCounter - 1 - arrayNumber) < 0.5 ) {
			arrayNumber++;
			if ( chainCounter - 1 - arrayNumber < 0 ) {						// if no entry is higher than 10%, take the maximum value
				Float maxPercentValue = Collections.max(percentChain);
				actualPercentage = maxPercentValue;
				for (int c = 0; c < maxProcessChainLength; c++) {
					if ( percentChainCount.get(c) == actualPercentage ) {
						actualPercentageIndex = c;
					}
				}
				break;
			}
			actualPercentageIndex = chainCounter - arrayNumber - 1;
			actualPercentage = percentChain.get(actualPercentageIndex);
		}
		
		System.out.println("End-Kette:\t" + actualPercentage + "\n\n");
		
		System.out.println("Actual Percentage Index: " + (actualPercentageIndex + 1) + "\n");
		
		for ( n = 0; n < processChains.size(); n++ ) {
			int chainLength = (processChains.get(n).length() + 1) / 5;
			if ( chainLength == (actualPercentageIndex + 1) ) {
				processChainsLength.add(processChains.get(n));
			}
		}
		
		for ( int m = 0; m < processChainsLength.size(); m++ ) {
			int counter = 0;
			for ( int j = 0; j < listedChains.size(); j++ ) {
				if ( processChainsLength.get(m).equals(listedChains.get(j)) == true ) {
					counter++;
				}
			}
			countProcessChainsLength.add(m, counter);
		}
		
		for ( int m = 0; m < processChainsLength.size(); m++ ) {
			int counterContain = 0;
			int counterSub = 0;
			for ( int j = 0; j < listedChains.size(); j++ ) {
				if ( listedChains.get(j).contains(processChainsLength.get(m)) == true ) {
					counterContain++;
				}
				if ( processChainsLength.get(m).contains(listedChains.get(j)) == true ) {
					counterSub++;
				}
			}
			countContainProcessChainsLength.add(m,counterContain);
			countSubProcessChainsLength.add(m,counterSub);
			countSubChainsDiffLength.add(m,counterContain-countProcessChainsLength.get(m));
		}
		
		
		for ( int j = 0; j < processChainsLength.size(); j++ ) {
			System.out.println((j+1) + ".\tProzesskette:\t"
					+ countProcessChainsLength.get(j) + "\t"
					+ countContainProcessChainsLength.get(j) + "\t" 
					+ countSubProcessChainsLength.get(j) + "\t"
					+ countSubChainsDiffLength.get(j) + "\t\t"
					+ processChainsLength.get(j)
			);
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