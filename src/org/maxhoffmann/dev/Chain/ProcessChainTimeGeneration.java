package org.maxhoffmann.dev.Chain;

import java.util.ArrayList;
import java.util.List;

import org.maxhoffmann.dev.ProductionAnalysisAnnotation.ProductionOrderHistory;

public class ProcessChainTimeGeneration {
	
	public ProcessChainTimeGeneration(List<ProductionOrderHistory> pohResult) {
	}
	
	public ArrayList<String> GenerateChainTimes(List<ProductionOrderHistory> pohInput) {
		
		List<ProductionOrderHistory> ProductionOrderData = pohInput;
		
		ArrayList<Double> processTimes = new ArrayList<Double>();
		ArrayList<Integer> orders = new ArrayList<Integer>();
		
		for ( ProductionOrderHistory poh : ProductionOrderData ) {
			orders.add(poh.getOrder().getOrderNo());
			processTimes.add(poh.getOperationTime());
		}

		System.out.println("\n\nProzessketten-Operation-Times:\n");
		
		ArrayList<String> chainTimes = new ArrayList<String>();
		
		int j = 0;
		String currentTimeLine = new String();
		
		for ( int i = 0; i < processTimes.size()-1; i++ ) {
			
			String currentTime = Double.toString(processTimes.get(i));
			
			if ( currentTimeLine.length() == 0 ) {
				currentTimeLine = currentTime;
			}
			
			if ( orders.get(i+1).equals(orders.get(i)) ) {
				currentTimeLine = currentTimeLine + "-" + processTimes.get(i+1);
			} else {
				chainTimes.add(j,currentTimeLine);
				currentTimeLine = "";
				j++;
			}
		}
		
		for ( j = 0; j < chainTimes.size(); j++ ) {
			System.out.println((j+1) + ".\t Prozesszeiten-Kette: \t" + chainTimes.get(j) );
		}
		
		return chainTimes;
	}
	
}