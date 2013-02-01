package org.maxhoffmann.dev.Chain;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;
import org.maxhoffmann.dev.object.ProcessChainEvaluation;

public class ProcessChainReconfiguration {

	private static final Logger LOGGER = Logger
			.getLogger(ProcessChainReconfiguration.class);

	ProcessChainEvaluation evaluation;
	ArrayList<String> workingChains;
	ArrayList<String> regularProcessChains;
	ArrayList<String> specialProcessChains;

	ArrayList<String> listedChains;
	ArrayList<String> combinedChains = new ArrayList<String>();
	String combinedChain = new String();

	Collection<ArrayList<String>> combinedChainsCollection;

	public ProcessChainReconfiguration(ProcessChainEvaluation evaluation,
			ArrayList<String> listedChains) {
		this.evaluation = evaluation;
		this.listedChains = listedChains;
	}

	public void chainCombination() {

		int chainCounter = 1;
		workingChains = evaluation.getCurrentMainChains();
		regularProcessChains = evaluation.getRegularProcessChains();
		specialProcessChains = evaluation.getSpecialProcessChains();

		for (int i = 0; i < workingChains.size() - 1; i++) {
			for (int j = 1 + i; j < workingChains.size(); j++) {
				combinedChain = workingChains.get(i) + "-"
						+ workingChains.get(j);
				combinedChains.add(combinedChain);
				LOGGER.info(chainCounter + ". kombinierte Kette:\t" + "K"
						+ (i + 1) + "-" + "K" + (j + 1) + "\t" + combinedChain);
				chainCounter++;
			}
		}

		LOGGER.info("\n\n\t\t\t\t\t   listed   regular   special\n");
		for (String chain : workingChains) {
			int subChainCounter = 0;
			int subRegularCounter = 0;
			int subSpecialCounter = 0;
			for (String processChain : listedChains) {
				if (chain.contains(processChain)) {
					subChainCounter++;
				}
			}
			for (String processChain : regularProcessChains) {
				if (chain.contains(processChain)) {
					subRegularCounter++;
				}
			}
			for (String processChain : specialProcessChains) {
				if (chain.contains(processChain)) {
					subRegularCounter++;
				}
			}
			LOGGER.info(chain + "\t\t\t    " + subChainCounter + "\t     "
					+ subRegularCounter + "       " + subSpecialCounter);
		}

		LOGGER.info("\n\n\t\t\t\t\t   listed   regular   special\n");
		for (String chain : combinedChains) {
			int subChainCounter = 0;
			int subRegularCounter = 0;
			int subSpecialCounter = 0;
			for (String processChain : listedChains) {
				if (chain.contains(processChain)) {
					subChainCounter++;
				}
			}
			for (String processChain : regularProcessChains) {
				if (chain.contains(processChain)) {
					subRegularCounter++;
				}
			}
			for (String processChain : specialProcessChains) {
				if (chain.contains(processChain)) {
					subRegularCounter++;
				}
			}
			LOGGER.info(chain + "\t    " + subChainCounter + "\t     "
					+ subRegularCounter + "       " + subSpecialCounter);
		}
	}

	public void chainReformation() {

		int chainCounter = 1;
		int distinctChainIndex = 0;

		ArrayList<Integer> countSpecialChains = new ArrayList<Integer>();
		ArrayList<Integer> subSpecialChains = new ArrayList<Integer>();

		workingChains = evaluation.getCurrentMainChains();
		regularProcessChains = evaluation.getRegularProcessChains();
		specialProcessChains = evaluation.getSpecialProcessChains();

		ArrayList<String> distinctSpecialChains = new ArrayList<String>();

		/**
		 * Calculation of distinct special Chains
		 */

		for (String chain : specialProcessChains) {
			if (distinctSpecialChains.contains(chain) == false) {
				distinctSpecialChains.add(distinctChainIndex, chain);
				distinctChainIndex++;
			}
		}

		for (String specialChain : distinctSpecialChains) {
			int countSpecialChain = 0;
			int subSpecialChain = 0;
			for (String chain : specialProcessChains) {
				if (specialChain.equals(chain)) {
					countSpecialChain++;
				}
				if (specialChain.contains(chain)) {
					subSpecialChain++;
				}
			}
			countSpecialChains.add(countSpecialChain);
			subSpecialChains.add(subSpecialChain);
		}

		LOGGER.info("\n");
		for (int i = 0; i < distinctSpecialChains.size(); i++) {
			LOGGER.info( countSpecialChains.get(i) + "\t"
					+ subSpecialChains.get(i) + "\t"
					+ distinctSpecialChains.get(i));
		}

	}

}
