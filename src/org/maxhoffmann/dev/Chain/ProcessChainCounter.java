package org.maxhoffmann.dev.Chain;

import java.util.ArrayList;
import java.util.Collections;

import org.apache.log4j.Logger;

public class ProcessChainCounter {
	
	private static final Logger LOGGER = Logger.getLogger(ProcessChainCounter.class);
	
	public ProcessChainCounter() {
	}
	
	public void ProcessChainOperations(ArrayList<String> generatedChains) {
		
		
		ArrayList<Integer> countProcessChains = new ArrayList<Integer>();				// Counter for all process chains
		ArrayList<Integer> countLengthProcessChains = new ArrayList<Integer>();			// Counter for process chains of all chain length
		
		ArrayList<Integer> countProcessChainsLength = new ArrayList<Integer>();			// Counter for process chains of a certain length,
																						// which is determined in the optimization algorithm
		
		ArrayList<Integer> countContainProcessChains = new ArrayList<Integer>();		// Counter for process chains, that contain one
																						// of the distinct determined process chains
		
		ArrayList<Integer> countSubProcessChains = new ArrayList<Integer>();			// Counter for Sub Process Chains within
																						// the distinctive determined process chains
		
		ArrayList<Integer> countSubChainsDiff = new ArrayList<Integer>();				// Counter for Sub Process Chains within the different
																						// process chains without counting the chains itself.
		
		ArrayList<Integer> countContainProcessChainsLength = new ArrayList<Integer>();	// The chain counter types like above,
		ArrayList<Integer> countSubProcessChainsLength = new ArrayList<Integer>();		// just counting the chains of a certain length
		ArrayList<Integer> countSubChainsDiffLength = new ArrayList<Integer>();
		
		ArrayList<String> processChainsLength = new ArrayList<String>();	// List of distinct process chains of a certain length (no duplicates)
		
		ArrayList<String> listedChains = new ArrayList<String>();			// List containing all available process chains (including duplicates)
		ArrayList<String> processChains = new ArrayList<String>();			// List containing distinct process chains (no duplicates)
		
		listedChains = generatedChains;										// Reading output of the generated process chains.
		int n = 0;
		
		/*
		 * Determination of the maximum chain length within all process chains.		
		 */
		
		int maxChainLength = 0;
		for ( int m = 0; m < listedChains.size(); m++ ) {
			int currentChainLength = listedChains.get(m).length();
			if ( currentChainLength > maxChainLength ) {
				maxChainLength = currentChainLength;
			}
		}
		
		/*
		 * Conversion of character to chain length (number of production entities in a chain).
		 */
		
		int maxProcessChainLength = ( maxChainLength + 1 ) / 5;
		LOGGER.info("Maximum Chain Length: " + maxProcessChainLength + "\n");
		
		/*
		 * Initialization of the counter list for each chain length.
		 */
		
		for ( int c = 0; c < maxProcessChainLength; c++ ) {
			countLengthProcessChains.add(c, 0);
		}
		
		/*
		 * Calculation of the distinctive process chains.
		 */
		
		for ( int m = 0; m < listedChains.size(); m++ ) {
			if ( processChains.contains(listedChains.get(m)) == false ) {
				processChains.add(n, listedChains.get(m));
				n++;
			}
		}
		
		/*
		 * Determination of the number of each distinctive process chain within all process chains.
		 * The routine counts all duplicates of process chains for each type.
		 */
		
		for ( n = 0; n < processChains.size(); n++ ) {
			int counter = 0;
			for ( int m = 0; m < listedChains.size(); m++ ) {
				if ( processChains.get(n).equals(listedChains.get(m)) == true ) {
					counter++;
				}
			}
			countProcessChains.add(n, counter);
		}
		
		/*
		 * This algorithm counts the total number of all process chains for each length.
		 * For example: number of 1-link chains, number of 2-link chains, etc. within the whole process chain list.
		 */
		
		for ( int m = 0; m < listedChains.size(); m++ ) {
			int index = ( ( listedChains.get(m).length() + 1 ) / 5 ) - 1;
			countLengthProcessChains.set(index, countLengthProcessChains.get(index) + 1);
		}
		
		ArrayList<Float> percentChainCount = new ArrayList<Float>();		// List containing the percentage values of chains of each length. 
		
		/*
		 * Calculation of the percentage amount of each chain-length category compared to all chains.
		 */
		
		LOGGER.info("Anzahl der Prozessketten:\n");
		for (int c = 0; c < maxProcessChainLength; c++) {
			percentChainCount.add((float) countLengthProcessChains.get(c) / listedChains.size());
			LOGGER.info((c+1) + "er-Kette:\t" + countLengthProcessChains.get(c) + 
					"\t Anteil an Gesamtzahl:  " + percentChainCount.get(c)  * 100 + " %");
		}
		LOGGER.info("\nTotal number of chains: " + listedChains.size() + "\n");
		
		
		double cumulatedLengthPercentage = 0;						// Initialization of cumulated percentage value
																	// of all chains smaller or equal a certain chain length.
		
		int chainCounter = 1;					// Counter variable in order to determine the longest chain
												// with a sufficient amount within all process chains.
		
		/*
		 * The percentage of each chain type (length) is cumulated until they make up more than 90 % of all chains.
		 * For example, if the 1-, 2-, 3-, 4- and 5-link chains together make up more than 90 % percent, break up.
		 */
		
		while (cumulatedLengthPercentage < 0.95) {	
			percentChainCount.add((float) countLengthProcessChains.get(chainCounter - 1) / listedChains.size());
			cumulatedLengthPercentage = cumulatedLengthPercentage + percentChainCount.get(chainCounter - 1);
			chainCounter++;
		}
		
		LOGGER.info("Nach der " + (chainCounter - 1)  + ". Kette: Kumulierte " + cumulatedLengthPercentage * 100 + " %");
		LOGGER.info("Anteil der " + (chainCounter - 1)  + ". Kette: " + percentChainCount.get(chainCounter - 2) * 100 + " %\n");
		
		/*
		 * The longest chain type of the cumulated chains is taken in order to generate the main process chains.
		 * Condition: The longest chain type make up more than 10 % of all chains, because it has to be representative.
		 * If for example, the 5-link chain is the last chain of the cumulated chains until 90 %, but makes up less than 10 %,
		 * than the 4-link chain is taken in order to generate the main process chains, and so on...
		 */
		
		int actualPercentageIndex = 0;	// index of the List that contains the percentage values of each chain length
		float actualPercentage = 0;		// Declaration of the actual percentage value according to the index of the percentage list.
		int arrayNumber = 1;			// Variable in order to search for the percentage value that fits to the given conditions.
		
		LOGGER.info("Start-Kette:\t" + percentChainCount.get(chainCounter - 1 - arrayNumber));
		
		actualPercentage = percentChainCount.get(chainCounter - 1 - arrayNumber);	// Initialization of the actual percentage value
		actualPercentageIndex = chainCounter - arrayNumber - 1;						// Initialization of the actual percentage list index
		
		while ( percentChainCount.get(chainCounter - 1 - arrayNumber) < 0.1 ) {
			arrayNumber++;
			if ( chainCounter - 1 - arrayNumber < 0 ) {						// if no entry is higher than 10%, take the maximum value
				Float maxPercentValue = Collections.max(percentChainCount);
				actualPercentage = maxPercentValue;
				for (int c = 0; c < maxProcessChainLength; c++) {			// if the maximum value is taken, find the according index
					if ( percentChainCount.get(c) == actualPercentage ) {
						actualPercentageIndex = c;
					}
				}
				break;
			}
			actualPercentageIndex = chainCounter - arrayNumber - 1;				// index and percentage values are determined
			actualPercentage = percentChainCount.get(actualPercentageIndex);	// after the run of the iteration process.
		}
		
		LOGGER.info("End-Kette:\t" + actualPercentage + "\n\n");
		
		LOGGER.info("Actual Percentage Index: " + (actualPercentageIndex + 1) + "\n");
		
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
			LOGGER.info((j+1) + ".\tProzesskette:\t"
					+ countProcessChainsLength.get(j) + "\t"
					+ countContainProcessChainsLength.get(j) + "\t" 
					+ countSubProcessChainsLength.get(j) + "\t"
					+ countSubChainsDiffLength.get(j) + "\t\t"
					+ processChainsLength.get(j)
			);
		}
		
		LOGGER.info("\n");
		
			
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
			LOGGER.info((n+1) + ".\tProzesskette:\t" 
					+ countProcessChains.get(n) + "\t" 
					+ countContainProcessChains.get(n) + "\t" 
					+ countSubProcessChains.get(n) + "\t"
					+ countSubChainsDiff.get(n) + "\t\t"
					+ processChains.get(n) );
		}
	}	
}