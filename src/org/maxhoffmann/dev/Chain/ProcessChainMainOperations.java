package org.maxhoffmann.dev.Chain;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

import org.apache.log4j.Logger;
import org.maxhoffmann.dev.object.ProcessChainEvaluation;
import org.maxhoffmann.dev.object.ProcessChainObject;

public class ProcessChainMainOperations {

	private static final Logger LOGGER = Logger
			.getLogger(ProcessChainMainOperations.class);

	Set<ProcessChainObject> mainChains = new TreeSet<>();
	ArrayList<String> generatedChains = new ArrayList<String>();

	ArrayList<String> workingChains = new ArrayList<String>();
	ArrayList<String> regularProcessChains = new ArrayList<String>();
	ArrayList<String> specialProcessChains = new ArrayList<String>();

	int countRegularChains = 0;
	int countSpecialChains = 0;

	ArrayList<Integer> regularChainsIteration = new ArrayList<Integer>();
	ArrayList<Integer> specialChainsIteration = new ArrayList<Integer>();

	public ProcessChainMainOperations() {
	}

	public ProcessChainMainOperations(Set<ProcessChainObject> mainChains,
			ArrayList<String> generatedChains) {
		this.mainChains = mainChains;
		this.generatedChains = generatedChains;
	}

	public ProcessChainEvaluation chainResults() {

		for (ProcessChainObject mainChain : mainChains) {
			String actualChain = mainChain.getProcessChain();
			workingChains.add(actualChain);
		}

		LOGGER.info("Hauptprozessketten (A PRIORI):\n");
		for (int i = 0; i < workingChains.size(); i++) {
			LOGGER.info((i + 1) + ".\tProzesskette:  " + workingChains.get(i));
		}

		for (String chain : generatedChains) {
			for (String workingChain : workingChains) {
				if (workingChain.contains(chain)) {
					regularProcessChains.add(chain);
					countRegularChains++;
					break;
				}
			}
		}

		regularChainsIteration.add(regularProcessChains.size());

		for (String chain : generatedChains) {
			boolean equalChains = false;
			for (String regularChain : regularProcessChains) {
				if (chain.equals(regularChain)) {
					equalChains = true;
				}
			}
			if (equalChains == false) {
				specialProcessChains.add(chain);
			}
		}

		specialChainsIteration.add(specialProcessChains.size());

		LOGGER.info("\nRegular Process Chains:\t"
				+ regularChainsIteration.get(regularChainsIteration.size() - 1)
				+ "\t=>  "
				+ (float) 100
				* (regularChainsIteration.get(regularChainsIteration.size() - 1))
				/ (regularChainsIteration.get(regularChainsIteration.size() - 1) + specialChainsIteration
						.get(specialChainsIteration.size() - 1))
				+ " %\t of the products can be manufactured using the main process chains.");
		LOGGER.info("Special Process Chains:\t"
				+ specialChainsIteration.get(specialChainsIteration.size() - 1)
				+ "\t=>  "
				+ (float) 100
				* (specialChainsIteration.get(specialChainsIteration.size() - 1))
				/ (regularChainsIteration.get(regularChainsIteration.size() - 1) + specialChainsIteration
						.get(specialChainsIteration.size() - 1))
				+ " %\t can not be manufactured using the current configuration.");
		LOGGER.info("\n");

		ProcessChainEvaluation evaluation = new ProcessChainEvaluation();
		evaluation.setCurrentMainChains(workingChains);
		evaluation.setRegularProcessChains(regularProcessChains);
		evaluation.setSpecialProcessChains(specialProcessChains);
		evaluation.setRegularChains(regularChainsIteration);
		evaluation.setSpecialChains(specialChainsIteration);

		return evaluation;
	}
}