package org.maxhoffmann.dev.Chain;

import org.maxhoffmann.dev.ProductionAnalysisAnnotation.ProductionOrderHistory;

import java.util.ArrayList;
import java.util.List;


public class ProcessChainGeneration {
	
	public ProcessChainGeneration() {
	}
	
	public void ProcessChainBuild(List<ProductionOrderHistory> pohInput) {
		
		List<ProductionOrderHistory> ProductionOrderData = pohInput;
		
		ArrayList<Integer> countProcessChains = new ArrayList<Integer>();
		ArrayList<Integer> countContainProcessChains = new ArrayList<Integer>();
		ArrayList<Integer> countSubProcessChains = new ArrayList<Integer>();
		ArrayList<Integer> countSubChainsDiff = new ArrayList<Integer>();
		// ArrayList<Integer> countContainBreakProcessChains = new ArrayList<Integer>();
		
		ArrayList<String> chains = new ArrayList<String>();
		ArrayList<Integer> orders = new ArrayList<Integer>();
		
		for ( ProductionOrderHistory poh : ProductionOrderData ) {
			chains.add(poh.getResourceGroup().getLabel());
			orders.add(poh.getOrder().getOrderNo());
		}
		
		/*
		for ( int i = 0; i < chains.size(); i++ ) {
			System.out.println(orders.get(i) + "  " + chains.get(i));
		}
		*/
		
		System.out.println("\n\nProzessketten:\n");
		
		ArrayList<String> listedChains = new ArrayList<String>();
		int j = 0;
		String currentChain = new String();
		
		// System.out.println("Length: " + currentChain.length());
		
		for ( int i = 0; i < chains.size()-1; i++ ) {
			
			String currentLabel = chains.get(i);
			
			if ( currentChain.length() == 0 ) {
				currentChain = currentLabel;
			}
			
			if ( orders.get(i+1).equals(orders.get(i)) ) {
				currentChain = currentChain + "-" + chains.get(i+1);
				// System.out.println(orders.get(i) + " " + orders.get(i+1) + "  " + currentChain);
			} else {
				listedChains.add(j,currentChain);
				// System.out.println(orders.get(i) + " " + orders.get(i+1) + "  " + listedChains.get(j));
				currentChain = "";
				j++;
			}
			
			/*			
			do {
				currentChain = currentChain + currentLabel;
			} while ( chains.get(i+1) == chains.get(i) );			
			*/
		}
		
		ArrayList<String> processChains = new ArrayList<String>();
		int n = 0;
		
		
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
		
		/*
		HashMap<Integer,String> mapProcessChains = new HashMap<Integer,String>();
		
		for ( n = 0; n < processChains.size(); n++ ) {
			mapProcessChains.put(countProcessChains.get(n),processChains.get(n));
			System.out.println(mapProcessChains.get(n));
		}
		*/
	}
	
}
