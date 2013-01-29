package org.maxhoffmann.dev.Chain;

import org.apache.log4j.Logger;
import org.maxhoffmann.dev.ProductionAnalysisAnnotation.ProductionOrderHistory;

import java.util.ArrayList;
import java.util.List;


public class ProcessChainGeneration {
	
	private static final Logger LOGGER = Logger.getLogger(ProcessChainGeneration.class);

	public ProcessChainGeneration() {
	}

	public ArrayList<String> ProcessChainBuild(List<ProductionOrderHistory> pohInput) {
		
		List<ProductionOrderHistory> ProductionOrderData = pohInput;
		
		ArrayList<String> chains = new ArrayList<String>();
		ArrayList<Integer> orders = new ArrayList<Integer>();
		
		for ( ProductionOrderHistory poh : ProductionOrderData ) {
			chains.add(poh.getResourceGroup().getLabel());
			orders.add(poh.getOrder().getOrderNo());
		}
		
		LOGGER.info("\n\nProzessketten-Analyse:\n\n");
		
		ArrayList<String> listedChains = new ArrayList<String>();
		int j = 0;
		String currentChain = new String();
		
		for ( int i = 0; i < chains.size()-1; i++ ) {
			
			String currentLabel = chains.get(i);
			
			if ( currentChain.length() == 0 ) {
				currentChain = currentLabel;
			}
			
			if ( orders.get(i+1).equals(orders.get(i)) ) {
				currentChain = currentChain + "-" + chains.get(i+1);
			} else {
				listedChains.add(j,currentChain);
				currentChain = "";
				j++;
			}
		}
		
		
		return listedChains;
		
		
		/*
		ArrayList<String> processChains = new ArrayList<String>();
		int n = 0;	
		
		for ( int m = 0; m < listedChains.size(); m++ ) {
			if ( processChains.contains(listedChains.get(m)) == false ) {
				processChains.add(n, listedChains.get(m));
				n++;
			}
		}
		*/
		
		
		/*
		HashMap<Integer,String> mapProcessChains = new HashMap<Integer,String>();
		
		for ( n = 0; n < processChains.size(); n++ ) {
			mapProcessChains.put(countProcessChains.get(n),processChains.get(n));
			LOGGER.info(mapProcessChains.get(n));
		}
		*/
		
	}
	
}